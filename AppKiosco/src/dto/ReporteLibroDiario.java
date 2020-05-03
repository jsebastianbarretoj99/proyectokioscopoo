package dto;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public class ReporteLibroDiario {
    private String tipo;
    private int cantidadPrestamo;
    private double preciPrestamo;

    public ReporteLibroDiario() {
    }

    public ReporteLibroDiario(String tipo, int cantidadPrestamo, double preciPrestamo) {
        this.tipo = tipo;
        this.cantidadPrestamo = cantidadPrestamo;
        this.preciPrestamo = preciPrestamo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCantidadPrestamo() {
        return cantidadPrestamo;
    }

    public void setCantidadPrestamo(int cantidadPrestamo) {
        this.cantidadPrestamo = cantidadPrestamo;
    }

    public double getPreciPrestamo() {
        return preciPrestamo;
    }

    public void setPreciPrestamo(double preciPrestamo) {
        this.preciPrestamo = preciPrestamo;
    }
    
    
}
