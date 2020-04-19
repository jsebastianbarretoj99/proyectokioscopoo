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

    @Override
    public double precioTotal() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
    
    
}
