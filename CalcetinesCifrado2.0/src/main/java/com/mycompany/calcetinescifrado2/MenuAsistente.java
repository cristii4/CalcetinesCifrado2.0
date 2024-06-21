package com.mycompany.calcetinescifrado2;

import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class MenuAsistente {
    
    public void menuAsistente() throws IOException {
        Scanner sc = new Scanner(System.in);
        Gestor a = new Gestor();
        int op;
        
        do {
            System.out.println("Seleccione una opcion:");
            System.out.println("1- Listar productos");
            System.out.println("2- Agregar producto");
            System.out.println("3- Modificar venta");
            System.out.println("4- Activar/Desactivar producto");
            System.out.println("5- Crear venta");
            System.out.println("6- Añadir un cliente");
            System.out.println("7- Modificar informacion de un cliente");
            System.out.println("0- Salir");
            op = sc.nextInt();
            
            //Para quitar el salto de linea
            sc.nextLine();
            switch(op) {
                case 1: { // Listar productos
                    System.out.println(a.leer("Productos.txt"));
                    break;
                }
                case 2: { // Agregar producto
                    String estampado = "";
                    String color = "";
                    int precio;
                    String activo = "";
                    System.out.print("Introduce el estampado del calcetin: ");
                    estampado = sc.nextLine();
                    System.out.print("Introduce el color del calcetin: ");
                    color = sc.nextLine();
                    System.out.print("Introduce el precio del calcetin: ");
                    precio = Integer.parseInt(sc.nextLine());
                    System.out.print("¿Se encuentra activo? Si / No");
                    activo = sc.nextLine();

                    Random r = new Random();
                    int idProducto = r.nextInt(9000) + 1000;

                    String nuevo = idProducto + ";" + estampado + ";" + color + ";" + precio + ";" + activo;
                    System.out.print("El producto: ");
                    System.out.println(a.Agregar("Productos.txt",nuevo));
                    System.out.print(" ha sido creado.\n");
                    break;
                }
                case 3: { // Modificar venta
                    System.out.print("\nIntroduce el identificador de la venta que deseas modificar: \n");
                    System.out.println(a.leer("Ventas.txt"));
                    int idVenta;
                    idVenta = Integer.parseInt(sc.nextLine());

                    System.out.print("¿Como deseas modificarla?\n");

                    System.out.print("¿Quieres cambiar el nombre? Si / No\n");
                    String cambiarNombre = sc.nextLine();
                    String nombreAntiguo = "";
                    String nombreNuevo = "";
                    String productoAntiguo = "";
                    String productoNuevo = "";

                    if( cambiarNombre.equals("si") || cambiarNombre.equals("Si") ) {
                        nombreAntiguo =  a.leerVentas("Ventas.txt",idVenta,1);
                        System.out.print("Introduce el nuevo nombre: ");
                        nombreNuevo = sc.nextLine();
                    } else if ( cambiarNombre.equals("no") || cambiarNombre.equals("No") ) {
                        nombreAntiguo =  a.leerVentas("Ventas.txt",idVenta,1);
                        nombreNuevo = nombreAntiguo;
                    }

                    System.out.print("¿Quieres cambiar el producto? Si / No\n");
                    String cambiarCalcetin = sc.nextLine();
                    String calcetinNuevo = "";
                    if( cambiarCalcetin.equals("si") || cambiarCalcetin.equals("Si") ) {
                        productoAntiguo =  a.leerVentas("Ventas.txt",idVenta,2);
                        System.out.print("Introduce el nuevo producto: ");
                        productoNuevo = sc.nextLine();
                    } else if ( cambiarCalcetin.equals("no") || cambiarCalcetin.equals("No") ) {
                        productoAntiguo =  a.leerVentas("Ventas.txt",idVenta,2);
                        productoNuevo = productoAntiguo;
                    }

                    String antiguo = "";
                    String nuevo = "";

                    antiguo = idVenta + ";" + nombreAntiguo + ";" + productoAntiguo;
                    nuevo = idVenta + ";" + nombreNuevo + ";" + productoNuevo;

                    System.out.print("La venta: ' " + antiguo + " ' ha sido modificada a:");
                    System.out.println(a.Modificar("Ventas.txt",antiguo,nuevo));
                    break;
                }
                case 4: { // Desactivar producto
                    System.out.print("\nIntroduce el identificador del producto: \n");
                    System.out.println(a.leer("Productos.txt"));
                    int idProducto = Integer.parseInt(sc.nextLine());

                    String estampado = "";
                    String color = "";
                    String precio = "";
                    String activo = "";

                    estampado =  a.leerVentas("Productos.txt",idProducto,1);
                    color =  a.leerVentas("Productos.txt",idProducto,2);
                    precio =  a.leerVentas("Productos.txt",idProducto,3);
                    activo =  a.leerVentas("Productos.txt",idProducto,4);

                    String antiguo = idProducto + ";" + estampado + ";" + color + ";" + precio + ";" + activo;

                    if( activo.equals("si") || activo.equals("Si") ) {
                        System.out.print("El producto se encuentra: " + activo + "\n"); // activado
                        System.out.print("¿Quieres desactivarlo? Si / No ");
                        String ActivoNuevo = sc.nextLine();
                        if( ActivoNuevo.equals("si") || activo.equals("Si") ) {
                            activo = "No";
                        } else {
                            activo = "Si";
                        }

                    } else {
                        System.out.print("El producto se encuentra: " + activo + "\n"); // desactivado
                        System.out.print("¿Quieres activarlo? Si / No ");
                        String ActivoNuevo = sc.nextLine();
                        if( ActivoNuevo.equals("si") || activo.equals("Si") ) {
                            activo = "Si";
                        } else {
                            activo = "No";
                        }
                    }
                        String nuevo = idProducto + ";" + estampado + ";" + color + ";" + precio + ";" + activo;

                        System.out.print("El estado del producto: ");
                        System.out.println(a.Modificar("Productos.txt",antiguo,nuevo));
                        System.out.print(" ha sido modoficado.");      
                        break;
                }
                case 5: { // Crear venta
                    System.out.print("\nIntroduce el nombre del comprador: \n");
                    String nombreComprador = sc.nextLine();

                    System.out.print("\nIntroduce el estampado del producto: \n");
                    String estampadoProducto = sc.nextLine();

                    Random r = new Random();
                    int idVenta = r.nextInt(9000) + 1000;

                    String nuevo = idVenta + ";" + nombreComprador + ";" + estampadoProducto;
                    System.out.print("La venta: ");
                    System.out.println(a.Agregar("Ventas.txt",nuevo));
                    System.out.print(" ha sido creada.");
                    break;
                }
                case 6: // Añadir cliente
                {
                    a.descifrar("Clientes.txt");
                    System.out.print("\nIntroduce el nombre del cliente: ");
                    String nombreCliente = sc.nextLine();
                    System.out.print("\nIntroduce el primer apellido: ");
                    String primerApellido = sc.nextLine();
                    System.out.print("\nIntroduce el segundo apellido: ");
                    String segundoApellido = sc.nextLine();
                    System.out.print("\nIntroduce la direccion completa: ");
                    String direccion = sc.nextLine();
                    System.out.print("\nIntroduce el numero de telefono: ");
                    String numeroTelefono = sc.nextLine();

                    Random r = new Random();
                    int idCliente = r.nextInt(9000) + 1000;
                    String nuevo = idCliente + ";" + nombreCliente + ";" + primerApellido + ";" + segundoApellido + ";" + direccion + ";" + numeroTelefono;

                    System.out.println("El cliente: ");
                    System.out.println(a.Agregar("Clientes.txt",nuevo));
                    System.out.print("El cliente ha sido creado.");

                    a.cifrar("Clientes.txt");
                    break;
                }
                case 7:// Modificar cliente
                {
                    a.descifrar("Clientes.txt");
                    System.out.print("\nIntroduce el identificador del cliente que deseas modificar: \n");
                    System.out.println(a.leer("Clientes.txt"));
                    int idCliente;
                    idCliente = Integer.parseInt(sc.nextLine());

                    System.out.print("¿Como deseas modificarlo?\n");

                    System.out.print("¿Quieres cambiar el nombre? Si / No\n");
                    String cambiarNombre = sc.nextLine();
                    String nombreAntiguo = "";
                    String nombreNuevo = "";
                    String primerAntiguo = "";
                    String primerNuevo = "";
                    String segundoAntiguo = "";
                    String segundoNuevo = "";
                    String direccionAntiguo = "";
                    String direccionNuevo = "";
                    String telefonoAntiguo = "";
                    String telefonoNuevo = "";

                    if( cambiarNombre.equals("si") || cambiarNombre.equals("Si") ) {
                        nombreAntiguo =  a.leerVentas("Clientes.txt",idCliente,1);
                        System.out.print("Introduce el nuevo nombre: ");
                        nombreNuevo = sc.nextLine();
                    } else if ( cambiarNombre.equals("no") || cambiarNombre.equals("No") ) {
                        nombreAntiguo =  a.leerVentas("Clientes.txt",idCliente,1);
                        nombreNuevo = nombreAntiguo;
                    }

                    System.out.print("¿Quieres cambiar el primer apellido? Si / No\n");
                    String cambiarApe1 = sc.nextLine();
                    if( cambiarApe1.equals("si") || cambiarApe1.equals("Si") ) {
                        primerAntiguo =  a.leerVentas("Clientes.txt",idCliente,2);
                        System.out.print("Introduce el nuevo primer apellido: ");
                        primerNuevo = sc.nextLine();
                    } else if ( cambiarApe1.equals("no") || cambiarApe1.equals("No") ) {
                        primerAntiguo =  a.leerVentas("Clientes.txt",idCliente,2);
                        primerNuevo = primerAntiguo;
                    }

                    System.out.print("¿Quieres cambiar el segundo apellido? Si / No\n");
                    String cambiarApe2 = sc.nextLine();
                    if( cambiarApe2.equals("si") || cambiarApe2.equals("Si") ) {
                        segundoAntiguo =  a.leerVentas("Clientes.txt",idCliente,3);
                        System.out.print("Introduce el nuevo segundo apellido: ");
                        segundoNuevo = sc.nextLine();
                    } else if ( cambiarApe2.equals("no") || cambiarApe2.equals("No") ) {
                        segundoAntiguo =  a.leerVentas("Clientes.txt",idCliente,3);
                        segundoNuevo = segundoAntiguo;
                    }

                    System.out.print("¿Quieres cambiar la direccion? Si / No\n");
                    String cambiarDireccion = sc.nextLine();
                    if( cambiarDireccion.equals("si") || cambiarDireccion.equals("Si") ) {
                        direccionAntiguo =  a.leerVentas("Clientes.txt",idCliente,4);
                        System.out.print("Introduce la nueva direccion: ");
                        direccionNuevo = sc.nextLine();
                    } else if ( cambiarDireccion.equals("no") || cambiarDireccion.equals("No") ) {
                        direccionAntiguo =  a.leerVentas("Clientes.txt",idCliente,4);
                        direccionNuevo = direccionAntiguo;
                    }

                    System.out.print("¿Quieres cambiar el numero de telefono? Si / No\n");
                    String cambiarTelefono = sc.nextLine();
                    if( cambiarTelefono.equals("si") || cambiarTelefono.equals("Si") ) {
                        telefonoAntiguo =  a.leerVentas("Clientes.txt",idCliente,5);
                        System.out.print("Introduce el nuevo numero de telefono: ");
                        telefonoNuevo = sc.nextLine();
                    } else if ( cambiarTelefono.equals("no") || cambiarTelefono.equals("No") ) {
                        telefonoAntiguo =  a.leerVentas("Clientes.txt",idCliente,5);
                        telefonoNuevo = telefonoAntiguo;
                    }


                    String antiguo = "";
                    String nuevo = "";

                    antiguo = idCliente + ";" + nombreAntiguo + ";" + primerAntiguo + ";" + segundoAntiguo + ";" + direccionAntiguo + ";" + telefonoAntiguo;
                    nuevo = idCliente + ";" + nombreNuevo + ";" + primerNuevo + ";" + segundoNuevo + ";" + direccionNuevo + ";" + direccionNuevo + ";" + telefonoNuevo;

                    System.out.print("El cliente: ' " + antiguo + " ' ha sido modificado a:");
                    System.out.println(a.Modificar("Clientes.txt",antiguo,nuevo));

                    a.cifrar("Clientes.txt");
                    break;
                }
                case 0: { // Salir
                    break;
                }

                default:{
                    System.out.println("Introduzca una opcion valida");
                }
            }
        } while (op != 0);
    }
    
} // CLASS
