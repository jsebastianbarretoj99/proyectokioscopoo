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
