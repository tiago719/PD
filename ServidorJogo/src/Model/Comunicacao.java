package Model;

import classescomunicacao.Jogadas;
import classescomunicacao.ModelJogo.GameModel;
import classescomunicacao.ModelJogo.ObservableGame;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

public class Comunicacao extends Thread {

    public static final int PORTO = 5001;
    public static final int PORTO2 = 5002;
    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;

    ServerSocket socket;
    ObjectInputStream in;

    Socket socketUser1, socketUser2;
    GameModel gameModel;
    Par par;

    Thread t1, t2;

    public Comunicacao(GameModel og, int idPar) {
        this.gameModel = og;
        par = new Par(null, null);
        t1 = new recebeJogadas(par, 1, og);
        t2 = new recebeJogadas(par, 2, og);

        t1.start();
        t2.start();
        try {
            socket = new ServerSocket(5000 + idPar);
        } catch (IOException ex) {
            System.out.println("Comunicacao: " + ex);
        }
    }

    public ObjectInputStream getObjectInputStream() {
        return in;
    }

    @Override
    public void run() {
        try {

            while (true) {
                Socket nextCliente = socket.accept();
                if (par.getUser1() == null) {
                    new ObjectOutputStream(nextCliente.getOutputStream()).writeObject(gameModel);
                    par.setUser1(nextCliente);

                } else if (par.getUser2() == null) {
                    new ObjectOutputStream(nextCliente.getOutputStream()).writeObject(gameModel);
                    par.setUser2(nextCliente);

                } else /*if (par.getUser1().equals(nextCliente))*/ {
//                    socketUser1 = nextCliente;
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
