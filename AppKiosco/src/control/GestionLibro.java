/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Descuento;
import entity.Libro;
import entity.PaperBook;
import entity.PorSaga;
import java.util.HashMap;



/**
 *
 * @author vale-
 */
public class GestionLibro {
    
    public GestionLibro() {
    }

    //punto 1 a I
    public HashMap<String, Libro> crearColeccionLibro() {
        HashMap<String, Libro> librosDispo     = new HashMap<>();
        HashMap<Integer, Libro> saga           = new HashMap<>();
        HashMap<Integer, Descuento> descuento  = new HashMap<>();
        
        PaperBook pb1 = new PaperBook("100", 200, "H7", 10, 500, "Harry Potter y las Reliquias de la Muerte", 0, 0);
        PaperBook pb2 = new PaperBook("101", 200, "H6", 5, 500, "Harry Potter y el Principe Mestizo", 0, 0);
        PaperBook pb3 = new PaperBook("104", 200, "H1", 1, 200, "Harry Potter y la Piedra filosofal", 0, 0);
        PaperBook pb4 = new PaperBook("102", 200, "H5", 8, 300, "Harry Potter y la Orden del Fenix", 0, 0);
        PaperBook pb5 = new PaperBook("103", 200, "H2", 10, 600, "Harry Potter y la camara Secreta", 0, 0);
        saga.put(1, pb3);
        saga.put(2, pb5);
        saga.put(5, pb4);
        saga.put(6, pb2);
        saga.put(7, pb1);
        pb1.setSaga(saga);
        pb2.setSaga(saga);
        pb3.setSaga(saga);
        pb4.setSaga(saga);
        pb5.setSaga(saga);
        PorSaga ps1 = new PorSaga(, 0)
        descuento.put(0, )
        
        return librosDispo;
    }
}

