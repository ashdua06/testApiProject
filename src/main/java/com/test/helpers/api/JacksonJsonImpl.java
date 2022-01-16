package com.test.helpers.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

public class JacksonJsonImpl {

    private static final ThreadLocal<ObjectMapper> tlObjectMapper = new ThreadLocal<ObjectMapper>();
    private static volatile JacksonJsonImpl _instance;

    public JacksonJsonImpl() {

    }

    public static JacksonJsonImpl getInstance() {
        if (_instance == null) {
            synchronized (JacksonJsonImpl.class) {
                if (_instance == null) {
                    _instance = new JacksonJsonImpl();
                }
            }
        }
        return _instance;
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = tlObjectMapper.get();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            objectMapper.setVisibilityChecker(
                    objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            tlObjectMapper.set(objectMapper);
        }
        return objectMapper;
    }


    private ObjectMapper getObjectMapperNullFields() {
        ObjectMapper objectMapper = tlObjectMapper.get();
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();

            objectMapper.setVisibilityChecker(
                    objectMapper.getVisibilityChecker().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
            objectMapper.configure(JsonParser.Feature.ALLOW_BACKSLASH_ESCAPING_ANY_CHARACTER, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            tlObjectMapper.set(objectMapper);
        }
        return objectMapper;
    }

    public <T> String toJSon(T t) throws IOException {
        try {
            String json = getObjectMapper().writeValueAsString(t);
            return json;
        } catch (JsonGenerationException jge) {
            throw jge;
        } catch (JsonMappingException jme) {
            throw jme;
        } catch (IOException ioe) {
            throw ioe;
        }
    }


    public <T> List<T> fromJsonArray(String jsonArray, CollectionType type) throws IOException{
        try {
            List<T> t = getObjectMapper().readValue(jsonArray, type);
            return t;
        }
        catch (JsonParseException jpe) {
            throw jpe;
        } catch (JsonMappingException jme) {
            throw jme;
        } catch (IOException ioe) {
            throw ioe;
        }
    }
    public <T> T fromJson(String json,
                          Class<T> clazz) throws IOException {

        try {
            T t = getObjectMapper().readValue(json, clazz);
            return t;
        } catch (JsonParseException jpe) {
            throw jpe;
        } catch (JsonMappingException jme) {
            throw jme;
        } catch (IOException ioe) {
            throw ioe;
        }
    }

    public <T> T fromJson(File file,
                          Class<T> clazz) throws IOException {

        try {
            T t = getObjectMapper().readValue(file, clazz);
            return t;
        } catch (JsonParseException jpe) {
            throw jpe;
        } catch (JsonMappingException jme) {
            throw jme;
        } catch (IOException ioe) {
            throw ioe;
        }
    }


    public <T> T fromJson(String json,
                          TypeReference typeRef) throws IOException {

        try {
            T t = getObjectMapper().readValue(json, typeRef);
            return t;
        } catch (JsonParseException jpe) {
            throw jpe;
        } catch (JsonMappingException jme) {
            throw jme;
        } catch (IOException ioe) {
            throw ioe;
        }
    }

    public <T> List<HashMap> toListHashMap(T t) throws IOException {
        List<HashMap> jsonMap = getObjectMapper().convertValue(t, List.class);
        return jsonMap;
    }

    public <T> HashMap toHashMap(T t) throws IOException {
        HashMap jsonMap = getObjectMapper().convertValue(t, HashMap.class);
        return jsonMap;
    }


    public <T> JsonNode toJsonNode(String json) throws IOException {
        try {
            JsonNode jsonNode = getObjectMapper().readTree(json);
            return jsonNode;
        } catch (JsonGenerationException jge) {
            throw jge;
        } catch (JsonMappingException jme) {
            throw jme;
        } catch (IOException ioe) {
            throw ioe;
        }
    }

}
