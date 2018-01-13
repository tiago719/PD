/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classescomunicacao.*;
import java.util.ArrayList;

/**
 *
 * @author Tiago Coutinho
 */
public class Cliente
{
    private String nomeUtilizador, nome;
    private boolean parFormado, logado;
    private int id;
    private ArrayList<FormarPar> pedidos;
    private FormarPar par;

    public Cliente(String nomeUtilizador, String nome,boolean parFormado, int id)
    {
        this.nomeUtilizador = nomeUtilizador;
        this.id=id;
        this.nome = nome;
        this.parFormado = parFormado;
        pedidos=new ArrayList<>();
        par=null;
    }

    public void setPedidos(ArrayList<FormarPar> pedidos)
    {
        this.pedidos = pedidos;
    }
    
    public Cliente()
    {
        pedidos=new ArrayList<>();
        par=null;
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

    public ClienteEnviar getClienteEnviar()
    {
        return new ClienteEnviar(nomeUtilizador, nome, parFormado);
    }

    public boolean isLogado()
    {
        return logado;
    }

    public void setLogado(boolean logado)
    {
        this.logado = logado;
    }
    
    public void addPedido(FormarPar formarPar)
    {
        pedidos.add(formarPar);
    }

    public ArrayList<FormarPar> getPedidos()
    {
        return pedidos;
    }
    
    public void removePedido(FormarPar formarPar)
    {
        for(FormarPar f : pedidos)
        {
            if((f.getUitlizadorQueFezPedido().equals(formarPar.getUitlizadorQueFezPedido()) || f.getUitlizadorQueFezPedido().equals(formarPar.getUtilizadorQueResponde()))
                    && (f.getUtilizadorQueResponde().equals(formarPar.getUitlizadorQueFezPedido()) || f.getUtilizadorQueResponde().equals(formarPar.getUtilizadorQueResponde())))
            {
                pedidos.remove(f);
                return;
            }
        }
    }
    
    public void setPar(FormarPar formarPar)
    {
        parFormado=true;
        par=formarPar;
        if(par!=null)
            pedidos.clear();
    }
    
    public FormarPar getPar()
    {
        return par;
    }
}
