/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author vale-
 */
public class EAgregarLibroEnPrestamo {
    private String error;
    private int totalLibros;
    private int totalLibrosSaga;
    private double valorLibrosSaga;
    private double valorTotalPrestamo;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getTotalLibros() {
        return totalLibros;
    }

    public void setTotalLibros(int totalLibros) {
        this.totalLibros = totalLibros;
    }

    public int getTotalLibrosSaga() {
        return totalLibrosSaga;
    }

    public void setTotalLibrosSaga(int totalLibrosSaga) {
        this.totalLibrosSaga = totalLibrosSaga;
    }

    public double getValorLibrosSaga() {
        return valorLibrosSaga;
    }

    public void setValorLibrosSaga(double valorLibrosSaga) {
        this.valorLibrosSaga = valorLibrosSaga;
    }

    public double getValorTotalPrestamo() {
        return valorTotalPrestamo;
    }

    public void setValorTotalPrestamo(double valorTotalPrestamo) {
        this.valorTotalPrestamo = valorTotalPrestamo;
    }

    public EAgregarLibroEnPrestamo() {
    }

    public EAgregarLibroEnPrestamo(String error, int totalLibros, int totalLibrosSaga, double valorLibrosSaga, double valorTotalPrestamo) {
        this.error = error;
        this.totalLibros = totalLibros;
        this.totalLibrosSaga = totalLibrosSaga;
        this.valorLibrosSaga = valorLibrosSaga;
        this.valorTotalPrestamo = valorTotalPrestamo;
    }
    
}
