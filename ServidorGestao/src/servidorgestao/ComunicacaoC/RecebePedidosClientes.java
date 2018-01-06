/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ModelGestaoUtilizadores;
import BaseDados.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import classescomunicacao.Mensagem;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Cliente;
import Model.ObservableGame;
import java.net.Socket;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebePedidosClientes extends Thread
{
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ObservableGame observableGame;
    
    public RecebePedidosClientes(Socket socket, ObservableGame observableGame)
    {
        try
        {
            this.observableGame=observableGame;
            this.socket=socket;
            in=new ObjectInputStream(socket.getInputStream());
            out=new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex)
        {
            Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void run()
    {
        int a=0, ret;
        while(a==0)//TODO: define condicao de paragem
        {
            try
            {            
                Object returnedObject=in.readObject();
                if(returnedObject instanceof RegistoUtilizador)
                {
                    ret=observableGame.regista((RegistoUtilizador)returnedObject);
                    
                    Integer novo = new Integer(ret);
                    out.writeObject(novo);
                    out.flush();
                }
                else if(returnedObject instanceof Login)
                {
                    ret=observableGame.login((Login)returnedObject, this);
                    
                    Integer novo = new Integer(ret);
                    out.writeObject(novo);
                    out.flush();
                }
                else if(returnedObject instanceof Mensagem )
                {
                    //TODO: Trabalha preto
                }
            } catch (IOException | ClassNotFoundException ex)
            {
                Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try
        {
            socket.close();
        } catch (IOException ex)
        {
            Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObjectOutputStream getOut()
    {
        return out;
    }

    public void setOut(ObjectOutputStream out)
    {
        this.out = out;
    }

    public ObjectInputStream getIn()
    {
        return in;
    }

    public void setIn(ObjectInputStream in)
    {
        this.in = in;
    }
    
}
