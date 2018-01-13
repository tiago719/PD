/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classescomunicacao.Jogadas;
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
class recebeJogadas extends Thread {

    Par par;
    int user;
    GameModel gameModel;

    public recebeJogadas(Par par, int u, GameModel gameModel) {
        this.par = par;
        this.user = u;
        this.gameModel = gameModel;

    }

    public Socket getSocketAtual() {
        switch (user) {
            case 1:
                return par.getUser1();
            case 2:
                return par.getUser2();
        }
        return null;
    }

    public ObjectInputStream getInAtual() {
        switch (user) {
            case 1:
                return par.getIn1();
            case 2:
                return par.getIn2();
        }
        return null;
    }

    public ObjectOutputStream getOutAtual() {
        switch (user) {
            case 1:
                return par.getOut1();
            case 2:
                return par.getOut2();
        }
        return null;
    }

    @Override
    public void run() {
        while (true) {
            if (getSocketAtual() != null) {
                try {
                    ObjectOutputStream out = getOutAtual();
                    out.flush();
                    ObjectInputStream input = getInAtual();

                    Object objectRecebidoUtilizador = input.readObject();

                    if (objectRecebidoUtilizador instanceof Jogadas) {
                        Jogadas jogada = (Jogadas) objectRecebidoUtilizador;

                        gameModel.placeToken(jogada.getLinha(), jogada.getColuna());

                        if (par.getUser1() != null) {

                            par.getOut1().writeObject(gameModel);
                            par.getOut1().flush();
                        }

                        if (par.getUser2() != null) {

                            par.getOut2().writeObject(gameModel);
                            par.getOut2().flush();
                        }
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    Logger.getLogger(recebeJogadas.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

}
