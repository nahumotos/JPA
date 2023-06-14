package libreria.servicios;

import libreria.entidades.Cliente;
import libreria.persistencia.ClienteDAO;

public class ClienteServicios {

    private ClienteServicios clienteServicio;
    
    private final ClienteDAO DAO;

    public ClienteServicios() {
        this.DAO = new ClienteDAO();
    }

    public void setServicios(ClienteServicios clienteServicio) {
        this.clienteServicio = clienteServicio;
        
    }

   
    public Cliente crearCliente(long id,long documento, String nombre,String apellido,String telefono) {
        Cliente cliente = new Cliente();
        try {
            cliente.setId(id);
            cliente.setId(documento);
            cliente.setNombre(nombre);
            cliente.setNombre(nombre);
            cliente.setNombre(apellido);
            cliente.setNombre(telefono);
            DAO.guardar(cliente);
            return cliente;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
