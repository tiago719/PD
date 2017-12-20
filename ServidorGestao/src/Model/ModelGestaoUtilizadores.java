package Model;

import classescomunicacao.RegistoUtilizador;
import Model.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelGestaoUtilizadores {

    public ModelGestaoUtilizadores() {

    }

    public static int AdicionaUtil(RegistoUtilizador res) {
        PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
        try {
            if (p.ExisteUsername(res.getUsername())) {
                return -1;
            } else {
                if (res.getUsername().length() > 15) {
                    return -2;
                } else {
                    if (res.getNome().length() > 15) {
                        return -3;
                    } else {
                        if (res.getPassword().length() > 30) {
                            return -4;
                        } else {
                            try {
                                p.AdicionaUtilizador(res.getNome(), res.getUsername(), res.getPassword());
                                return 1;
                            } catch (NoSuchAlgorithmException ex) {
                                Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
                                return -5;
                            }
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            return -5;
        }
    }
    
    public static int LoginUtil(Login l)
    {
          PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
          
        try {
            if(p.VerificaLogin(l.getNome(), l.getPassword()) == true)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
        
    }

}
