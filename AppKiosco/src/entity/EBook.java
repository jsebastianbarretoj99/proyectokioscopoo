package entity;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez Mayo 04 2020
 */
public abstract class EBook extends Libro {

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
