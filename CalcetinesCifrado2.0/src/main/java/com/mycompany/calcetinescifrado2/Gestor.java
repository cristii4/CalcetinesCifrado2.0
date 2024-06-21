package com.mycompany.calcetinescifrado2;

import java.io.*;
import java.util.Scanner;

public class Gestor {
    
    public int Nivel(String direccion, String nombre) {
        int nivel=4;
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                String[] parts = bfRead.split("-");
                String NombreLinea = parts[0];
                String NivelLinea = parts[1]; // 1 2 o 3
                if(NombreLinea.equals(nombre)){
                    nivel = Integer.parseInt(NivelLinea);
                }
                
            }
            
        } catch(IOException e) {
            System.err.println("Archivo no encontrado");
        }
        return nivel;
    }
    
    public String leer(String direccion){
        
        String texto = " ";
        
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                
                temp = temp + bfRead +"\n";
                
            }
            
            texto = temp;
            
        }catch(IOException e){ 
            System.err.println("Archivo no encontrado");
        }
        
        return texto;
    }
    
    public String leerNombres(String direccion){
        
        String texto = " ";
        
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                String[] parts = bfRead.split("-");
                String NombreLinea = parts[0];
                temp = temp + NombreLinea +"\n";
                
            }
            
            texto = temp;
            
        }catch(IOException e){ 
            System.err.println("Archivo no encontrado");
        }
        
        return texto;
    }
    
    public String leerVentas(String direccion, int identificadorVenta, int posicion){

        String trozos[] = null;
        String nombre = "";

        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String temp = "";
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                trozos = bfRead.split(";");
                if(identificadorVenta == Integer.parseInt(trozos[0])) {
                    nombre = trozos[posicion]; // devuelve el nombre y el producto cuando se modifican las ventas
                } 
            }
            
        }catch(IOException e){ 
            System.err.println("Archivo no encontrado");
        }
        
        return nombre;
    }
    
    public String Modificar(String direccion, String antiguo, String nuevo ) throws IOException{
        
        String texto = "";
        String temp = "";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                if(bfRead.equals(antiguo)){
                }
                else {
                temp = temp + bfRead +"\n";
                }
                
            }
            temp = temp + nuevo +"\n";
            texto = temp;
            
        }catch(IOException e){ 
            System.err.println("Archivo no encontrado");
        }
        FileWriter fichero = new FileWriter (direccion);
        fichero.write(texto);
        fichero.close();
        return nuevo;
    }
    
    public String Eliminar(String direccion, String numeroVenta ) throws IOException{
        
        String texto = " ";
        String temp = "";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                if(bfRead.equals(numeroVenta)){
                }
                else {
                    temp = temp + bfRead +"\n";
                }
            }
            
            texto = temp;
            
        }catch(IOException e){ 
            System.err.println("Archivo no encontrado");
        }
        FileWriter fichero = new FileWriter (direccion);
        fichero.write(texto);
        fichero.close();
        return numeroVenta;
    }
    
    public String Agregar(String direccion, String nuevo) throws IOException{
        
        String texto = " ";
        String temp = "";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                temp = temp + bfRead +"\n";
            }
            temp = temp + nuevo +"\n";
            texto = temp;
            
        }catch(IOException e){ 
            System.err.println("Archivo no encontrado");
        }
        FileWriter fichero = new FileWriter (direccion);
        fichero.write(texto);
        fichero.close();
        return nuevo;
    }
    
    public void cifrar(String direccion) throws FileNotFoundException, IOException {
        String letras = "A0BCD1EFG2HIJ3KLM4NÑO5PQR6STU7VWX8YZ9";
        int idCliente;
        File ficheroClientes = new File(direccion);
        Scanner fileIn = new Scanner(new FileReader(ficheroClientes));
        String linea = "";
        String trozos[];
        String textoCodificado = "";
        String texto = "";
        String linea1 = "";
        String antiguo = "";
        
        while (fileIn.hasNextLine()) { 
            linea = fileIn.nextLine();
            trozos = linea.split(";");
            idCliente = Integer.parseInt(trozos[0]);
            String nombre = trozos[1];
            String ape1 = trozos[2];
            String ape2 = trozos[3];
            linea1 = idCliente + ";" + nombre + ";" + ape1 + ";" + ape2; 
            antiguo = idCliente + ";" + nombre + ";" + ape1 + ";" + ape2 + ";" + trozos[4] + ";" + trozos[5]; 
            
            char caracterCalle;
            String cifradoCalle = "";
            String calle = trozos[4].toUpperCase();
            for (int i = 0; i < calle.length(); i++) {
                caracterCalle = calle.charAt(i);
                int pos = letras.indexOf(caracterCalle);
                if(pos == -1){ // Si no esta contenido en letras se pone el caracter que se ha leido
                    cifradoCalle += caracterCalle;
                }else{ 
                    cifradoCalle += letras.charAt( (pos + 3) % letras.length() );
                }
            }
            
            char caracterTelf;
            String cifradoTelf = "";
            for (int j = 0; j < trozos[5].length(); j++) {
                caracterTelf = trozos[5].charAt(j);
                int pos = letras.indexOf(caracterTelf);
                if(pos == -1){
                    cifradoTelf += caracterTelf;
                }else{
                    cifradoTelf += letras.charAt( (pos + 3) % letras.length() );
                }
            }

            linea = linea1 + ";" + cifradoCalle + ";" + cifradoTelf;
            linea = linea.toUpperCase();

            reescribir("Clientes.txt", idCliente, linea, antiguo);

        }

    }
    
    public String reescribir(String direccion, int codificar, String texto, String antiguo) throws IOException{
        String resultado = "se ha reescrito el texto\n";
        String temp = "";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(direccion));
            String bfRead;
            while((bfRead = bf.readLine()) != null){
                if(bfRead.equals(antiguo)){
                }
                else {
                temp = temp + bfRead +"\n";
                }
                
            }
            temp = temp + texto +"\n";
            texto = temp;
        }catch(IOException e){ 
            System.err.println("Archivo no encontrado");
        }
        
        
        
        FileWriter fichero = new FileWriter (direccion);
        fichero.write(texto);
        fichero.close();
        
        return resultado;
    }
    
    public void descifrar(String direccion) throws FileNotFoundException, IOException {
        String letras = "A0BCD1EFG2HIJ3KLM4NÑO5PQR6STU7VWX8YZ9";
        int idCliente;
        File ficheroClientes = new File(direccion);
        Scanner fileIn = new Scanner(new FileReader(ficheroClientes));
        String linea = "";
        String trozos[];
        String textoCodificado = "";
        String texto = "";
        String linea1 = "";
        String antiguo = "";
        
        while (fileIn.hasNextLine()) { 
            linea = fileIn.nextLine();
            trozos = linea.split(";");
            idCliente = Integer.parseInt(trozos[0]);
            String nombre = trozos[1];
            String ape1 = trozos[2];
            String ape2 = trozos[3];
            linea1 = idCliente + ";" + nombre + ";" + ape1 + ";" + ape2; 
            antiguo = idCliente + ";" + nombre + ";" + ape1 + ";" + ape2 + ";" + trozos[4] + ";" + trozos[5]; 
            
            char caracterCalle;
            String descifradoCalle = "";
            String calle = trozos[4].toUpperCase();
            for (int i = 0; i < calle.length(); i++) {
                caracterCalle = calle.charAt(i);
                int pos = letras.indexOf(caracterCalle);
                if(pos == -1){ 
                    descifradoCalle += caracterCalle;
                } else{ 
                    if( pos - 3 < 0 ) {
                        descifradoCalle += letras.charAt( letras.length() + (pos - 3) );
                    } else {
                        descifradoCalle += letras.charAt( (pos - 3) % letras.length() );
                    }
                }
            }
 
            char caracterTelf;
            String descifradoTelf = "";
            for (int j = 0; j < trozos[5].length(); j++) {
                caracterTelf = trozos[5].charAt(j);
                int pos = letras.indexOf(caracterTelf);
                if(pos == -1){
                    descifradoTelf += caracterTelf;
                } else{
                    if ( pos - 3 < 0 ) {
                        descifradoTelf += letras.charAt( letras.length() + (pos - 3) );
                    } else {
                        descifradoTelf += letras.charAt( (pos - 3) % letras.length() );
                    }
                }
            }

            linea = linea1 + ";" + descifradoCalle + ";" + descifradoTelf;
            linea = linea.toUpperCase();

            reescribir("Clientes.txt", idCliente, linea, antiguo);

        }
    }
    
} // CLASS
