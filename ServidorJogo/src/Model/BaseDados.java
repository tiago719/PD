package Model;

import java.sql.*;
//teste
public class BaseDados {

    private Connection Con;
    private Statement St;
    private ResultSet Rs;
    private String BDName = "pdgalo";
    private String User = "root";
    private String Pass = "";
    private String IpBD = null;

    public BaseDados() {

    }

    public ResultSet Modifica(String q) {
        ResultSet resposta = null;
        try {
            St.executeUpdate(q);
            resposta= St.getGeneratedKeys();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
        }
        finally{
            return resposta;
        }
    }

    public ResultSet Le(String q) {
        try {
            Rs = St.executeQuery(q);

            return Rs;
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
        }
        return null;
    }

    public void CloseConnection() {
        try {
            Con.close();

        } catch (SQLException e) {

        }
    }

    public void setIpBD(String IpBD) {
        boolean flag = false;
        if (IpBD == null) {
            flag = true;
        }
        this.IpBD = IpBD;

        if (flag == false) {
            return;
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Con = DriverManager.getConnection("jdbc:mysql://" + IpBD + "/" + BDName, User, Pass);
            St = Con.createStatement();
        } catch (Exception ex) {
            System.out.println("Erro: " + ex);
        }
    }

}
