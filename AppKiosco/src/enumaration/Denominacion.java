/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package enumaration;

/**
 *
 * @author vale-
 */
public enum Denominacion {

    CIENMIL(100000),
    CICUENTAMIL(50000),
    VEINTEMIL(20000),
    DIEZMIL(10000);

    private double valor;
   
        
        
    private Denominacion(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    
}
