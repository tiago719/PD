/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import BaseDados.PesquisasGestaoUtilizadores;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.FormarPar;
import classescomunicacao.Login;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.ComunicacaoC.RecebePedidosClientes;

/**
 *
 * @author Tiago Coutinho
 */
public class ObservableGame extends java.util.Observable {

    private ServerModel serverModel;
    private HashMap<RecebePedidosClientes, Cliente> mapa;

    public ServerModel getServerModel() {
        return serverModel;
    }

    public ObservableGame(String IpDB) {
        serverModel = new ServerModel("localhost");
        mapa = new HashMap<>();
        serverModel.setLogados(false);
        
    }
    
    public synchronized void novoCliente(RecebePedidosClientes recebePedidosClientes, Cliente cliente)
    {
        mapa.put(recebePedidosClientes, cliente);
    }

    public Cliente getCliente(RecebePedidosClientes recebePedidosClientes) {
        return mapa.get(recebePedidosClientes);
    }

    public HashMap<RecebePedidosClientes, Cliente> getTodosClientes() {
        return mapa;
    }
    
    public synchronized void removeCliente(RecebePedidosClientes recebePedidosClientes)
    {
        serverModel.setLogOut(mapa.get(recebePedidosClientes));
        mapa.remove(recebePedidosClientes);

        setChanged();
        notifyObservers();
        recebePedidosClientes.stop();
        
    }

    //getters
    public ArrayClienteEnviar getClientesEnviar() {
        return serverModel.getClientesEnviar(mapa);
    }

    //
    public int regista(RegistoUtilizador registoUtilizador) {
        return serverModel.regista(registoUtilizador);
    }

    public int login(Login login ,RecebePedidosClientes recebePedidosClientes) {

        Cliente cliente=new Cliente();
        int ret = serverModel.login(login, cliente);
        if (ret > 0) {
            novoCliente(recebePedidosClientes, cliente);
        }
        return ret;
    } 
    
    public synchronized void update()
    { 
        setChanged();
        notifyObservers();
    }

    public HashMap getMapa() {
        return mapa;
    }
    public void FormaPar(FormarPar formarPar) 
    {
        serverModel.formarPar(formarPar,mapa.entrySet());
        
        setChanged();
        notifyObservers();
    }

    public boolean temPar(FormarPar formarPar)
    {
        return serverModel.temPar(formarPar);
    }
    
    
//    public boolean ExistePar(FormarPar formarPar) {
//        try {
//            PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
//            
//            int id = p.GetidByUserName(formarPar.getNik1Util());
//            int id1 = p.GetidByUserName(formarPar.getNik2Util());
//            return p.VeirficaExiste(id,id1);
//        } catch (SQLException ex) {
//            Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
//        
//        }
//        return false;
//    }
}
