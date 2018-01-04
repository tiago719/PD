/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import classescomunicacao.ClienteEnviar;
import java.util.ArrayList;
import servidorgestao.Cliente;

/**
 *
 * @author Tiago Coutinho
 */
public class AtualizaClientes extends Thread 
{
    private ArrayList<Cliente> clientesLogados;
    private ArrayList<ClienteEnviar> clientesEnviar;
    
    public AtualizaClientes()
    {
        clientesLogados=new ArrayList<>();
        clientesEnviar=new ArrayList<>();
    }
    
    public void addCliente(Cliente cliente)
    {
        clientesLogados.add(cliente);
        clientesEnviar.add(cliente.getClienteEnviar());
    }
    
    public void removeCliente(Cliente cliente)
    {
        for(Cliente c:clientesLogados)
        {
            if(cliente==c)
            {
                clientesLogados.remove(c);
                clientesEnviar.remove(c.getClienteEnviar());
                return;
            }
        }
    }
    
    public void atualizaClientes()
    {
        for(Cliente c:clientesLogados)
        {
            c.atualizaCliente(clientesEnviar);
        }
    }
    
    @Override
    public void run()
    {
        
    }
}
