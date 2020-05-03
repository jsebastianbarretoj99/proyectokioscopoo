package entity;

import java.util.HashMap;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
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
