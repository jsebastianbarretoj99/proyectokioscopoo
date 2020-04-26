package entity;
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

    public PorEBook(double porcentaje) {
        super(porcentaje);
    }

    public PorEBook() {
    }
    
    // punto 4 a IV c II 2
    @Override
    public double calcularTotal(double valor){ 
        return valor*super.getPorcentaje();
    }
 
}
