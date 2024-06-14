
package com.ApiBook.Api.principal;



import com.ApiBook.Api.modelo.Datos;
import com.ApiBook.Api.servicio.ApiConection;
import com.ApiBook.Api.servicio.ConvertirDatos;
import com.ApiBook.Api.servicio.LibroServicio;
import java.util.Scanner;



public class Principal {
    private  ApiConection api = new ApiConection();
    private ConvertirDatos convertir = new ConvertirDatos();
    private static final String URL_BASE ="https://gutendex.com/books/";
     private LibroServicio libroServicio;
    
       
    public Principal(LibroServicio libroServicio) {
        this.libroServicio = libroServicio;
    }
    Scanner sc = new Scanner(System.in);
    
    
    public void mostrarMenu(){
        int valor = 1;
        do {
             var menu = "\n"
                     + ""+"""
                       Elija la opción a través de su número:
                       1- buscar libro por titulo
                       2- listar libros registrados
                       3- listar autores registrados
                       4- listar autores vivos en un determinado año
                       5- listar libros por idioma
                       0 - salir
                       """;
            System.out.println(menu);
            valor = sc.nextInt();
            sc.nextLine();
            
            switch (valor) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    librosGuardados();
                    break;
                case 3:
                    mostrarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresPorAno();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Gracias");
                    break;
                default:
                    System.out.println("El valor ingresado no es valido");
            }
            
        } while (valor != 0);
      
    }
    private void buscarLibroPorTitulo() {
        System.out.println("-------------------------");
        System.out.println("Ingresa el nombre del libro a buscar");
        var LibroBuscar = sc.nextLine();
        try {
            var json = api.obtenerJson(URL_BASE + "?search=" + LibroBuscar.replace(" ", "+").toLowerCase());
            var dato = convertir.convertirDatos(json, Datos.class);
            libroServicio.buscarLibroPorTitulo(dato, LibroBuscar);
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar el libro por título", e);
        }
    }

    private void librosGuardados() {
         libroServicio.buscarLibros();
    }

    private void  mostrarAutoresRegistrados() {
        libroServicio.listaAutoresRegistrados();
    }

    private void listarAutoresPorAno() {
        try {
             System.out.println("Escribe el año de nacimiento del Autor");
            var valor = sc.nextInt();
            sc.nextLine();

            libroServicio.listaAutoresPorano(valor);
         
        } catch (Exception e) {
            System.out.println(" Valor ingresado no valido, debe ser caracteresnumericos ");
        }          
    }

    private void mostrarLibrosPorIdioma() {
        var opciones = """
                       - Ingresa alguna de las siguientes opciones....
                       es - Español.
                       en - Ingles.
                       pt -  Portugues
                       """;
        System.out.println(opciones);
        var valor = sc.nextLine();
        libroServicio.mostrarLibrosPorIdioma(valor.toUpperCase());
    }
    
}
