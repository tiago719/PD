package Model;

import classescomunicacao.*;
import Model.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.Cliente;
import servidorgestao.ComunicacaoC.AtualizaClientes;

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
    
    public static void LoginUtil(Login l, Cliente cliente, AtualizaClientes atualizaClientes)
    {
          PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
          int idUtilizadorLogado;
          
         try {
            idUtilizadorLogado=p.VerificaLogin(l.getNome(), l.getPassword());
            if(idUtilizadorLogado!= -1)
            {
                Integer novo = new Integer(1);
                cliente.getOut().writeObject(novo);
                cliente.getOut().flush();
                
                try
                {
                    sleep(1000);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                p.setClienteLogado(idUtilizadorLogado);
                cliente.setLogado(true);
                cliente.setId(idUtilizadorLogado);
                cliente.setNomeUtilizador(l.getNome());
                cliente.setNome(p.getNome(idUtilizadorLogado));

                atualizaClientes.addClienteLogado(cliente);
                atualizaClientes.atualizaClientes(idUtilizadorLogado);               
            }
            else
            {
                Integer novo = new Integer(0);
                cliente.getOut().writeObject(novo);
                cliente.getOut().flush();
                
            }
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }
}
