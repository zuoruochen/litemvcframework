package com.zrc.util;

import org.apache.commons.beanutils.ConvertUtils;

public class CastUtil {
    public static <T> T castString(Class<T> type, String s) {
        return (T)ConvertUtils.convert(s, type);
    }

    public static Object castStringWithException(Class<?> type, String s) {
        if(type == String.class) {
            return s;
        } else if(type == int.class || type == Integer.class) {
            Integer i = Integer.parseInt(s);
            if(type == int.class) {
                return i.intValue();
            }
            return i;
        } else if(type == float.class || type == Float.class){
            Float f = Float.parseFloat(s);
            if(type == float.class)
                return f.floatValue();
            return f;
        } else if(type == boolean.class || type == Boolean.class) {
            Boolean b = Boolean.parseBoolean(s);
            if(type == boolean.class)
                return b.booleanValue();
            return b;
        } else if(type == char[].class ) {
            return s.toCharArray();
        } else if(type == double.class || type == Double.class) {
            Double d = Double.parseDouble(s);
            if(type == double.class)
                return d.doubleValue();
            return Double.parseDouble(s);
        } else if(type == long.class || type == Long.class) {
            Long l = Long.parseLong(s);
            if(type == long.class)
                return l.longValue();
            return l;
        } else if(type == short.class || type == Short.class) {
            Short sh = Short.parseShort(s);
            if(type == short.class)
                return sh.shortValue();
            return sh;
        } else if(type == byte.class || type == Byte.class) {
            Byte b = Byte.parseByte(s);
            if(type == byte.class)
                return b.byteValue();
            return b;
        } else if(type == char.class) {
            if(s.length() == 1)
                return s.charAt(0);
            else
                return new RuntimeException("string can't cast to " + type);
        } else {
            return new RuntimeException("string can't cast to " + type);
        }
    }
}
