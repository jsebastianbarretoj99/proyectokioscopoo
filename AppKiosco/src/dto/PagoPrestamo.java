/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entity.Billete;
import enumaration.Denominacion;
import java.util.HashMap;

/**
 *
 * @author vale-
 */
public class PagoPrestamo {

    private HashMap<Denominacion, Billete> pagoBillete;
    private double totalIntro;
    private double valorPrestamo;
    private double saldoFaltante;

    public PagoPrestamo() {
        this.pagoBillete = new HashMap<>();
    }

    public PagoPrestamo(HashMap<Denominacion, Billete> pagoBillete, double totalIntro, double valorPrestamo, double saldoFaltante) {
        this.pagoBillete = pagoBillete;
        this.totalIntro = totalIntro;
        this.valorPrestamo = valorPrestamo;
        this.saldoFaltante = saldoFaltante;
    }

    public HashMap<Denominacion, Billete> getPagoBillete() {
        return pagoBillete;
    }

    public void setPagoBillete(HashMap<Denominacion, Billete> pagoBillete) {
        this.pagoBillete = pagoBillete;
    }

    public double getTotalIntro() {
        return totalIntro;
    }

    public void setTotalIntro(double totalIntro) {
        this.totalIntro = totalIntro;
    }

    public double getValorPrestamo() {
        return valorPrestamo;
    }

    public void setValorPrestamo(double valorPrestamo) {
        this.valorPrestamo = valorPrestamo;
    }

    public double getSaldoFaltante() {
        return saldoFaltante;
    }

    public void setSaldoFaltante(double saldoFaltante) {
        this.saldoFaltante = saldoFaltante;
    }
    
    
    

}
