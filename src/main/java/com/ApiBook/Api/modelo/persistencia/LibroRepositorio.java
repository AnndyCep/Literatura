
package com.ApiBook.Api.modelo.persistencia;


import com.ApiBook.Api.dto.LibroDTO;
import com.ApiBook.Api.modelo.Autor;
import com.ApiBook.Api.modelo.Libro;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LibroRepositorio  extends JpaRepository<Libro, Integer>{
    Optional<Libro> findByTituloContainsIgnoreCase(String nombreTitulo);
    
    List<Libro> findTop5ByOrderByDescargasDesc();
    
    @Query("SELECT a FROM Autor a")
    public List<Autor> listaAutoresRegistrados();

    @Query("SELECT a FROM Autor a WHERE a.fechaCumpleanos = :valor")
    public List<Autor> listaAutoresPorAno(int valor);

    @Query("SELECT l FROM Libro l WHERE l.lenguajes = :valor")
    public List<Libro> buscarLibroPorIdioma(String valor);
}
