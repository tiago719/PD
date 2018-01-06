/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classescomunicacao.ClienteEnviar;
import classescomunicacao.Login;
import classescomunicacao.RegistoUtilizador;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import servidorgestao.ComunicacaoC.RecebePedidosClientes;

/**
 *
 * @author Tiago Coutinho
 */
public class ObservableGame extends java.util.Observable
{
    private ServerModel serverModel;
    private HashMap<RecebePedidosClientes, Cliente> mapa;
    
    public ObservableGame()
    {
        serverModel=new ServerModel();
        mapa=new HashMap<>();
    }
    
    public void novoCliente(RecebePedidosClientes recebePedidosClientes, Cliente cliente)
    {
        mapa.put(recebePedidosClientes, cliente);
    }
    
    //getters
    public ArrayList<ClienteEnviar> getClientesEnviar()
    {
        return serverModel.getClientesEnviar();
    }
    
    //
    
    public int regista(RegistoUtilizador registoUtilizador)
    {
        return serverModel.regista(registoUtilizador);
    }
    
    public int login(Login login, RecebePedidosClientes recebePedidosClientes)
    {
        Cliente cliente=null;
        int ret = serverModel.login(login,cliente);
        
        novoCliente(recebePedidosClientes, cliente);
        
        setChanged();
        notifyObservers();
        
        return ret;
    } 
}
