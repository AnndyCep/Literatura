
package com.ApiBook.Api.servicio;

import com.ApiBook.Api.dto.AutorDTO;
import com.ApiBook.Api.dto.LibroDTO;
import com.ApiBook.Api.modelo.Autor;
import com.ApiBook.Api.modelo.Datos;
import com.ApiBook.Api.modelo.DatosLibros;
import com.ApiBook.Api.modelo.Libro;
import com.ApiBook.Api.modelo.persistencia.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LibroServicio {

    @Autowired
    public LibroRepositorio libroRepositorio;

    public void  buscarLibroPorTitulo(Datos dato, String LibroBuscar) {
        Optional<LibroDTO> libroDTO = libroRepositorio.findByTituloContainsIgnoreCase(LibroBuscar)
                .map(l -> new LibroDTO(l.getTitulo(), l.getAutores(), l.getLenguajes(), l.getDescargas()));
        
        if (dato.datosLibros().isEmpty()) {
             System.out.println("Libro no existe, Ingrese un libro valido");
        }
        if (libroDTO.isEmpty()) {
            Optional<DatosLibros> datoLibro = dato.datosLibros().stream().filter(l -> l.titulo().toLowerCase().contains(LibroBuscar.toLowerCase())).findFirst();
            if (datoLibro.isPresent()) {
                guardarLibro(datoLibro);  
            }
        } 
        if (libroDTO.isPresent()) {
            System.out.println("\t\t" +"<------>  LIBRO YA REGISTRADO <------> ...");
               mostrarLibro(libroDTO);
        } 
{
            
        }
    }
   
    public void listaAutoresPorano(int valor) {
         List<AutorDTO> listaAutores = convertirAutorToDTO(libroRepositorio.listaAutoresPorAno(valor));
        imprimirAutor(listaAutores);
    }

    public void buscarLibros() {
        List<LibroDTO> libroDTO = convertirLibroToDTO(libroRepositorio.findAll());
        
        imprimirLibros(libroDTO);
        
    }
    
    public void listaAutoresRegistrados() {
        List <AutorDTO> autor = convertirAutorToDTO(libroRepositorio.listaAutoresRegistrados());
        imprimirAutor(autor);
    }
    
    private void imprimirAutor(List<AutorDTO> lista){
        lista.forEach(a -> System.out.println(" DATOS AUTOR " + "\n\n" 
                + "Nombre: " + a.nombre() + "\n"
                + "Fecha de nacimiento: " + a.fechaCumpleanos() + "\n"));
    }
    
     public void mostrarLibrosPorIdioma(String valor) {
        List <LibroDTO> listaLibros = convertirLibroToDTO(libroRepositorio.buscarLibroPorIdioma(valor));
         if (listaLibros.isEmpty()) {
             System.out.println(" <------> No se registraron libros con esa consulta <------>");
         } else {
             imprimirLibros(listaLibros);
         }
        
    }
     
    private List<AutorDTO> convertirAutorToDTO(List <Autor> autor){
        return autor.stream().map(a -> new AutorDTO(a.getNombre(), a.getFechaCumpleanos())).collect(Collectors.toList());
    }
    
     private List<LibroDTO> convertirLibroToDTO(List <Libro> libro){
        return libro.stream().map(l -> new LibroDTO(l.getTitulo(), l.getAutores(), l.getLenguajes(), l.getDescargas())).collect(Collectors.toList());
    }
     
     private void guardarLibro(Optional<DatosLibros> datoLibro) {
        Libro libroEncontrado = new Libro(datoLibro.get());
        List<Autor> listaAutores = datoLibro.get().datosAutor().stream().map(a -> new Autor(a)).collect(Collectors.toList());
        libroEncontrado.setAutores(listaAutores);
        libroRepositorio.save(libroEncontrado);
    }
     
     private void mostrarLibro(Optional<LibroDTO> libroDTO) {
         if (libroDTO.isPresent()) {
              System.out.println("\t"+ "DATOS LIBRO ");
             System.out.println(" Nombre del Libro " + libroDTO.get().titulo() + "\n"
                     + " Autores " + libroDTO.get().autores() + "\n"
                     + " Lenguaje " + libroDTO.get().lenguajes() + "\n"
                     + " Descargas " + libroDTO.get().descargas());
         } if (libroDTO.isEmpty()) {
            System.out.println(" No se encontraron resuldatos de la consulta.");
         } 
    }

    private void imprimirLibros(List<LibroDTO> libroDTO) {
       libroDTO.forEach(l -> System.out.println(" DATOS LIBROS " + "\n"
                + "\n"
                + "Nombre: " + l.titulo() + "\n"
                + "Autores:" + l.autores() + "\n"
                + "Lenguaje: " + l.lenguajes() + "\n"
                + "Descargas: " + l.descargas()+ "\n"));
    }

   

    
    
}
