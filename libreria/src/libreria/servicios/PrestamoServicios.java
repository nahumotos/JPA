package libreria.servicios;

import java.util.Date;
import libreria.entidades.Cliente;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.persistencia.PrestamoDAO;

public class PrestamoServicios {

    private PrestamoServicios prestamoServicio;
    
    private final PrestamoDAO DAO;
    LibroServicios ls= new LibroServicios();
    public PrestamoServicios() {
        this.DAO = new PrestamoDAO();
    }

   public void setServicios(PrestamoServicios prestamoServicio) {
        this.prestamoServicio = prestamoServicio;
        
    }

    // este método recibe dos parámetros: pais y provincia y los utiliza
    //  para inicializar un objeto Prestamo con estos valores. Luego se
    //  invoca al método guardar de la clase DAO padre y se pasa el objeto 
    //  a persistir.
    public Prestamo crearPrestamo(long id,Date fechaPrestamo, Date fechaDevolucion,Libro libro, Cliente cliente) {
        Prestamo prestamo = new Prestamo();
        try {
            if (libro.getEjemplaresRestantes()<= libro.getEjemplaresPrestados()){
                throw new Exception("Ingrese un libro valido no hay para prestar");
            }
            
            prestamo.setId(id);
            prestamo.setFechaPrestamo(fechaPrestamo);
            prestamo.setFechaPrestamo(fechaDevolucion);
            prestamo.setLibro(libro);
            prestamo.setCliente(cliente);
            DAO.guardar(prestamo);
            ls.prestarlibro(libro.getId());
            return prestamo;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   public void devolucion (long id) throws Exception{
       Prestamo prestamo = DAO.buscarPorId(id);
       
       ls.devolverlibro(prestamo.getLibro().getId());
       DAO.eliminar(prestamo);
   }
  
}
