/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dto.AcabarPrestamo;
import dto.IniciarPrestamo;
import dto.EAgregarLibroEnPrestamo;
import dto.ListarLibros;
import dto.PagoPrestamo;
import entity.Billete;
import entity.Descuento;
import entity.EBookImage;
import entity.EBookVideo;
import entity.Libro;
import entity.PaperBook;
import entity.PorEBook;
import entity.PorSaga;
import entity.Prestamo;
import enumaration.Denominacion;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author vale-
 */
public class Libreria {

    public GestionLibro gestion = new GestionLibro();
    public Prestamo prestamoActual;
    public HashMap< Denominacion, Billete> dineroAcumulado;
    public HashMap< Integer, Prestamo> prestamos;
    public HashMap< String, Libro> librosDisponibles;

    // 1 c 
    public Libreria() {
        crearColeccionLibros();
        crearColeccionBilletes();
    }

    // 1 a II
    private void crearColeccionLibros() {
        this.librosDisponibles = this.gestion.crearColeccionLibro();
    }

    // 1 b II
    private void crearColeccionBilletes() {
        this.dineroAcumulado = new HashMap();
        this.dineroAcumulado.put(Denominacion.CIENMIL, new Billete(100, Denominacion.CIENMIL));
        this.dineroAcumulado.put(Denominacion.CICUENTAMIL, new Billete(100, Denominacion.CICUENTAMIL));
        this.dineroAcumulado.put(Denominacion.VEINTEMIL, new Billete(100, Denominacion.VEINTEMIL));
        this.dineroAcumulado.put(Denominacion.DIEZMIL, new Billete(100, Denominacion.DIEZMIL));
    }

    //punto 2
    public IniciarPrestamo iniciraPrestamo() {
        LocalDate ahora = LocalDate.now();
        IniciarPrestamo acab = new IniciarPrestamo();
        if (unidadesDisponiblesLibros()) {
            if (prestamos.isEmpty()) {
                prestamos.put(0, new Prestamo(ahora, 1));
            } else {
                prestamos.put(prestamos.size(), new Prestamo(ahora, prestamos.size() + 1));
            }

            prestamoActual = prestamos.get(prestamos.size() - 1);

            acab.setError(null);
            acab.setPres(prestamoActual);
            return acab;
        } else {
            acab.setError("No existen Unidades disponibles de ningún libro");
            acab.setPres(null);
            return acab;
        }

    }

    // 2 b 4 1
    private boolean unidadesDisponiblesLibros() {
        int acom = 0;
        for (Libro lib : this.librosDisponibles.values()) {
            acom += lib.getUnidadesDisponibles();
        }
        if (acom > 0) {
            return true;
        } else {
            return false;
        }
    }

    // 3 
    public HashMap<String, ListarLibros> listarLibros() {
        HashMap<String, ListarLibros> lista = new HashMap<>();
        ListarLibros ob = new ListarLibros();
        for (Libro lib : this.librosDisponibles.values()) {
            ob.setIsbn(lib.getIsbn());
            ob.setNombre(lib.getNombre());
            //ob.setPrecio(precioTotal(ob));    REVISARRR
            if (lib instanceof PaperBook) {
                ob.setTipo("PB");
            } else if (lib instanceof EBookImage) {
                ob.setTipo("EBI");
            } else if (lib instanceof EBookVideo) {
                ob.setTipo("EBV");
            }
            lista.put(lib.getIsbn(), ob);
        }
        return lista;
    }

    //Punto 4 
    public HashMap<String, Libro> buscarSaga(String isbn) {
        Libro lib = this.librosDisponibles.get(isbn);
        return lib.getSaga();
    }

    // Punto 4 
    public EAgregarLibroEnPrestamo agregarLibro(String isbn, HashMap<String, Libro> saga) {
        EAgregarLibroEnPrestamo errorAgregar = new EAgregarLibroEnPrestamo();
        Libro lib = buscarLibroIsbn(isbn);
        double precioT = 0, totalDescuentos = 0;
        // punto 4 a I
        if (lib != null) {
            // punto 4 a II
            if (unidadesDisponiblesLibros(isbn)) {
                // punto 4 a III
                this.prestamoActual.librosEnPrestamo.put(lib.getIsbn(), lib);
                // punto 4 a IV c I
                precioT = librosSagaPrestamo(lib.getIsbn());
                for (Descuento des : lib.getDescuentos().values()) {
                    if (des instanceof PorSaga) {
                        PorSaga sa = (PorSaga) des;
                        totalDescuentos += des.calcularTotal(precioT);
                    } else if (des instanceof PorEBook) {
                        PorEBook sa = (PorEBook) des;
                        totalDescuentos += des.calcularTotal(precioT);
                    }
                }
                precioT -= totalDescuentos;
            } else {
                errorAgregar.setError("No hay unidades suficientes para el prestamo del libro solicitado");
            }
            errorAgregar.setTotalLibrosSaga(totalLibrosSaga(isbn));
            errorAgregar.setValorLibrosSaga(precioTotalSaga(isbn));
        } else {
            errorAgregar.setError("El libro solicitado no existe");
            errorAgregar.setTotalLibrosSaga(0);
            errorAgregar.setValorLibrosSaga(0);
        }
        errorAgregar.setTotalLibros(totalLibrosPrestamo());
        errorAgregar.setValorTotalPrestamo(valorTAcumulado());
        return errorAgregar;
    }

