package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAO<T,PK> {

    protected final EntityManager em = Persistence
            .createEntityManagerFactory("LibreriaPU")
            .createEntityManager();

    public void insert(T entidad) throws Exception {
        try {
            em.getTransaction().begin();
            em.persist(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar ");
        }
    }

    public void update(T entidad) throws Exception {
        try {
            em.getTransaction().begin();
            em.merge(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al actualizar ");
        }
    }

    public void delete(T entidad) throws Exception {
        try {
            em.getTransaction().begin();
            em.remove(entidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar ");
        }
    }
    public T getByCode(Class<T>clase,PK pk) throws Exception {
        try {
            T t= em.find(clase, pk);
            return t;
        } catch (Exception e) {
            throw new Exception("Error al buscar por código");
        }
    }
}
