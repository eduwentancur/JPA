package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Libro;

public class LibroDAO implements Crud<Libro,Long> {
    
    private EntityManager EM= Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    @Override
    public void insert(Libro autor) throws Exception {
        try {
            EM.getTransaction().begin();
            EM.persist(autor);
            EM.getTransaction().commit();
        } catch (Exception e) {
            EM.getTransaction().rollback();
            throw new Exception("Error al insertar ");
        }
    }
    @Override
    public void update(Libro entidad) throws Exception {
        try {
            EM.getTransaction().begin();
            EM.merge(entidad);
            EM.getTransaction().commit();
        } catch (Exception e) {
            EM.getTransaction().rollback();
            throw new Exception("Error al actualizar ");
        }
    }
    @Override
    public void delete(Libro entidad) throws Exception {
        try {
            EM.getTransaction().begin();
            EM.remove(entidad);
            EM.getTransaction().commit();
        } catch (Exception e) {
            EM.getTransaction().rollback();
            throw new Exception("Error al insertar ");
        }
    }
    @Override
    public Libro getByCode(Class<Libro>clase,Long pk) throws Exception {
        try {
            Libro t= EM.find(clase, pk);
            return t;
        } catch (Exception e) {
            throw new Exception("Error al buscar por código");
        }
    }
    public List<Libro> obtenerLibros() throws Exception {
        try {
            List<Libro> libros = EM.createQuery("SELECT m FROM Libro m", Libro.class)
                    .getResultList();
            return libros;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros");
        }
    }
    public List<Libro> obtenerPorTitulo(String titulo) throws Exception {
        try {
            List<Libro> libros = EM.createQuery("SELECT l FROM Libro l WHERE l.titulo LIKE :titulo", Libro.class)
                    .setParameter("titulo", titulo)
                    .getResultList();
            return libros;
        } catch (Exception e) {
            throw new Exception("Error al buscar titulo");
        }
    }
    public List<Libro> obtenerPorAutor(String nombre) {
        try {
            List<Libro> libros = EM.createQuery("SELECT l FROM Libro l "
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
            List<Libro> libros = EM.createQuery("SELECT l "
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
