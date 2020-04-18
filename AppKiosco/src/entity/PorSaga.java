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
public  class  PorSaga extends Descuento{
    private int numeroSaga;

    public int getNumeroSaga() {
        return numeroSaga;
    }

    public void setNumeroSaga(int numeroSaga) {
        this.numeroSaga = numeroSaga;
    }
    

    public PorSaga(int numeroSaga, double porcentaje) {
        super(porcentaje);
        this.numeroSaga = numeroSaga;
    }
    
    public PorSaga() {
    }
    
    
    @Override
    public void calcularTotal(){
    
    }
}
