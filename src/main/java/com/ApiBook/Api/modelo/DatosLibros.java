
package com.ApiBook.Api.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibros ( 
        @JsonAlias("title")
        String titulo,
        @JsonAlias("authors")
        List<DatosAutor> datosAutor,
        @JsonAlias("languages")
        List<String> lenguaje,
        @JsonAlias("download_count")
        Double descargas
        ){
       
  
}
