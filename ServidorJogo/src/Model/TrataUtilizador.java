package Model;

import classescomunicacao.Jogadas;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

/**
 *
 * @author edu_f
 */
public class TrataUtilizador extends Thread {

    private int porto = 5000;
    private BaseDados BD;
    
    public TrataUtilizador() {
        BD = new BaseDados();
    }

    @Override
    public void run() {
        ServerSocket server;
        try {

            server = new ServerSocket(porto);
            while (true) {
                Socket nextCliente = server.accept();

                ObjectInputStream in = new ObjectInputStream(nextCliente.getInputStream());

                Object objectRecebidoUtilizador = in.readObject();
                
                if (objectRecebidoUtilizador instanceof Jogadas){
                    Jogadas jog = (Jogadas) objectRecebidoUtilizador;
                    ResultSet rs = BD.Le("SELECT count (*) FROM par JOIN utilizador on utilizador.IDUTILIZADOR = par.IDU1 or utilizador.IDUTILIZADOR = par.IDU2 where utilizador.USERNAME = "+ jog.getNickname() +" and par.FORMADO = 1");
                    if (rs.getInt(1) != 0){
                        
                    }
                }

            }
        } catch (Exception ex) {

        }
    }

}
