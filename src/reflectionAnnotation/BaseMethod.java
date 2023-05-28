package reflectionAnnotation;

import objects.Generate;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static reflectionAnnotation.FieldTypes.*;

public class BaseMethod {
    public void fillObjectsFields(Object object) {
        Random random = new Random();
        Class<?> clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Annotation annotation = field.getDeclaredAnnotation(Generate.class);
            if (annotation == null) {
                continue;
            }
            String nameType = String.valueOf(field.getType());
            final String setterName = "set" + field.getName().substring(0, 1).toUpperCase()
                    + field.getName().substring(1);
            try {
                if (nameType.equals(TYPE_STRING.type)) {
                    Method methodSetterName = clazz.getDeclaredMethod(setterName, field.getType());
                    methodSetterName.invoke(object, generatingRandomString().substring(0, 1).toUpperCase()
                                            + generatingRandomString().substring(1));
                }
                if (nameType.equals(TYPE_INT.type)) {
                    Method methodSetterName = clazz.getDeclaredMethod(setterName, field.getType());
                    methodSetterName.invoke(object, random.nextInt(1, 100));
                }
                if (nameType.equals(TYPE_DATE.type)) {
                    Method methodSetterName = clazz.getDeclaredMethod(setterName, field.getType());
                    methodSetterName.invoke(object, timestamp());
                }
                if (nameType.equals(TYPE_BOOLEAN.type)) {
                    Method methodSetterName = clazz.getDeclaredMethod(setterName, field.getType());
                    methodSetterName.invoke(object, random.nextBoolean());
                }
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public static String generatingRandomString() {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 5;
        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static Date timestamp() {
        return new Date(ThreadLocalRandom.current().nextInt() * 1000L);
    }
}
