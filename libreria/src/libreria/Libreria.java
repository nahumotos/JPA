/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libreria;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.persistencia.AutorDAO;
import libreria.servicios.AutorServicios;
import libreria.servicios.EditorialServicios;
import libreria.servicios.LibroServicios;
import libreria.servicios.ServicioMain;

/**
 *
 * @author Nahuel
 */
public class Libreria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        //final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("libreriaPU");
        // EntityManager em = EMF.createEntityManager();
        AutorServicios as = new AutorServicios();
        EditorialServicios es = new EditorialServicios();
        LibroServicios ls = new LibroServicios();
        ServicioMain sm = new ServicioMain();

        java.util.Scanner leer = new java.util.Scanner(System.in).useDelimiter("\n");
        /*sm.crearAutor();
        sm.crearEditorial();
        sm.crearlibro();*/
        sm.menu();

    }

}
