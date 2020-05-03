/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.HashMap;

/**
 *
 * @author juan-
 */
public abstract class Libro {

    private String isbn;
    private int unidadesDisponibles;
    private double precioBase;
    private String nombre;
    private int numeroImagenes;
    private int numeroVideos;
    private HashMap<Integer,Libro> saga;
    private HashMap<Integer, Descuento> descuentos;

    public Libro(){
        this.saga = new HashMap<>();
        this.descuentos = new HashMap<>();
    }
    
    public Libro(String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVideos) {
        this.isbn = isbn;
        this.unidadesDisponibles = unidadesDisponibles;
        this.precioBase = precioBase;
        this.nombre = nombre;
        this.numeroImagenes = numeroImagenes;
        this.numeroVideos = numeroVideos;        
        this.saga = new HashMap<>();
        this.descuentos = new HashMap<>();
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getUnidadesDisponibles() {
        return unidadesDisponibles;
    }

    public void setUnidadesDisponibles(int unidadesDisponibles) {
        this.unidadesDisponibles = unidadesDisponibles;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroImagenes() {
        return numeroImagenes;
    }

    public void setNumeroImagenes(int numeroImagenes) {
        this.numeroImagenes = numeroImagenes;
    }

    public int getNumeroVideos() {
        return numeroVideos;
    }

    public void setNumeroVideos(int numeroVIdeos) {
        this.numeroVideos = numeroVIdeos;
    }

    public HashMap<Integer, Libro> getSaga() {
        return saga;
    }

    public void setSaga(HashMap<Integer, Libro> saga) {
        this.saga = saga;
    }

    public HashMap<Integer, Descuento> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(HashMap<Integer, Descuento> descuentos) {
        this.descuentos = descuentos;
    }

    @Override
    public String toString() {
        return "Libro{" + "isbn=" + isbn + ", unidadesDisponibles=" + unidadesDisponibles + ", precioBase=" + precioBase + ", nombre=" + nombre + '}';
    }
    public abstract double precioTotal();
    
}
