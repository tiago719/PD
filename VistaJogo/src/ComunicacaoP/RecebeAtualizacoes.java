/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacaoP;

import Cliente.logic.ObservableGame;
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

    public List<FormarPar> getPares() {
        return pares;
    }

    public void setPares(List<FormarPar> pares) {
        this.pares = pares;
    }

    public RecebeAtualizacoes(ObservableGame observableGame, ObjectInputStream in) {
        this.observableGame = observableGame;
        this.in = in;
        pares = new ArrayList<>();
    }

    @Override
    public void run() 
    {
            try {
                while (true)
                {
                    Object returnedObject = in.readObject();

                    if (returnedObject instanceof ArrayClienteEnviar) {
                        observableGame.setClientesLogados((ArrayClienteEnviar) returnedObject);
                    } else if (returnedObject instanceof Mensagem) {
                        mensagem = (Mensagem)returnedObject;
                        observableGame.Update();
                    } 
                    else if(returnedObject instanceof FormarPar)
                    {
                        int ret=((FormarPar) returnedObject).getAceite();
                        if(ret==Constantes.PEDIDO_FEITO)                        
                            pares.add((FormarPar)returnedObject);
                        else if(ret==Constantes.PEDIDO_ACEITE)
                        {
                            observableGame.TemPar((FormarPar)returnedObject);
                        }
                        observableGame.Update();
                    }
                }
            } 
            catch (Exception e) { 
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

    public void RemovePar(int i) {
        if(pares.size() >=i)
        {
            pares.remove(i);
        }
    }

    public void RemovePar(FormarPar pedidoPar)
    {
        for(FormarPar formarPar:pares)
            if(pedidoPar==formarPar)
            {
                pares.remove(pedidoPar);
                return;
            }
    }

    public void RemoveAllPar()
    {
        pares.clear();
    }
}
