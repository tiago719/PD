/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ModelGestaoUtilizadores;
import Model.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import classescomunicacao.Mensagem;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.Cliente;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebePedidosClientes extends Thread
{
    Cliente cliente;
    ObjectInputStream in;
    ObjectOutputStream out;
    AtualizaClientes atualizaClientes;
    
    public RecebePedidosClientes(Cliente cliente, AtualizaClientes atualizaClientes)
    {
        this.cliente=cliente;
        in=cliente.getIn();
        out=cliente.getOut();
        this.atualizaClientes=atualizaClientes;
    }
    
    @Override
    public void run()
    {
        int a=0;
        while(a==0)//TODO: define condicao de paragem
        {
            try
            {            
                Object returnedObject=in.readObject();
                if(returnedObject instanceof RegistoUtilizador)
                {
                    enviaRespostaCliente((RegistoUtilizador)returnedObject);
                }
                else if(returnedObject instanceof Login)
                {
                    ModelGestaoUtilizadores.LoginUtil((Login)returnedObject, cliente, atualizaClientes);
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
            cliente.getSocket().close();
        } catch (IOException ex)
        {
            Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void enviaRespostaCliente(RegistoUtilizador registo)
    {
        try
        {
            PesquisasGestaoUtilizadores pesquisasGestaoUtilizadores= new PesquisasGestaoUtilizadores();//TODO: nao devia ser só uma referencia em vez de estarmos sempre a criar?
            
            int devolve = ModelGestaoUtilizadores.AdicionaUtil(registo);
            
            if(devolve==1)
            {
                    try {
                        pesquisasGestaoUtilizadores.AdicionaUtilizador(registo.getNome(), registo.getUsername(), registo.getPassword());
                } catch (NoSuchAlgorithmException ex) 
                {
                    devolve=-5;
                    Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            Integer novo = new Integer(devolve);
            out.writeObject(novo);
            out.flush();
        } catch (IOException ex)
        {
            Logger.getLogger(RecebeClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
