package com.pgms.shared.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.stereotype.Component;

/**
 * Created by user-1 on 19/7/15.
 */
@Component
public class PgmsObjectMapper extends ObjectMapper {

    public PgmsObjectMapper() {
        super();
        SimpleModule module = new SimpleModule("PolymorphicPreferenceDetailsDeserializerModule", new Version(1, 0, 0, null));
        registerModule(module);
        configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
    }

}
