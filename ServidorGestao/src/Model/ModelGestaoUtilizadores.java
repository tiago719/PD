package Model;

import classescomunicacao.*;
import Model.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.ComunicacaoC.AtualizaClientes;

public class ModelGestaoUtilizadores {

    private AtualizaClientes atualizaClientes;
    
    public ModelGestaoUtilizadores() {
        atualizaClientes=new AtualizaClientes();
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
    
    public static int LoginUtil(Login l, String ip, int porto, AtualizaClientes atualizaClientes)
    {
          PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
          int idUtilizadorLogado;
          
        try {
            if((idUtilizadorLogado=p.VerificaLogin(l.getNome(), l.getPassword())) != -1)
            {
                p.setClienteLogado(idUtilizadorLogado);
                atualizaClientes.addCliente(p.getClienteLogado(idUtilizadorLogado, ip, porto));
                atualizaClientes.atualizaClientes();
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
