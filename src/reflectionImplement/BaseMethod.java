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
    Random random = new Random();

    public void fillObjectsFields(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getDeclaredAnnotation(Generate.class);
            if (annotation == null) {
                continue;
            }
            final String setterName = "set" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
            Object obj = null;
            try {
                Method methodSetterName = clazz.getDeclaredMethod(setterName, field.getType());
                if (field.getType().equals(String.class)) {
                    obj = generateRandomString();
                }
                if (field.getType().equals(int.class)) {
                    obj = generateInt();
                }
                if (field.getType().equals(Date.class)) {
                    obj = generateDate();
                }
                if (field.getType().equals(boolean.class)) {
                    obj = generateBoolean();
                }
                methodSetterName.invoke(object, obj);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private Date generateDate() {
        return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
    }

    private Object generateBoolean() {
        final Object obj;
        obj = random.nextBoolean();
        return obj;
    }

    private Object generateInt() {
        final Object obj;
        obj = random.nextInt(1, 100);
        return obj;
    }
}
