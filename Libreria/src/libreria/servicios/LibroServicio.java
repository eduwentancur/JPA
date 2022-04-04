package libreria.servicios;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import libreria.entidades.Libro;

import libreria.persistencia.LibroDAO;

public class LibroServicio {

    private Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
    private LibroDAO libroDAO = new LibroDAO();

    public Libro IngresarLibro() throws Exception {
        Libro libroNuevo = new Libro();
        AutorServicio autorNuevo = new AutorServicio();
        EditorialServicio editorialNuevo = new EditorialServicio();
        Scanner read = new Scanner(System.in, "ISO-8859-1").useDelimiter("\n").useLocale(Locale.US);
        System.out.println("Ingrese ISBN");
        libroNuevo.setISBN(read.nextLong());
        System.out.println("Ingrese Titulo");
        libroNuevo.setTitulo(read.next());
        System.out.println("Ingrese año");
        libroNuevo.setAnio(read.nextInt());
        System.out.println("Ingrese ejemplares");
        Integer ejemplares = read.nextInt();
        libroNuevo.setEjemplares(ejemplares);
        libroNuevo.setEjemplaresPrestados(0);
        libroNuevo.setEjemplaresRestantes(ejemplares);
        libroNuevo.setAlta(false);
        libroNuevo.setAutor(autorNuevo.crearAutor());
        libroNuevo.setEditorial(editorialNuevo.crearEditorial());
        libroDAO.insert(libroNuevo);
        return libroNuevo;
    }

    public void altaBaja() throws Exception {
        int opcion;
        do {
            System.out.println("1 - dar de alta un libro");
            System.out.println("2 - dar de baja un libro");
            System.out.println("3- salir");
            opcion = read.nextInt();
            switch (opcion) {
                case 1:
                    darAltaLibro();
                    break;
                case 2:
                    darBajaLibro();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
        } while (opcion != 3);

    }

    public void darAltaLibro() throws Exception {
        mostrarTodosLosLibros();
        System.out.println("Ingrese ISBN del libro");
        Long isbn = read.nextLong();
        Libro libro = libroDAO.getByCode(Libro.class, isbn);
        libro.setAlta(Boolean.TRUE);
        libroDAO.update(libro);
    }

    public void darBajaLibro() throws Exception {
        mostrarTodosLosLibros();
        System.out.println("Ingrese ISBN del libro");
        Long isbn = read.nextLong();
        Libro libro = libroDAO.getByCode(Libro.class, isbn);
        libro.setAlta(Boolean.FALSE);
        libroDAO.update(libro);
    }

    public void editarLibro() throws Exception {
        mostrarTodosLosLibros();
        System.out.println("Ingrese el ISBN del libro que desea editar");
        Libro libro = libroDAO.getByCode(Libro.class, read.nextLong());
        System.out.println("Libro Obtenido");
        System.out.println(libro.toString());
        int opcion;
        do {
            System.out.println("Que desea editar?");
            System.out.println("1- Titulo");
            System.out.println("2- Año");
            System.out.println("3- Ejemplares");
            System.out.println("4- Ejemplares prestados");
            System.out.println("5- Salir");
            opcion = read.nextInt();
            switch (opcion) {
                case 1:
                    editTitulo(libro);
                    break;
                case 2:
                    editAnio(libro);
                    break;
                case 3:
                    editEjemplares(libro);
                    break;
                    
                case 4:
                    editEjemplaresPrestados(libro);
                    break;
                case 5:
                    break;
                default:
                    System.out.println("opcion incorrecta");
                    throw new AssertionError();
            }
        } while (opcion != 5);
    }

    public void editTitulo(Libro libro) throws Exception {
        System.out.println("Ingrese el nombre nuevo");
        libro.setTitulo(read.next());
        libroDAO.update(libro);
        mostrarTodosLosLibros();
    }

    public void editAnio(Libro libro) throws Exception {
        System.out.println("Ingrese año nuevo");
        libro.setAnio(read.nextInt());
        libroDAO.update(libro);
        mostrarTodosLosLibros();
    }

    public void editEjemplares(Libro libro) throws Exception {
        System.out.println("Ingrese cantidad de ejemplares");
        libro.setEjemplares(read.nextInt());
        libro.setEjemplaresRestantes(libro.getEjemplares() - libro.getEjemplaresPrestados());
        libroDAO.update(libro);
        mostrarTodosLosLibros();
    }

    public void editEjemplaresPrestados(Libro libro) throws Exception {
        System.out.println("Cuantos ejemplares prestados");
        int ejemplaresP = read.nextInt();
        libro.setEjemplaresPrestados(ejemplaresP);
        libro.setEjemplaresRestantes(libro.getEjemplares() - ejemplaresP);
        libroDAO.update(libro);
        mostrarTodosLosLibros();
    }

    public void buscarLibro() throws Exception {
        int opcion;
        do {
            System.out.println("Como desea buscar?");
            System.out.println("1 buscar libro por isbn");
            System.out.println("2 busca libro por titulo");
            System.out.println("3 buscar libro por autor");
            System.out.println("4 buscar libro por editorial");
            System.out.println("5 salir");
            opcion = read.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese ISBN para buscar");
                    Libro libro = libroDAO.getByCode(Libro.class, read.nextLong());
                    System.out.println("El libro encontrado es \n" + libro.toString());
                    break;
                case 2:
                    System.out.println("Ingrese el Titulo para buscar");
                    List<Libro> libros = libroDAO.obtenerPorTitulo(read.next());
                    System.out.println("El libro encontrado es \n" + libros.toString());
                    break;
                case 3:
                    System.out.println("Ingrese Nombre del Autor");
                    List<Libro> libroAutor = libroDAO.obtenerPorAutor(read.next());
                    System.out.println("El libro encontrado es \n" + libroAutor.toString());
                    break;
                case 4:
                    System.out.println("Ingrese nombre de la Editorial");
                    List<Libro> libroEditorial = libroDAO.obtenerPorEditorial(read.next());
                    System.out.println("El libro encontrado es \n" + libroEditorial.toString());
                    break;
                default:
                    System.out.println("Ingreso opcion incorrecta");
                    break;
            }
        } while (opcion != 5);
    }

    public void mostrarTodosLosLibros() throws Exception {
        System.out.println("LISTA DE TODOS LOS LIBROS\n");
        System.out.printf("%-10s%-10s%-10s%-20s%-30s%-30s%-30s%-30s\n", "ISBN", "Titulo", "Año", 
                "Ejemplares","Ejemplares Prestados", "Ejemplares Restantes","Nombre Autor", "Nombre editorial");
        List<Libro> libros = libroDAO.obtenerLibros();
        for (Libro libro : libros) {
            System.out.printf("%-10s%-10s%-10s%-20s%-30s%-30s%-30s%-30s\n",libro.getISBN(),libro.getTitulo(),libro.getAnio(),
                    libro.getEjemplares(),libro.getEjemplaresPrestados(),libro.getEjemplaresRestantes(),libro.getAutor().getNombre(),libro.getEditorial().getNombre());
        }
    }
}
