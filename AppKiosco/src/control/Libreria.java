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
import dto.ReporteDiario;
import dto.ReporteLibroDiario;
import entity.Billete;
import entity.Descuento;
import entity.EBook;
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

    private GestionLibro gestion = new GestionLibro();
    private Prestamo prestamoActual;
    private HashMap< Denominacion, Billete> dineroAcumulado;
    private HashMap< Integer, Prestamo> prestamos;
    private HashMap< String, Libro> librosDisponibles;

    // 1 c 
    public Libreria() {
        crearColeccionLibros();
        crearColeccionBilletes();
    }

    public GestionLibro getGestion() {
        return gestion;
    }

    public void setGestion(GestionLibro gestion) {
        this.gestion = gestion;
    }

    public Prestamo getPrestamoActual() {
        return prestamoActual;
    }

    public void setPrestamoActual(Prestamo prestamoActual) {
        this.prestamoActual = prestamoActual;
    }

    public HashMap<Denominacion, Billete> getDineroAcumulado() {
        return dineroAcumulado;
    }

    public void setDineroAcumulado(HashMap<Denominacion, Billete> dineroAcumulado) {
        this.dineroAcumulado = dineroAcumulado;
    }

    public HashMap<Integer, Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(HashMap<Integer, Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public HashMap<String, Libro> getLibrosDisponibles() {
        return librosDisponibles;
    }

    public void setLibrosDisponibles(HashMap<String, Libro> librosDisponibles) {
        this.librosDisponibles = librosDisponibles;
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
    public HashMap<Integer, Libro> buscarSaga(String isbn) {
        Libro lib = this.librosDisponibles.get(isbn);
        return lib.getSaga();
    }

    // Punto 4 
    public EAgregarLibroEnPrestamo agregarLibro(String isbn, HashMap<Integer, Libro> saga) {
        EAgregarLibroEnPrestamo errorAgregar = new EAgregarLibroEnPrestamo();
        Libro lib = buscarLibroIsbn(isbn);
        lib.setSaga(saga);
        // punto 4 a I
        if (lib != null) {
            // punto 4 a II
            if (unidadesDisponiblesLibros(isbn)) {
                // punto 4 a III
                this.prestamoActual.getLibrosEnPrestamo().put(lib.getIsbn(), lib);
                // punto 4 a IV c I
                errorAgregar.setValorLibrosSaga(totalSaga(saga));
            } else {
                errorAgregar.setError("No hay unidades suficientes para el prestamo del libro solicitado");
                errorAgregar.setValorLibrosSaga(0);
            }
        } else {
            errorAgregar.setError("El libro solicitado no existe");
            errorAgregar.setValorLibrosSaga(0);
        }
        errorAgregar.setTotalLibros(totalLibrosPrestamo());
        errorAgregar.setTotalLibrosSaga(totalLibrosSaga(lib));
        errorAgregar.setValorTotalPrestamo(totalPrestamo());
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
        if (lib.getUnidadesDisponibles() > this.prestamoActual.getLibrosEnPrestamo().get(isbn).getUnidadesDisponibles()) {
            return true;
        }
        return false;
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

    private boolean buscarLibroSaga(int key, HashMap<Integer, Libro> saga) {
        return saga.containsKey(key);
    }

    private double sumaDescuentos(Libro lib, double precioT) {
        double totalDescuentos = 0;
        for (Descuento des : lib.getDescuentos().values()) {
            if (des instanceof PorSaga) {
                PorSaga sa = (PorSaga) des;
                if (buscarLibroSaga(sa.getNumeroSaga(), lib.getSaga())) {
                    totalDescuentos += sa.calcularTotal(precioT, lib.getSaga());
                }
            } else if (des instanceof PorEBook) {
                PorEBook eb = (PorEBook) des;
                totalDescuentos += eb.calcularTotal(precioT, lib.getSaga());
            }
        }
        return totalDescuentos;
    }

    // punto 4 a V 2 a
    private int totalLibrosPrestamo() {
        int tot = 0;
        for (Libro lib : this.prestamoActual.getLibrosEnPrestamo().values()) {
            tot += (lib.getSaga().size() + 1);
        }
        return tot;
    }

    // punto 4 a V 3 a
    private int totalLibrosSaga(Libro libro) {
        return libro.getSaga().size();
    }

    private double totalSaga(HashMap<Integer, Libro> saga) {
        double precioT = 0;
        for (Libro libro : saga.values()) {
            precioT += usePrecioTotal(libro);
        }
        return precioT;
    }
    // punto 4 a V 5 a ; Punto 6 a II 3 a

    private double totalPrestamo(){
        double precioT = 0;
        for (Libro lib : this.prestamoActual.getLibrosEnPrestamo().values()) {
            precioT += totalSaga(lib.getSaga());
            precioT += usePrecioTotal(lib);
            precioT -= sumaDescuentos(lib, precioT);
        }
        return precioT;
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
            if (buscarDenominacio(demo, this.prestamoActual.getPagoBillete())) {
                bil = this.prestamoActual.getPagoBillete().get(demo);
                bil.setCantidad(bil.getCantidad() + 1);
            } else {
                this.prestamoActual.getPagoBillete().put(demo, new Billete(1, demo));
            }
        }

        //6 a II 1 
        pago.setPagoBillete(this.prestamoActual.getPagoBillete());
        // 6 a II 2  
        pago.setTotalIntro(totalIntroducido(this.prestamoActual.getPagoBillete()));
        // 6 a II 3
        pago.setValorPrestamo(totalPrestamo());
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
        return totalIntroducido(this.prestamoActual.getPagoBillete()) - totalPrestamo();
    }

<<<<<<< HEAD
=======
    //Punto 7
    private AcabarPrestamo terminarPrestamo() {

        AcabarPrestamo acabar = new AcabarPrestamo();
        //punto 7 b I 1
        if (verificarVueltas(listaBill())) {
            //punto 7 b II 1    
            if (totalIntroducido(dineroAcumulado) >= saldoFaltante()) {
                // punto 7 b III 1 
                actualizarExistenciaLibro();

                // punto 7 b IV 1 
                acabar.setError(null);
                // punto 7 b IV 2
                acabar.setNumeroTotalPrestamo(totalLibrosPrestamo());
                //Punto 7 b IV 3
                acabar.setValorTPrestamo(totalPrestamo());
                // punto 7 b IV 4
                acabar.setTotalIntroBilletes(numeroIntroducidoBilletes(this.prestamoActual.getPagoBillete()));
                // punto 7 b IV 5
                acabar.setValorTVueltas(saldoFaltante());
                // punto 7 b III 6
                actualizarBilletes();
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
        for (Libro lib : this.prestamoActual.getLibrosEnPrestamo().values()) {
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
            vuel = numBillete(b1.getDenominacion().CIENMIL, vuel, vueltas);
            vuel = numBillete(b1.getDenominacion().CICUENTAMIL, vuel, vueltas);
            vuel = numBillete(b1.getDenominacion().VEINTEMIL, vuel, vueltas);
            vuel = numBillete(b1.getDenominacion().DIEZMIL, vuel, vueltas);
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
            vuel = vuel - ((int) b1.getDenominacion().getValor() * b1.getCantidad());
            vueltas.put(consT, b1);
        }
        return vuel;
    }
    // punto 7 b III 2

    private void actualizarBilletes() {

        for (Billete bill : this.prestamoActual.getPagoBillete().values()) {
            for (Billete bill2 : this.dineroAcumulado.values()) {
                if (bill2.getDenominacion().getValor() == bill.getDenominacion().getValor()) {
                    bill2.setCantidad(bill2.getCantidad() + bill.getCantidad());

                }
            }
        }
        for (Billete bill : this.listaBill().values()) {
            for (Billete bill2 : this.dineroAcumulado.values()) {
                if (bill2.getDenominacion().getValor() == bill.getDenominacion().getValor()) {
                    bill2.setCantidad(bill2.getCantidad() - bill.getCantidad());

                }
            }
        }
        this.prestamoActual.getPagoBillete().clear();
    }

    private int numeroIntroducidoBilletes(HashMap<Denominacion, Billete> listaBilletes) {
        int cantidad = 0;
        for (Billete bill : listaBilletes.values()) {
            cantidad += bill.getCantidad();
        }
        return cantidad;
    }
>>>>>>> c4c34f0a8b4c697fa80858edcabd073c69c0f97a
    
    // Punto 8
    public ReporteDiario generarReporte() {
        ReporteDiario reporte = new ReporteDiario();
        reporte.setValorPrestamoD(valorTPresatamosD());
        reporteCantidad(reporte);
        reporteTotal(reporte);
        reporteNoVendidos(reporte);
        reporteSitiosDescarga(reporte);
        return reporte;
    }

    // 8 b I 1 
    private double valorTPresatamosD() {
        double precioT = 0;
        for (Prestamo pres : this.prestamos.values()) {
            this.prestamoActual = pres;
            precioT += totalPrestamo();
        }
        return precioT;
    }

    // punto 8 b II 2
    private void reporteCantidad(ReporteDiario repor) {
        ReporteLibroDiario libro = new ReporteLibroDiario();
        int canP = 0, canEV = 0, canEI = 0, precioP = 0, precioEV = 0, precioEI = 0;
        for (Prestamo pres : this.prestamos.values()) {
            for (Libro lib : pres.getLibrosEnPrestamo().values()) {
                if (lib instanceof PaperBook) {
                    PaperBook lib2 = (PaperBook) lib;
                    canP++;
                    precioP += lib2.precioTotal();
                } else if (lib instanceof EBookImage) {
                    EBookImage lib2 = (EBookImage) lib;
                    canEI++;
                    precioEI += lib2.precioTotal();
                } else {
                    EBookVideo lib2 = (EBookVideo) lib;
                    canEV++;
                    precioEV += lib2.precioTotal();
                }
            }
        }
        libro.setTipo("PaperBook");
        libro.setCantidadPrestamo(canP);
        libro.setPreciPrestamo(precioP);
        repor.getReporteD().put(libro.getTipo(), libro);
        libro.setTipo("EBookImage");
        libro.setCantidadPrestamo(canEI);
        libro.setPreciPrestamo(precioEI);
        repor.getReporteD().put(libro.getTipo(), libro);
        libro.setTipo("EBookVideo");
        libro.setCantidadPrestamo(canEV);
        libro.setPreciPrestamo(precioEV);
        repor.getReporteD().put(libro.getTipo(), libro);
    }

    // punto 8 b II 3
    private void reporteTotal(ReporteDiario repor) {
        ReporteLibroDiario libro = new ReporteLibroDiario();
        libro.setTipo("EBook");
        libro.setCantidadPrestamo(repor.getReporteD().get("EBookVideo").getCantidadPrestamo() + repor.getReporteD().get("EBookImage").getCantidadPrestamo());
        repor.getReporteD().put(libro.getTipo(), libro);
        libro.setTipo("Book");
        libro.setCantidadPrestamo(repor.getReporteD().get("PaperBook").getCantidadPrestamo() + repor.getReporteD().get("EBook").getCantidadPrestamo());
        repor.getReporteD().put(libro.getTipo(), libro);
    }

    // punto 8 b III 1
    private void reporteNoVendidos(ReporteDiario repor) {
        Integer acomp = 0, acomeb = 0;
        for (Libro lib : this.librosDisponibles.values()) {
            for (Prestamo pres : this.prestamos.values()) {
                if (!pres.getLibrosEnPrestamo().containsKey(lib.getIsbn())) {
                    if (lib instanceof PaperBook) {
                        acomp += lib.getUnidadesDisponibles();
                    } else {
                        acomeb += lib.getUnidadesDisponibles();
                    }
                }
            }
        }
        repor.getLibrosNoVendidos().put("PaperBook", acomp);
        repor.getLibrosNoVendidos().put("EBook", acomeb);
    }
    // punto 8 b IV 1
    private void reporteSitiosDescarga(ReporteDiario repor) {
        for (Libro lib : this.librosDisponibles.values()) {
            if (!(lib instanceof PaperBook)) {
                EBook lib2 = (EBook) lib;
                repor.getSitiosDescarga().put(lib.getIsbn(), lib2.getSitioDescarga());                
            }
        }
    }
}
