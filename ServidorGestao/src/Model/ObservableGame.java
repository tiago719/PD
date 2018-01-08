/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.Login;
import classescomunicacao.RegistoUtilizador;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import servidorgestao.ComunicacaoC.RecebePedidosClientes;

/**
 *
 * @author Tiago Coutinho
 */
public class ObservableGame extends java.util.Observable
{
    private ServerModel serverModel;
    private HashMap<RecebePedidosClientes, Cliente> mapa;

    public ServerModel getServerModel() {
        return serverModel;
    }
    
    public ObservableGame()
    {
        serverModel=new ServerModel();
        mapa=new HashMap<>();
        serverModel.setLogados(false);
    }
    
    public void novoCliente(RecebePedidosClientes recebePedidosClientes, Cliente cliente)
    {
        mapa.put(recebePedidosClientes, cliente);
    }
    
    public Cliente getCliente(RecebePedidosClientes recebePedidosClientes)
    {
        return mapa.get(recebePedidosClientes);
    }
    
    public HashMap<RecebePedidosClientes, Cliente> getTodosClientes()
    {
        return mapa;
    }
    
    public void removeCliente(RecebePedidosClientes recebePedidosClientes)
    {
        recebePedidosClientes.stop();
        serverModel.setLogOut(mapa.get(recebePedidosClientes));
        mapa.remove(recebePedidosClientes);
        
        setChanged();
        notifyObservers();
    }    
    //getters
    public ArrayClienteEnviar getClientesEnviar()
    {
        return serverModel.getClientesEnviar();
    }
    
    //
    
    public int regista(RegistoUtilizador registoUtilizador)
    {
        return serverModel.regista(registoUtilizador);
    }
    
    public int login(Login login, Cliente cliente, RecebePedidosClientes recebePedidosClientes)
    {
        int ret=serverModel.login(login,cliente);
        if(ret==1)
            novoCliente(recebePedidosClientes, cliente);
        return ret;
    } 
    
    public void update()
    { 
        setChanged();
        notifyObservers();
    }

    public HashMap getMapa()
    {
        return mapa;
    }
}
