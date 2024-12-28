package be.vinci.services;

import jakarta.json.*;

import java.lang.reflect.*;
import java.util.logging.Logger;

/**
 * Class analyzer. It saves a class into attribute, from a constructor, and
 * gives a lot of convenient methods to transform this into a JSON object
 * to print the UML diagram.
 */
public class ClassAnalyzer {

    private Class aClass;

    public ClassAnalyzer(Class aClass) {
        this.aClass = aClass;
    }

    /**
     * Create a JSON Object with all the info of the class.
     * @return
     */
    public JsonObject getFullInfo() {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("name", aClass.getSimpleName());
        objectBuilder.add("fields", getFields());
        objectBuilder.add("methods", getMethods());
        return objectBuilder.build();
    }


    /**
     * From the field descriptor f, create a Json Object with all field data.
     * Example :
     * {
     * name: "firstname",
     * type: "String",
     * visibility : "private"  // public, private, protected, package
     * isStatic: false,
     * }
     * @param f filed descriptor - describe an attribute
     * @return the generated JSON
     */
    public JsonObject getField(Field f) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        // Nom du champ
        objectBuilder.add("name", f.getName());

        // Gestion des types génériques
        Type genericType = f.getGenericType();
        if (genericType instanceof ParameterizedType) {
            StringBuilder typeBuilder = getStringBuilderFields(f, (ParameterizedType) genericType);
            objectBuilder.add("type", typeBuilder.toString());
        } else {
            // Type simple
            objectBuilder.add("type", f.getType().getSimpleName());
        }

        // Visibilité du champ
        objectBuilder.add("visibility", getFieldVisibility(f));

        // Staticité
        objectBuilder.add("isStatic", isFieldStatic(f));

        return objectBuilder.build();
    }

    private static StringBuilder getStringBuilderFields(Field f, ParameterizedType genericType) {
        StringBuilder typeBuilder = new StringBuilder(f.getType().getSimpleName());
        typeBuilder.append("<");

        Type[] typeArguments = genericType.getActualTypeArguments();
        for (int i = 0; i < typeArguments.length; i++) {
            if (typeArguments[i] instanceof Class) {
                // Nom simple si c'est une classe
                typeBuilder.append(((Class<?>) typeArguments[i]).getSimpleName());
            } else {
                // Nom complet sinon
                typeBuilder.append(typeArguments[i].getTypeName());
            }
            if (i < typeArguments.length - 1) {
                typeBuilder.append(", ");
            }
        }
        typeBuilder.append(">");
        return typeBuilder;
    }

    /**
     * Get fields, and create a Json Array with all fields data.
     * Example :
     * [ {}, {} ]
     * This method rely on the getField() method to handle each field one by one.
     */
    public JsonArray getFields() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO START Add all fields descriptions to array (use the getField() method above)
        for (Field f : aClass.getDeclaredFields()) {
            arrayBuilder.add(getField(f));
        }
        return arrayBuilder.build();
        // TODO END
    }

    /**
     * Return whether a field is static or not
     *
     * @param f the field to check
     * @return true if the field is static, false else
     */
    private boolean isFieldStatic(Field f) {
        // TODO START
        return Modifier.isStatic(f.getModifiers());
        // TODO END
    }

    /**
     * Get field visibility in a string form
     *
     * @param f the field to check
     * @return the visibility (public, private, protected, package)
     */
    private String getFieldVisibility(Field f) {
        // TODO START
        if (Modifier.isPublic(f.getModifiers())) {
            return "public";
        } else if (Modifier.isPrivate(f.getModifiers())) {
            return "private";
        } else if (Modifier.isProtected(f.getModifiers())) {
            return "protected";
        } else {
            return "package";
        } // TODO END
    }

    private JsonObject getMethod(Method m) {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        // TODO START add missing info
        objectBuilder.add("name", m.getName());
        objectBuilder.add("returnType", m.getReturnType().getSimpleName());
        objectBuilder.add("parameters", getParameters(m));
        objectBuilder.add("visibility", getMethodVisibility(m));
        objectBuilder.add("isStatic", isMethodStatic(m));
        objectBuilder.add("isAbstract",isMethodAbstract(m));
        return objectBuilder.build();
    }

    private JsonArray getMethods() {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO START Add all methods descriptions to array (use the getMethod() method above)
        for (Method m : aClass.getDeclaredMethods()) {
            arrayBuilder.add(getMethod(m));
        }
        return arrayBuilder.build();
        // TODO END
    }

    private JsonArray getParameters(Method m) {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        // TODO START Add all parameters descriptions to array
        for (Class<?> c : m.getParameterTypes()) {
            arrayBuilder.add(c.getSimpleName());
        }
        return arrayBuilder.build();
        // TODO END
    }

    private boolean isMethodStatic(Method m) {
        // TODO START
        return Modifier.isStatic(m.getModifiers());
        // TODO END
    }

    private boolean isMethodAbstract(Method m) {
        // TODO START
        return Modifier.isAbstract(m.getModifiers());
        // TODO END
    }

    private String getMethodVisibility(Method m) {
        // TODO START
        if (Modifier.isPublic(m.getModifiers())) {
            return "public";
        } else if (Modifier.isPrivate(m.getModifiers())) {
            return "private";
        } else if (Modifier.isProtected(m.getModifiers())) {
            return "protected";
        } else {
            return "package";
        } // TODO END
    }

}
