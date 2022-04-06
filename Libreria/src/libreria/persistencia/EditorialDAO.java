package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import libreria.entidades.Editorial;

public class EditorialDAO implements Crud<Editorial,Integer> {
    
    private EntityManager EM= Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    @Override
    public void insert(Editorial entidad) throws Exception {
        try {
            EM.getTransaction().begin();
            EM.persist(entidad);
            EM.getTransaction().commit();
        } catch (Exception e) {
            EM.getTransaction().rollback();
            throw new Exception("Error al insertar ");
        }
    }
    @Override
    public void update(Editorial entidad) throws Exception {
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
    public void delete(Editorial entidad) throws Exception {
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
    public Editorial getByCode(Class<Editorial>clase,Integer pk) throws Exception {
        try {
            Editorial t= EM.find(clase, pk);
            return t;
        } catch (Exception e) {
            throw new Exception("Error al buscar por código");
        }
    }
    public List<Editorial> mostrarTodosLasEditoriales() throws Exception{
        try {
            List<Editorial> editoriales = EM.createQuery("SELECT e FROM Editorial e", Editorial.class)
                    .getResultList();
            return editoriales;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros");
        }
    }
}
