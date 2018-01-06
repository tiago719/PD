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
    JogosDecorrer jd;

    public TrataUtilizador() {
        BD = new BaseDados();
        jd = new JogosDecorrer();
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

                if (objectRecebidoUtilizador instanceof Jogadas) {
                    Jogadas jog = (Jogadas) objectRecebidoUtilizador;
                    ResultSet rs = BD.Le("SELECT count (*) FROM par JOIN utilizador on utilizador.IDUTILIZADOR = par.IDU1 or utilizador.IDUTILIZADOR = par.IDU2 where utilizador.USERNAME = " + jog.getNickname() + " and par.FORMADO = 1");
                    if (rs.getInt(1) != 0) {
                        rs = BD.Le("SELECT IDPAR, USERNAME, IDUTILIZADOR FROM par JOIN utilizador on utilizador.IDUTILIZADOR = par.IDU1 or utilizador.IDUTILIZADOR = par.IDU2");
                        int idUser = rs.getInt("IDUTILIZADOR");
                        String nick1 = rs.getString("USERNAME");
                        String nick2 = rs.getString("USERNAME");

                        int idPar = rs.getInt("IDPAR");
                        rs = BD.Le("SELECT jogo.EMCURSO, jogo.TERMINOU, jogo.INTERROMPIDO FROM jogo WHERE jogo.IDPAR = " + idPar);
                        if (rs.getBoolean("jogo.EMCURSO")) {
                            rs = BD.Le("SELECT IDJOGO FROM jogo WHERE jogo.IDPAR = " + idPar);
                            jd.getJogoDecorrer(idJogo);
                        } else if (rs.getBoolean("jogo.TERMINOU")) {

                        } else if (rs.getBoolean("jogo.INTERROMPIDO")) {

                        } else { //novo jogo
                            rs = BD.Modifica("INSERT INTO jogo (IDJOGO, IDUTILIZADOR, IDPAR, RESULTADO, VENCEDOR, EMCURSO, TERMINOU, INTERROMPIDO) VALUES (NULL, '"+ idUser +"', '" + idPar + "', '-1', '-1', '1', '0', '0')");
                            int idJogo = rs.getInt("IDJOGO");
                            if(idJogo > 0){
                                jd.addNovoJogo(idJogo, nick1, nick2, nextCliente);
                            }
                            
                            
                        }

                        jd.addNovoJogo(porto, nick1, nick2);
                    }
                }

            }
        } catch (Exception ex) {

        }
    }

}
