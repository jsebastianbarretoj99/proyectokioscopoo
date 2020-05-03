package dto;

import java.util.HashMap;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public class ReporteDiario {

    private double valorPrestamoD;
    private HashMap< String, ReporteLibroDiario> reporteD;
    private HashMap< String, Integer> librosNoVendidos;
    private HashMap< String, String> sitiosDescarga;

    public ReporteDiario() {
        this.reporteD = new HashMap<>();
        this.librosNoVendidos = new HashMap<>();
        this.sitiosDescarga = new HashMap<>();
    }

    public ReporteDiario(double valorPrestamoD, HashMap<String, ReporteLibroDiario> reporteD, HashMap<String, Integer> librosNoVendidos, HashMap<String, String> sitiosDescarga) {
        this.valorPrestamoD = valorPrestamoD;
        this.reporteD = reporteD;
        this.librosNoVendidos = librosNoVendidos;
        this.sitiosDescarga = sitiosDescarga;
        this.reporteD = new HashMap<>();
        this.librosNoVendidos = new HashMap<>();
        this.sitiosDescarga = new HashMap<>();
    }

    public double getValorPrestamoD() {
        return valorPrestamoD;
    }

    public void setValorPrestamoD(double valorPrestamoD) {
        this.valorPrestamoD = valorPrestamoD;
    }

    public HashMap<String, ReporteLibroDiario> getReporteD() {
        return reporteD;
    }

    public void setReporteD(HashMap<String, ReporteLibroDiario> reporteD) {
        this.reporteD = reporteD;
    }

    public HashMap<String, Integer> getLibrosNoVendidos() {
        return librosNoVendidos;
    }

    public void setLibrosNoVendidos(HashMap<String, Integer> librosNoVendidos) {
        this.librosNoVendidos = librosNoVendidos;
    }

    public HashMap<String, String> getSitiosDescarga() {
        return sitiosDescarga;
    }

    public void setSitiosDescarga(HashMap<String, String> sitiosDescarga) {
        this.sitiosDescarga = sitiosDescarga;
    }

}
