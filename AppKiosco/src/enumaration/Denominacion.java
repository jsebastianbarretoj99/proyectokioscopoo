package enumaration;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez Mayo 04 2020
 */
public enum Denominacion {

    CIENMIL(100000),
    CINCUENTAMIL(50000),
    VEINTEMIL(20000),
    DIEZMIL(10000);

    private double valor;

    private Denominacion(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

}
