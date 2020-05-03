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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        this.prestamos = new HashMap();
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
        this.dineroAcumulado.put(Denominacion.CIENMIL, new Billete(10, Denominacion.CIENMIL));
        this.dineroAcumulado.put(Denominacion.CICUENTAMIL, new Billete(20, Denominacion.CICUENTAMIL));
        this.dineroAcumulado.put(Denominacion.VEINTEMIL, new Billete(4, Denominacion.VEINTEMIL));
        this.dineroAcumulado.put(Denominacion.DIEZMIL, new Billete(7, Denominacion.DIEZMIL));
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

    // Punto 3 
    public HashMap<String, ListarLibros> listarLibros() {
        HashMap<String, ListarLibros> lista = new HashMap<>();
        for (Libro lib : this.librosDisponibles.values()) {
            ListarLibros ob = new ListarLibros();
            ob.setIsbn(lib.getIsbn());
            ob.setNombre(lib.getNombre());
            ob.setPrecio(usePrecioTotal(lib));
            if (lib instanceof PaperBook) {
                ob.setTipo("PB");
            } else if (lib instanceof EBookImage) {
                ob.setTipo("EBI");
            } else if (lib instanceof EBookVideo) {
                ob.setTipo("EBV");
            }
            lista.put(lib.getIsbn(), ob);
            ob.setSaga(lib.getSaga());
        }
        return lista;
    }

    //Punto 4 
    public EAgregarLibroEnPrestamo agregarLibros(Libro lib_in) {
        EAgregarLibroEnPrestamo errorAgregar = new EAgregarLibroEnPrestamo();
        Libro lib_ver;
        if(lib_in ==null){
            lib_ver = null;
        }else{
           lib_ver = buscarLibroIsbn(lib_in.getIsbn());
        }        
        //Punto 4 a I 
        if (lib_ver != null) {
            //Punt1 4 a II
            if (unidadesDisponiblesLibros(lib_in.getIsbn(), lib_ver)) {

                errorAgregar.setError(verificarSaga(lib_in, lib_ver));
                // punto 4 a III
                if (this.prestamoActual.getLibrosEnPrestamo().containsKey(lib_in.getIsbn())) {
                    Libro lib_p = this.prestamoActual.getLibrosEnPrestamo().get(lib_in.getIsbn());
                    lib_p.setUnidadesDisponibles(lib_p.getUnidadesDisponibles() + lib_in.getUnidadesDisponibles());
                    //Buscamos en la saga del libro en prestamo los libros de la saga actual. 
                    for (Map.Entry<Integer, Libro> lib_entry : lib_in.getSaga().entrySet()) {
                        Integer key = lib_entry.getKey();
                        Libro libs = lib_entry.getValue();
                        Libro lib_actual = lib_p.getSaga().get(key);
                        if (lib_actual != null) {
                            lib_actual.setUnidadesDisponibles(lib_actual.getUnidadesDisponibles() + lib_in.getUnidadesDisponibles());
                        } else {
                            lib_p.getSaga().put(key, libs);
                        }
                    }
                } else {
                    this.prestamoActual.getLibrosEnPrestamo().put(lib_in.getIsbn(), lib_in);
                }
                // punto 4 a IV c I
                errorAgregar.setValorLibrosSaga(totalSaga(lib_in.getSaga()));
            } else {
                errorAgregar.setError("No hay unidades suficientes para el prestamo del libro solicitado");
                errorAgregar.setValorLibrosSaga(0);
            }
        } else {
            errorAgregar.setError("El libro solicitado no existe");
            errorAgregar.setValorLibrosSaga(0);
        }
        errorAgregar.setTotalLibros(totalLibrosPrestamo());
        errorAgregar.setTotalLibrosSaga(totalLibrosSaga(lib_in));
        errorAgregar.setValorTotalPrestamo(totalPrestamo());
        return errorAgregar;
    }

    //Punto 4 a I 1
    public Libro buscarLibroIsbn(String isbn_p) {
        return this.librosDisponibles.get(isbn_p);
    }

    // punto 4 I 2
    private boolean unidadesDisponiblesLibros(String isbn, Libro lib_ver) {
        if (this.prestamoActual.getLibrosEnPrestamo().containsKey(isbn)) {
            if (lib_ver.getUnidadesDisponibles() > this.prestamoActual.getLibrosEnPrestamo().get(isbn).getUnidadesDisponibles()) {
                return true;
            }
        }
        if (lib_ver.getUnidadesDisponibles() > 0) {
            return true;
        }
        return false;
    }

    //Punto saga verificamos los libros de la saga ingresados
    private String verificarSaga(Libro lib, Libro lib_ver) {
        String error = " ";
        List<Integer> llaves = new ArrayList<>();
        //verificar que los isbn se encuentran y si hay unidades disponibles
        //libro de entrada
        for (Map.Entry<Integer, Libro> lib_entry : lib.getSaga().entrySet()) {
            Integer key = lib_entry.getKey();
            Libro lib_in = lib_entry.getValue();
            // Saga del libro en libros disponibles
            if (!(lib_in == null)) {
                for (Libro lib_saga : lib_ver.getSaga().values()) {
                    //Hace parte de la saga 
                    if (lib_in.getIsbn().equals(lib_saga.getIsbn())) {
                        //Unidades disponibles 
                        if (!unidadesDisponiblesLibrosSaga(lib.getIsbn(), lib_ver)) {
                            error += lib_in.getIsbn() + " no hay unidades disponibles, ";
                            lib.getSaga().remove(key);
                        }
                    }
                }
            } else {
                llaves.add(key);
            }
        }
        if (lib_ver.getSaga().isEmpty()) {
            error = null;
        } else {
            error += " El/los código(s) no hacen partes de la saga : ";
            for (Integer llav : llaves) {
                error += llav + " ";
                lib.getSaga().remove(llav);
            }
        }
        return error;
    }

    //Punto saga verificamos las unididades disponibles para los libros de la saga 
    private boolean unidadesDisponiblesLibrosSaga(String isbn_p, Libro lib_ver) {
        int acum = 0;
        if (this.prestamoActual.getLibrosEnPrestamo().containsKey(isbn_p)) {
            acum += this.prestamoActual.getLibrosEnPrestamo().get(isbn_p).getUnidadesDisponibles();
        }
        for (Libro lib_pre : this.prestamoActual.getLibrosEnPrestamo().values()) {
            for (Libro lib_saga : lib_pre.getSaga().values()) {
                if (isbn_p.equals(lib_saga.getIsbn())) {
                    acum += lib_saga.getUnidadesDisponibles();
                }
            }
        }
        if (lib_ver.getUnidadesDisponibles() > acum) {
            return true;
        }
        return false;
    }

    // Punto 4 a IV 1 a, b i ii
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

    //Buscar el libro de la saga : verificar que si hace parte del descueto del 
    //libro.
    private boolean buscarLibroSaga(int key, HashMap<Integer, Libro> saga) {
        return saga.containsKey(key);
    }

    // Suma de descuentos: punto 4 a iv ii 1 2 
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
            tot += lib.getUnidadesDisponibles();
            tot += totalLibrosSaga(lib);
        }
        return tot;
    }

    // punto 4 a V 3 a
    private int totalLibrosSaga(Libro libro) {
        int tot = 0;
        if(libro == null)
            return 0;
        
        for (Libro libs : libro.getSaga().values()) {
            tot += libs.getUnidadesDisponibles();
        }
        return tot;
    }

    // punto 4 a iv c i
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
    private void modificarValor(ReporteLibroDiario libro, String tipo, int canP, int precioP) {
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
