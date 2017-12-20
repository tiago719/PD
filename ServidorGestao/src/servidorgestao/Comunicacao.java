/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import classescomunicacao.Login;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Coutinho
 */
public class Comunicacao
{
    public static final int PORTO = 5001;
    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;
    
    public Comunicacao(){}
    
    public void recebeRegistos()
    {
        ServerSocket serverSocket=null;
        try
        {
            serverSocket=new ServerSocket(PORTO);
            while(true)
            {
                Socket nextClient=serverSocket.accept();
                
                ObjectInputStream in = new ObjectInputStream(nextClient.getInputStream());
                
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
                try {
                    serverSocket.close();
            } catch (IOException ex) {
                    System.out.println("Erro a fechar o socket:" + ex);
            }
        }
    }
    
    public void recebeLogins()
    {
        ServerSocket serverSocket=null;
        try
        {
            serverSocket=new ServerSocket(PORTO);
            while(true)
            {
                Socket nextClient=serverSocket.accept();
                
                ObjectInputStream in = new ObjectInputStream(nextClient.getInputStream());
                
                Login returnedObject = (Login)in.readObject();
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
                try {
                    serverSocket.close();
            } catch (IOException ex) {
                    System.out.println("Erro a fechar o socket:" + ex);
            }
        }
    }
}
