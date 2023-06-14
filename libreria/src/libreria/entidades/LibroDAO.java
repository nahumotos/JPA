// Esta clase se encarga de la persistencia de los registros
// de tipo Libro
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;

public class LibroDAO {

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
    // Toma como parámetro el objeto de tipo Libro que se quiere persistir
    public void guardar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite eliminar un registro de la base de datos.
    // Como parámetro se pasa el objeto a eliminar de la base de datos.
    // Se busca en la base de datos el registro que contenga la misma información
    // que el parámetro recibido, y se elimina.
    public void eliminar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.remove(libro);
        em.getTransaction().commit();
        desconectar();
    }

    // Este método nos permite modificar un registro de una base de datos.
    // Recibe como parámetro el objeto con los datos cambiados (debe mantener
    // la misma llave primaria) y lo reemplaza por el anterior.
    public void editar(Libro libro) {
        conectar();
        em.getTransaction().begin();
        em.merge(libro);
        em.getTransaction().commit();
        desconectar();
    }

    public Libro buscarPorIsbn(Long isbn) throws Exception {
        
        try {
            if (isbn==null) {
                throw new Exception("Debe indicar isbn");
            }
            conectar();
            Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.isbn = :isbn").setParameter("isbn", isbn).getSingleResult();
            desconectar();
            return libro;
        } catch (Exception e) {
            throw e;
        }
    }

    public Libro buscarPorId(Long id) throws Exception {
        try {
            if (id==null) {
                throw new Exception("Debe indicar el id");
            }
            conectar();
        Libro libro = em.find(Libro.class, id);
        desconectar();
        return libro;
        } catch (Exception e) {
            throw e;
        }
        //.find solo funciona con claves primarias
    }

    public Libro buscarPorTitulo(String titulo) throws Exception {
        try {
            if (titulo==null) {
                 throw new Exception("Debe indicar el titulo");
            }
             conectar();
        Libro libro = (Libro) em.createQuery("SELECT l FROM Libro l WHERE l.titulo = :titulo").setParameter("titulo", titulo).getSingleResult();
        desconectar();
        return libro;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<Libro> buscarPorNombreAutor(String nombre) throws Exception {
        try {
            if (nombre==null) {
            throw new Exception("Debe indicar el nombre del autor");
            }
            conectar();
        List<Libro> libro = em.createQuery("SELECT l FROM Libro l join Autor a WHERE a.nombre like :nombre").setParameter("nombre", nombre).getResultList();
        desconectar();
        return libro;
        } catch (Exception e) {
            throw e;
        }   
    }

    public List<Libro> buscarPorNombreEditorial(String nombre) throws Exception {
        try {
            if (true) {
            throw new Exception("Debe indicar el nombre de la editorial");

            }
            conectar();
        List<Libro> libro = em.createQuery("SELECT l FROM Libro l join Editorial e  WHERE e.nombre like :nombre").setParameter("nombre", nombre).getResultList();
        desconectar();
        return libro;
        } catch (Exception e) {
            throw e;
        } 
    }

    public List<Libro> listarLibros() throws Exception {
       
        conectar();
        List<Libro> libros = em.createQuery("SELECT d FROM Libro d")
                .getResultList();
        desconectar();
        return libros;
    }
 
}
