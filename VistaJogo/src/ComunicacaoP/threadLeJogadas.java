/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacaoP;

import Cliente.logic.ObservableGame;
import classescomunicacao.ModelJogo.GameModel;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu_f
 */
class threadLeJogadas extends Thread {

    ObservableGame observableGame;
    Socket socketModeloJogo;
    ObjectInputStream in;


    threadLeJogadas(ObservableGame observableGame, Socket socketModeloJogo ) {
        this.observableGame = observableGame;
        try {
            this.in = new ObjectInputStream(socketModeloJogo.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(threadLeJogadas.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.socketModeloJogo = socketModeloJogo;
    }

    @Override
    public void run() {
        while (true) {

            try {
                in = new ObjectInputStream(socketModeloJogo.getInputStream());
               
                Object o = in.readObject();

                if (o instanceof GameModel)
                {
                    observableGame.setGameModel((GameModel) o);
                    observableGame.Update();
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(threadLeJogadas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
