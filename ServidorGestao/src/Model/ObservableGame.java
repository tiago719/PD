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
import servidorgestao.ComunicacaoC.RecebePedidosClientes;

/**
 *
 * @author Tiago Coutinho
 */
public class ObservableGame extends java.util.Observable
{
    private ServerModel serverModel;

    public ServerModel getServerModel() {
        return serverModel;
    }
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
    
    public Cliente getCliente(RecebePedidosClientes recebePedidosClientes)
    {
        return mapa.get(recebePedidosClientes);
    }
    
    public HashMap<RecebePedidosClientes, Cliente> getTodosClientes()
    {
        return mapa;
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
    
    public int login(Login login, RecebePedidosClientes recebePedidosClientes)
    {
        return serverModel.login(login,null);
    } 
    
    public void update()
    { 
        setChanged();
        notifyObservers();
    }
}
