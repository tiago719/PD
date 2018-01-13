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
import java.util.ArrayList;

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
    public void AdicionaUtilizador(String Nome, String Username, String PalavraChave) throws NoSuchAlgorithmException {
        bd.Modifica("INSERT INTO utilizador(IDUTILIZADOR, NOME, USERNAME, PASSWORD, LOGADO) VALUES ( null,'" + Nome.trim() + "','" + Username.trim() + "','" + SHA1(PalavraChave) + "', false);");

    }

    ///FUNÇAO CONVERTE STRING PARA SHA1
    static String SHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    //VERIFICA SE O Username EXISTE NA BASE DE DADOS
    public boolean ExisteUsername(String Username) throws SQLException {

        ResultSet Rt;

        Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + Username + "';");

        if (Rt.next()) {

            return true;
        } else {

            return false;
        }

    }

    //VERIFICA SE O Username E Password ESTÁ REGISTADO NA BASE DE DADOS
    public int VerificaLogin(String Username, String Password) throws SQLException {
        ResultSet Rt;
        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME='" + Username + "'and PASSWORD='" + SHA1(Password) + "';");
            if (Rt.next()) {
                int id = Rt.getInt("IDUTILIZADOR");
                if (Rt.getBoolean("LOGADO")==true) {
                    return -1;
                }

                return id;
            } else {

                return -1;
            }
        } catch (NoSuchAlgorithmException ex) {
            System.out.println();
        }
        return -1;
    }

    public void setClienteLogado(int id) {

        try {
            bd.Modifica("UPDATE UTILIZADOR SET LOGADO=1 WHERE IDUTILIZADOR=" + id + ";");
        } catch (Exception e) {
        }
    }

    public String getNome(String username) {
        ResultSet Rt = null;
        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME='" + username + "';");

            if (Rt.next()) {
                String nome = Rt.getString("NOME");

                return nome;
            } else {

                return null;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    public String getNome(int id) {

        ResultSet Rt = null;

        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE IDUTILIZADOR=" + id + ";");

            if (Rt.next()) {
                String nome = Rt.getString("NOME");
                return nome;
            } else {

                return null;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    public void setLogados() {
        bd.Modifica("delete from jogo");
        
        bd.Modifica("UPDATE utilizador SET LOGADO=0;");
        bd.Modifica("DELETE FROM par;");

    }

    public void AdicionaSMS(Mensagem sms) throws SQLException {

        ResultSet Rt, Rt1;
        int idmensagem;

        Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + sms.getRemetente() + "';");

        if (Rt.next()) {

            int id = Rt.getInt("IDUTILIZADOR");
            idmensagem = bd.Modifica("INSERT INTO `mensagem`(`IDMENSAGEM`, `IDUTILIZADOR`, `MENSAGEM`) VALUES (null," + Rt.getInt("IDUTILIZADOR") + ",'" + sms.getMensagem() + "')");
            if (sms.getDistinatario() != null) {
                Rt1 = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + sms.getDistinatario() + "';");
                if (Rt1.next()) {
                    int idutilizador = Rt1.getInt("IDUTILIZADOR");
                    bd.Modifica("INSERT INTO `utilizador_mensagem`(`IDUTILIZADOR`, `IDMENSAGEM`) VALUES (" + idutilizador + "," + idmensagem + ")");
                }

            }

        }

    }

    public void setLogout(Cliente cliente) {

        int ret;

        try {
            ret = bd.Modifica("UPDATE utilizador SET LOGADO=0 WHERE IDUTILIZADOR=" + cliente.getId() + ";");
        } catch (Exception e) {

        }
    }

    public boolean VeirficaExiste(int id1, int id2) {

        ResultSet Rt, Rt1;

        Rt = bd.Le("SELECT * FROM par WHERE IDU1 = " + id1 + " AND IDU2 = " + id2 + ";");

        try {
            if (Rt.next()) 
            {

                if (Rt.getBoolean("FORMADO") == false) 
                {
                    return false;
                }

                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    
    public boolean temPar(int id1, int id2)
    {
        try
        {
            ResultSet Rt;

            Rt = bd.Le("SELECT * FROM par WHERE IDU1 = " + id1 + " OR IDU2 = " + id1 + " OR IDU1= "+ id2 + " OR IDU2= "+id2+" ;");
            
            while(Rt.next()) 
            {
                if (Rt.getBoolean("FORMADO") != false) 
                {
                    return true;
                }
            }
            return false;
        }
        catch(SQLException e)
        {
            
        }
        return false;
    }

    public int FormaPar(String nik1Util, String nik2Util) {

        int id2 = -1;
        int id1 = -1;

        try {
            id1 = GetidByUserName(nik1Util);
            id2 = GetidByUserName(nik2Util);
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (id2 != -1 && id1 != -1) {
            if (VeirficaExiste(id1, id2)) {
                return -1;
            }
            return bd.Modifica("INSERT INTO `par`(`IDPAR`, `FORMADO`, `IDU1`, `IDU2`) VALUES (null, '0' ,'" + id1 + "', '" + id2 + "');");
            
        }
        return -1;
    }

    public int GetidByUserName(String username) throws SQLException {

        ResultSet Rt1;

        Rt1 = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + username + "';");
        if (Rt1.next()) {
            int responsta = Rt1.getInt("IDUTILIZADOR");

            return responsta;

        } else {

            return -1;
        }
    }

    public int ConfirmaPar(String nik1Util, String nik2Util) {
        int id1, id2;

        try {
            id1 = GetidByUserName(nik1Util);
            id2 = GetidByUserName(nik2Util);
        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }

        try {
           bd.Modifica("UPDATE `par` SET `FORMADO`=1 WHERE IDU1 = " + id1 + " AND IDU2 = " + id2 + ";");
           int idPar;
           
           ResultSet rs = bd.Le("select idPar from par where IDU1 = " + id1 + " AND IDU2 = " + id2 + ";");
           rs.next();
           return rs.getInt("idPar");

        } catch (Exception e) {

        }
        
        return -1;
    }

    public void EliminaPar(String nik1Util, String nik2Util)
    {
        int id1, id2;

        try {
            id1 = GetidByUserName(nik1Util);
            id2 = GetidByUserName(nik2Util);
            
            bd.Modifica("DELETE FROM par WHERE (IDU1= " + id1 + " AND IDU2= " + id2 + " ) OR (IDU1= " + id2 + " AND IDU2= " + id1 +" ) ;");

        } catch (SQLException ex) {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        
    }

    public boolean ExistePedido(String nik1Util, String nik2Util)
    {
        try
        {
            int id1=GetidByUserName(nik1Util);
            int id2=GetidByUserName(nik2Util);
            
            ResultSet Rt;

            Rt = bd.Le("SELECT * FROM par WHERE ( IDU1 = " + id1 + " AND IDU2 = " + id2 + " ) OR ( IDU1= "+ id2 + " AND IDU2= "+id1+" ) ;");
            
            if(Rt.next())
                return true;
            else
                return false;
        }
        catch(SQLException e)
        {
            
        }
        return false;
    }

    public void EliminaPedidos(String nik1Util, String nik2Util)
    {
        try
        {
            int id1=GetidByUserName(nik1Util);
            int id2=GetidByUserName(nik2Util);
            
            bd.Modifica("DELETE FROM par WHERE (IDU1 = " + id1 + " OR IDU2 = " + id2 + " OR IDU1= "+ id2 + " OR IDU2= "+id1+") AND FORMADO = 0 ;");

        } catch (SQLException ex)
        {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public String getUsername(int id)
    {
        ResultSet Rt = null;

        try {
            Rt = bd.Le("SELECT * FROM utilizador WHERE IDUTILIZADOR=" + id + ";");

            if (Rt.next()) {
                String nome = Rt.getString("USERNAME");
                return nome;
            } else {

                return null;
            }

        } catch (SQLException e) {
        }

        return null;
    }

    public ArrayList<FormarPar> getPedidosUtilizadores(String nik1Util, String nik2Util)
    {
        int id1ret, id2ret;
        ArrayList<FormarPar> temp=new ArrayList<>();

        try
        {
            int id1=GetidByUserName(nik1Util);
            int id2=GetidByUserName(nik2Util);
            
            ResultSet Rt;
            
            Rt=bd.Le("SELECT * FROM par WHERE (IDU1 = " + id1 + " OR IDU2 = " + id2 + " OR IDU1= "+ id2 + " OR IDU2= "+id1+") AND FORMADO = 0 ;");
            
            while(!Rt.isClosed() && Rt.next())
            {
                id1ret=Rt.getInt("IDU1");
                id2ret=Rt.getInt("IDU2");
                
                FormarPar formarPar=new FormarPar(getUsername(id1ret), getUsername(id2ret));
                formarPar.setAceite(Constantes.PEDIDO_RECUSADO);
                temp.add(formarPar);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
    }
    
    public ArrayList<FormarPar> getPares()
    {
        ArrayList<FormarPar> temp=new ArrayList<>();
        int id1ret,id2ret;
        boolean formado;
        try
        {
            
            ResultSet Rt;
            
            Rt=bd.Le("SELECT * FROM par;");
            
            while(!Rt.isClosed() && Rt.next())
            {
                id1ret=Rt.getInt("IDU1");
                id2ret=Rt.getInt("IDU2");
                formado = Rt.getBoolean("FORMADO");
                
                FormarPar formarPar=new FormarPar(getUsername(id1ret), getUsername(id2ret));
                if(formado)
                    formarPar.setAceite(Constantes.PEDIDO_ACEITE);
                else
                    formarPar.setAceite(Constantes.PEDIDO_FEITO);
                temp.add(formarPar);
            }
        } catch (SQLException ex)
        {
            Logger.getLogger(PesquisasGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return temp;
    }
}
