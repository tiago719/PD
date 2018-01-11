/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BaseDados.PesquisasGestaoUtilizadores;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.Login;
import classescomunicacao.RegistoUtilizador;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            cliente.setClienteEnviar();
            arrayClienteEnviar.addCliente(cliente.getClienteEnviar());
            return 1;
        }        
        return ret;
    }
    
    public ArrayClienteEnviar getClientesEnviar()
    {
        return arrayClienteEnviar;
    }

    void setLogados(boolean b)
    {
        pesquisasGestaoUtilizadores.setLogados(b);
    }

    void setLogOut(Cliente cliente)
    {
        arrayClienteEnviar.removeCliente(cliente.getClienteEnviar());
        pesquisasGestaoUtilizadores.setLogout(cliente);
    }
}
