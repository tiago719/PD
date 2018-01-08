/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classescomunicacao.*;

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

    public Cliente(String nomeUtilizador, String nome,boolean parFormado, int id)
    {
        this.nomeUtilizador = nomeUtilizador;
        this.id=id;
        this.nome = nome;
        this.parFormado = parFormado;
        informacaoEnviar = new ClienteEnviar(nomeUtilizador, nome, parFormado);
    }
    
    public Cliente(){}

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

    void setClienteEnviar()
    {
        informacaoEnviar = new ClienteEnviar(nomeUtilizador, nome, parFormado);
    }
}
