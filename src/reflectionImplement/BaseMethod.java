package reflectionImplement;

import objects.Generate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class BaseMethod {
    private final Random random = new Random();

    public void fillObjectsFields(Object object) {
        final Class<?> clazz = object.getClass();
        final Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getDeclaredAnnotation(Generate.class);
            if (annotation == null) {
                continue;
            }
            final String setterName = "set" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
            final Object generation;
            try {
                Method methodSetterName = clazz.getDeclaredMethod(setterName, field.getType());
                if (field.getType().equals(String.class)) {
                    generation = generateRandomString();
                } else if (field.getType().equals(int.class)) {
                    generation = generateInt();
                } else if (field.getType().equals(Date.class)) {
                    generation = generateDate();
                } else {
                    generation = generateBoolean();
                }
                methodSetterName.invoke(object, generation);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateRandomString() {
        final int leftLimit = 97; // letter 'a'
        final int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        final Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private Date generateDate() {
        return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
    }

    private Object generateBoolean() {
        return random.nextBoolean();
    }

    private Object generateInt() {
        return random.nextInt(1, 100);
    }
}
