/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import classescomunicacao.*;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author edu_f
 */
public class ServidorGestao {
    
    public static final int PORTO = 5001;
    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;
    
    public static void main(String[] args) {
        
        ServerSocket serverSocket=null;
        try
        {
            serverSocket=new ServerSocket(PORTO);
            while(true)
            {
                Socket nextClient=serverSocket.accept();
                
                ObjectInputStream in = new ObjectInputStream(nextClient.getInputStream());
                in.readObject();
                
                RegistoUtilizador returnedObject = (RegistoUtilizador)in.readObject();
                ObjectOutputStream out = new ObjectOutputStream(nextClient.getOutputStream());
                
                Integer novo = new Integer(1);
                out.writeObject(novo);
                out.flush();
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            
        }
    }
    
}
