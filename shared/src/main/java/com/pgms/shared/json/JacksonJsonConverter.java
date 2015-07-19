package com.pgms.shared.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Created by user-1 on 19/7/15.
 */
@Component
public class JacksonJsonConverter implements JsonConverter {

    @Autowired
    private PgmsObjectMapper objectMapper;

    @Override
    public String toJson(Object o) throws IOException {
        return objectMapper.writeValueAsString(o);
    }

    @Override
    public String toJsonIgnoreException(Object o) {
        try {
            return toJson(o);
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public <T> T fromJson(String json, Class<T> type) throws IOException {
        return objectMapper.readValue(json.getBytes(), type);
    }

    @Override
    public <T> T fromJson(String json, TypeData<T> typeData) throws IOException {
        return objectMapper.readValue(json.getBytes(), typeData);
    }

    @Override
    public <T> T convert(Object o, TypeData<T> typeData) throws IOException {
        return objectMapper.convertValue(o, typeData);
    }
}
