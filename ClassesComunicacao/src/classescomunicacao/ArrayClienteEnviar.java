/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classescomunicacao;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Tiago Coutinho
 */
public class ArrayClienteEnviar implements Serializable
{
    private ArrayList<ClienteEnviar> clientes;
    private FormarPar parFormado;
    private ArrayList<FormarPar> listaPedidos;

    public ArrayClienteEnviar()
    {
        clientes=new ArrayList<>();
    }

    public ArrayList<ClienteEnviar> getClientes()
    {
        return clientes;
    }

    public void setClientes(ArrayList<ClienteEnviar> clientes)
    {
        this.clientes = clientes;
    }
    
    public void addCliente(ClienteEnviar clienteEnviar)
    {
        clientes.add(clienteEnviar);
    }

    public void removeCliente(ClienteEnviar clienteEnviar)
    {
        clientes.remove(clienteEnviar);
    }  

    public void clear()
    {
        clientes.clear();
    }
    
    public void addPedido(FormarPar formarPar)
    {
        listaPedidos.add(formarPar);
    }
    
    public void removePedido(FormarPar formarPar)
    {
        for(FormarPar f:listaPedidos)
        {
            if((f.getUitlizadorQueFezPedido().equals(formarPar.getUitlizadorQueFezPedido()) || f.getUitlizadorQueFezPedido().equals(formarPar.getUtilizadorQueResponde()))
                    && (f.getUtilizadorQueResponde().equals(formarPar.getUitlizadorQueFezPedido()) || f.getUtilizadorQueResponde().equals(formarPar.getUtilizadorQueResponde())))
            {
                listaPedidos.remove(f);
                return;
            }
        }
    }
    
    public void setPar(FormarPar formarPar)
    {
        parFormado=formarPar;
    }
    
    public FormarPar getPar()
    {
        return parFormado;
    }
    
    public void setArrayPedidos(ArrayList<FormarPar> lista)
    {
        listaPedidos=lista;
    }

    public ArrayList<FormarPar> getListaPedidos()
    {
        return listaPedidos;
    }
}
