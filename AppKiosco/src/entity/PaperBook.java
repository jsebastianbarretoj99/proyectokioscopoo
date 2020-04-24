/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author vale-
 */
public class PaperBook extends Libro{

    public PaperBook(String isbn, int unidadesDisponibles, double precioBase, String nombre, int numeroImagenes, int numeroVIdeos) {
        super(isbn, unidadesDisponibles, precioBase, nombre, numeroImagenes, numeroVIdeos);
    }

    @Override
    public double precioTotal() {
        return 0.0;
    }
    
}
