/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria.servicios;

import java.text.SimpleDateFormat;
import java.util.Date;
import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.persistencia.AutorDAO;
import libreria.persistencia.ClienteDAO;
import libreria.persistencia.EditorialDAO;
import libreria.persistencia.LibroDAO;
import libreria.persistencia.PrestamoDAO;

public class ServicioMain {

    java.util.Scanner leer = new java.util.Scanner(System.in).useDelimiter("\n");
    //SERVICIOS
    AutorServicios as = new AutorServicios();
    EditorialServicios es = new EditorialServicios();
    LibroServicios ls = new LibroServicios();
    PrestamoServicios ps = new PrestamoServicios();
    ClienteServicios cs = new ClienteServicios();
    //DAO
    AutorDAO adao = new AutorDAO();
    LibroDAO ldao = new LibroDAO();
    EditorialDAO edao = new EditorialDAO();
    PrestamoDAO pdao = new PrestamoDAO();
    ClienteDAO cdao = new ClienteDAO();

    Autor a1;
    Autor a2;
    Autor a3;
    Editorial e1;
    Editorial e2;
    Editorial e3;

    public void crearAutor() {
        //libro=id, isbn, titulo,fecha,ejmtotales,ejmrestantes,ejmprestados,autor,editorial
        a1 = as.crearAutor(1, "Juan");
        a2 = as.crearAutor(2, "lionel");
        a3 = as.crearAutor(3, "Dios");

    }

    public void crearEditorial() {
        e1 = es.crearEditorial(1, "messi");
        e2 = es.crearEditorial(2, "San Pedro");
        e3 = es.crearEditorial(3, "DBZ");
    }

    public void crearlibro() {
        ls.crearLibro(1, 789654, "El Paraiso", 254512, 100, 50, 50, a1, e1);
        ls.crearLibro(2, 15678, "Tsunami", 010425, 20, 5, 15, a1, e2);
        ls.crearLibro(3, 4532, "Flash", 051526, 45, 10, 35, a2, e3);
        ls.crearLibro(4, 152, "Primero", 876513, 2, 1, 1, a3, e2);

    }

    public void crearPrestamo() throws Exception {
        System.out.println("----------crear prestamo---------");
        System.out.println("Ingrese el id del libro a prestar");
        Libro idl = ldao.buscarPorId(leer.nextLong());

        System.out.println("Ingrese el id del cliente");
        // Cliente idc = cdao.listarTodos().get(leer.nextInt());
        Cliente idc = cdao.buscarPorId(leer.nextLong());

        // fecha actual
        Date datep = new Date();
        System.out.println("Ingrese la fecha en que se devolvió el libro en formato dd/MM/yyyy");
        String fd = leer.next();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dated = fd;
        Date fechadev = df.parse(dated);
        if (datep.after(fechadev)) {
            System.out.println("No se puede prestar libros con fecha anterior a la fecha actual,");

        }

        System.out.println("Ingrese id del préstamo");

        int i = leer.nextInt();

        ps.crearPrestamo(i, datep, fechadev, idl, idc);

    }

    public void crearCliente() {
        System.out.println("---------crear cliente---------");
        try {
            System.out.println("ingrese id,documento, nombre, apellido y telefono");
            cs.crearCliente(leer.nextInt(), leer.nextLong(), leer.next(), leer.next(), leer.next());
        } catch (Exception e) {
        }

    }

    public void menu() throws Exception {
        int op;
        
        do {
            System.out.println("MENU");
            System.out.println("Ingrese una opcion:");
            System.out.println("1. Búsqueda de un Autor por nombre.");
            System.out.println("2. Búsqueda de un libro por ISBN.");
            System.out.println("3. Búsqueda de un libro por Título.");
            System.out.println("4. Para la Búsqueda de un libro/s por nombre de Autor.");
            System.out.println("5. Para la Búsqueda de un libro/s por nombre de Editorial");
            System.out.println("6. crear cliente");
            System.out.println("7. crear prestamo");
            System.out.println("8. devolucion de un libro");
            System.out.println("9. mostrar todos los prestamos de un cliente");
            System.out.println("10. mostrar todos los libros");
            System.out.println("11. mostrar todos los prestamos");
            System.out.println("12. mostrar todos los clientes");
            System.out.println("13. mostrar todos los autores");
            System.out.println("14. mostrar todos los editoriales");
            System.out.println("15. Salir");
            op = leer.nextInt();
            switch (op) {
                case 1:
                    System.out.println("ingrese el nombre del autor");
                    System.out.println(adao.buscarPorNombre(leer.next()));

                    break;
                case 2:
                    System.out.println("ingrese Nº isbn:");
                    System.out.println(ldao.buscarPorIsbn(leer.nextLong()));

                    break;
                case 3:
                    System.out.println("Ingrese Titulo del libro:");
                    System.out.println(ldao.buscarPorTitulo(leer.next()));

                    break;
                case 4:
                    System.out.println("Ingrese el nombre del autor del libro ");
                    System.out.println(ldao.buscarPorNombreAutor(leer.next()));
                    break;
                case 5:
                    System.out.println("Ingrese el nombre de la editorial del libro ");
                    System.out.println(ldao.buscarPorNombreEditorial(leer.next()));
                    break;
                case 6:
                    System.out.println("crear cliente");
                    crearCliente();
                    break;
                case 7:
                    System.out.println("crear prestamo");
                    crearPrestamo();
                    break;
                case 8:
                    System.out.println("ingrese el id del prestmao");
                    ps.devolucion(leer.nextLong());
                    break;
                case 9:
                    System.out.println("ingrese el id del cliente");
                    System.out.println(pdao.mostrarPrestamocliente(leer.nextLong()));
                    break;
                case 10:
                    ldao.listarLibros().forEach((a) -> System.out.println(a.toString()));
                    break; 
                case 11:
                    pdao.listarTodos().forEach((a) -> System.out.println(a.toString()));
                    break;
                case 12:
                    cdao.listarTodos().forEach((a) -> System.out.println(a.toString()));
                    break;
                case 13:

                    adao.listarTodos().forEach((a) -> System.out.println(a.toString()));
                    break;
                case 14:

                    edao.listarTodos().forEach((a) -> System.out.println(a.toString()));
                    break;
            }
        } while (op != 15);

    }

}
