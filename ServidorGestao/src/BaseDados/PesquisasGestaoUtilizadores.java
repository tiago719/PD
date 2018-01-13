/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BaseDados;

import BaseDados.BaseDados;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Cliente;
import classescomunicacao.Constantes;
import classescomunicacao.FormarPar;
import classescomunicacao.Mensagem;
import java.sql.Statement;
import java.util.ArrayList;
import classescomunicacao.Partida;
import classescomunicacao.ClienteEnviar;

/**
 *
 * @author andre
 */
public class PesquisasGestaoUtilizadores {

    private BaseDados bd;

    public PesquisasGestaoUtilizadores() {
        bd = new BaseDados();
    }

    @Override
    protected void finalize() {
        bd.CloseConnection();
        try {
            super.finalize();
        } catch (Throwable ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /// FUNÇAO PARA REGISTAR UTILIZADORES
    public void AdicionaUtilizador(String Nome, String Username, String PalavraChave) {
        Statement s = bd.getStatement();
        bd.Modifica("INSERT INTO utilizador(IDUTILIZADOR, NOME, USERNAME, PASSWORD, LOGADO) VALUES ( null,'" + Nome.trim() + "','" + Username.trim() + "','" + SHA1(PalavraChave) + "', false);", s);

    }

    ///FUNÇAO CONVERTE STRING PARA SHA1
    static String SHA1(String input) {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < result.length; i++) {
                sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //VERIFICA SE O Username EXISTE NA BASE DE DADOS
    public boolean ExisteUsername(String Username) {
        ResultSet Rt = null;
        Statement s = bd.getStatement();
        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + Username + "';", s);

            if (Rt.next()) {

                return true;
            } else {

                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    //VERIFICA SE O Username E Password ESTÁ REGISTADO NA BASE DE DADOS
    public int VerificaLogin(String Username, String Password) {
        ResultSet Rt = null;
        Statement s = bd.getStatement();
        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME='" + Username + "'and PASSWORD='" + SHA1(Password) + "';", s);
            if (Rt.next()) {
                int id = Rt.getInt("IDUTILIZADOR");
                if (Rt.getBoolean("LOGADO") == true) {
                    return -1;
                }

                return id;
            } else {

                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt.close();
                s.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public void setClienteLogado(int id) {
        Statement s = bd.getStatement();
        try {
            bd.Modifica("UPDATE UTILIZADOR SET LOGADO=1 WHERE IDUTILIZADOR=" + id + ";", s);
        } catch (Exception e) {
        }
    }

    public String getNome(String username) {
        Statement s = bd.getStatement();
        ResultSet Rt = null;
        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME='" + username + "';", s);

            if (Rt.next()) {
                String nome = Rt.getString("NOME");

                return nome;
            } else {

                return null;
            }

        } catch (SQLException e) {
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public String getNome(int id) {
        Statement s = bd.getStatement();

        ResultSet Rt = null;

        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE IDUTILIZADOR=" + id + ";", s);

            if (Rt.next()) {
                String nome = Rt.getString("NOME");
                return nome;
            } else {

                return null;
            }

        } catch (SQLException e) {
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public void setLogados() {
        Statement s = bd.getStatement();
        Statement s2 = bd.getStatement();
        Statement s3 = bd.getStatement();

        bd.Modifica("delete from jogo", s);

        bd.Modifica("UPDATE utilizador SET LOGADO=0;", s2);
        bd.Modifica("DELETE FROM par;", s3);

    }

    public void AdicionaSMS(Mensagem sms) {
        Statement s = bd.getStatement();
        Statement s2 = bd.getStatement();
        Statement s3 = bd.getStatement();
        Statement s4 = bd.getStatement();
        ResultSet Rt = null;
        ResultSet Rt1 = null;
        try {
            int idmensagem;
            Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + sms.getRemetente() + "';", s);
            if (Rt.next()) {

                int id = Rt.getInt("IDUTILIZADOR");
                idmensagem = bd.Modifica("INSERT INTO `mensagem`(`IDMENSAGEM`, `IDUTILIZADOR`, `MENSAGEM`) VALUES (null," + Rt.getInt("IDUTILIZADOR") + ",'" + sms.getMensagem() + "')", s2);
                if (sms.getDistinatario() != null) {
                    Rt1 = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + sms.getDistinatario() + "';", s3);
                    if (Rt1.next()) {
                        int idutilizador = Rt1.getInt("IDUTILIZADOR");
                        bd.Modifica("INSERT INTO `utilizador_mensagem`(`IDUTILIZADOR`, `IDMENSAGEM`) VALUES (" + idutilizador + "," + idmensagem + ")", s4);
                    }

                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt.close();
                Rt1.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setLogout(Cliente cliente) {
        Statement s = bd.getStatement();
        int ret;

        try {
            ret = bd.Modifica("UPDATE utilizador SET LOGADO=0 WHERE IDUTILIZADOR=" + cliente.getId() + ";", s);
        } catch (Exception e) {

        }
    }

    public boolean VeirficaExiste(int id1, int id2) {
        Statement s = bd.getStatement();
        ResultSet Rt;

        Rt = bd.Le("SELECT * FROM par WHERE IDU1 = " + id1 + " AND IDU2 = " + id2 + ";", s);

        try {
            if (Rt.next()) {

                if (Rt.getBoolean("FORMADO") == false) {
                    return false;
                }

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return false;
    }

    public boolean temPar(int id1, int id2) {
        Statement s = bd.getStatement();
        ResultSet Rt = null;
        try {
            Rt = bd.Le("SELECT * FROM par WHERE IDU1 = " + id1 + " OR IDU2 = " + id1 + " OR IDU1= " + id2 + " OR IDU2= " + id2 + " ;", s);

            while (Rt.next()) {
                if (Rt.getBoolean("FORMADO") != false) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {

        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public int FormaPar(String nik1Util, String nik2Util) {
        Statement s = bd.getStatement();
        int id1 = GetidByUserName(nik1Util);
        int id2 = GetidByUserName(nik2Util);

        if (id2 != -1 && id1 != -1) {
            if (VeirficaExiste(id1, id2)) {
                return -1;
            }
            return bd.Modifica("INSERT INTO `par`(`IDPAR`, `FORMADO`, `IDU1`, `IDU2`) VALUES (null, '0' ,'" + id1 + "', '" + id2 + "');", s);
        }
        return -1;
    }

    public int GetidByUserName(String username) {
        Statement s = bd.getStatement();
        ResultSet Rt1 = null;
        try {
            Rt1 = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + username + "';", s);
            if (Rt1.next()) {
                int responsta = Rt1.getInt("IDUTILIZADOR");

                return responsta;

            } else {

                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt1.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return -1;
    }

    public String GetNomeById(int id) {
        Statement s = bd.getStatement();
        ResultSet Rt1 = null;
        try {
            Rt1 = bd.Le("SELECT * FROM utilizador WHERE IDUTILIZADOR = " + id + ";", s);
            if (Rt1.next()) {
                String responsta = Rt1.getString("USERNAME");

                return responsta;

            } else {

                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt1.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public int ConfirmaPar(String nik1Util, String nik2Util) {
        Statement s = bd.getStatement();
        ResultSet rs = null;
        int id1 = GetidByUserName(nik1Util);
        int id2 = GetidByUserName(nik2Util);

        try {
            bd.Modifica("UPDATE `par` SET `FORMADO`=1 WHERE IDU1 = " + id1 + " AND IDU2 = " + id2 + ";", s);
            int idPar;

            rs = bd.Le("select idPar from par where IDU1 = " + id1 + " AND IDU2 = " + id2 + ";", s);
            rs.next();
            return rs.getInt("idPar");

        } catch (Exception e) {

        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return -1;
    }

    public void EliminaPar(String nik1Util, String nik2Util) {
        Statement s = bd.getStatement();
        int id1 = GetidByUserName(nik1Util);
        int id2 = GetidByUserName(nik2Util);

        bd.Modifica("DELETE FROM par WHERE (IDU1= " + id1 + " AND IDU2= " + id2 + " ) OR (IDU1= " + id2 + " AND IDU2= " + id1 + " ) ;", s);
    }

    public boolean ExistePedido(String nik1Util, String nik2Util) {
        Statement s = bd.getStatement();
        ResultSet Rt = null;
        try {
            int id1 = GetidByUserName(nik1Util);
            int id2 = GetidByUserName(nik2Util);

            Rt = bd.Le("SELECT * FROM par WHERE ( IDU1 = " + id1 + " AND IDU2 = " + id2 + " ) OR ( IDU1= " + id2 + " AND IDU2= " + id1 + " ) ;", s);

            if (Rt.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {

        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public void EliminaPedidos(String nik1Util, String nik2Util) {
        Statement s = bd.getStatement();
        int id1 = GetidByUserName(nik1Util);
        int id2 = GetidByUserName(nik2Util);

        bd.Modifica("DELETE FROM par WHERE (IDU1 = " + id1 + " OR IDU2 = " + id2 + " OR IDU1= " + id2 + " OR IDU2= " + id1 + ") AND FORMADO = 0 ;", s);
    }

    public String getUsername(int id) {
        Statement s = bd.getStatement();
        ResultSet Rt = null;

        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE IDUTILIZADOR=" + id + ";", s);

            if (Rt.next()) {
                String nome = Rt.getString("USERNAME");
                return nome;
            } else {

                return null;
            }

        } catch (SQLException e) {
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public ArrayList<FormarPar> getPedidosUtilizadores(String nik1Util, String nik2Util) {
        Statement s = bd.getStatement();
        int id1ret, id2ret;
        ArrayList<FormarPar> temp = new ArrayList<>();
        ResultSet Rt = null;
        try {
            int id1 = GetidByUserName(nik1Util);
            int id2 = GetidByUserName(nik2Util);

            Rt = bd.Le("SELECT * FROM par WHERE (IDU1 = " + id1 + " OR IDU2 = " + id2 + " OR IDU1= " + id2 + " OR IDU2= " + id1 + ") AND FORMADO = 0 ;", s);

            while (Rt.next()) {
                id1ret = Rt.getInt("IDU1");
                id2ret = Rt.getInt("IDU2");

                FormarPar formarPar = new FormarPar(getUsername(id1ret), getUsername(id2ret));
                formarPar.setAceite(Constantes.PEDIDO_RECUSADO);
                temp.add(formarPar);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return temp;
    }

    public ArrayList<FormarPar> getPares() {
        ArrayList<FormarPar> temp = new ArrayList<>();
        ResultSet Rt = null;
        Statement s = bd.getStatement();
        int id1ret, id2ret;
        boolean formado;
        try {
            Rt = bd.Le("SELECT * FROM par;", s);

            while (Rt.next()) {
                id1ret = Rt.getInt("IDU1");
                id2ret = Rt.getInt("IDU2");

                formado = Rt.getBoolean("FORMADO");

                FormarPar formarPar = new FormarPar(getUsername(id1ret), getUsername(id2ret));
                if (formado) {
                    formarPar.setAceite(Constantes.PEDIDO_ACEITE);
                } else {
                    formarPar.setAceite(Constantes.PEDIDO_FEITO);
                }
                temp.add(formarPar);
            }
        } catch (Exception e) {

        } finally {
            try {
                s.close();
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return temp;
    }

    public boolean temPar(int id) {
        Statement s = bd.getStatement();
        ResultSet Rt = null;
        try {
            Rt = bd.Le("SELECT * FROM par WHERE IDU1 = " + id + " OR IDU2 = " + id + " ;", s);

            while (Rt.next()) {
                if (Rt.getBoolean("FORMADO") != false) {

                    return true;
                }
            }
            return false;
        } catch (SQLException e) {

        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    public ArrayList<FormarPar> getPedidosUtilizador(String username) {
        Statement s = bd.getStatement();
        ResultSet Rt = null;
        int id1, id2;

        ArrayList<FormarPar> pares = new ArrayList<>();
        try {
            int id = GetidByUserName(username);
            Rt = bd.Le("SELECT * FROM par WHERE (IDU1 = " + id + " OR IDU2 = " + id + " ) AND FORMADO=0;", s);
            while (Rt.next()) {
                id1 = Rt.getInt("IDU1");
                id2 = Rt.getInt("IDU2");

                pares.add(new FormarPar(getUsername(id1), getUsername(id2), Constantes.PEDIDO_FEITO));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pares;
    }

    public FormarPar getPar(String username) {
        Statement s = bd.getStatement();
        ResultSet Rt = null;
        int id1, id2;

        ArrayList<FormarPar> pares = new ArrayList<>();
        try {
            int id = GetidByUserName(username);
            Rt = bd.Le("SELECT * FROM par WHERE (IDU1 = " + id + " OR IDU2 = " + id + " ) AND FORMADO=1;", s);
            if (Rt.next()) {
                id1 = Rt.getInt("IDU1");
                id2 = Rt.getInt("IDU2");

                return new FormarPar(getUsername(id1), getUsername(id2), Constantes.PEDIDO_ACEITE);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    public ArrayList<Partida> getPartidas() {
        ArrayList<Partida> temp = new ArrayList<>();
        ResultSet Rt = null, Rt1 = null;
        Statement s = bd.getStatement();
        int id1ret, id2ret, terminado = -1, interrompido = -1;
        boolean formado;
        try {
            Rt = bd.Le("SELECT * FROM jogo;", s);

            while (Rt.next()) {

                String NomeVencedor = GetNomeById(Rt.getInt("VENCEDOR"));
                boolean Terminou = Rt.getBoolean("TERMINOU");
                boolean Interrompido = Rt.getBoolean("INTERROMPIDO");

                Rt1 = bd.Le("SELECT * FROM par WHILE IDPAR = " + Rt.getInt("IDPAR") + ";", s);

                String NomeUtil1 = GetNomeById(Rt1.getInt("IDU1"));
                String NomeUtil2 = GetNomeById(Rt1.getInt("IDU2"));

                if (Terminou) {
                    terminado = 1;
                } else {
                    terminado = 0;
                }

                if (Interrompido) {
                    interrompido = 1;
                } else {
                    interrompido = 0;
                }

                temp.add(new Partida(NomeVencedor, NomeUtil1, NomeUtil2, terminado, interrompido));

            }
        } catch (Exception e) {

        } finally {
            try {
                s.close();
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return temp;
    }

    public ArrayList<ClienteEnviar> getJogadoresLogados() {
        ArrayList<ClienteEnviar> temp = new ArrayList<>();
        ResultSet Rt = null, Rt1 = null;
        Statement s = bd.getStatement();
        int id1ret, id2ret, terminado = -1, interrompido = -1;
        boolean formado;

        try {
            Rt = bd.Le("SELECT * FROM utilizador;", s);

            while (Rt.next()) {
                int id = Rt.getInt("IDUTILIZADOR");

                Rt1 = bd.Le("SELECT * FROM par WHERE ( IDU1 = " + id + " OR IDU2 = " + id + ") AND FORMADO = 1  ;", s);
                if (Rt1.next()) {
                    temp.add(new ClienteEnviar(Rt.getString("USERNAME"), Rt.getString("NOME"), true));
                } else {
                    temp.add(new ClienteEnviar(Rt.getString("USERNAME"), Rt.getString("NOME"), false));
                }
            }
        } catch (Exception e) {

        } finally {
            try {
                s.close();
                Rt.close();
            } catch (SQLException ex) {
                Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
        }
    }
}
