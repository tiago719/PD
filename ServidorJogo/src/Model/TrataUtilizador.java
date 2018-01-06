package Model;

import classescomunicacao.AcoesPartida;
import classescomunicacao.Jogadas;
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
                ObjectOutputStream out = new ObjectOutputStream(nextCliente.getOutputStream());

                Object objectRecebidoUtilizador = in.readObject();

                if (objectRecebidoUtilizador instanceof Jogadas) {
                    Jogadas jog = (Jogadas) objectRecebidoUtilizador;
                    ResultSet rs = BD.Le("SELECT count (*) FROM par JOIN "
                            + "utilizador on utilizador.IDUTILIZADOR = par.IDU1"
                            + " or utilizador.IDUTILIZADOR = par.IDU2 "
                            + "where utilizador.USERNAME = "
                            + jog.getNickname() + " and par.FORMADO = 1");
                    rs.next();
                    
                    if (rs.getInt(1) != 0) {
                        rs = BD.Le("SELECT jogo.EMCURSO, jogo.TERMINOU, "
                                + "jogo.INTERROMPIDO FROM jogo WHERE "
                                + "jogo.IDJOGO = " + jog.getIdJogo());
                        rs.next();

                        if (rs.getInt("jogo.EMCURSO") == 1) {
                            //TODO: aplica jogada e envia no model
                            
                            
                            
                            ObservableGame og = ((ThreadJogos)
                                    jd.getJogoDecorrer(jog.getIdJogo())).getOg();
                            og.placeToken(jog.getLinha(), jog.getColuna());
                            
                            
                            
                            out.writeObject(og.getGameData());
                            
                            

                        } else if (rs.getInt("jogo.TERMINOU") == 1) {

                        } else if (rs.getInt("jogo.INTERROMPIDO") == 1) {

                        }
                    }
                } else if (objectRecebidoUtilizador instanceof AcoesPartida) {
                    AcoesPartida ap = (AcoesPartida) objectRecebidoUtilizador;

                    ResultSet rs;

                    switch (ap.getAcao()) {
                        case 1:
                            rs = BD.Modifica("INSERT INTO jogo (IDJOGO, "
                                    + "IDUTILIZADOR, IDPAR, RESULTADO, VENCEDOR,"
                                    + " EMCURSO, TERMINOU, INTERROMPIDO) "
                                    + "VALUES (NULL, '" + ap.getIdUser() + "', '" 
                                    + ap.getIdPar() + "', '-1', '-1', '1', '0', '0')");
                            
                            int idJogo = rs.getInt("IDJOGO");
                            if (idJogo > 0) {
                                rs = BD.Le("SELECT jogo.IDPAR FROM `jogo` JOIN"
                                        + " par on par.IDPAR = jogo.IDPAR "
                                        + "WHERE jogo.IDJOGO = " + idJogo);
                                rs.next();
                                int idPar = rs.getInt("jogo.IDPAR");

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
                                jd.addNovoJogo(idJogo, nick1, nick2, nextCliente);
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
