package com.zrc.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperUtil {
    private static ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static ObjectMapper getObjectMapper() {
        return OBJECT_MAPPER;
    }
}
