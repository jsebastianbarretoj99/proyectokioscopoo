/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;

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
    private int numeroVIdeos;
    private ArrayList<Libro> saga = new ArrayList<>();
    private ArrayList<Descuento> descuentos = new ArrayList<>();

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

    public int getNumeroVIdeos() {
        return numeroVIdeos;
    }

    public void setNumeroVIdeos(int numeroVIdeos) {
        this.numeroVIdeos = numeroVIdeos;
    }

    public ArrayList<Libro> getSaga() {
        return saga;
    }

    public void setSaga(ArrayList<Libro> saga) {
        this.saga = saga;
    }

    public ArrayList<Descuento> getDescuentos() {
        return descuentos;
    }

    public void setDescuentos(ArrayList<Descuento> descuentos) {
        this.descuentos = descuentos;
    }

    public Libro(String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        this.isbn = isbn;
        this.unidadesDisponibles = unidadesDisponibles;
        this.precioBase = precioBase;
        this.nombre = nombre;
        this.numeroImagenes = numeroImagenes;
        this.numeroVIdeos = numeroVIdeos;
    }
 
    public abstract double precioTotal();
    
}
