/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import dto.AcabarPrestamo;
import entity.Billete;
import entity.Libro;
import entity.Prestamo;
import enumaration.Denominacion;
import java.time.LocalDate;
import java.util.HashMap;

/**
 *
 * @author vale-
 */
public class Libreria {

    public GestionLibro gestion;
    public Prestamo prestamoActual;
    public HashMap< Integer, Billete> dineroAcumulado;
    public HashMap< Integer, Prestamo> prestamos;
    public HashMap< Integer, Libro> librosDisponibles;

    // 1 c 
    public Libreria() {
        crearColeccionBilletes();
    }

    // 1 b II
    private void crearColeccionBilletes() {
        this.dineroAcumulado = new HashMap();
        this.dineroAcumulado.put(0, new Billete(100, Denominacion.CIENMIL));
        this.dineroAcumulado.put(1, new Billete(100, Denominacion.CICUENTAMIL));
        this.dineroAcumulado.put(2, new Billete(100, Denominacion.VEINTEMIL));
        this.dineroAcumulado.put(3, new Billete(100, Denominacion.DIEZMIL));
    }

    public AcabarPrestamo iniciraPrestamo() {
        LocalDate ahora = LocalDate.now();
        AcabarPrestamo acab = new AcabarPrestamo();
        if (unidadesDisponiblesLibros()) {
            if (prestamos.isEmpty())
                prestamos.put(0, new Prestamo(ahora,1));
            else 
                this.prestamos.put(prestamos.size(), new Prestamo(ahora,prestamos.size()+1));
            
            this.prestamoActual = prestamos.get(prestamos.size()-1);
            
            acab.setError(null);
            acab.setPres(prestamoActual);
            return acab;
        }
        else{
            acab.setError("No existen Unidades disponibles de ningún libro");
            acab.setPres(null);
            return acab;
        }
            
            
        

    }
    
    // 2 b 4 1
    private boolean unidadesDisponiblesLibros(){
        int acom =0;
        for(Libro lib : this.librosDisponibles.values()){
            acom += lib.getUnidadesDisponibles();
        }
        if(acom>0)
            return true;
        else 
            return false;
    }

}
