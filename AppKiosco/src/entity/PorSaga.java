package entity;

import java.util.HashMap;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public  class  PorSaga extends Descuento{
    private int numeroSaga;

    public PorSaga() {
    }
    
    public PorSaga(int numeroSaga, double porcentaje) {
        super(porcentaje);
        this.numeroSaga = numeroSaga;
    }
        
    public int getNumeroSaga() {
        return numeroSaga;
    }

    public void setNumeroSaga(int numeroSaga) {
        this.numeroSaga = numeroSaga;
    }
    
    @Override
    public double calcularTotal(double valor, HashMap <Integer,Libro> saga){
        if(!saga.containsKey(this.numeroSaga)){
            return 0.0;
        } else {
            return valor*super.getPorcentaje();
        }
    }
}
