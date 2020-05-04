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
    private double precioPrestamo;

    public ReporteLibroDiario() {
    }

    public ReporteLibroDiario(String tipo, int cantidadPrestamo, double preciPrestamo) {
        this.tipo = tipo;
        this.cantidadPrestamo = cantidadPrestamo;
        this.precioPrestamo = preciPrestamo;
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

    public double getPrecioPrestamo() {
        return precioPrestamo;
    }

    public void setPrecioPrestamo(double precioPrestamo) {
        this.precioPrestamo = precioPrestamo;
    }

    @Override
    public String toString() {
        return "ReporteLibroDiario{" + "tipo=" + tipo + ", cantidadPrestamo=" + cantidadPrestamo + ", precioPrestamo=" + precioPrestamo + '}';
    }
    
    
    
    
}
