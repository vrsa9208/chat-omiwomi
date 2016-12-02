
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrador
 */
public class ConexionCliente extends Thread{
    
    private Socket mSocket;
    private boolean mConectado;
    private DataInputStream mEntradaDatos;
    private DataOutputStream mSalidaDatos;
    
    public ConexionCliente(Socket socket){
        this.mSocket = socket;
        this.mConectado = true;
        try{
            this.mEntradaDatos = new DataInputStream(this.mSocket.getInputStream());
            this.mSalidaDatos = new DataOutputStream(this.mSocket.getOutputStream());
        } catch(IOException ex){
            System.out.println("Error al crear la entrada y salida del cliente: " + ex.getMessage());
        }
    }

    @Override
    public void run() {
        System.out.println("Cliente " + this.mSocket.getInetAddress().getHostName() + " conectado.");
        while(mConectado){
            try {
                String mensajeRecibido = mEntradaDatos.readUTF();
                System.out.println("Mensaje recibido de " + this.mSocket.getInetAddress().getHostName() + ": " + mensajeRecibido);
            } catch(IOException ex){
                mConectado = false;
            }
        }
        System.out.println("Cliente " + this.mSocket.getInetAddress().getHostName() + " desconectado.");
        desconectar();
    }
    
    private void desconectar(){
        try{
            this.mEntradaDatos.close();
        } catch(IOException ex){
            System.out.println("Error al cerrar el flujo de datos de entrada del cliente " + this.mSocket.getInetAddress().getHostName() + ": " + ex.getMessage());
        }
        
        try{
            this.mSalidaDatos.close();
        } catch(IOException ex){
            System.out.println("Error al cerrar el flujo de datos de salida del cliente " + this.mSocket.getInetAddress().getHostName() + ": " + ex.getMessage());
        }
        
        try{
            this.mSocket.close();
        } catch(IOException ex){
            System.out.println("Error al cerrar el socket " + ex.getMessage());
        }
    }
}
