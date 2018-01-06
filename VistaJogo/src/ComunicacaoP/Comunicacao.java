package ComunicacaoP;

import static Cliente.logic.Constants.PORTO_SERVIDOR_GESTAO;
import classescomunicacao.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Comunicacao {

    public static final int PORTO = 5001;
    public static final int PORTO2 = 5002;
    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;
    
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Comunicacao()
    {
        try
        {
            socket=new Socket(IP, PORTO2);
            out = new ObjectOutputStream(socket.getOutputStream());
            in= new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex)
        {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int registo(String nome, String email, String password)
    {
        try
        {
            RegistoUtilizador novo = new RegistoUtilizador(nome, email, password);
            out.writeObject(novo);
            out.flush();

            Integer returnedObject = (Integer) in.readObject();

            return returnedObject;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e);

        }
        return -5;
    }

    public int login(String username, String password) {
        //TODO_ Fechas este socket. MAS NAO NESTA FUNCAO
        try {
            Login novo = new Login(username, password);
            out.writeObject(novo);
            out.flush();

            Integer returnedObject = (Integer) in.readObject();

//            if (returnedObject == 1) {
//                socketMensagens = new Socket(IP, PORTO);
//            }

            return returnedObject;

        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    public ObjectInputStream getObjectInputStream() {
        return in;
    }

    public void EnviaSMSTodos(String sms) {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            Mensagem envia = new Mensagem();
            envia.setDistinatario(null);
            envia.setMensagem(sms);
            //envia.setRemetente(UserName);

            out.writeObject(envia);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void EnviaSMSDestinatario(String sms, String Destinatario) {

        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            Mensagem envia = new Mensagem();
            envia.setDistinatario(Destinatario);
            envia.setMensagem(sms);
            //envia.setRemetente(UserName);
            out.writeObject(envia);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
