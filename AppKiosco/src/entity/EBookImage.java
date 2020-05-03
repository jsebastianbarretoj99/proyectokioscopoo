package entity;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
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
