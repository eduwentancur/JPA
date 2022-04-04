package libreria.persistencia;

import java.util.List;
import libreria.entidades.Libro;

public class LibroDAO extends DAO<Libro, Long> {

    public List<Libro> obtenerLibros() throws Exception {
        try {
            List<Libro> libros = em.createQuery("SELECT m FROM Libro m", Libro.class)
                    .getResultList();
            return libros;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros");
        }
    }

    public List<Libro> obtenerPorTitulo(String titulo) throws Exception {

        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class)
                    .setParameter("titulo", titulo)
                    .getResultList();
            return libros;
        } catch (Exception e) {
            throw new Exception("Error al buscar titulo");
        }
    }

    public List<Libro> obtenerPorAutor(String nombre) {
        try {
            List<Libro> libros = em.createQuery("SELECT l FROM Libro l "
                    + " WHERE l.autor.nombre LIKE CONCAT('%', :nombre, '%')", Libro.class)
                    .setParameter("nombre", nombre)
                    .getResultList();
            return libros;
        } catch (Exception e) {
            System.out.println("ERROR al buscar por Autor" + e);
            throw e;
        }
    }
    
    public List<Libro> obtenerPorEditorial(String nombre) {
        try {
            List<Libro> libros = em.createQuery("SELECT l "
                    + " FROM Libro l"
                    + " WHERE l.editorial.nombre LIKE CONCAT('%', :nombre, '%')", Libro.class)
                    .setParameter("nombre", nombre)
                    .getResultList();
            return libros;
        } catch (Exception e) {
            System.out.println("ERROR al buscar por Editorial" + e);
            throw e;
        }
    }
    

}
