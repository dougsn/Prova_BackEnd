package com.dh.Back_Client2;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class JsonUtil {

    public static String asJsonString (Object object){
        try {
            ObjectMapper objectMapper = getObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
            return objectMapper.writeValueAsString(object);
        } catch (Exception e){
            throw  new RuntimeException(e);
        }
    }

    public static <T> T objectFromString(Class<T> aClass, String value) throws JsonProcessingException{
        return getObjectMapper().readValue(value, aClass);
    }

    private static ObjectMapper getObjectMapper() {
        return new ObjectMapper().registerModule(new ParameterNamesModule()).configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());

    }

    public static <T> T jsonStringToObject(String content, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        T obj = null;
        ObjectMapper objMapper = new ObjectMapper();
        obj = objMapper.readValue(content, clazz);
        return obj;
    }

    public static <T> T jsonStringToObjectArray(String content, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        T obj = null;
        ObjectMapper mapper = new ObjectMapper();
        mapper = new ObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        obj = mapper.readValue(content, mapper.getTypeFactory().constructCollectionType(List.class, clazz));
        return obj;
    }

}
