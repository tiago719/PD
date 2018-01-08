/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ObservableGame;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Coutinho
 */
public class EnviaAtualizacoesJogadores implements Observer
{
    private ObservableGame observableGame;
    private LogicaComunicacao logicaComunicacao;
    
    public EnviaAtualizacoesJogadores(ObservableGame observableGame, LogicaComunicacao logicaComunicacao)
    {
        this.observableGame=observableGame;
        this.logicaComunicacao=logicaComunicacao;
        observableGame.addObserver(this);
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        ArrayClienteEnviar temp = new ArrayClienteEnviar();
        for(RecebePedidosClientes rpc:logicaComunicacao.getClientes())
        {
            temp.getClientes().clear();
            String nomeClienteAtual=observableGame.getCliente(rpc).getNomeUtilizador();
            for(ClienteEnviar clienteEnviar : observableGame.getClientesEnviar().getClientes())               
                if(!nomeClienteAtual.equals(clienteEnviar.getNomeUtilizador()))
                {
                    temp.addCliente(clienteEnviar);
                }
            try
            {
                rpc.getOut().reset();
                rpc.getOut().writeObject(temp);
                rpc.getOut().flush();
            } catch (IOException ex)
            {
                Logger.getLogger(EnviaAtualizacoesJogadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
