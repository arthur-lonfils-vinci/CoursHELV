package be.vinci.aj;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Decode {

    private static Class<?> clazz;

    public static void main(String[] args) {
        try {
            clazz = Class.forName("be.vinci.aj.secret.Secrets");

            Object object = clazz.getDeclaredConstructor().newInstance();

            System.out.println("Classe Name: "+clazz.getSimpleName());

            System.out.println("Fields:");
            for(Field field: clazz.getDeclaredFields()){
                field.setAccessible(true);
                System.out.println("- "+field.getName()+" | "+field.getType().getSimpleName()+" | "+field.get(object));
            }

            System.out.println("Methods:");
            for (Method method : clazz.getDeclaredMethods()) {
                method.setAccessible(true);
                System.out.println("- "+method.getName()+" | "+method.getReturnType().getSimpleName()+" | "+method.invoke(object));
            }


        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
