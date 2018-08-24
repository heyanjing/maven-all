package com.he.maven.core.Json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by heyanjing on 2018/8/24 21:04.
 */
public class Jsons {
    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    // ########################################
    // ###***********创建ObjectMapper**************####
    // ########################################
    public static ObjectMapper newObjectMapper(ObjectMapper mapper, String dateFormat) {
        if (mapper == null) {
            mapper = new ObjectMapper();
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
            javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
            mapper.registerModule(javaTimeModule);

            //在反序列化时忽略在 json 中存在但 Java 对象不存在的属性
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //在序列化时日期格式默认为 yyyy-MM-dd'T'HH:mm:ss.SSSZ
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,false);
            //在序列化时忽略值为 null 的属性
            mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            //忽略值为默认值的属性
            mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_DEFAULT);
        }
        if (dateFormat == null) {
            dateFormat = DATE_TIME;
        }
        mapper.setDateFormat(new SimpleDateFormat(dateFormat));
        return mapper;
    }

    public static ObjectMapper getInstance(String dateFormat) {
        return newObjectMapper(null, dateFormat);
    }

    public static ObjectMapper getInstance() {
        return getInstance(null);
    }

    // ########################################
    // ###*************convert**************####
    // ########################################
    public static <T> T convert(ObjectMapper mapper, Object obj, String dateFormat, Class<T> clazz, TypeReference<?> type) {
        mapper = mapper == null ? getInstance(dateFormat) : mapper;
        if (type != null) {
            return mapper.convertValue(obj, type);
        } else {
            return mapper.convertValue(obj, clazz);
        }
    }

    public static <T> T convert(ObjectMapper mapper, Object obj, String dateFormat, Class<T> clazz) {
        return convert(mapper, obj, dateFormat, clazz, null);
    }

    public static <T> T convert(ObjectMapper mapper, Object obj, Class<T> clazz) {
        return convert(mapper, obj, null, clazz);
    }

    public static <T> T convert(Object obj, String dateFormat, Class<T> clazz) {
        return convert(null, obj, dateFormat, clazz, null);
    }

    public static <T> T convert(Object obj, Class<T> clazz) {
        return convert(obj, null, clazz);
    }

    public static <T> T convert(ObjectMapper mapper, Object obj, String dateFormat, TypeReference<?> type) {
        return convert(mapper, obj, dateFormat, null, type);
    }

    public static <T> T convert(ObjectMapper mapper, Object obj, TypeReference<?> type) {
        return convert(mapper, obj, null, type);
    }

    public static <T> T convert(Object obj, String dateFormat, TypeReference<?> type) {
        return convert(null, obj, dateFormat, null, type);
    }

    public static <T> T convert(Object obj, TypeReference<?> type) {
        return convert(obj, null, type);
    }

    // ########################################
    // ###**************toJson**************####
    // ########################################

    public static String toJson(ObjectMapper mapper, Object o, String dateFormat, boolean prettyFormat) {
        String json = null;
        mapper = mapper == null ? getInstance(dateFormat) : mapper;
        try {
            if (prettyFormat) {
                json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(o);
            } else {
                json = mapper.writeValueAsString(o);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    public static String toJson(ObjectMapper mapper, Object o) {
        return toJson(mapper, o, null, false);
    }

    public static String toJson(Object o) {
        return toJson(null, o, null, false);
    }

    public static String toJson(ObjectMapper mapper, Object o, boolean prettyFormat) {
        return toJson(mapper, o, null, prettyFormat);
    }

    public static String toJson(Object o, boolean prettyFormat) {
        return toJson(null, o, null, prettyFormat);
    }

    public static String toJson(Object o, String dateFormat, boolean prettyFormat) {
        return toJson(null, o, dateFormat, prettyFormat);
    }

    public static String toJson(Object o, String dateFormat) {
        return toJson(null, o, dateFormat, false);
    }

    // ########################################
    // ###**************toBean**************####
    // ########################################
    public static <T> T toBean(ObjectMapper mapper, String json, String dateFormat, Class<T> clazz, TypeReference<?> type) {
        mapper = mapper == null ? getInstance(dateFormat) : mapper;
        try {
            if (type != null) {
                return mapper.readValue(json, type);
            } else {
                return mapper.readValue(json, clazz);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toBean(ObjectMapper mapper, String json, Class<T> clazz) {
        return toBean(mapper, json, null, clazz, null);
    }

    public static <T> T toBean(ObjectMapper mapper, String json, TypeReference<?> type) {
        return toBean(mapper, json, null, null, type);
    }

    public static <T> T toBean(ObjectMapper mapper, String json, String dateFormat, TypeReference<?> type) {
        return toBean(mapper, json, dateFormat, null, type);
    }

    public static <T> T toBean(String json, String dateFormat, Class<T> clazz) {
        return toBean(null, json, dateFormat, clazz, null);
    }

    public static <T> T toBean(String json, Class<T> clazz) {
        return toBean(null, json, null, clazz, null);
    }

    public static <T> T toBean(String json, TypeReference<?> type) {
        return toBean(null, json, null, null, type);
    }

    public static <T> T toBean(String json, String dateFormat, TypeReference<?> type) {
        return toBean(null, json, dateFormat, null, type);
    }

    // ########################################
    // ###*************toList**************####
    // ########################################
    public static <T> List<T> toList(ObjectMapper mapper, String json, Class<T> clazz) {
        List<T> objs = new ArrayList<>();
        List<LinkedHashMap<String, Object>> maps = toBean(mapper, json, new TypeReference<List<Object>>() {
        });
        if (maps != null) {
            for (LinkedHashMap<String, Object> map : maps) {
                objs.add(convert(mapper, map, clazz));
            }
        }
        return objs;
    }

    public static <T> List<T> toList(String json, String dateFormat, Class<T> clazz) {
        List<T> objs = new ArrayList<>();
        List<LinkedHashMap<String, Object>> maps = toBean(json, dateFormat, new TypeReference<List<Object>>() {
        });
        if (maps != null) {
            for (LinkedHashMap<String, Object> map : maps) {
                objs.add(convert(map, clazz));
            }
        }
        return objs;
    }

    public static <T> List<T> toList(String json, Class<T> clazz) {
        List<T> objs = new ArrayList<>();
        List<LinkedHashMap<String, Object>> maps = toBean(json, new TypeReference<List<Object>>() {
        });
        if (maps != null) {
            for (LinkedHashMap<String, Object> map : maps) {
                objs.add(convert(map, clazz));
            }
        }
        return objs;
    }

    // ########################################
    // ###*************toMap**************####
    // ########################################
    public static Map<String, Object> toMap(ObjectMapper mapper, Object obj) {
        return convert(mapper, obj, new TypeReference<HashMap<String, Object>>() {
        });
    }

    public static Map<String, Object> toMap(Object obj) {
        return convert(obj, new TypeReference<HashMap<String, Object>>() {
        });
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(String json) {
        try {
            return getInstance().readValue(json, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
