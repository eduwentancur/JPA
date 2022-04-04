package libreria.persistencia;

import java.util.List;

import libreria.entidades.Editorial;

public class EditorialDAO extends DAO<Editorial,Integer> {
    
    public List<Editorial> mostrarTodosLasEditoriales() throws Exception{
        try {
            List<Editorial> editoriales = em.createQuery("SELECT e FROM Editorial e", Editorial.class)
                    .getResultList();
            return editoriales;
        } catch (Exception e) {
            throw new Exception("Error al buscar libros");
        }
    }
}
