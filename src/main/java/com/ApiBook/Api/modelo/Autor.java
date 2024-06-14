
package com.ApiBook.Api.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "Autores")
public class Autor {
        
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;
        private String nombre;
        private String fechaCumpleanos;
        @ManyToOne()
        private Libro libro;
        
        public Autor() {}
        
        public Autor(DatosAutor autor){
            this.nombre = autor.nombre();
            this.fechaCumpleanos= autor.anoNacimeinto();
        }
      
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

        
    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaCumpleanos() {
        return fechaCumpleanos;
    }

    public void setFechaCumpleanos(String fechaCumpleanos) {
        this.fechaCumpleanos = fechaCumpleanos;
    }

    @Override
    public String toString() {
        return "nombre= " + nombre + " , fechaCumpleanos= " + fechaCumpleanos + '}';
    }
        
        
}
