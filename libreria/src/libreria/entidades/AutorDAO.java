// Esta clase se encarga de la persistencia de los registros
// de tipo Autor
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorDAO {

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
    // Toma como parámetro el objeto de tipo Autor que se quiere persistir
    public void guardar(Autor autor) {

        conectar();
        em.getTransaction().begin();
        em.persist(autor);
        em.getTransaction().commit();
        desconectar();

    }

    // Este método nos permite eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Autor autor) {
        conectar();
        em.getTransaction().begin();
        em.remove(autor);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editar(Autor autor) {
        conectar();
        em.getTransaction().begin();
        em.merge(autor);
        em.getTransaction().commit();
        desconectar();
    }

    public Autor buscarPorNombre(String nombre) throws Exception {
        try {
            if (nombre == null) {
                throw new Exception("Debe indicar el nombre");
            }
            conectar();
            Autor autor = (Autor) em.createQuery("SELECT a FROM Autor a WHERE a.nombre like :nombre")
                    .setParameter("nombre", nombre).getSingleResult();

            return autor;

        } catch (Exception e) {
            throw e;
        } finally {
            desconectar();
        }

    }

    public List<Autor> listarTodos() throws Exception {

        conectar();
        List<Autor> autores = em.createQuery("SELECT d FROM Autor d")
                .getResultList();
        desconectar();
        return autores;
    }
}
