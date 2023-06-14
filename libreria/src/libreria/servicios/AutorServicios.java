package libreria.servicios;

import libreria.entidades.Autor;
import libreria.persistencia.AutorDAO;

public class AutorServicios {

    private AutorServicios autorServicio;
    
    private final AutorDAO DAO;

    public AutorServicios() {
        this.DAO = new AutorDAO();
    }

   public void setServicios(AutorServicios autorServicio) {
        this.autorServicio = autorServicio;
        
    }

    // este método recibe dos parámetros: pais y provincia y los utiliza
    //  para inicializar un objeto Autor con estos valores. Luego se
    //  invoca al método guardar de la clase DAO padre y se pasa el objeto 
    //  a persistir.
    public Autor crearAutor(long id,String nombre) {
        Autor autor = new Autor();
        try {
            autor.setId(id);
            autor.setNombre(nombre);
            DAO.guardar(autor);
            return autor;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
   
}
