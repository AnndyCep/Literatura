
package com.ApiBook.Api.dto;
import com.ApiBook.Api.modelo.Autor;
import java.util.List;


public record LibroDTO(
        String titulo,
        List<Autor> autores,
        String lenguajes,
        double descargas
        ) {

}
