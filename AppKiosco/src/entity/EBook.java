/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author vale-
 */
public abstract class EBook extends Libro{
    
    private String sitioDescarga;

    public String getSitioDescarga() {
        return sitioDescarga;
    }

    public void setSitioDescarga(String sitioDescarga) {
        this.sitioDescarga = sitioDescarga;
    }

    public EBook(String sitioDescarga, String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
        this.sitioDescarga = sitioDescarga;
    }
    
    @Override
    public abstract double precioTotal();
}
