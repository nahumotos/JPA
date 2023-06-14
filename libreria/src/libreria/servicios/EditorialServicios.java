package libreria.servicios;

import libreria.entidades.Editorial;
import libreria.persistencia.EditorialDAO;

public class EditorialServicios {

    private EditorialServicios editorialServicio;
    
    private final EditorialDAO DAO;

    public EditorialServicios() {
        this.DAO = new EditorialDAO();
    }

    public void setServicios(EditorialServicios editorialServicio) {
        this.editorialServicio = editorialServicio;
        
    }

    // este método recibe dos parámetros: pais y provincia y los utiliza
    //  para inicializar un objeto Editorial con estos valores. Luego se
    //  invoca al método guardar de la clase DAO padre y se pasa el objeto 
    //  a persistir.
    public Editorial crearEditorial(long id, String nombre) {
        Editorial editorial = new Editorial();
        try {
            editorial.setId(id);
            editorial.setNombre(nombre);
            DAO.guardar(editorial);
            return editorial;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
