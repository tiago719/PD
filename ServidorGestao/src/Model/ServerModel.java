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
            arrayClienteEnviar.addCliente(cliente.getClienteEnviar());
            return 1;
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
        try
        {
            int id1=pesquisasGestaoUtilizadores.GetidByUserName(formarPar.getNik1Util());
            int id2=pesquisasGestaoUtilizadores.GetidByUserName(formarPar.getNik2Util());
            return pesquisasGestaoUtilizadores.temPar(id1, id2);
        } catch (SQLException ex)
        {
            Logger.getLogger(ServerModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public void formarPar(FormarPar formarPar, Set<Map.Entry<RecebePedidosClientes, Cliente>> entrySet)
    {
        if (formarPar.getAceite()==Constantes.PEDIDO_FEITO) 
        {
            if(pesquisasGestaoUtilizadores.ExistePedido(formarPar.getNik1Util(), formarPar.getNik2Util()))
                return;
                
            for (Map.Entry<RecebePedidosClientes, Cliente> entry : entrySet) {
                RecebePedidosClientes key = entry.getKey();
                Cliente value = entry.getValue();

                if (formarPar.getNik2Util().equals(value.getNomeUtilizador())) {
                    try {

                        key.getOut().writeObject(formarPar);
                    } catch (IOException ex) {
                        Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        key.getOut().flush();
                    } catch (IOException ex) {
                        Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    pesquisasGestaoUtilizadores.FormaPar(formarPar.getNik1Util(), formarPar.getNik2Util());
                    break;
                }
            }
        } 
        else if(formarPar.getAceite()==Constantes.PEDIDO_RECUSADO)
        {
             pesquisasGestaoUtilizadores.EliminaPar(formarPar.getNik1Util(), formarPar.getNik2Util());
        }
        else if(formarPar.getAceite()==Constantes.PEDIDO_ACEITE)
        {
             formarPar.setIdPar(pesquisasGestaoUtilizadores.ConfirmaPar(formarPar.getNik1Util(), formarPar.getNik2Util()));
             
              for (Map.Entry<RecebePedidosClientes, Cliente> entry : entrySet) {
                RecebePedidosClientes key = entry.getKey();
                Cliente value = entry.getValue();

                if (formarPar.getNik1Util().equals(value.getNomeUtilizador()) || formarPar.getNik2Util().equals(value.getNomeUtilizador())) {
                    value.setParFormado(true);
                    try {
                        key.getOut().writeObject(formarPar);
                    } catch (IOException ex) {
                        Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        key.getOut().flush();
                    } catch (IOException ex) {
                        Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
}
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
