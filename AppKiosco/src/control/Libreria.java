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

    private double totalPrestamo() {
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

    // punto 7     
    public AcabarPrestamo terminarPrestamo() {
        AcabarPrestamo acab = new AcabarPrestamo();
        //7 b I
        if (saldoFaltante() >= 0) {
            //7 b II
            if (verificarVueltas(saldoFaltante()) >= 0) {
                //7 b III 1 
                actualizarExistenciaLibro();
                //7 b III 2
                actualizarBilletes();
                // 7 b IV 5
                acab.setValorTVueltas(saldoFaltante());
            } else {
                acab.setError("No se pueden dar vueltas: Dinero insufciente en caja");
            }
            // 7 b IV 2
            acab.setNumeroTotalLibros(totalLibrosPrestamo());
            // 7 b IV 3
            acab.setValorTPrestamo(totalPrestamo());

        } else {
            acab.setError("El dinero ingresado no cubre el valor del prestamo");
        }
        // 7 b IV 4
        acab.setTotalIntroBilletes(totalIntroducido(this.prestamoActual.getPagoBillete()));

        return acab;
    }

    //7 b II 
    private double verificarVueltas(double vueltas) {
        return totalIntroducido(this.getDineroAcumulado()) - vueltas;
    }

    // 7 b III 1
    private void actualizarExistenciaLibro() {
        Libro lib1;
        for (Libro lib : this.prestamoActual.getLibrosEnPrestamo().values()) {
            lib1 = buscarLibroIsbn(lib.getIsbn());
            lib1.setUnidadesDisponibles(lib1.getUnidadesDisponibles() - 1);
            for (Libro sa : lib1.getSaga().values()) {
                sa.setUnidadesDisponibles(sa.getUnidadesDisponibles() - 1);
            }
        }
    }

    // 7 b III 2
    private void actualizarBilletes() {
        Billete bild;
        for (Billete bila : this.prestamoActual.getPagoBillete().values()) {
            bild = this.dineroAcumulado.get(bila.getDenominacion());
            bild.setCantidad(bild.getCantidad() + bila.getCantidad());
        }
    }

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
        modificarValor(libro, "PaperBook", canP, precioP);
        repor.getReporteD().put(libro.getTipo(), libro);
        modificarValor(libro, "EBookImage", canEI, precioEI);
        repor.getReporteD().put(libro.getTipo(), libro);
        modificarValor(libro, "EBookVideo", canEV, precioEV);
        repor.getReporteD().put(libro.getTipo(), libro);
    }
    
    //punto 8 b II 2 
    private void modificarValor(ReporteLibroDiario libro, String tipo, int canP, int precioP){
        libro.setTipo(tipo);
        libro.setCantidadPrestamo(canP);
        libro.setPreciPrestamo(precioP);
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
