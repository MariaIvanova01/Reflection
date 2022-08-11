package reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        Class<Reflection> reflection = Reflection.class;

        Field[] fields = reflection.getDeclaredFields();

        Arrays.stream(fields)
                .filter(field -> !Modifier.isPrivate(field.getModifiers()))
                .sorted(Comparator.comparing(Field::getName))
                .forEach(field -> System.out.printf("%s must be private!%n",field.getName()));

        Method[] methods = reflection.getDeclaredMethods();

        Method[] getters = Arrays.stream(methods)
                .filter(method -> method.getName().startsWith("get"))
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);

        Arrays.stream(getters).filter(getter -> !Modifier.isPublic(getter.getModifiers()))
                .forEach(getter -> System.out.printf("%s have to be public!%n",getter.getName()));

        Method[] setters = Arrays.stream(methods).filter(method -> method.getName().startsWith("set"))
                .sorted(Comparator.comparing(Method::getName))
                .toArray(Method[]::new);

        Arrays.stream(setters).filter(setter -> !Modifier.isPublic(setter.getModifiers()))
                .forEach(setter -> System.out.printf("%s have to be private!%n",setter.getName()));
    }
}
