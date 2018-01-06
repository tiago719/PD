/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ModelGestaoUtilizadores;
import Model.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import classescomunicacao.Mensagem;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.Cliente;
import static servidorgestao.Comunicacao.PORTO2;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebeObjetos extends Thread {

    AtualizaClientes atualizaClientes;

    public RecebeObjetos() {
        atualizaClientes = new AtualizaClientes();
    }

    @Override
    public void run() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORTO2);
        } catch (IOException ex) {
            Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        int a = 0;
        while (a == 0)//TODO:definir condição de paragem 
        {
            try {
                Socket nextClient = serverSocket.accept();

                ObjectInputStream in = new ObjectInputStream(nextClient.getInputStream());
                ObjectOutputStream out = new ObjectOutputStream(nextClient.getOutputStream());


                    Object returnedObject=in.readObject();
                    if(returnedObject instanceof RegistoUtilizador)
                    {
                        enviaRespostaCliente((RegistoUtilizador)returnedObject, out);
                    }
                    else if(returnedObject instanceof Login)
                    {
                        ModelGestaoUtilizadores.LoginUtil((Login)returnedObject, nextClient, out, atualizaClientes);
                    }
                    else if(returnedObject instanceof Mensagem )
                    {
                        //TODO: Trabalha preto
                    }
                 }
                catch (Exception e) 
                {
                    System.out.println(e);

                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(RecebeLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void enviaRespostaCliente(RegistoUtilizador registo, ObjectOutputStream out) {
        try {
            int devolve = ModelGestaoUtilizadores.AdicionaUtil(registo);

            Integer novo = new Integer(devolve);
            out.writeObject(novo);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(RecebeObjetos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void TrataMensagens(Mensagem sms) {
        if (sms.getDistinatario() == null) {
            for (Cliente c : atualizaClientes.getClientesLogados()) {
                try {
                    c.getOut().writeObject(sms);

                    c.getOut().flush();

                } catch (IOException ex) {
                    Logger.getLogger(RecebeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            for (Cliente c : atualizaClientes.getClientesLogados()) {

                if (c.getNomeUtilizador().equals(sms.getDistinatario())) {
                    try {
                        c.getOut().writeObject(sms);
                        c.getOut().flush();
                        
                        PesquisasGestaoUtilizadores pesq = new PesquisasGestaoUtilizadores();
                        
                        try {
                            pesq.AdicionaSMS(sms);
                        } catch (SQLException ex) {
                            Logger.getLogger(RecebeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(RecebeObjetos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                }

            }
        }
    }
}
