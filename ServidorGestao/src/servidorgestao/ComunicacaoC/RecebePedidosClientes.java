/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ModelGestaoUtilizadores;
import BaseDados.PesquisasGestaoUtilizadores;
import classescomunicacao.Login;
import classescomunicacao.Mensagem;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Model.Cliente;
import Model.ObservableGame;
import com.sun.java.accessibility.util.EventID;
import java.net.Socket;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebePedidosClientes extends Thread {

    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private ObservableGame observableGame;

    public RecebePedidosClientes(Socket socket, ObservableGame observableGame) {
        try {
            this.observableGame = observableGame;
            this.socket = socket;
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        int a = 0, ret;
        while (a == 0)//TODO: define condicao de paragem
        {
            try {
                Object returnedObject = in.readObject();
                if (returnedObject instanceof RegistoUtilizador) {
                    ret = observableGame.regista((RegistoUtilizador) returnedObject);

                    Integer novo = new Integer(ret);
                    out.writeObject(novo);
                    out.flush();
                } else if (returnedObject instanceof Login) {
                    ret = observableGame.login((Login) returnedObject, this);

                    Integer novo = new Integer(ret);
                    out.writeObject(novo);
                    out.flush();

                    observableGame.novoCliente(this, observableGame.getCliente(this));

                    observableGame.update();
                } else if (returnedObject instanceof Mensagem) {
                    TrataMensagens((Mensagem)returnedObject);
                }
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }

    public void TrataMensagens(Mensagem sms) {
        if (sms.getDistinatario() == null) {
            for (Map.Entry<RecebePedidosClientes, Cliente> en : observableGame.getTodosClientes().entrySet()) {
                try {
                    RecebePedidosClientes key = en.getKey();
                    Cliente value = en.getValue();
                    sms.setRemetente(value.getNome());
                    key.getOut();
                    key.getOut().writeObject(sms);
                    key.getOut().flush();
                } catch (IOException ex) {
                    Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } else {
            for (Map.Entry<RecebePedidosClientes, Cliente> en : observableGame.getTodosClientes().entrySet()) {
                RecebePedidosClientes key = en.getKey();
                Cliente value = en.getValue();
                if (value.getNomeUtilizador().equals(sms.getDistinatario())) {
                    try {
                        key.getOut().writeObject(sms);
                        key.getOut().flush();

                        PesquisasGestaoUtilizadores pesq = new PesquisasGestaoUtilizadores();

                        pesq.AdicionaSMS(sms);
                        break;
                    } catch (IOException ex) {
                        Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(RecebePedidosClientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

            }
        }
    }
}