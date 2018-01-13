package BaseDados;



import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class BaseDados {

    private Connection Con;
    private Statement St;
    private ResultSet Rs;
    private String BDName = "pdgalo";
    private String User = "root";
    private String Pass = "";
    
    public BaseDados()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            Con = DriverManager.getConnection("jdbc:mysql://localhost/" + BDName,User,Pass);
            St = Con.createStatement();
        }catch(Exception ex)
        {
            System.out.println("Erro: " + ex);
        }
    }
    
    public Statement getStatement()
    {
        try
        {
            return Con.createStatement();
        } catch (SQLException ex)
        {
            Logger.getLogger(BaseDados.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int Modifica (String q, Statement statement)
    {
        int resposta = -1;
        try{
            resposta = statement.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
            ResultSet t  = statement.getGeneratedKeys();
            
            if(t.next()){
                
                resposta=t.getInt(1);
            }
            
            return resposta;
        }catch(Exception ex){
            try {
                resposta = St.executeUpdate(q);
            } catch (SQLException ex1) {
                Logger.getLogger(BaseDados.class.getName()).log(Level.SEVERE, null, ex1);
            }
            System.out.println("Erro: " + ex);
        }
        return resposta;
    }
    
    public ResultSet Le(String q, Statement statement)
    {
        try{
            Rs = statement.executeQuery(q);
            
            return Rs;
        }catch(Exception ex)
        {
            System.out.println("Erro: " + ex);
        }
        return null;
    }
    public void CloseConnection()
    {
        try {
            Con.close();

        } catch (SQLException e) {

        }
    }
}

