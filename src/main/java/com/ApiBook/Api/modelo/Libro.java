
package com.ApiBook.Api.modelo;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    //@Transient no mapea ese atributo
    @OneToMany(fetch = FetchType.EAGER , mappedBy = "libro", cascade = CascadeType.ALL)
     private List<Autor> autores;
    
    private String lenguajes;
    private double descargas;
    
    public Libro(){
   }
    
   public Libro(DatosLibros libro){
    this.titulo = libro.titulo();
    this.lenguajes = Optional.of(libro.lenguaje().get(0).toUpperCase()).orElse("N/A");
    this.descargas = libro.descargas();
    //this.autores = libro.datosAutor().stream().map(a -> new Autor(a)).collect(Collectors.toList());
}
   
   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
   
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        autores.forEach(a -> a.setLibro(this));
        this.autores = autores;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    
    public double getDescargas() {
        return descargas;
    }

    public void setDescargas(double descargas) {
        this.descargas = descargas;
    }

   

    @Override
    public String toString() {
        return "titulo= " + titulo  + " , lenguajes= " + lenguajes + " , descargas= " + descargas + '}';
    }
    
    
    

}
