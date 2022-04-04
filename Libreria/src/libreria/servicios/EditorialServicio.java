
package libreria.servicios;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;


public class EditorialServicio {
    
    private EditorialDAO editorialDAO = new EditorialDAO();
    private Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
    public Editorial crearEditorial() throws Exception{
        Editorial editorialNuevo = new Editorial();
        System.out.println("Ingrese nombre de la EDITORIAL");
        editorialNuevo.setNombre(read.next());
        editorialNuevo.setAlta(Boolean.FALSE);
        editorialDAO.insert(editorialNuevo);
        return editorialNuevo;
    }
    
    public void darAltaEditorial() throws Exception{
        System.out.println("Ingrese id de la editorial");
        Editorial editorial = editorialDAO.getByCode(Editorial.class, read.nextInt());
        editorial.setAlta(Boolean.TRUE);
        editorialDAO.update(editorial);
    }
    
    public void editarUnaEditorial() throws Exception{
        System.out.println("Ingrese id de la editorial");
        Editorial editorial = editorialDAO.getByCode(Editorial.class, read.nextInt());
        if (editorial==null) {
            System.out.println("No se encuentra esa editorial");
        }else{
            System.out.println(editorial.toString());
            System.out.println("Ingrese nuevo nombre para la editorial");
            editorial.setNombre(read.next());
            editorialDAO.update(editorial);
            System.out.println("SE MODIFICO CORRECTAMENTE");
            System.out.println(editorial.toString());
        }
    }
    
    public void mostrarEditoriales() throws Exception{
        List<Editorial> editoriales = editorialDAO.mostrarTodosLasEditoriales();
        for (Editorial editorial : editoriales) {
            System.out.println(editorial.toString());
        }
    }
    
}
