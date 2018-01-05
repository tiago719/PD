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
    
    public static void LoginUtil(Login l, Socket socket, ObjectOutputStream out, AtualizaClientes atualizaClientes)
    {
          PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
          int idUtilizadorLogado;
          
        try {
            idUtilizadorLogado=p.VerificaLogin(l.getNome(), l.getPassword());
            if(idUtilizadorLogado!= -1)
            {
                Integer novo = new Integer(1);
                out.writeObject(novo);
                out.flush();
                
                try
                {
                    sleep(1000);
                } catch (InterruptedException ex)
                {
                    Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                p.setClienteLogado(idUtilizadorLogado);
                atualizaClientes.addCliente(p.getClienteLogado(idUtilizadorLogado, socket, out));
                atualizaClientes.atualizaClientes(idUtilizadorLogado);
                return;                
            }
            else
            {
                Integer novo = new Integer(0);
                out.writeObject(novo);
                out.flush();
                
                return;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(IOException e)
        {
            Logger.getLogger(ModelGestaoUtilizadores.class.getName()).log(Level.SEVERE, null, e);
        }    
    }
}
