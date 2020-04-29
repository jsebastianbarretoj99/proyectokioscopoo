/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boundary;

import control.Libreria;
import dto.IniciarPrestamo;
import dto.ListarLibros;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author xboxk
 */
public class PantallaKiosco {

    /**
     * @param args the command line arguments
     */
    // Punto 1 
    private Libreria quiosco = new Libreria();
    public static void main(String[] args) {
        // TODO code application logic here
       // agregarLibro(isbn,busquedaSaga(isbn))       
        PantallaKiosco panta = new PantallaKiosco();
        String clave = "1234", cvIngre; // La clave que habilita al 
        // Administrador // punto 9 a 
        int opcion =0;
        // Punto 1 d: Se inicia el día. 
        panta.quiosco = new Libreria();
        Scanner teclado = new Scanner(System.in);
        System.out.println("Menú de Prestamos de libros en quiosco");
        // punto 2 a 
        System.out.println("1. Iniciar préstamo.");
        // punto 7 a 
        System.out.println("2. Terminar prestamo.");
        // punto 8 a 
        System.out.println("3. Devolver Billetes");
        // punto 9 a 
        System.out.println("4. Reporte diario.");
        System.out.println("5. Salir");
        System.out.println("Digite una opcion");

        opcion = teclado.nextInt();
        
        while(opcion != 5){
            switch (opcion){
                    case 1 :
                        IniciarPrestamo pres= panta.quiosco.iniciraPrestamo();
                        if(pres.getError() == null){
                            System.out.println("El prestamo se creo");
                            System.out.println("Con fecha y hora : " 
                                    + pres.getPres().getFechaHora());
                            
                            despliegueLibros(panta);
                        }
                        else 
                            System.out.println(pres.getError());                        
                        
                        break;
                 
                     
            }
            
            opcion =5;
                        
        }
        

       
    }
    
    public static void despliegueLibros(PantallaKiosco pan){
        for(ListarLibros lib : pan.quiosco.listarLibros().values()){
            System.out.println(lib.toString());
        }
    }
}
