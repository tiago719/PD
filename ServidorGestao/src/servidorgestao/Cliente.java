/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import classescomunicacao.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Coutinho
 */
public class Cliente
{
    private ClienteEnviar informacaoEnviar;
    private String nomeUtilizador, nome;
    private boolean parFormado, logado;
    private int id;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Socket getSocket() {
        return socket;
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public Cliente(String nomeUtilizador, String nome, ObjectOutputStream out,ObjectInputStream in, boolean parFormado, Socket socket, int id)
    {
        this.nomeUtilizador = nomeUtilizador;
        this.id=id;
        this.nome = nome;
        this.parFormado = parFormado;
        this.socket=socket;
        informacaoEnviar = new ClienteEnviar(nomeUtilizador, nome, parFormado);
        this.out = out;
        this.in=in;
    }

    public String getNomeUtilizador()
    {
        return nomeUtilizador;
    }

    public void setNomeUtilizador(String nomeUtilizador)
    {
        this.nomeUtilizador = nomeUtilizador;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public boolean isParFormado()
    {
        return parFormado;
    }

    public void setParFormado(boolean parFormado)
    {
        this.parFormado = parFormado;
    } 

    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id=id;
    }
    
    public void atualizaCliente(ArrayList<ClienteEnviar> clientesEnviar)
    {
        try
        {
            out.writeObject(clientesEnviar);
            out.flush();
        } catch (IOException ex)
        {
            System.err.println(ex);
        }  
    }
    
    public ClienteEnviar getClienteEnviar()
    {
        return informacaoEnviar;
    }

    public boolean isLogado()
    {
        return logado;
    }

    public void setLogado(boolean logado)
    {
        this.logado = logado;
    }

    public ObjectInputStream getIn()
    {
        return in;
    }
}
