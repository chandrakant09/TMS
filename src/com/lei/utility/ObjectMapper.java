package com.lei.utility;

import java.io.IOException;
import java.lang.annotation.Annotation;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * 
 * @author Shrikant Kushwaha
 *
 */
public class ObjectMapper extends com.fasterxml.jackson.databind.ObjectMapper {
	private void autoconfigureFeatures(JavaType javaType) {
        Annotation rootAnnotation = javaType.getRawClass().getAnnotation(JsonRootName.class);
        this.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, rootAnnotation != null);
    }

    @Override
    protected Object _readMapAndClose(JsonParser jsonParser, JavaType javaType) throws IOException, JsonParseException, JsonMappingException {
        autoconfigureFeatures(javaType);
        return super._readMapAndClose(jsonParser, javaType);
    }
}
