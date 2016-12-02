
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.Socket;
import java.net.UnknownHostException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class Prueba {
    
    public static void main(String[] args) {
        System.out.println("Cliente del chat");
        
        int puertoConexion = 9001;
        String direccionConexion = "127.0.0.1"; //localhost
        Socket socket = null;
        DataInputStream entradaDatos = null;
        DataOutputStream salidaDatos = null;
        
        try {
            socket = new Socket("127.0.0.1", puertoConexion);
            entradaDatos = new DataInputStream(socket.getInputStream());
            salidaDatos = new DataOutputStream(socket.getOutputStream());
            
            salidaDatos.writeUTF("Soy el cliente. Estoy probando");
        } catch (UnknownHostException ex) {
            System.out.println("No se ha podido conectar con el servidor: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("No se ha podido conectar con el servidor: " + ex.getMessage());
        }
        //Desconectar
        try{
            if(entradaDatos != null) entradaDatos.close();
        } catch(IOException ex){
            System.out.println("Error al cerrar el flujo de datos de entrada del cliente: " + ex.getMessage());
        }
        
        try{
            if(salidaDatos != null) salidaDatos.close();
        } catch(IOException ex){
            System.out.println("Error al cerrar el flujo de datos de salida del cliente: " + ex.getMessage());
        }
        
        try{
            if(socket != null) socket.close();
        } catch(IOException ex){
            System.out.println("Error al cerrar el socket: " + ex.getMessage());
        }
    }
    
}
