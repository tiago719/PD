package Model;

import classescomunicacao.*;
import BaseDados.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModelGestaoUtilizadores {

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
                            return 1;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
            return -5;
        }
    }
    
    public static int LoginUtil(Login l, PesquisasGestaoUtilizadores pesquisasGestaoUtilizadores)
    {
          int idUtilizadorLogado;
          
         try {
            idUtilizadorLogado=pesquisasGestaoUtilizadores.VerificaLogin(l.getNome(), l.getPassword());
            if(idUtilizadorLogado!= -1)
            {               
                pesquisasGestaoUtilizadores.setClienteLogado(idUtilizadorLogado);
                return idUtilizadorLogado;
            }
            else
            {
                return -1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }   
         return -1;
    }
}
