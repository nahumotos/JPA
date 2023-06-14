package libreria.servicios;

import libreria.entidades.Autor;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.LibroDAO;

public class LibroServicios {

    private LibroServicios libroServicio;
    
    private final LibroDAO DAO;

    public LibroServicios() {
        this.DAO = new LibroDAO();
    }

    public void setServicios(LibroServicios libroServicio) {
        this.libroServicio = libroServicio;
        
    }

    // este método recibe dos parámetros: pais y provincia y los utiliza
    //  para inicializar un objeto Libro con estos valores. Luego se
    //  invoca al método guardar de la clase DAO padre y se pasa el objeto 
    //  a persistir.
    public Libro crearLibro(long id,long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresRestantes, Integer ejemplaresPrestados, Autor autor, Editorial editorial) {
        Libro libro = new Libro();
        try {
            libro.setId(id);
            libro.setIsbn(isbn);
            libro.setTitulo(titulo);
            libro.setAnio(anio);
            libro.setEjemplares(ejemplares);
            libro.setEjemplaresRestantes(ejemplaresRestantes);
            libro.setEjemplaresPrestados(ejemplaresPrestados);
            libro.setAutor(autor);
            libro.setEditorial(editorial);
            DAO.guardar(libro);
            return libro;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
     public void prestarlibro(long id) throws Exception{
        Libro libro =DAO.buscarPorId(id);
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
        DAO.editar(libro);
  }
      public void devolverlibro(long id) throws Exception{
        
        Libro libro =DAO.buscarPorId(id);
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()-1);
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
        DAO.editar(libro);
        
  }
}