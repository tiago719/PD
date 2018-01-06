/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ModelGestaoUtilizadores;
import classescomunicacao.Login;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servidorgestao.Comunicacao.PORTO2;

public class RecebeLogin extends Thread 
{
    AtualizaClientes atualizaClientes;
    
    public RecebeLogin()
    {
        atualizaClientes=new AtualizaClientes();
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        int devolve = 0;
       
        try
        {
            serverSocket = new ServerSocket(PORTO2);
        } catch (IOException ex)
        {
            Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        int a=0;
            while (a==0) //TODO:definir condição de paragem 
            {
                try 
                {
                    Socket nextClient = serverSocket.accept();
                    ObjectInputStream in = new ObjectInputStream(nextClient.getInputStream());
                    Login returnedObject = (Login) in.readObject();
                    ObjectOutputStream out = new ObjectOutputStream(nextClient.getOutputStream());
                    ModelGestaoUtilizadores.LoginUtil(returnedObject, nextClient, out, atualizaClientes);

                 }
                catch (Exception e) 
                {
                    System.out.println(e);
                }
            }
            
            if(serverSocket!=null)
            {
                try 
                {
                    serverSocket.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }

}
