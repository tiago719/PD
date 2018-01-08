/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.Cliente;
import Model.ObservableGame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servidorgestao.Constantes.PORTO2;

/**
 *
 * @author Tiago Coutinho
 */
public class LogicaComunicacao extends Thread
{
    private ObservableGame observableGame;
    EnviaAtualizacoesJogadores enviaAtualizacoesJogadores;
    
    public LogicaComunicacao(ObservableGame observableGame)
    {
        this.observableGame=observableGame;
        enviaAtualizacoesJogadores=new EnviaAtualizacoesJogadores(observableGame, this);
    }
    
    @Override
    public void run()
    {
        ServerSocket serverSocket = null;

        try
        {
            serverSocket = new ServerSocket(PORTO2);
        } catch (IOException ex)
        {
            Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        int a=0;
            while (a==0)//TODO:definir condição de paragem 
            {
                try 
                {
                    Socket nextClient = serverSocket.accept();              
                    
                    RecebePedidosClientes novoCliente= new RecebePedidosClientes(nextClient,observableGame);
                    
                    novoCliente.setDaemon(true);
                    novoCliente.start();
                    
                 }
                catch (IOException e) 
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
                    Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
}
