/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classescomunicacao;

import java.io.Serializable;

/**
 *
 * @author Tiago Coutinho
 */
public class ClienteEnviar implements Serializable
{
    static final long serialVersionUID = 1L;
    private String nomeUtilizador, nome;
    private boolean parFormado;

    public ClienteEnviar(String nomeUtilizador, String nome, boolean parFormado)
    {
        this.nomeUtilizador = nomeUtilizador;
        this.nome = nome;
        this.parFormado = parFormado;
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
}
