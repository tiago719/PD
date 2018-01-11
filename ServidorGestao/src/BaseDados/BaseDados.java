package BaseDados;



import java.sql.*;


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
    
    public int Modifica (String q)
    {
        int resposta = -1;
        try{
            resposta = St.executeUpdate(q, Statement.RETURN_GENERATED_KEYS);
            ResultSet t  = St.getGeneratedKeys();
            
            if(t.next()){
                resposta=t.getInt(1);
            }
            return resposta;
        }catch(Exception ex)
        {
            System.out.println("Erro: " + ex);
        }
        return resposta;
    }
    
    public ResultSet Le(String q)
    {
        try{
            Rs = St.executeQuery(q);
            
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

