
package com.ApiBook.Api.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertirDatos implements IConveritrDatos{

    private ObjectMapper mapper = new ObjectMapper();
    @Override
    public <T> T convertirDatos(String json, Class<T> clase) {
        try {
            return mapper.readValue(json, clase);
        } catch (JsonProcessingException ex) {
            throw new RuntimeException(ex);
        }
    }
}
