/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import control.Libreria;
import dto.EAgregarLibroEnPrestamo;
import dto.IniciarPrestamo;
import dto.ListarLibros;
import entity.EBookImage;
import entity.EBookVideo;
import entity.Libro;
import entity.PaperBook;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author xboxk
 */
public class PantallaKiosco {

    /**
     * @param args the command line arguments
     */
    // Punto 1 
    private Libreria quiosco = new Libreria();

    public static void main(String[] args) {
        // TODO code application logic here
        // agregarLibro(isbn,busquedaSaga(isbn))       
        PantallaKiosco panta = new PantallaKiosco();
        String clave = "1234", cvIngre; // La clave que habilita al 
        // Administrador // punto 9 a 
        int opcion = 0;
        // Punto 1 d: Se inicia el día. 
        panta.quiosco = new Libreria();
        Scanner teclado = new Scanner(System.in);
        System.out.println("Menú de Prestamos de libros en quiosco");
        // punto 2 a 
        System.out.println("1. Iniciar préstamo.");
        // punto 7 a 
        System.out.println("2. Terminar prestamo.");
        // punto 8 a 
        System.out.println("3. Devolver Billetes");
        // punto 9 a 
        System.out.println("4. Reporte diario.");
        System.out.println("5. Salir");
        System.out.println("Digite una opcion");

        opcion = teclado.nextInt();

        while (opcion != 5) {
            switch (opcion) {
                case 1:
                    IniciarPrestamo pres = panta.quiosco.iniciraPrestamo();
                    if (pres.getError() == null) {
                        System.out.println("El prestamo se creo");
                        System.out.println("Con fecha y hora : "
                                + pres.getPres().getFechaHora());
                        //punto 3 b 
                        for (ListarLibros lib : panta.quiosco.listarLibros().values()) {
                            System.out.println(lib.toString());
                        }
                        //Continuación Punto 3 y Punto 4 
                        agregarLibro(panta);

                    } else {
                        System.out.println(pres.getError());
                    }

                    break;

            }

            opcion = 5;

        }
    }

    // punto 4 
    public static void agregarLibro(PantallaKiosco pan) {
        Scanner teclado = new Scanner(System.in);
        String isbn_p = "si";
        Integer isbn_s;
        int acum = 0;
        System.out.println("Agregue el isbn del libro que desea pedir:"
                + "De lo contrario escriba NO");
        isbn_p = teclado.nextLine();

        while (!isbn_p.equals("NO")) {
            Libro lib_pe = pan.quiosco.buscarLibroIsbn(isbn_p);
            Libro libro = construirLibro(pan, lib_pe);
            if (!lib_pe.getSaga().isEmpty()) {
                for (Map.Entry<Integer, Libro> lib_entry : lib_pe.getSaga().entrySet()) {
                        Integer key = lib_entry.getKey();
                        Libro libs = lib_entry.getValue();
                        System.out.print("Código :" + key + " ");
                        System.out.println(libs.toString());
                }
                System.out.println("Si desea agrear un libro de la lista de saga "
                        + "escriba CODIGO de lo contrario -1");
                isbn_s = teclado.nextInt();
                while (isbn_s != -1) {
                    libro.getSaga().put(isbn_s, construirLibro(pan,lib_pe.getSaga().get(isbn_s)));
                    System.out.println("Si desea agregar otro libro escriba el"
                            + " CODIGO. Si no escriba -1");
                    isbn_s = teclado.nextInt();
                }
            }
            //Agregar libro
            EAgregarLibroEnPrestamo errorAgregar = pan.quiosco.agregarLibros(libro);
            
            System.out.println(errorAgregar.toString());
            
            System.out.println("Si desea agregar un nuevo libro escriba el Isbn"
                    + "De lo contrario escriba NO");
            isbn_p = teclado.nextLine();
        }

    }

    public static Libro construirLibro(PantallaKiosco pan, Libro lib_pe) {
        if (lib_pe instanceof PaperBook) {
            PaperBook lib = (PaperBook) lib_pe;
            return new PaperBook(lib.getUbicacion(), lib.getPrecioPapeleria(), lib.getIsbn(), 1, lib.getPrecioBase(), lib.getNombre(), lib.getNumeroImagenes(), lib.getNumeroVideos());
        } else if (lib_pe instanceof EBookImage) {
            EBookImage lib = (EBookImage) lib_pe;
            return new EBookImage(lib.getPrecioPorImagen(), lib.getSitioDescarga(), lib.getIsbn(), 1, lib.getPrecioBase(), lib.getNombre(), lib.getNumeroImagenes(), lib.getNumeroVideos());
        } else if (lib_pe instanceof EBookVideo) {
            EBookVideo lib = (EBookVideo) lib_pe;
            return new EBookVideo(lib.getPrecioPorVideo(), lib.getSitioDescarga(), lib.getIsbn(), 1, lib.getPrecioBase(), lib.getNombre(), lib.getNumeroImagenes(), lib.getNumeroVideos());
        }
        return null;
    }
}