    //punto 4 a I 1 
    private Libro buscarLibroIsbn(String isbn_p) {
        for (Libro lib : this.librosDisponibles.values()) {
            if (isbn_p.equals(lib.getIsbn())) {
                return lib;
            }
        }
        return null;
    }

    // punto 4 I 2
    private boolean unidadesDisponiblesLibros(String isbn) {
        Libro lib = this.librosDisponibles.get(isbn);
        if (lib.getUnidadesDisponibles() > this.prestamoActual.librosEnPrestamo.get(isbn).getUnidadesDisponibles()) {
            return true;
        }
        return false;
    }

    // punto 4 a IV c I
    private double librosSagaPrestamo(String isbn) {
        double acum = 0;
        if (!buscarSaga(isbn).isEmpty()) {
            for (Libro lib : buscarSaga(isbn).values()) {
                for (Libro lib2 : this.prestamoActual.librosEnPrestamo.values()) {
                    if (lib2.getIsbn().equals(lib.getIsbn())) {
                        acum += usePrecioTotal(lib);
                    }
                }
            }
        } else {
            acum += usePrecioTotal(buscarLibroIsbn(isbn));
        }
        return acum;
    }

    // punto 4 a V 2 a
    private int totalLibrosPrestamo() {
        return this.prestamoActual.librosEnPrestamo.size();
    }

    // punto 4 a V 3 a
    private int totalLibrosSaga(String isbn) {
        return this.librosDisponibles.get(isbn).getSaga().size();
    }

    // punto 4 a V 4 a
    private double precioTotalSaga(String isbn) {
        double acom = 0;
        for (Libro lib : buscarSaga(isbn).values()) {
            acom += usePrecioTotal(lib);
        }
        return acom;
    }

    private double usePrecioTotal(Libro lib) {
        if (lib instanceof PaperBook) {
            PaperBook auxL = (PaperBook) lib;
            return auxL.precioTotal();
        } else if (lib instanceof EBookVideo) {
            EBookVideo auxL = (EBookVideo) lib;
            return auxL.precioTotal();
        } else {
            EBookImage auxL = (EBookImage) lib;
            return auxL.precioTotal();
        }
    }

    // punto 4 a V 5 a ; Punto 6 a II 3 a
    private double valorTAcumulado() {
        double acumulado = 0;
        for (Libro lib : this.prestamoActual.librosEnPrestamo.values()) {
            acumulado += usePrecioTotal(lib);
        }
        return acumulado;
    }

    // punto 5 
    public HashMap<Integer, Denominacion> listarBillete() {
        HashMap<Integer, Denominacion> deno = new HashMap<>();
        for (Billete bil : this.dineroAcumulado.values()) {
            deno.put(deno.size(), bil.getDenominacion());
        }
        return deno;
    }

    //punto 6 
    public PagoPrestamo introducirBillete(Denominacion demo) {
        Billete bil;
        PagoPrestamo pago = new PagoPrestamo();
        int tam;
        // 6 a I 1  
        if (buscarDenominacio(demo, this.dineroAcumulado)) {
            //6 a I 2
            if (buscarDenominacio(demo, this.prestamoActual.pagoBillete)) {
                bil = this.prestamoActual.pagoBillete.get(demo);
                bil.setCantidad(bil.getCantidad() + 1);
            } else {
                this.prestamoActual.pagoBillete.put(demo, new Billete(1, demo));
            }
        }

        //6 a II 1 
        pago.setPagoBillete(this.prestamoActual.pagoBillete);
        // 6 a II 2
        pago.setTotalIntro(totalIntroducido(this.prestamoActual.pagoBillete));
        // 6 a II 3
        pago.setValorPrestamo(valorTAcumulado());
        // 6 a II 4
        pago.setSaldoFaltante(saldoFaltante());

        return pago;
    }

    //6 a I 1 
    private boolean buscarDenominacio(Denominacion demo, HashMap<Denominacion, Billete> lista) {
        return lista.containsKey(demo);

    }

    // 6 a II 2 a 
    private double totalIntroducido(HashMap<Denominacion, Billete> listaBilletes) {
        double total = 0;
        for (Billete bil : listaBilletes.values()) {
            total += (bil.getCantidad() * bil.getDenominacion().getValor());
        }
        return total;
    }

    // 6 a II 4 a 
    private double saldoFaltante() {
        return totalIntroducido(this.prestamoActual.pagoBillete) - valorTAcumulado();
    }

