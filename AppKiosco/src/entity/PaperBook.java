package entity;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public class PaperBook extends Libro {

    private String ubicacion;
    private double precioPapeleria;

    public PaperBook(String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
    }

    public PaperBook(String ubicacion, double precioPapeleria, String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
        this.ubicacion = ubicacion;
        this.precioPapeleria = precioPapeleria;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public double getPrecioPapeleria() {
        return precioPapeleria;
    }

    public void setPrecioPapeleria(double precioPapeleria) {
        this.precioPapeleria = precioPapeleria;
    }

    // punto 4 a IV 1 a 
    @Override
    public double precioTotal() {
        return super.getPrecioBase() + this.precioPapeleria;
    }

}
