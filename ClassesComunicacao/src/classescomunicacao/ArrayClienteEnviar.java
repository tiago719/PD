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
}
