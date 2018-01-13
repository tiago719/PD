/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import classescomunicacao.AcoesPartida;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu_f
 */
class RecebeAcoes extends Thread {

    Socket nextCliente;
    JogosDecorrer jogosDecorrer;
    BaseDados BD;
    ObjectOutputStream out;
    ObjectInputStream in;

    public RecebeAcoes(Socket nextCliente, JogosDecorrer jogosDecorrer, BaseDados BD) {

        this.nextCliente = nextCliente;
        this.jogosDecorrer = jogosDecorrer;
        this.BD = BD;
        try {
            out = new ObjectOutputStream(nextCliente.getOutputStream());
            out.flush();
            in = new ObjectInputStream(nextCliente.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(RecebeAcoes.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(RecebeAcoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
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
                            rs = BD.Le("select count(*) as 'jogoCriado' from jogo where emcurso = 1 and idpar = " + ap.getIdPar());
                            rs.next();
                            if (rs.getInt("jogoCriado") != 0) {
                                out.writeObject(jogosDecorrer.getGameModel(ap.getIdPar()));
                                out.flush();
                                break;
                            }

                            rs = BD.Modifica("INSERT INTO jogo (IDJOGO, "
                                    + "IDPAR, RESULTADO, VENCEDOR,"
                                    + " EMCURSO, TERMINOU, INTERROMPIDO) "
                                    + "VALUES (NULL, '" + ap.getIdPar()
                                    + "', '-1', '-1', '1', '0', '0')");
                            rs.next();

                            int idJogo = rs.getInt(1);
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
                                out.writeObject(jogosDecorrer.getGameModel(idPar));
                                out.flush();
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
            } catch (IOException ex) {
                Logger.getLogger(RecebeAcoes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RecebeAcoes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(RecebeAcoes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
