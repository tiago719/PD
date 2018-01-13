/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BaseDados.PesquisasGestaoUtilizadores;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.Constantes;
import classescomunicacao.FormarPar;
import classescomunicacao.Login;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.ComunicacaoC.RecebePedidosClientes;

/**
 *
 * @author Tiago Coutinho
 */
public class ServerModel
{
    private PesquisasGestaoUtilizadores pesquisasGestaoUtilizadores;
    private ArrayClienteEnviar arrayClienteEnviar;

    public ServerModel()
    {
        arrayClienteEnviar=new ArrayClienteEnviar();
        pesquisasGestaoUtilizadores=new PesquisasGestaoUtilizadores();
    }
    
    public int regista(RegistoUtilizador registoUtilizador)
    {
        int devolve = ModelGestaoUtilizadores.AdicionaUtil(registoUtilizador);
        
        try
        {
            if(devolve==1)
                pesquisasGestaoUtilizadores.AdicionaUtilizador(registoUtilizador.getNome(), registoUtilizador.getUsername(), registoUtilizador.getPassword());
        } catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
            return -5;
        }        
        return devolve;
    }
    
    public int login(Login login, Cliente cliente)
    {
        int ret=ModelGestaoUtilizadores.LoginUtil(login,pesquisasGestaoUtilizadores);
        
        if(ret!=-1 && ret!=0)
        {
            cliente.setNomeUtilizador(login.getNome());
            cliente.setNome(pesquisasGestaoUtilizadores.getNome(ret));
            cliente.setLogado(true);
            cliente.setId(ret);
            
            cliente.setPedidos(pesquisasGestaoUtilizadores.getPedidosUtilizador(cliente.getNomeUtilizador()));
            cliente.setPar(pesquisasGestaoUtilizadores.getPar(cliente.getNomeUtilizador()));
        }        
        return ret;
    }
    
    public ArrayClienteEnviar getClientesEnviar()
    {
        return arrayClienteEnviar;
    }

    public void setLogados(boolean b)
    {
        pesquisasGestaoUtilizadores.setLogados();
    }

    public void setLogOut(Cliente cliente)
    {
        arrayClienteEnviar.removeCliente(cliente.getClienteEnviar());
        pesquisasGestaoUtilizadores.setLogout(cliente);
    }

    public boolean temPar(FormarPar formarPar)
    {
        int id1=pesquisasGestaoUtilizadores.GetidByUserName(formarPar.getUitlizadorQueFezPedido());
        int id2=pesquisasGestaoUtilizadores.GetidByUserName(formarPar.getUtilizadorQueResponde());
        return pesquisasGestaoUtilizadores.temPar(id1, id2);
    }
    
    public void formarPar(FormarPar formarPar, Set<Map.Entry<RecebePedidosClientes, Cliente>> entrySet)
    {
        if (formarPar.getAceite()==Constantes.PEDIDO_FEITO) 
        {
            if(pesquisasGestaoUtilizadores.ExistePedido(formarPar.getUitlizadorQueFezPedido(), formarPar.getUtilizadorQueResponde()))
                return;
            
            pesquisasGestaoUtilizadores.FormaPar(formarPar.getUitlizadorQueFezPedido(), formarPar.getUtilizadorQueResponde());
                
            for (Map.Entry<RecebePedidosClientes, Cliente> entry : entrySet) {
                RecebePedidosClientes key = entry.getKey();
                Cliente value = entry.getValue();

                if (formarPar.getUitlizadorQueFezPedido().equals(value.getNomeUtilizador())||formarPar.getUtilizadorQueResponde().equals(value.getNomeUtilizador())) 
                {
                    value.addPedido(formarPar);
                }
            }
        } 
        else if(formarPar.getAceite()==Constantes.PEDIDO_RECUSADO)
        {
             pesquisasGestaoUtilizadores.EliminaPar(formarPar.getUitlizadorQueFezPedido(), formarPar.getUtilizadorQueResponde());
             
             for (Map.Entry<RecebePedidosClientes, Cliente> entry : entrySet) {
                RecebePedidosClientes key = entry.getKey();
                Cliente value = entry.getValue();

                if (formarPar.getUitlizadorQueFezPedido().equals(value.getNomeUtilizador())||formarPar.getUtilizadorQueResponde().equals(value.getNomeUtilizador())) 
                {
                    value.removePedido(formarPar);
                }
            }
             
        }
        else if(formarPar.getAceite()==Constantes.PEDIDO_ACEITE)
        {
            formarPar.setIdPar(pesquisasGestaoUtilizadores.ConfirmaPar(formarPar.getUitlizadorQueFezPedido(), formarPar.getUtilizadorQueResponde()));
             
              for (Map.Entry<RecebePedidosClientes, Cliente> entry : entrySet) {
                RecebePedidosClientes key = entry.getKey();
                Cliente value = entry.getValue();

                if (formarPar.getUitlizadorQueFezPedido().equals(value.getNomeUtilizador()) || formarPar.getUtilizadorQueResponde().equals(value.getNomeUtilizador())) 
                {
                    value.setPar(formarPar);
                }
            }
            pesquisasGestaoUtilizadores.EliminaPedidos(formarPar.getUitlizadorQueFezPedido(), formarPar.getUtilizadorQueResponde());
        }
    }

    ArrayClienteEnviar getClientesEnviar(HashMap<RecebePedidosClientes, Cliente> mapa)
    {
        arrayClienteEnviar.clear();
        for(Cliente cliente : mapa.values())
        {
            arrayClienteEnviar.addCliente(cliente.getClienteEnviar());
        }
        return arrayClienteEnviar;
    }
}