    //Punto 7
    private AcabarPrestamo terminarPrestamo() {

        AcabarPrestamo acabar = new AcabarPrestamo();
        //punto 7 b I 1
        if (this.verificarVueltas(this.listaBill())) {
            //punto 7 b II 1    
            if (totalIntroducido(dineroAcumulado) >= saldoFaltante()) {
                // punto 7 b III 1 
                actualizarExistenciaLibro();

                // punto 7 b IV 1 
                acabar.setError(null);
                // punto 7 b IV 2
                acabar.setNumeroTotalPrestamo(this.totalLibrosPrestamo());
                //Punto 7 b IV 3
                acabar.setValorTPrestamo(this.valorTAcumulado());
                // punto 7 b IV 4
                acabar.setTotalIntroBilletes(this.numeroIntroducidoBilletes(this.prestamoActual.pagoBillete));
                // punto 7 b IV 5
                acabar.setValorTVueltas(this.saldoFaltante());
                // punto 7 b III 6
                this.actualizarBilletes();
            } else {
                // punto 7 b IV 1 
                acabar.setError("no hay dinero suficiente para devolver");
            }
        } else {
            // punto 7 b IV 1 
            acabar.setError("no ingreso el dinero suficiente");
        }
        return acabar;
    }

    //Punto 7 b III a 
    public void actualizarExistenciaLibro() {
        Libro lib1;
        for (Libro lib : this.prestamoActual.librosEnPrestamo.values()) {
            lib1 = buscarLibroIsbn(lib.getIsbn());
            lib1.setUnidadesDisponibles(lib1.getUnidadesDisponibles() - 1);
            for (Libro ob : lib1.getSaga().values()) {
                ob.setUnidadesDisponibles(ob.getUnidadesDisponibles() - 1);
            }

        }
    }
//actualizar Billetes retorna lista de billetes de vueltas. 7 b III 2
    public HashMap<Denominacion, Billete> listaBill() {

        double vuel = saldoFaltante();
        HashMap<Denominacion, Billete> bilvuel = new HashMap<>();

        if (vuel > 0) {

            bilvuel = calculaVueltas((int) vuel);
        }
        return bilvuel;
    }
//7 bII 
    private boolean verificarVueltas(HashMap<Denominacion, Billete> acabarVueltas) {
        Billete bil2;
        for (Billete bil : acabarVueltas.values()) {
            bil2 = buscarDenominacion(bil, this.dineroAcumulado);
            if (bil2.getCantidad() < bil.getCantidad()) {
                return false;
            }
        }
        return true;
    }

    private Billete buscarDenominacion(Billete bil, HashMap<Denominacion, Billete> bil_p) {
        for (Billete bill : bil_p.values()) {
            if (bil.getDenominacion().getValor() == bill.getDenominacion().getValor()) {
                return bill;
            }
        }
        return null;
    }

    private HashMap<Denominacion, Billete> calculaVueltas(int vuel) {
        HashMap<Denominacion, Billete> vueltas = new HashMap<>();
        Billete b1 = new Billete();

        if (vuel > 0) {
            vuel = numBillete(b1.denominacion.CIENMIL, vuel, vueltas);
            vuel = numBillete(b1.denominacion.CICUENTAMIL, vuel, vueltas);
            vuel = numBillete(b1.denominacion.VEINTEMIL, vuel, vueltas);
            vuel = numBillete(b1.denominacion.DIEZMIL, vuel, vueltas);

            return vueltas;
        } else {
            return null;
        }
    }

    private int numBillete(Denominacion consT, int vuel, HashMap<Denominacion, Billete> vueltas) {
        Billete b1 = new Billete();
        if (vuel >= (int) consT.getValor()) {
            b1.setCantidad(vuel / (int) consT.getValor());
            b1.setDenominacion(consT);
            vuel = vuel - ((int) b1.denominacion.getValor() * b1.getCantidad());
            vueltas.put(consT, b1);
        }
        return vuel;
    }
    // punto 7 b III 2

    private void actualizarBilletes() {

        for (Billete bill : this.prestamoActual.pagoBillete.values()) {
            for (Billete bill2 : this.dineroAcumulado.values()) {
                if (bill2.denominacion.getValor() == bill.denominacion.getValor()) {
                    bill2.setCantidad(bill2.getCantidad() + bill.getCantidad());

                }
            }
        }
        for (Billete bill : this.listaBill().values()) {
            for (Billete bill2 : this.dineroAcumulado.values()) {
                if (bill2.denominacion.getValor() == bill.denominacion.getValor()) {
                    bill2.setCantidad(bill2.getCantidad() - bill.getCantidad());

                }
            }
        }
        this.prestamoActual.pagoBillete.clear();
    }

    private int numeroIntroducidoBilletes(HashMap<Denominacion, Billete> listaBilletes) {
        int cantidad = 0;
        for (Billete bill : listaBilletes.values()) {
            cantidad += bill.getCantidad();
        }
        return cantidad;
    }

}
