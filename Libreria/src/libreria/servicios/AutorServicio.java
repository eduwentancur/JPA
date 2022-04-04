package libreria.servicios;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorServicio {
    
    private AutorDAO autorDAO = new AutorDAO();
    private Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
    public Autor crearAutor() throws Exception{
        Autor autorNuevo = new Autor();
        System.out.println("Ingrese nombre del AUTOR ");
        autorNuevo.setNombre(read.next());
        autorNuevo.setAlta(Boolean.FALSE);
        autorDAO.insert(autorNuevo);
        return autorNuevo;
    }
    
    public void darAltaAutor() throws Exception{
        System.out.println("Ingrese id del autor");
        Autor autor = autorDAO.getByCode(Autor.class, read.nextInt());
        autor.setAlta(Boolean.TRUE);
        autorDAO.update(autor);
    }
    
    public void editAutor() throws Exception{
        System.out.println("Ingresar el id del autor");
        Autor autor = autorDAO.getByCode(Autor.class, read.nextInt());
        if (autor == null) {
            System.out.println("NO SE ENCOTRO AUTOR CON ESE ID");
        }else{
            System.out.println(autor.toString());
            System.out.println("Ingrese el nombre nuevo para modificar");
            autor.setNombre(read.next());
            autorDAO.update(autor);
            System.out.println("MODIFICADO EL AUTOR \n"+autor.toString());
        }
    }
    
    public void buscarPorNombre() throws Exception{
        System.out.println("Ingresar nombre del AUTOR");
        List<Autor> autores = autorDAO.getByName(read.next());
        System.out.printf("%-15s%-15s\n","ID","NOMBRE AUTOR");
        for (Autor autor : autores) {
            System.out.printf("%-15s%-15s\n",autor.getId(),autor.getNombre());
        }
    }
    
    public void mostrarAutores() throws Exception{
        List<Autor> autores = autorDAO.mostrarTodosLosAutores();
        System.out.printf("%-15s%-15s\n","ID","NOMBRE");
        for (Autor autore : autores) {
            System.out.printf("%-15s%-15s\n",autore.getId(),autore.getNombre());
        }
    }
}
