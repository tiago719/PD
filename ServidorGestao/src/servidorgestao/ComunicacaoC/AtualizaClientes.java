/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import java.util.ArrayList;
import servidorgestao.Cliente;

/**
 *
 * @author Tiago Coutinho
 */
public class AtualizaClientes extends Thread 
{
    private ArrayList<Cliente> clientesLogados;
    
    public AtualizaClientes()
    {
        clientesLogados=new ArrayList<>();
    }
    
    public void addCliente(Cliente cliente)
    {
        clientesLogados.add(cliente);
    }
    
    public void removeCliente(Cliente cliente)
    {
        for(Cliente c:clientesLogados)
        {
            if(cliente==c)
                clientesLogados.remove(c);
        }
    }
    
    public void atualizaClientes()
    {
        for(Cliente c:clientesLogados)
        {
            c.atualizaCliente(clientesLogados);
        }
    }
    
    @Override
    public void run()
    {
        
    }
}
