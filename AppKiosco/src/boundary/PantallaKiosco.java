/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import control.Libreria;
import dto.AcabarPrestamo;
import dto.IniciarPrestamo;
import dto.ListarLibros;
import dto.PagoPrestamo;
import entity.Billete;
import entity.Libro;
import enumaration.Denominacion;
import java.util.ArrayList;
import java.util.HashMap;
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
        AcabarPrestamo acabP;
        int opcion = 0;
        boolean ver = true, noTermino = false; 
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
                        listarBilletes(panta);
                        introducirBilletes(panta);
                        System.out.println("Termino el ingreso de los billetes");
                    } else {
                        System.out.println(pres.getError());
                    }
                    
                    break;
                
                case 2:    //punto 7 a
                    
                    if (ver) {
                        System.out.println("No se ha iniciado un prestamo.");
                    } else {
                        acabP = panta.quiosco.terminarPrestamo();
                        noTermino = true;
                        // punto 7 b V
                        acabP = panta.quiosco.terminarPrestamo();
                        System.out.println("Acabar Prestamo:");
                        System.out.println("Error = " + acabP.getError());
                        System.out.println("Total de libros del prestamo = " + acabP.getNumeroTotalLibros());
                        System.out.println("Valor total del prestamos = " + acabP.getValorTPrestamo());
                        System.out.println("Total introducido de billtes = " + acabP.getTotalIntroBilletes());
                        System.out.println("Total de las vueltas = " + acabP.getValorTVueltas());

                        while ("El dinero ingresado no cubre el valor del prestamo".equals(acabP.getError())) {
                            // punto 6 a III
                            introducirBilletes(panta);
                            System.out.println("Finalizó el ingreso de Billetes");
                            // punto 7 b V
                            acabP = panta.quiosco.terminarPrestamo();
                            System.out.println("Acabar Prestamo:");
                            System.out.println("Error = " + acabP.getError());
                            System.out.println("Total de libros del prestamo = " + acabP.getNumeroTotalLibros());
                            System.out.println("Valor total del prestamos = " + acabP.getValorTPrestamo());
                            System.out.println("Total introducido de billtes = " + acabP.getTotalIntroBilletes());
                            System.out.println("Total de las vueltas = " + acabP.getValorTVueltas());

                        }
                        ver = true;
                    }

                    break;

            }

            opcion = 5;

        }
    }

    // punto 4 
    public static void agregarLibro(PantallaKiosco pan) {
        Scanner teclado = new Scanner(System.in);
        String isbn_p = "si", isbn_s;
        int acum = 0;
        HashMap<Integer, String> isbn_saga = new HashMap();

        System.out.println("Agregue el isbn del libro que desea pedir:"
                + "De lo contrario escriba NO");
        isbn_p = teclado.nextLine();

        while (!isbn_p.equals("NO")) {
            System.out.println("Si desea agrear un libro de la lista de saga "
                    + "escriba SI de lo contario NO");
            isbn_s = teclado.nextLine();
            while (!isbn_s.equals("NO")) {
                for (Libro lib : pan.quiosco.getLibrosDisponibles().get(isbn_p).getSaga().values()) {
                    System.out.println(lib.toString());
                }
                System.out.println("Escriba el isbn del libro de saga que desea"
                        + " agregar al prestamo. Si no desea agregar mas escriba NO");
                isbn_s = teclado.nextLine();
                if (!isbn_s.equals("NO")) {
                    isbn_saga.put(acum, isbn_s);
                    acum++;
                }
            }
            //Agregar libro
            //pan.quiosco.agregarLibro(isbn_p, isbn_saga );

            System.out.println("Si desea agregar un nuevo libro escriba el Isbn"
                    + "De lo contrario escriba NO");
            isbn_p = teclado.nextLine();
        }

    }

    //Punto 5 
    public static void listarBilletes(PantallaKiosco panta) {
        HashMap<Integer, Denominacion> listaBilletes;
        listaBilletes = panta.quiosco.listarBillete();
        System.out.println("Numeraciones posibles para introducir :");
        for (Denominacion bil : listaBilletes.values()) {
            System.out.println(bil.getValor());
        }
    }

    public static void introducirBilletes(PantallaKiosco panta) {
        PagoPrestamo p = new PagoPrestamo();
        Scanner teclado = new Scanner(System.in);
        int dem_p;
        System.out.println("Ingrese una nominación del billete, finalice con '-1' ");
        dem_p = teclado.nextInt();
        while (dem_p == -1) {
            System.out.println("No ha ingresado Billetes");
            System.out.println("Ingrese una nominación del billete, finalice con '-1' ");
            dem_p = teclado.nextInt();
        }
        while (dem_p != -1) {
            Denominacion dbillete;
            dbillete.setValor(100000);
            p = panta.quiosco.introducirBillete(Denominacion.DIEZMIL);
            if (p.getTotalIntro() == 0.0) {
                System.out.println("La denominacion no es valida");
            } else {
                System.out.println(p.toString());
            }
            
            System.out.println("Ingrese una nominación del billete, finalice con '-1' ");
            dem_p = teclado.nextInt();
        }
    }

    public static Denominacion introducirNumeracion(int valor) {
        switch (valor) {
                case 100000:
                    return Denominacion.CIENMIL;
                    
                case 50000:
                    
                    return Denominacion.CICUENTAMIL;
                case 20000:
                    return Denominacion.VEINTEMIL;
                case 10000:
                    return Denominacion.DIEZMIL;
            }
        return null;
    }
}
