/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.Cliente;
import Model.ObservableGame;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Coutinho
 */
public class EnviaAtualizacoesJogadores implements Observer {

    private ObservableGame observableGame;
    private LogicaComunicacao logicaComunicacao;

    public EnviaAtualizacoesJogadores(ObservableGame observableGame, LogicaComunicacao logicaComunicacao) {
        this.observableGame = observableGame;
        this.logicaComunicacao = logicaComunicacao;
        observableGame.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        ArrayClienteEnviar temp = new ArrayClienteEnviar();

        HashMap<RecebePedidosClientes, Cliente> temp2 = observableGame.getMapa();

        for (Map.Entry<RecebePedidosClientes, Cliente> en : temp2.entrySet()) {
            RecebePedidosClientes key = en.getKey();
         
            temp.getClientes().clear();
            String nomeClienteAtual = observableGame.getCliente(key).getNomeUtilizador();
            for (ClienteEnviar clienteEnviar : observableGame.getClientesEnviar().getClientes()) {
                if (!nomeClienteAtual.equals(clienteEnviar.getNomeUtilizador())) {
                    temp.addCliente(clienteEnviar);
                }
            }
            try {
                key.getOut().reset();
                key.getOut().writeObject(temp);
                key.getOut().flush();
            } catch (IOException ex) {
                observableGame.removeCliente(key);
            }

        }
    }

}
