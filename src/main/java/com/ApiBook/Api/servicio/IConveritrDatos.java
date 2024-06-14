
package com.ApiBook.Api.servicio;

public interface IConveritrDatos {
    
    <T> T convertirDatos (String json, Class<T> clase);
}
