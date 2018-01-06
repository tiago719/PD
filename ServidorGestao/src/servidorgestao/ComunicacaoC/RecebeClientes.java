/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ModelGestaoUtilizadores;
import classescomunicacao.Login;
import classescomunicacao.Mensagem;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.Cliente;
import static servidorgestao.Comunicacao.PORTO2;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebeClientes extends Thread
{
    private AtualizaClientes atualizaClientes;
    private ArrayList<RecebePedidosClientes> recebePedidosClientes;
    
    public RecebeClientes()
    {
        atualizaClientes=new AtualizaClientes();
        recebePedidosClientes=new ArrayList<>();
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
            Logger.getLogger(RecebeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
        int a=0;
            while (a==0)//TODO:definir condição de paragem 
            {
                try 
                {
                    Socket nextClient = serverSocket.accept();
                    
                    
                    ObjectInputStream in= new ObjectInputStream(nextClient.getInputStream());
                    ObjectOutputStream out = new ObjectOutputStream(nextClient.getOutputStream());
                    Cliente c=new Cliente(null, null, out, in, false, nextClient, -1);
                    atualizaClientes.addCliente(c);                    
                    
                    RecebePedidosClientes novoCliente= new RecebePedidosClientes(c, atualizaClientes);
                    recebePedidosClientes.add(novoCliente);
                    
                    novoCliente.start();
                    
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
                    Logger.getLogger(RecebeClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    
}
