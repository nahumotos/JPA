package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;

public class ClienteDAO {

    private final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("libreriaPU");
    private EntityManager em = EMF.createEntityManager();

    // Este método nos permite conectar con la base de datos
    // se verifica si la conexión está realizada, en caso que
    // no esté realizada, se realiza.
    public void conectar() {
        if (!em.isOpen()) {
            em = EMF.createEntityManager();
        }
    }

    // Este método nos permite desconectarnos de la base de datos
    // Se verifica si existe una conexión, y de ser el caso, se
    // cierra la conexión y se desconecta el programa con la base de datos.
    public void desconectar() {
        if (em.isOpen()) {
            em.close();
        }
    }

    // este método nos permite persistir un objeto en la base de datos.
    // Toma como parámetro el objeto de tipo Cliente que se quiere persistir
    public void guardar(Cliente cliente) {

        conectar();
        em.getTransaction().begin();
        em.persist(cliente);
        em.getTransaction().commit();
        desconectar();

    }

    // Este método nos permite eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        em.remove(cliente);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editar(Cliente cliente) {
        conectar();
        em.getTransaction().begin();
        em.merge(cliente);
        em.getTransaction().commit();
        desconectar();
    }

    public Cliente buscarPorNombre(String nombre) throws Exception {
        try {
            if (nombre == null) {
                throw new Exception("Debe indicar el nombre");
            }
            conectar();
            Cliente cliente = (Cliente) em.createQuery("SELECT a FROM Cliente a WHERE a.nombre like :nombre")
                    .setParameter("nombre", nombre).getSingleResult();

            return cliente;

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

    }
     public Cliente buscarPorId(Long id) throws Exception {
        try {
            if (id==null) {
                throw new Exception("Debe indicar el id");
            }
            conectar();
        Cliente cliente = em.find(Cliente.class, id);
        desconectar();
        return cliente;
        } catch (Exception e) {
            throw e;
        }
        //.find solo funciona con claves primarias
    }

    public List<Cliente> listarTodos() throws Exception {

        conectar();
        List<Cliente> clientes = em.createQuery("SELECT d FROM Cliente d")
                .getResultList();
        desconectar();
        return clientes;
    }
}