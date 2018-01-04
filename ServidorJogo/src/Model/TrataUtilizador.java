package Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author edu_f
 */
public class TrataUtilizador extends Thread{

    private int porto = 5000;
    
    
    
    @Override
    public void run() {
        ServerSocket server;
        try {
             server = new ServerSocket(porto);
             
             while (true) {
                 Socket nextCliente = server.accept();
                 
                 BufferedReader in = new BufferedReader(new InputStreamReader(nextCliente.getInputStream()));
                 
                 
            }
        } catch (IOException ex) {
            
        }
    }
    
    
    
}
