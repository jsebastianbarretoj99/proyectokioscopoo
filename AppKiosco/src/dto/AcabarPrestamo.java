/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Prestamo;

/**
 *
 * @author vale-
 */
public class AcabarPrestamo {
    String error;
    Prestamo pres;

    public AcabarPrestamo() {
    }

    public AcabarPrestamo(String error, Prestamo pres) {
        this.error = error;
        this.pres = pres;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Prestamo getPres() {
        return pres;
    }

    public void setPres(Prestamo pres) {
        this.pres = pres;
    }
    
    
    
}
