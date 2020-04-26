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
<<<<<<< HEAD:AppKiosco/src/dto/AcabarPrestamo.java
public class AcabarPrestamo {
    String error;
    int numeroTotalPrestamo;
    double valorTPrestamo;
    int totalIntroBilletes;
    double valorTVueltas;
=======
public class IniciarPrestamo {
    private String error;
    private Prestamo pres ;
>>>>>>> 00354a3972593b46be8ab0b53b83fb798e890e29:AppKiosco/src/dto/IniciarPrestamo.java

    public IniciarPrestamo() {
    }

<<<<<<< HEAD:AppKiosco/src/dto/AcabarPrestamo.java
    public AcabarPrestamo(String error, int numeroTotalPrestamo, double valorTPrestamo, int totalIntroBilletes, double valorTVueltas) {
=======
    public IniciarPrestamo(String error, Prestamo pres) {
>>>>>>> 00354a3972593b46be8ab0b53b83fb798e890e29:AppKiosco/src/dto/IniciarPrestamo.java
        this.error = error;
        this.numeroTotalPrestamo = numeroTotalPrestamo;
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

    public int getNumeroTotalPrestamo() {
        return numeroTotalPrestamo;
    }

    public void setNumeroTotalPrestamo(int numeroTotalPrestamo) {
        this.numeroTotalPrestamo = numeroTotalPrestamo;
    }

    public double getValorTPrestamo() {
        return valorTPrestamo;
    }

    public void setValorTPrestamo(double valorTPrestamo) {
        this.valorTPrestamo = valorTPrestamo;
    }

    public int getTotalIntroBilletes() {
        return totalIntroBilletes;
    }

    public void setTotalIntroBilletes(int totalIntroBilletes) {
        this.totalIntroBilletes = totalIntroBilletes;
    }

    public double getValorTVueltas() {
        return valorTVueltas;
    }

    public void setValorTVueltas(double valorTVueltas) {
        this.valorTVueltas = valorTVueltas;
    }

    
}
