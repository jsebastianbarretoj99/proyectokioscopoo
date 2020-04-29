/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author xboxk
 */
public class ListarLibros{
    private String isbn;
    private String nombre;
    private double precio;
    private String tipo; // EB = EBook, EBV = EBookVideo, EBI = EBookImage, PB = PaperBook

    public ListarLibros() {
    }

    public ListarLibros(String isbn, String nombre, double precio, String tipo) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ListarLibros{" + "isbn=" + isbn + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + '}';
    }
    
}
