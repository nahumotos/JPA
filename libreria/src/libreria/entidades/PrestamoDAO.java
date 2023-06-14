package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Prestamo;


public class PrestamoDAO {

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
    // Toma como parámetro el objeto de tipo Prestamo que se quiere persistir
    public void guardar(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.persist(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.remove(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editar(Prestamo prestamo) {
        conectar();
        em.getTransaction().begin();
        em.merge(prestamo);
        em.getTransaction().commit();
        desconectar();
    }

    public Prestamo buscarPorId(Long id) throws Exception {
        try {
            if (null == id) {
                throw new Exception("Debe indicar el id");
            }
            conectar();
            Prestamo prestamo = em.find(Prestamo.class, id);
            desconectar();
            return prestamo;
        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }
    }

    public List<Prestamo> listarTodos() throws Exception {
        conectar();
        List<Prestamo> prestamos = em.createQuery("SELECT d FROM Prestamo d")
                .getResultList();
        desconectar();
        return prestamos;
    }
    public List<Prestamo> mostrarPrestamocliente(Long id) throws Exception {
        
        try {
            if (id==null) {
                throw new Exception("Debe indicar isbn");
            }
            conectar();
            List<Prestamo> prestamos = em.createQuery("SELECT p FROM Prestamo p join Cliente c WHERE c.id = :id").setParameter("id", id).getResultList();
            desconectar();
            return prestamos;
        } catch (Exception e) {
            throw e;
        }
    }
  
}
