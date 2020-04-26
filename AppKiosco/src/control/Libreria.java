/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dto.AcabarPrestamo;
import dto.EAgregarLibroEnPrestamo;
import dto.ListarLibros;
import dto.PagoPrestamo;
import entity.Billete;
import entity.EBookImage;
import entity.EBookVideo;
import entity.Libro;
import entity.PaperBook;
import entity.Prestamo;
import enumaration.Denominacion;
import java.time.LocalDate;
import java.util.HashMap;
/**
 *
 * @author vale-
 */
public abstract class Libreria{

    public GestionLibro gestion = new GestionLibro();
    public Prestamo prestamoActual;
    public HashMap< Denominacion, Billete> dineroAcumulado;
    public HashMap< Integer, Prestamo> prestamos;
    public HashMap< String, Libro> librosDisponibles;

    // 1 c 
    public Libreria(){
        crearColeccionLibros();
        crearColeccionBilletes();      
    }

    // 1 a II
    private void crearColeccionLibros(){
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
    public AcabarPrestamo iniciraPrestamo() {
        LocalDate ahora = LocalDate.now();
        AcabarPrestamo acab = new AcabarPrestamo();
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
    public HashMap<String, ListarLibros> listarLibros(){
        HashMap<String, ListarLibros> lista = new HashMap<>();
        ListarLibros ob = new ListarLibros();
        for(Libro lib: this.librosDisponibles.values()){
            ob.setIsbn(lib.getIsbn());
            ob.setNombre(lib.getNombre());
            //ob.setPrecio(precioTotal(ob));    REVISARRR
            if(lib instanceof PaperBook){
                ob.setTipo("PB");
            }else if(lib instanceof EBookImage){
                ob.setTipo("EBI");
            }else if(lib instanceof EBookVideo){
                ob.setTipo("EBV");
            }
            lista.put(lib.getIsbn(), ob);
        }
        return lista;
    }
    
    //Punto 4 
    public HashMap<String, Libro> buscarSaga(String isbn){
        Libro lib = this.librosDisponibles.get(isbn);
        return lib.getSaga();
    }
    
    
    // Punto 4 
    public EAgregarLibroEnPrestamo agregarLibro(String isbn, HashMap<String, Libro> saga){
        EAgregarLibroEnPrestamo errorAgregar = new EAgregarLibroEnPrestamo();
        Libro lib = buscarLibroIsbn(isbn);
        if(lib != null){
            if(unidadesDisponiblesLibros(isbn)){
                
            }else{
            errorAgregar.setError("No hay unidades suficientes para el prestamo del libro solicitado");
            errorAgregar.setTotalLibros(this.prestamoActual.librosEnPrestamo.size());
            errorAgregar.setTotalLibrosSaga(this.librosDisponibles.get(isbn).getSaga().size());
            errorAgregar.setValorLibrosSaga(); 
            }
        }else{
            errorAgregar.setError("El libro solicitado no existe");
            errorAgregar.setTotalLibros(this.prestamoActual.librosEnPrestamo.size());
            errorAgregar.setTotalLibrosSaga(0);
            errorAgregar.setValorLibrosSaga(0);
        }
            
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
    private boolean unidadesDisponiblesLibros(String isbn){
        Libro lib = this.librosDisponibles.get(isbn);
        if(lib.getUnidadesDisponibles() > this.prestamoActual.librosEnPrestamo.get(isbn).getUnidadesDisponibles())
            return true;
        return false;
    }
    
    // punto 4 a V 5 a ; Punto 6 a II 3 a
    private double valorTAcumulado(){
        return 0;
    }
    
    // punto 4 b 
    
    

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
        pago.setTotalIntro(totalIntroducido());
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
    private double totalIntroducido() {
        double total = 0;
        for (Billete bil : this.prestamoActual.pagoBillete.values()) {
            total += (bil.getCantidad() * bil.getDenominacion().getValor());
        }
        return total;
    }

    // 6 a II 4 a 
    private double saldoFaltante() {
        return totalIntroducido() - valorTAcumulado();
    }
    
    public double valorTotalAcumulado(){
        double acumulado = 0;
        
         for(Libro lib: this.prestamoActual.librosEnPrestamo.values()){
             acumulado += lib.precioTotal();        
        }
         return acumulado;
    }
}
