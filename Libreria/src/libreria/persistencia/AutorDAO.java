
package libreria.persistencia;

import java.util.List;
import libreria.entidades.Autor;


public class AutorDAO extends DAO<Autor, Integer> {

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
