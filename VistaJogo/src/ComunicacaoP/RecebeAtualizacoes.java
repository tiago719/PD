/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacaoP;

import Cliente.logic.ObservableGame;
import Cliente.ui.gui.EcraInicial.PedidoPar;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.Constantes;
import static classescomunicacao.Constantes.PEDIDO_ACEITE;
import classescomunicacao.FormarPar;
import classescomunicacao.Mensagem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebeAtualizacoes extends Thread {

    ObservableGame observableGame;
    ObjectInputStream in;
    public Mensagem mensagem;
    public List<FormarPar> pares;

    public synchronized List<FormarPar> getPares() {
        return pares;
    }

    public void setPares(List<FormarPar> paresRecebidos) {
        
        pares.clear();
        
        for(FormarPar formarPar : paresRecebidos)
        {
            if(!formarPar.getUitlizadorQueFezPedido().equals(observableGame.getUserName()))
                pares.add(formarPar);
        }
    }

    public RecebeAtualizacoes(ObservableGame observableGame, ObjectInputStream in) {
        this.observableGame = observableGame;
        this.in = in;
        pares = new ArrayList<>();
    }

    @Override
    public void run() 
    {
        boolean flag=false;
            try {
                while (true)
                {
                    Object returnedObject = in.readObject();
                    flag=false;
                    if (returnedObject instanceof ArrayClienteEnviar) 
                    {
                        ArrayClienteEnviar arrayClienteEnviar=(ArrayClienteEnviar) returnedObject;
                        observableGame.setClientesLogados(arrayClienteEnviar.getClientes());
                        observableGame.SetPares(arrayClienteEnviar.getListaPedidos());
                        observableGame.setPar(arrayClienteEnviar.getPar());
                    } else if (returnedObject instanceof Mensagem) {
                        mensagem = (Mensagem)returnedObject;
                        observableGame.Update();
                    } 
                }
            } 
            catch (IOException | ClassNotFoundException e) { 
                Logger.getLogger("ERRO : " + e.getMessage());
            }
        }
    
    public Mensagem getMensagem() {
        return mensagem;
    }
    
    public void LimpaMensagem()
    {
        mensagem = null;
    }

    public synchronized void RemovePar(int i) {
        if(pares.size() >=i)
        {
            pares.remove(i);
        }
    }

    public synchronized void RemovePar(FormarPar pedidoPar)
    {
        for(FormarPar formarPar:pares)
            if(pedidoPar==formarPar)
            {
                pares.remove(pedidoPar);
                return;
            }
    }

    public synchronized void RemoveAllPar()
    {
        pares.clear();
    }

    public ArrayList<FormarPar> getPedidosPares()
    {
        return (ArrayList) pares;
    }
}
