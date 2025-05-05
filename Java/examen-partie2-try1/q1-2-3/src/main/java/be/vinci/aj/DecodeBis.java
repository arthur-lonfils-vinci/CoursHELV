package be.vinci.aj;

import java.lang.management.MemoryType;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DecodeBis {

    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("be.vinci.aj.secret.Secrets");

            Object instance = clazz.getDeclaredConstructor().newInstance();

            System.out.println("Classe Name: " + clazz.getSimpleName());
            for (Method method : clazz.getDeclaredMethods()) {
                method.setAccessible(true);
                System.out.println(method.invoke(instance));
            }

            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                System.out.println(field.get(instance));
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException |
                 InvocationTargetException e) {
            System.out.println("Classe non trouvée");
        }
    }
}
