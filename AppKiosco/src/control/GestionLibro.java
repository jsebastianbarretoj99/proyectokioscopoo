/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import entity.Descuento;
import entity.EBookImage;
import entity.EBookVideo;
import entity.Libro;
import entity.PaperBook;
import entity.PorEBook;
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
        
        PorSaga ps1 = new PorSaga(1, 0.10);
        PorSaga ps2 = new PorSaga(2, 0.05);
        PorSaga ps3 = new PorSaga(3, 0.04);
        PorSaga ps4 = new PorSaga(4, 0.08);
        PorSaga ps5 = new PorSaga(5, 0.03);
        
        descuento.put(0,ps1);
        descuento.put(1,ps2);
        descuento.put(2,ps3);
        descuento.put(3,ps4);
        descuento.put(4,ps5);
        
        pb1.setDescuentos(descuento);
        pb2.setDescuentos(descuento);
        pb3.setDescuentos(descuento);
        pb4.setDescuentos(descuento);
        pb5.setDescuentos(descuento);
        
        librosDispo.put(pb1.getIsbn(), pb1);
        librosDispo.put(pb2.getIsbn(), pb2);
        librosDispo.put(pb3.getIsbn(), pb3);
        librosDispo.put(pb4.getIsbn(), pb4);
        librosDispo.put(pb5.getIsbn(), pb5);
        
        saga.clear();
        descuento.clear();
        
        // Solo un libro 
        PaperBook pb6 = new PaperBook("110", 300, "L0", 6, 600, "Los Miserables", 0, 0);
        PaperBook pb7 = new PaperBook("120", 400, "O0", 10, 300, "Orgullo y Prejuicio", 0, 0);
        PaperBook pb8 = new PaperBook("130", 100, "O0", 10, 300, "La isla bajo el mar", 0, 0);
        
        librosDispo.put(pb6.getIsbn(), pb6);
        librosDispo.put(pb7.getIsbn(), pb7);
        librosDispo.put(pb8.getIsbn(), pb8);
       
        
        // EbookImage 
        EBookImage eb1 = new EBookImage(100,"www.sagadiver.com", "200", 10, 50, "Divergente", 15, 0);
        EBookImage eb2 = new EBookImage(100,"www.sagadiver.com", "201", 6, 50, "Insurgente", 20, 0);
        EBookImage eb3 = new EBookImage(100,"www.sagadiver.com", "202", 9, 50, "Allegiant", 25, 0);
        EBookImage eb4 = new EBookImage(100,"www.sagadiver.com", "203", 3, 30, "Cuatro", 18, 0);
        
        saga.put(1, eb1);
        saga.put(2, eb2);
        saga.put(3, eb3);
        saga.put(4, eb4);
        
        eb1.setSaga(saga);
        eb2.setSaga(saga);
        eb3.setSaga(saga);
        eb4.setSaga(saga);
        
        PorSaga es1 = new PorSaga(1, 0.20);
        PorSaga es2 = new PorSaga(2, 0.25);
        PorSaga es3 = new PorSaga(3, 0.05);
        PorSaga es4 = new PorSaga(4, 0.10);
        
        PorEBook pe1 = new PorEBook(0.10);
        PorEBook pe2 = new PorEBook(0.15);
        PorEBook pe3 = new PorEBook(0.08);
        PorEBook pe4 = new PorEBook(0.20);
        
        descuento.put(0, es1);
        descuento.put(1, es2);
        descuento.put(2, es3);
        descuento.put(3, es4);
        descuento.put(4, pe1);
        descuento.put(5, pe2);
        descuento.put(6, pe3);
        descuento.put(7, pe4);
        
        eb1.setDescuentos(descuento);
        eb2.setDescuentos(descuento);
        eb3.setDescuentos(descuento);
        eb4.setDescuentos(descuento);
        
        librosDispo.put(eb1.getIsbn(), eb1);
        librosDispo.put(eb2.getIsbn(), eb2);
        librosDispo.put(eb3.getIsbn(), eb3);
        librosDispo.put(eb4.getIsbn(), eb4);    
        
        descuento.clear();
        saga.clear();
        
        // por EBookIamge
        EBookImage eb5 = new EBookImage(200,"www.circuitos.com", "210", 10, 100, "El último adios", 11, 0);
        EBookImage eb6 = new EBookImage(50,"www.sagadiver.com", "220", 12, 50, "La chica del tren ", 5, 0);
        EBookImage eb7 = new EBookImage(150,"www.sagadiver.com", "230", 4, 50, "Bajo la misma estrella", 8, 0);
        
        PorEBook ebi5 = new PorEBook(0.20);
        PorEBook ebi6 = new PorEBook(0.25);
        PorEBook ebi7 = new PorEBook(0.30);
        
        descuento.put(0, ebi5);
        librosDispo.put(eb5.getIsbn(), eb5);
        descuento.clear();
        
        descuento.put(0, ebi6);
        librosDispo.put(eb6.getIsbn(), eb6);
        descuento.clear();
        
        descuento.put(0, ebi7);
        librosDispo.put(eb7.getIsbn(), eb7);
        descuento.clear();     
        
        //Por EBookVideo   
        EBookVideo ev1 = new EBookVideo(100, "www.tusvideos.com","300", 15, 5000, "El codigo Da Vinci", 0, 12 );
        EBookVideo ev2 = new EBookVideo(200, "www.tusvideos.com","301", 15, 2500, "Angeles y demonios ", 0, 4);
        EBookVideo ev3 = new EBookVideo(400, "www.tusvideos.com","302", 10, 6000, "Inferno ", 0, 8 );
        
        saga.put(1, ev1);
        saga.put(2, ev2);
        saga.put(3, ev3);
        
        ev1.setSaga(saga);
        ev2.setSaga(saga);
        ev3.setSaga(saga);
        
        PorSaga esv1 = new PorSaga(1, 0.30);
        PorSaga esv2 = new PorSaga(2, 0.15);
        PorSaga esv3 = new PorSaga(3, 0.09);
        
        PorEBook pev1 = new PorEBook(0.30);
        PorEBook pev2 = new PorEBook(0.15);
        PorEBook pev3 = new PorEBook(0.35);
        
        descuento.put(0, esv1);
        descuento.put(1, esv2);
        descuento.put(2, esv3);
        descuento.put(3, pev1);
        descuento.put(4, pev2);
        descuento.put(5, pev3);
        
        ev1.setDescuentos(descuento);
        ev2.setDescuentos(descuento);
        ev3.setDescuentos(descuento);
        
        librosDispo.put(ev1.getIsbn(), ev1);
        librosDispo.put(ev2.getIsbn(), ev2);
        librosDispo.put(ev3.getIsbn(), ev3);  
        
        descuento.clear();
        saga.clear();
        
        //Por EBookVideo 
        EBookVideo ev4 = new EBookVideo(300, "www.ninos.com","310", 5, 10000, "El principito", 0, 20 );
        EBookVideo ev5 = new EBookVideo(250, "www.codigofacil.com","320", 10, 3000, "Muy facil ", 0, 15);
        
        PorEBook ebv4 = new PorEBook(0.40);
        PorEBook ebv5 = new PorEBook(0.05);
        
        descuento.put(0, ebv4);
        librosDispo.put(ev4.getIsbn(), ev4);
        descuento.clear();
        
        descuento.put(0, ebv5);
        librosDispo.put(ev5.getIsbn(), ev5);
        
        descuento.clear();
        saga.clear();
        
        return librosDispo;
    }
}

