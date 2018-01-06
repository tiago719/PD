/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.ModelJogo.ObservableGame;
import java.net.Socket;

/**
 *
 * @author edu_f
 */
public class ThreadJogos extends Thread{
    
    private ObservableGame og;
    private String nick1, nick2;
    Comunicacao comunicacao;
    Socket socket;

    public ThreadJogos(String nick1, String nick2, Socket socket) {
        this.nick1 = nick1;
        this.nick2 = nick2;
        this.socket = socket;
    }

    public ObservableGame getOg() {
        return og;
    }

    @Override
    public void run() {
        og = new ObservableGame(nick1, nick2);
        comunicacao= new Comunicacao(socket);
    }
    
}
