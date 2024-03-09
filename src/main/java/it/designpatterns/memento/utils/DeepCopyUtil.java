package it.designpatterns.memento.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * The DeepCopyUtil class provides a utility method for performing deep copies of objects using Jackson's ObjectMapper.
 */
public class DeepCopyUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T deepCopy(T value, Class<T> clazz) {
        try {
            return objectMapper.readValue( objectMapper.writeValueAsBytes( value ), clazz );
        } catch (IOException e) {
            throw new RuntimeException( "Error performing deep copy", e );
        }
    }
}
