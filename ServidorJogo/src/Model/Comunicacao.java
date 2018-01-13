package Model;

import classescomunicacao.Jogadas;
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

    ObservableGame observableGame;

    public Comunicacao(ObservableGame og, int idPar) {
        this.observableGame = og;
        socketUser1 = null;
        socketUser2 = null;
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
                if(socketUser1 == null){
                    socketUser1 = nextCliente;
                }
                else if (!nextCliente.equals(socketUser1) && socketUser2 == null) {
                    socketUser2 = nextCliente;
                } else {
//                    socketUser1 = nextCliente;
                } 

                ObjectOutputStream out = new ObjectOutputStream(nextCliente.getOutputStream());
                out.flush();
                ObjectInputStream input = new ObjectInputStream(nextCliente.getInputStream());
                socketUser1 = nextCliente;

                Object objectRecebidoUtilizador = input.readObject();

                if (objectRecebidoUtilizador instanceof Jogadas) {
                    Jogadas jogada = (Jogadas) objectRecebidoUtilizador;

                    observableGame.placeToken(jogada.getLinha(), jogada.getColuna());

                    ObjectOutputStream out1, out2;
                    if (socketUser1 != null){
                        out1 = new ObjectOutputStream(socketUser1.getOutputStream());
                        out1.writeObject(observableGame.getGameModel());
                        out1.flush();
                    }
                    
                    if (socketUser2 != null){
                        out2 = new ObjectOutputStream(socketUser2.getOutputStream());
                        out2.writeObject(observableGame.getGameModel());
                        out2.flush();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
