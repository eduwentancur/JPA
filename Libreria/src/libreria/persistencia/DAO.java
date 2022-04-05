package libreria.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DAO<T,PK> implements Crud<T,PK> {
    
    protected static final EntityManager EM= Persistence.createEntityManagerFactory("LibreriaPU").createEntityManager();
    
    @Override
    public void insert(T entidad) throws Exception {
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
    public void update(T entidad) throws Exception {
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
    public void delete(T entidad) throws Exception {
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
    public T getByCode(Class<T>clase,PK pk) throws Exception {
        try {
            T t= EM.find(clase, pk);
            return t;
        } catch (Exception e) {
            throw new Exception("Error al buscar por código");
        }
    }
    
}
