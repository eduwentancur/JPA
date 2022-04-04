package libreria.servicios;

import java.util.Locale;
import java.util.Scanner;

public class Servicio {

    private AutorServicio autorServicio = new AutorServicio();
    private EditorialServicio editorialServicio = new EditorialServicio();
    private LibroServicio libroServicio = new LibroServicio();

    public void Menu() throws Exception {
        Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
        int opcion;
        do {
            System.out.println("1-Cargar un libro");
            System.out.println("2-Cargar un autor");
            System.out.println("3-Cargar una editorial");
            System.out.println("4-Dar de alta/baja libro ");
            System.out.println("5-Dar de alta/baja un autor");
            System.out.println("6-Dar de alta/baja una editorial ");
            System.out.println("7-Editar libro ");
            System.out.println("8-Editar un autor");
            System.out.println("9-Editar una editorial");
            System.out.println("10-Buscar autor por nombre");
            System.out.println("11-Buscar libro");
            System.out.println("12-Mostrar todos los autores");
            System.out.println("13-Mostrar todas las editoriales");
            System.out.println("14-Mostrar todos los libros");
            System.out.println("0- SALIR");
            opcion = read.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Libro nuevo cargado\nLibro:"+libroServicio.IngresarLibro());
                    break;
                case 2:
                    System.out.println("Autor cargado\nAutor: "+autorServicio.crearAutor().getNombre());
                    break;
                case 3:
                    System.out.println("Editorial cargada\nEditorial: "+editorialServicio.crearEditorial().getNombre());
                    break;
                case 4:
                    libroServicio.altaBaja();
                    break;
                case 5:
                    autorServicio.darAltaAutor();
                    break;
                case 6:
                    editorialServicio.darAltaEditorial();
                    break;
                case 7:
                    libroServicio.editarLibro();
                    break;
                case 8:
                    autorServicio.editAutor();
                    break;
                case 9:
                    editorialServicio.editarUnaEditorial();
                    break;
                case 10:
                    autorServicio.buscarPorNombre();
                    break;
                case 11:
                    libroServicio.buscarLibro();
                    break;
                case 12:
                    autorServicio.mostrarAutores();
                    break;
                case 13:
                    editorialServicio.mostrarEditoriales();
                    break;
                case 14:
                    libroServicio.mostrarTodosLosLibros();
                    break;
                case 0:
                    System.out.println("CHAU CHAU");
                    break;
                default:
                    System.out.println("Ingreso opcion incorrecta");
                    break;
                   
            }

        } while (opcion != 0);

    }
    
}
