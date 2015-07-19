package com.pgms.shared.json;

import java.io.IOException;

/**
 * Created by user-1 on 19/7/15.
 */
public interface JsonConverter {

    String toJson(Object o) throws IOException;

    String toJsonIgnoreException(Object o);

    <T> T fromJson(String json, Class<T> type) throws IOException;

    <T> T fromJson(String json, TypeData<T> typeData) throws IOException;

    <T> T convert(Object o, TypeData<T> typeData) throws IOException;
}
