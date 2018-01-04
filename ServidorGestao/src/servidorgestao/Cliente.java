/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import classescomunicacao.ClienteEnviar;
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
    private String nomeUtilizador, nome, ip;
    private boolean parFormado;
    private int porto;
    private Socket socket;
    private ObjectOutputStream out;

    public Cliente(String nomeUtilizador, String nome, boolean parFormado, String ip, int porto)
    {
        this.nomeUtilizador = nomeUtilizador;
        this.nome = nome;
        this.parFormado = parFormado;
        this.ip=ip;
        this.porto=porto;
        try
        {
            socket=new Socket(ip,porto);//TODO: fechar socket
            socket.setSoTimeout(2000);
            
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex)
        {
            System.out.println("Erro a criar Socket: \n" + ex);
        }
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

    public String getIp()
    {
        return ip;
    }

    public int getPorto()
    {
        return porto;
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
