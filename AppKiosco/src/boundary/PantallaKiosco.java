package boundary;

import control.Libreria;
import dto.AcabarPrestamo;
import dto.PagoPrestamo;
import enumaration.Denominacion;
import java.util.HashMap;
import dto.EAgregarLibroEnPrestamo;
import dto.IniciarPrestamo;
import dto.ListarLibros;
import dto.ReporteDiario;
import dto.ReporteLibroDiario;
import entity.Billete;
import entity.EBookImage;
import entity.EBookVideo;
import entity.Libro;
import entity.PaperBook;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public class PantallaKiosco {

    // Punto 1 
    private Libreria quiosco = new Libreria();

    public static void main(String[] args) {
        // TODO code application logic here
        // agregarLibro(isbn,busquedaSaga(isbn))       
        PantallaKiosco panta = new PantallaKiosco();
        String clave = "1234", cvIngre; // La clave que habilita al 
        // Administrador // punto 9 a 
        AcabarPrestamo acabP;
        ReporteDiario rP = new ReporteDiario();
        int opcion;
        boolean ver = true, noTermino = false, prestamoUno = false, vern = true;
        // Punto 1 d: Se inicia el día. 
        panta.quiosco = new Libreria();
        Scanner teclado = new Scanner(System.in);
        System.out.println("Menú de Prestamos de libros en quiosco");
        // punto 2 a 
        System.out.println("1. Iniciar préstamo.");
        // punto 7 a 
        System.out.println("2. Terminar prestamo.");
        // punto 8 a 
        System.out.println("3. Reporte diario.");
        System.out.println("4. Salir");
        System.out.println("Digite una opcion");
        opcion = teclado.nextInt();
        while (opcion != 4) {
            switch (opcion) {
                case 1:
                    IniciarPrestamo pres = panta.quiosco.iniciraPrestamo();
                    if (pres.getError() == null) {
                        System.out.println("El prestamo se creo");
                        System.out.println("Con fecha: "
                                + pres.getPres().getFechaHora());
                        //punto 3 b 
                        for (ListarLibros lib : panta.quiosco.listarLibros().values()) {
                            System.out.println(lib.toString());
                        }
                        //Continuación Punto 3 y Punto 4 
                        agregarLibro(panta);

                        listarBilletes(panta);
<<<<<<< HEAD
                        //introducirBilletes(panta);
=======
                        introducirBilletes(panta);
>>>>>>> 35c9e0075ebcbe9f47f4b8c3469aa088197d29ad
                        System.out.println("Termino el ingreso de los billetes");

                    } else {
                        System.out.println(pres.getError());
                    }

                    break;

                case 2:
                    //punto 7 a
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

                case 3:
                    if (prestamoUno) {
                        System.out.println("Reporte Diario");
                        System.out.println("Valor total prestamo del dia: " + rP.getValorPrestamoD());
                        while (vern) {
                            teclado = new Scanner(System.in);
                            System.out.println("Digite la clave de administracion");
                            cvIngre = teclado.nextLine();
                            if (clave.equals(cvIngre)) {
                                System.out.println("Reporte diario");
                                rP = panta.quiosco.generarReporte();
                                System.out.println("Valor total de prestamos del dia = " + rP.getValorPrestamoD());
                                System.out.println("Reporte de libros prestados= ");
                                for (ReporteLibroDiario rp : rP.getReporteD().values()) {
                                    System.out.println(rp.getTipo() + " " + rp.getCantidadPrestamo() + " " + rp.getPreciPrestamo());
                                }
                                System.out.println("Libros no vendidos");
                                System.out.print("PaperBook  cantidad:");
                                System.out.println(rP.getLibrosNoVendidos().get("PaperBook"));
                                System.out.print("EBook  cantidad:");
                                System.out.println(rP.getLibrosNoVendidos().get("EBook"));

                                System.out.println("Sitios de descarga");
                                for (String S : rP.getSitiosDescarga().values()) {
                                    System.out.println(S);
                                }

                                vern = false;
                            } else if ("NO".equals(cvIngre)) {
                                vern = false;
                            } else {
                                System.out.println("Clave incorrecta");
                            }
                            System.out.println("Digite la clave de administracion: (Si se quiere salir ingrese NO)");
                        }//fdfd
                    } else {
                        System.out.println("No hay prestamos hoy");
                    }
                    break;
                
                default:
                    System.out.println("No ingresaste una opción valida");
                    break;
            }
<<<<<<< HEAD
            teclado = new Scanner(System.in);
=======
>>>>>>> 35c9e0075ebcbe9f47f4b8c3469aa088197d29ad
            System.out.println("Menú de Prestamos de libros en quiosco");
            // punto 2 a 
            System.out.println("1. Iniciar préstamo.");
            // punto 7 a 
            System.out.println("2. Terminar prestamo.");
            // punto 8 a 
<<<<<<< HEAD
            System.out.println("3. Reporte diario.");
            System.out.println("4. Salir");
            System.out.println("Digite una opcion");
            opcion = teclado.nextInt();
=======
            System.out.println("3. Devolver Billetes");
            // punto 9 a 
            System.out.println("4. Reporte diario.");
            System.out.println("5. Salir");
            System.out.println("Digite una opcion");
            opcion = teclado.nextInt();

>>>>>>> 35c9e0075ebcbe9f47f4b8c3469aa088197d29ad
        }
    }

    // punto 4 
    public static void agregarLibro(PantallaKiosco pan) {
        Scanner teclado = new Scanner(System.in);
        String isbn_p;
        Integer isbn_s;
        System.out.println("Agregue el isbn del libro que desea pedir:"
                + "De lo contrario escriba NO");
        isbn_p = teclado.nextLine();
        while (!isbn_p.equals("NO")) {
            Libro lib_pe = pan.quiosco.buscarLibroIsbn(isbn_p);
            Libro libro = construirLibro(pan, lib_pe);
            if (libro != null) {
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
                        libro.getSaga().put(isbn_s, construirLibro(pan, lib_pe.getSaga().get(isbn_s)));
                        System.out.println("Si desea agregar otro libro escriba el"
                                + " CODIGO. Si no escriba -1");
                        isbn_s = teclado.nextInt();
                    }
                }
            }
            //Agregar libro
            EAgregarLibroEnPrestamo errorAgregar = pan.quiosco.agregarLibros(libro);
            System.out.println(errorAgregar.toString());
            System.out.println("Si desea agregar un nuevo libro escriba el Isbn"
                    + "De lo contrario escriba NO");
            teclado = new Scanner(System.in);
            isbn_p = teclado.nextLine();
        }

    }
    //Punto 5 

    public static void listarBilletes(PantallaKiosco panta) {
        HashMap<Integer, Denominacion> listaBilletes;
        listaBilletes = panta.quiosco.listarBillete();
        System.out.println("Numeraciones posibles para introducir :");
        for (Denominacion dem : listaBilletes.values()) {
            System.out.println(dem);
        }
    }

    //punto 6 
    public static void introducirBilletes(PantallaKiosco panta) {
        PagoPrestamo p;
        Scanner teclado = new Scanner(System.in);
        String dem_p;
        System.out.println("Ingrese una nominación del billete, finalice con '-1' ");
        dem_p = teclado.nextLine();
        while (dem_p.equals("-1")) {
            System.out.println("No ha ingresado Billetes");
            System.out.println("Ingrese una nominación del billete, finalice con '-1' ");
            dem_p = teclado.nextLine();
        }
        while (!dem_p.equals("-1")) {
            HashMap<Denominacion, Billete> pBil;
            p = panta.quiosco.introducirBillete(introducirNumeracion(dem_p));
            pBil = p.getPagoBillete();
            System.out.println("Billetes Ingresados:");
            System.out.println(pBil.keySet());
            System.out.println(p.toString());
            System.out.println("Ingrese una nominación del billete, finalice con '-1' ");
            dem_p = teclado.nextLine();
        }
    }

    public static Denominacion introducirNumeracion(String valor) {
        switch (valor) {
            case "CIENMIL":
                return Denominacion.CIENMIL;
            case "CINCUENTAMIL":
                return Denominacion.CICUENTAMIL;
            case "VEINTEMIL":
                return Denominacion.VEINTEMIL;
            case "DIEZMIL":
                return Denominacion.DIEZMIL;
        }
        return null;
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
