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
    private ArrayList<Cliente> clientes;
    private ArrayList<ClienteEnviar> clientesEnviar;
    private PesquisasGestaoUtilizadores pesquisasGestaoUtilizadores;
    
    public ServerModel()
    {
        clientes=new ArrayList<>();
        clientesEnviar=new ArrayList<>();
        pesquisasGestaoUtilizadores=new PesquisasGestaoUtilizadores();
    }
    
    public int regista(RegistoUtilizador registoUtilizador)
    {
        int devolve = ModelGestaoUtilizadores.AdicionaUtil(registoUtilizador);
        
        try
        {
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
        
        if(ret!=-1)
        {
            cliente=new Cliente(login.getNome(), pesquisasGestaoUtilizadores.getNome(ret), true, ret);
            clientes.add(cliente);
            clientesEnviar.add(cliente.getClienteEnviar());
            return 1;
        }        
        return ret;
    }
    
    public ArrayClienteEnviar getClientesEnviar()
    {
        return new ArrayClienteEnviar(clientesEnviar);
    }
}
