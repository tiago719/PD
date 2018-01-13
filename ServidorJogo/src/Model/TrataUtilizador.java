package Model;

import classescomunicacao.AcoesPartida;
import classescomunicacao.ConstantesIps;
import classescomunicacao.Jogadas;
import classescomunicacao.ModelJogo.GameModel;
import classescomunicacao.ModelJogo.ObservableGame;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

/**
 *
 * @author edu_f
 */
public class TrataUtilizador extends Thread {

    private BaseDados BD;
    JogosDecorrer jogosDecorrer;

    public TrataUtilizador(JogosDecorrer jd) {
        BD = new BaseDados();
        this.jogosDecorrer = jd;
    }

    @Override
    public void run() {
        ServerSocket server;
        try {

            server = new ServerSocket(ConstantesIps.PORTOSERVIDORJOGO);
            while (true) {
                Socket nextCliente = server.accept();
                Thread t1 = new RecebeAcoes(nextCliente, jogosDecorrer,BD );
                t1.start();
            }
        } catch (Exception ex) {

        }
    }

}
