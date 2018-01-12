package Model;

import classescomunicacao.AcoesPartida;
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

    private int porto = 5000;
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

            server = new ServerSocket(porto);
            while (true) {
                Socket nextCliente = server.accept();

                ObjectInputStream in = new ObjectInputStream(nextCliente.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(nextCliente.getOutputStream());

                Object objectRecebidoUtilizador = in.readObject();

                if (objectRecebidoUtilizador instanceof AcoesPartida) {
                    AcoesPartida ap = (AcoesPartida) objectRecebidoUtilizador;

                    ResultSet rs;

                    rs = BD.Le("select count(*) as 'hasPar' from par where idPar = " + ap.getIdPar());
                    rs.next();
                    if (rs.getInt("hasPar") < 1) {
                        continue;
                    }

                    switch (ap.getAcao()) {
                        case 1:
                            rs = BD.Le("select count (*) as 'jogoCriado' from jogo where emcurso = 1 and idpar = " + ap.getIdPar());
                            rs.next();
                            if (rs.getInt("jogoCriado") > 0){
                                out.writeObject(jogosDecorrer.getGameModel(ap.getIdPar()));
                                break;
                            }
                            
                            rs = BD.Modifica("INSERT INTO jogo (IDJOGO, "
                                    + "IDPAR, RESULTADO, VENCEDOR,"
                                    + " EMCURSO, TERMINOU, INTERROMPIDO) "
                                    + "VALUES (NULL, '" + ap.getIdPar()
                                    + "', '-1', '-1', '1', '0', '0')");

                            int idJogo = rs.getInt("IDJOGO");
                            if (idJogo > 0) {

                                int idPar = ap.getIdPar();

                                rs = BD.Le("SELECT IDU1, IDU2 FROM `par`"
                                        + " WHERE `IDPAR` = " + idPar);
                                rs.next();

                                int idU1 = rs.getInt("IDU1");
                                int idU2 = rs.getInt("IDU2");

                                rs = BD.Le("SELECT USERNAME FROM `utilizador`"
                                        + " WHERE IDUTILIZADOR = " + idU1);
                                rs.next();
                                String nick1 = rs.getString("USERNAME");
                                rs = BD.Le("SELECT USERNAME FROM `utilizador` "
                                        + "WHERE IDUTILIZADOR = " + idU2);
                                rs.next();
                                String nick2 = rs.getString("USERNAME");

                                rs.next();
                                jogosDecorrer.addNovoJogo(idJogo, nick1, nick2, ap.getIdPar());
                                //TODO: Se existir ficheiro de modelo jogo usar esse e nao fazer novo gamemodel
                                out.writeObject(new GameModel(nick1, nick2, idJogo));
                            }
                            break;
                        case 2:
                            //TODO: CONCLUIR JOGO

                            break;

                        case 3:
                            //TODO: CANCELAR JOGO

                            break;

                    }
                }

            }
        } catch (Exception ex) {

        }
    }

}
