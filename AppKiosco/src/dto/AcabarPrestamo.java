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
    private String error;
    private int numeroTotalLibros;
    private double valorTPrestamo;
    private double totalIntroBilletes;
    private double valorTVueltas;

    public AcabarPrestamo() {
    }

    public AcabarPrestamo(String error, int numeroTotalPrestamo, double valorTPrestamo, double totalIntroBilletes, double valorTVueltas){
        this.error = error;
        this.numeroTotalLibros = numeroTotalPrestamo;
        this.valorTPrestamo = valorTPrestamo;
        this.totalIntroBilletes = totalIntroBilletes;
        this.valorTVueltas = valorTVueltas;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getNumeroTotalLibros() {
        return numeroTotalLibros;
    }

    public void setNumeroTotalLibros(int numeroTotalLibros) {
        this.numeroTotalLibros = numeroTotalLibros;
    }

    public double getValorTPrestamo() {
        return valorTPrestamo;
    }

    public void setValorTPrestamo(double valorTPrestamo) {
        this.valorTPrestamo = valorTPrestamo;
    }

    public double getTotalIntroBilletes() {
        return totalIntroBilletes;
    }

    public void setTotalIntroBilletes(double totalIntroBilletes) {
        this.totalIntroBilletes = totalIntroBilletes;
    }

    public double getValorTVueltas() {
        return valorTVueltas;
    }

    public void setValorTVueltas(double valorTVueltas) {
        this.valorTVueltas = valorTVueltas;
    }
    
}
