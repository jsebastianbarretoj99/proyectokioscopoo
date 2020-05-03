package dto;

import entity.Libro;
import java.util.HashMap;

/**
 *
 * @author Juan Sebastian Barreto Jimenez Juan Camilo Devia Bastos Nicolas
 * Javier Ramirez Beltran Valentina López Suárez 
 * Mayo 04 2020
 */
public class ListarLibros{
    private String isbn;
    private String nombre;
    private double precio;
    private String tipo; // EB = EBook, EBV = EBookVideo, EBI = EBookImage, PB = PaperBook
    private HashMap<Integer, Libro> saga;
    
    public ListarLibros() {
    }

    public ListarLibros(String isbn, String nombre, double precio, String tipo, HashMap<Integer, Libro> saga) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.precio = precio;
        this.tipo = tipo;
        this.saga = saga;
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

    public HashMap<Integer, Libro> getSaga() {
        return saga;
    }

    public void setSaga(HashMap<Integer, Libro> saga) {
        this.saga = saga;
    }

    
    @Override
    public String toString() {
        return "ListarLibros{" + "isbn=" + isbn + ", nombre=" + nombre + ", precio=" + precio + ", tipo=" + tipo + '}';
    }
    
}
