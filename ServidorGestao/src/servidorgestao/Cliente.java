/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import classescomunicacao.*;
import java.io.IOException;
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
    private ClienteEnviar clienteEnviar;
    private String nomeUtilizador, nome;
    private boolean parFormado;
    private int id;
    private Socket socket;
    private ObjectOutputStream out;

    public Cliente(String nomeUtilizador, String nome, ObjectOutputStream out, boolean parFormado, Socket socket, int id)
    {
        this.nomeUtilizador = nomeUtilizador;
        this.id=id;
        this.nome = nome;
        this.parFormado = parFormado;
        this.socket=socket;
        clienteEnviar = new ClienteEnviar(nomeUtilizador, nome, parFormado);
        this.out = out;
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
        return clienteEnviar;
    }
}
