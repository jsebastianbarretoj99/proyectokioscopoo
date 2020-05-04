package dto;

import entity.Prestamo;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez Mayo 04 2020
 */
//  Punto 2
public class IniciarPrestamo {

    private String error;
    private Prestamo pres;

    public IniciarPrestamo() {
    }

    public IniciarPrestamo(String error, Prestamo pres) {
        this.error = error;
        this.pres = pres;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Prestamo getPres() {
        return pres;
    }

    public void setPres(Prestamo pres) {
        this.pres = pres;
    }

}
