
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class Server {
    
    public static void main(String[] args) {
        int puertoServidor = 9001;
        int numeroMaximoConexiones = 50;
        List<Cliente> listaClientes = new ArrayList();
        
        System.out.println("Iniciando servidor de chat");
        try {
            ServerSocket serverSocket = new ServerSocket(puertoServidor, numeroMaximoConexiones);
            
            while(true){
                Socket clienteSocket = serverSocket.accept();
                Cliente cliente = new Cliente(clienteSocket);
                listaClientes.add(cliente);
                cliente.start();
            }
        } catch(IOException ex){
            System.out.println("Error al iniciar el servidor de chat: " + ex.getMessage());
        }
                
    }
}
