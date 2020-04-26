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
public class EBookVideo extends EBook {
    private double precioPorVideo;

    public double getPrecioPorVideo() {
        return precioPorVideo;
    }

    public void setPrecioPorVideo(double precioPorVideo) {
        this.precioPorVideo = precioPorVideo;
    }

    public EBookVideo(double precioPorVideo, String sitioDescarga, String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(sitioDescarga, isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
        this.precioPorVideo = precioPorVideo;
    }

    // punto 4 a IV 1 b II
    @Override
    public double precioTotal() {
        return super.getPrecioBase() + (this.precioPorVideo*super.getNumeroVideos());
    }
    
    
    
    
    
    
}
