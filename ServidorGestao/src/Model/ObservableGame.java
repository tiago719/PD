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

    public ObservableGame() {
        serverModel = new ServerModel();
        mapa = new HashMap<>();
        serverModel.setLogados(false);
    }

    public void novoCliente(RecebePedidosClientes recebePedidosClientes, Cliente cliente) {
        mapa.put(recebePedidosClientes, cliente);
    }

    public Cliente getCliente(RecebePedidosClientes recebePedidosClientes) {
        return mapa.get(recebePedidosClientes);
    }

    public HashMap<RecebePedidosClientes, Cliente> getTodosClientes() {
        return mapa;
    }

    public void removeCliente(RecebePedidosClientes recebePedidosClientes) {
        recebePedidosClientes.stop();
        serverModel.setLogOut(mapa.get(recebePedidosClientes));
        mapa.remove(recebePedidosClientes);

        setChanged();
        notifyObservers();
    }

    //getters
    public ArrayClienteEnviar getClientesEnviar() {
        return serverModel.getClientesEnviar();
    }

    //
    public int regista(RegistoUtilizador registoUtilizador) {
        return serverModel.regista(registoUtilizador);
    }

    public int login(Login login, Cliente cliente, RecebePedidosClientes recebePedidosClientes) {
        int ret = serverModel.login(login, cliente);
        if (ret == 1) {
            novoCliente(recebePedidosClientes, cliente);
        }
        return ret;
    }

    public void update() {
        setChanged();
        notifyObservers();
    }

    public HashMap getMapa() {
        return mapa;
    }

    public void FormaPar(FormarPar formarPar) {

        if (!formarPar.isAceite()) {
            for (Map.Entry<RecebePedidosClientes, Cliente> entry : mapa.entrySet()) {
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
                    PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
                    p.FormaPar(formarPar.getNik1Util(), formarPar.getNik2Util());
                    break;
                }
            }
        } else {
             PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
             p.ConfirmaPar(formarPar.getNik1Util(), formarPar.getNik2Util());
        }
    }

    public boolean ExistePar(FormarPar formarPar) {
        try {
            PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
            
            int id = p.GetidByUserName(formarPar.getNik1Util());
            int id1 = p.GetidByUserName(formarPar.getNik2Util());
            return p.VeirficaExiste(id,id1);
        } catch (SQLException ex) {
            Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return false;
    }
}
