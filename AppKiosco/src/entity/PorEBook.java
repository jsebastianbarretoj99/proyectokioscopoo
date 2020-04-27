package entity;

import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NICOLAS
 */
public class PorEBook extends Descuento {

    public PorEBook() {
    }
        
    public PorEBook(double porcentaje) {
        super(porcentaje);
    }
    
    // punto 4 a IV c II 2
    @Override
    public double calcularTotal(double valor, HashMap <Integer,Libro> saga){ 
        return valor*super.getPorcentaje();
    }
 
}
