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
import static servidorgestao.Comunicacao.PORTO2;

/**
 *
 * @author Tiago Coutinho
 */
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
        try {
            serverSocket = new ServerSocket(PORTO2);
            while (true) {
                Socket nextClient = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(nextClient.getInputStream());

                Login returnedObject = (Login) in.readObject();
                
                devolve = ModelGestaoUtilizadores.LoginUtil(returnedObject, nextClient.getInetAddress().getHostAddress(), nextClient.getPort(), atualizaClientes);

                ObjectOutputStream out = new ObjectOutputStream(nextClient.getOutputStream());

                Integer novo = new Integer(devolve);
                out.writeObject(novo);
                out.flush();
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                if(serverSocket!=null)
                    serverSocket.close();
            } catch (IOException ex) {
                System.out.println("Erro a fechar o socket:" + ex);
            }
        }
    }
    }
