package com.mycompany.calcetinescifrado2;

import java.io.IOException;
import java.util.Scanner;

public class Principal {
    
    public static void main(String[] args) throws IOException {
        Gestor a = new Gestor();
        
        // Si el archivo "Clientes.txt" no esta cifrado descomentar 
        // la siguiente linea de codigo y ejecutar el codigo. En caso
        // de que este cifrado no hace falta descomentarla.
        a.cifrar("Clientes.txt");
        
        Scanner scan = new Scanner(System.in);
        int Nivel; //1=(Administrador) 2(Asistente) 3(Vendedor)
        
        System.out.print("Hola! Introduce tu nombre\n" );
        System.out.println(a.leerNombres("Usuarios.txt"));
        String nombre = scan.nextLine();
        System.out.print("Hola " + nombre +"\n");
        Nivel =(a.Nivel("Usuarios.txt",nombre));

        System.out.print("\nTienes nivel: " + Nivel +"\n");

        if( Nivel == 1 ) {
            MenuAdministrador ma = new MenuAdministrador();
            ma.menuAdmin();
        }
        if( Nivel == 2 ) {
            MenuAsistente ms = new MenuAsistente();
            ms.menuAsistente();
        }
        if( Nivel == 3 ) {
            MenuVendedor mv = new MenuVendedor();
            mv.menuVendedor();
        }

    } // MAIN
    
} // CLASS
