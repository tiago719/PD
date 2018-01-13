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
    ObjectInputStream in;ObjectOutputStream out;
    

    threadLeJogadas(ObservableGame observableGame, Socket socketModeloJogo, ObjectOutputStream out ) {
        this.observableGame = observableGame;
        this.out = out;
        try {
            this.in = new ObjectInputStream(socketModeloJogo.getInputStream());
        } catch (IOException ex) {

            Logger.getLogger(threadLeJogadas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {

            try {
                out.flush();
//                in = new ObjectInputStream(socketModeloJogo.getInputStream());
               
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
