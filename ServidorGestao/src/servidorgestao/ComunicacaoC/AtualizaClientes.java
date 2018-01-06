/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import classescomunicacao.*;
import java.util.ArrayList;
import servidorgestao.Cliente;


/**
 *
 * @author Tiago Coutinho
 */
public class AtualizaClientes
{
    private ArrayList<Cliente> clientes;
    private ArrayList<ClienteEnviar> clientesEnviar;
    
    public AtualizaClientes()
    {
        clientes=new ArrayList<>();
        clientesEnviar=new ArrayList<>();
    }
    
    public void addCliente(Cliente cliente)
    {
        clientes.add(cliente);
    }
    
    public void addClienteLogado(Cliente cliente)
    {
        if(cliente.isLogado())
            clientesEnviar.add(cliente.getClienteEnviar());
    }
    
    public void removeCliente(Cliente cliente)
    {
        for(Cliente c:clientes)
        {
            if(cliente==c)
            {
                clientes.remove(c);
                return;
            }
        }
    }
    
    public void removeClienteLogado(Cliente cliente)
    {
        for(ClienteEnviar c:clientesEnviar)
        {
            if(cliente.getClienteEnviar()==c)
            {
                clientesEnviar.remove(c);
                return;
            }
        }
    }
    
    public void atualizaClientes(int id)
    {
        ArrayList<ClienteEnviar> temp= new ArrayList<>();
        for(Cliente c:clientes)
        {
            if(c.isLogado())
            {
                temp.clear();
                for(ClienteEnviar ce: clientesEnviar)
                    if(!c.getNomeUtilizador().equals(ce.getNomeUtilizador()))
                        temp.add(ce);

                c.atualizaCliente(temp);
            }      
        }
    }
}
