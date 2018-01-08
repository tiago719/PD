/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacaoP;

import Cliente.logic.ObservableGame;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.Mensagem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
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

    public RecebeAtualizacoes(ObservableGame observableGame, ObjectInputStream in) {
        this.observableGame = observableGame;
        this.in = in;
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
                }
            } 
            catch (Exception e) { }
        }
    
    public Mensagem getMensagem() {
        return mensagem;
    }
    
    public void LimpaMensagem()
    {
        mensagem = null;
    }
}
