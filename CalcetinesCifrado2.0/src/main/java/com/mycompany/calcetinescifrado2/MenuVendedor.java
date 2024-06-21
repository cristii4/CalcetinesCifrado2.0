package com.mycompany.calcetinescifrado2;

import java.util.Scanner;

public class MenuVendedor {
    
    public void menuVendedor() {
        Scanner sc = new Scanner(System.in);
        int op;
        
        do {
            System.out.println("Seleccione una opcion:");
            System.out.println("1- Listar productos");
            System.out.println("0- Salir");
            op = sc.nextInt();
            //Para quitar el salto de linea
            sc.nextLine();
            switch(op) {
                case 1: {
                    Gestor a = new Gestor();
                    System.out.println(a.leer("Productos.txt"));
                    break;
                }
                case 0: {
                    // Salir

                    break;
                }

                default:{
                    System.out.println("Introduzca una opcion valida");
                }
            }
        } while (op != 0);
    }
    
} // CLASS