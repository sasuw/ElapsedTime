package net.sasu.lib.elapsedtime.util;

import java.lang.reflect.Field;

public class ReflectionUtil {

    private ReflectionUtil(){
        //static use only
    }

    /**
     * Makes field in given class accessible and returns field.
     *
     * @param clazz Class containing private field
     * @param fieldName Name of field
     * @return Field with given name
     * @throws NoSuchFieldException
     */
    public static Field makePrivateFieldAccessible(Class<?> clazz, String fieldName) throws NoSuchFieldException {
        Field f = clazz.getDeclaredField(fieldName);
        f.setAccessible(true);
        return f;
    }

    public static Object getPrivateFieldValue(Object object, String fieldName) throws NoSuchFieldException, IllegalAccessException {
        Field f = ReflectionUtil.makePrivateFieldAccessible(object.getClass(), fieldName);
        return f.get(object);
    }
}
