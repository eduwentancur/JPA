package libreria.persistencia;

public interface Crud<T,PK>{
    
    void insert(T entidad)throws Exception ;
    
    void update(T entidad) throws Exception;
    
    void delete(T entidad) throws Exception;
    
    T getByCode(Class<T>clase,PK pk)throws Exception;
    
}
