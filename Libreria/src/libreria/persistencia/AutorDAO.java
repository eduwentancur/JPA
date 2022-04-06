
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import libreria.entidades.Autor;


public class AutorDAO implements Crud<Autor,Integer> {
    
    private EntityManager EM= Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();

    @Override
    public void insert(Autor autor) throws Exception {
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
    public void update(Autor entidad) throws Exception {
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
    public void delete(Autor entidad) throws Exception {
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
    public Autor getByCode(Class<Autor>clase,Integer pk) throws Exception {
        try {
            Autor t= EM.find(clase, pk);
            return t;
        } catch (Exception e) {
            throw new Exception("Error al buscar por código");
        }
    }
    public List<Autor> getByName(String name) throws Exception {
        try {
            List<Autor> autores = EM.createQuery("SELECT a FROM Autor a WHERE a.nombre LIKE CONCAT('%', :name, '%')", Autor.class)
                    .setParameter("name", name)
                    .getResultList();
            return autores;
        } catch (Exception e) {
            throw new Exception("Error al buscar fabricantes por nombre");
        }
    }
    public List<Autor> mostrarTodosLosAutores() throws Exception{
        try {
            List<Autor> autores = EM.createQuery("SELECT a FROM Autor a", Autor.class)
                    .getResultList();
            return autores;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros");
        }
    }
}
