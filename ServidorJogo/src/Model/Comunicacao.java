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
    
    ObjectOutputStream out;
    ObservableGame observableGame;

    public Comunicacao(ObservableGame og, int idPar) {
        this.observableGame = og;
        try {
            socket = new ServerSocket(5000+ idPar);
        } catch (IOException ex) {
            System.out.println("Comunicacao: " + ex);
        }
    }

    public ObjectInputStream getObjectInputStream() {
        return in;
    }

    @Override
    public void run() {
        ServerSocket server;
        try {

            server = new ServerSocket(PORTO);
            while (true) {
                Socket nextCliente = server.accept();

                ObjectInputStream in = new ObjectInputStream(nextCliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(nextCliente.getOutputStream());

                Object objectRecebidoUtilizador = in.readObject();

                if (objectRecebidoUtilizador instanceof Jogadas) {
                    Jogadas jogada = (Jogadas)objectRecebidoUtilizador;

                    observableGame.placeToken(jogada.getLinha(), jogada.getColuna());

                    out.writeObject(observableGame);
                }
            }
        } catch (Exception e) {
        }
    }
}
