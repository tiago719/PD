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
import classescomunicacao.Mensagem;

/**
 *
 * @author andre
 */
public class PesquisasGestaoUtilizadores {

    
    private BaseDados bd;

    public PesquisasGestaoUtilizadores()
    {
    }

    /// FUNÇAO PARA REGISTAR UTILIZADORES
    public void AdicionaUtilizador(String Nome, String Username, String PalavraChave) throws NoSuchAlgorithmException
    {
        bd = new BaseDados();

        bd.Modifica("INSERT INTO utilizador(IDUTILIZADOR, NOME, USERNAME, PASSWORD, LOGADO) VALUES ( null,'" + Nome.trim() + "','" + Username.trim() + "','" + SHA1(PalavraChave) + "', false);");

        bd.CloseConnection();
    }

    ///FUNÇAO CONVERTE STRING PARA SHA1
    static String SHA1(String input) throws NoSuchAlgorithmException
    {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++)
        {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }

    //VERIFICA SE O Username EXISTE NA BASE DE DADOS
    public boolean ExisteUsername(String Username) throws SQLException
    {
        bd = new BaseDados();
        ResultSet Rt;

        Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME = '" + Username + "';");

        if (Rt.next())
        {
            bd.CloseConnection();
            return true;
        } else
        {
            bd.CloseConnection();
            return false;
        }

    }

    //VERIFICA SE O Username E Password ESTÁ REGISTADO NA BASE DE DADOS
    public int VerificaLogin(String Username, String Password) throws SQLException
    {
        bd = new BaseDados();
        ResultSet Rt;
        try
        {
            Rt = bd.Le("SELECT * FROM utilizador WHERE USERNAME='" + Username + "'and PASSWORD='" + SHA1(Password)+ "';");
            if (Rt.next())
            {
                int id = Rt.getInt("IDUTILIZADOR");
                if(Rt.getBoolean("LOGADO")) 
                    return -1;

                bd.CloseConnection();
                return id;
            } else
            {
                bd.CloseConnection();
                return -1;
            }
        } catch (NoSuchAlgorithmException ex)
        {
            System.out.println();
        }
        return -1;
    }

    public void setClienteLogado(int id)
    {
        bd = new BaseDados();
        try
        {
            bd.Modifica("UPDATE UTILIZADOR SET LOGADO=1 WHERE IDUTILIZADOR=" + id + ";");
        } catch (Exception e)
        {
        }

        bd.CloseConnection();
    }

    public String getNome(String username)
    {
         bd = new BaseDados();
        ResultSet Rt = null;   
        try
        {
            Rt=bd.Le("SELECT * FROM utilizador WHERE USERNAME='" + username +"';");
            
            if(Rt.next())
            {
                String nome=Rt.getString("NOME");
                bd.CloseConnection();
                return nome;
            }
            else
            {
                bd.CloseConnection();
                return null;
            }
            
        } catch (SQLException e)
        {
        }

        bd.CloseConnection();
        return null;
    }
    
    public String getNome(int id) {

        bd = new BaseDados();
        ResultSet Rt = null;
        
        try
        {
            Rt=bd.Le("SELECT * FROM utilizador WHERE IDUTILIZADOR=" + id +";");
            
            if(Rt.next())
            {
                String nome=Rt.getString("NOME");
                bd.CloseConnection();
                return nome;
            }
            else
            {
                bd.CloseConnection();
                return null;
            }
            
        } catch (SQLException e)
        {
        }

        bd.CloseConnection();
        return null;
   }

    public void setLogados(boolean b)
    {
        bd = new BaseDados();
        BaseDados bdModifica=new BaseDados();
        ResultSet Rt = null;
        int id;
        
        try
        {
            Rt=bd.Le("SELECT * FROM utilizador;");
            
            while(Rt.next())
            {
                id=Rt.getInt("IDUTILIZADOR");
                bdModifica.Modifica("UPDATE utilizador SET LOGADO=0 WHERE IDUTILIZADOR=" + id+";");
            }
            bd.CloseConnection();
            bdModifica.CloseConnection();
        }
        catch(Exception e)
        {
            
        }
    }

    
    public void AdicionaSMS(Mensagem sms) throws SQLException {
        bd = new BaseDados();
        ResultSet Rt, Rt1;

        Rt = bd.Le("SELECT * WHERE USERNAME = '" + sms.getRemetente() + "';");

        if (Rt.next()) {
            bd.Modifica("INSERT INTO `mensagem`(`IDMENSAGEM`, `IDUTILIZADOR`, `MENSAGEM`) VALUES (null," + Rt.getInt("IDUTILIZADOR") + ", + '" + sms.getMensagem() + "')");
            if (sms.getDistinatario() != null) {
                Rt = bd.Le("SELECT * FROM mensagem WHERE IDUTILIZADOR = " + Rt.getInt("IDUTILIZADOR") + ";");
                Rt1 = bd.Le("SELECT * WHERE USERNAME = '" + sms.getDistinatario() + "';");

                bd.Modifica("INSERT INTO `utilizador_mensagem`(`IDUTILIZADOR`, `IDMENSAGEM`) VALUES (" + Rt1.getInt("IDUTILIZADOR") + "," + Rt.getInt("IDMENSAGEM") + ")");
            }
        }

        bd.CloseConnection();
    }

    public void setLogout(Cliente cliente)
    {
        bd = new BaseDados();
        BaseDados bdModifica=new BaseDados();
        int ret;
        int id;
        
        try
        {
            ret=bd.Modifica("UPDATE utilizador SET LOGADO=0 WHERE IDUTILIZADOR=" + cliente.getId()+";");

            bd.CloseConnection();
            bdModifica.CloseConnection();
        }
        catch(Exception e)
        {
            
        }
    }
}
