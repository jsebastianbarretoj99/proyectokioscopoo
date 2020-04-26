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
public class EBookImage extends EBook {

    private double precioPorImagen;

    public double getPrecioPorImagen() {
        return precioPorImagen;
    }

    public void setPrecioPorImagen(double precioPorImagen) {
        this.precioPorImagen = precioPorImagen;
    }

    public EBookImage(double precioPorImagen, String sitioDescarga, String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(sitioDescarga, isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
        this.precioPorImagen = precioPorImagen;
    }

    // punto 4 a IV 1 b I 
    @Override
    public double precioTotal(){
        return super.getPrecioBase() + (this.precioPorImagen*super.getNumeroImagenes());
    }
   
}
