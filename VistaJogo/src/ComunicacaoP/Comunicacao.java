package ComunicacaoP;

import classescomunicacao.*;
import static classescomunicacao.ConstantesIps.PORTO2;
import static classescomunicacao.Constantes.CLIENT_LEFT;
import static classescomunicacao.ConstantesIps.PORTOSERVIDORJOGO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;

public class Comunicacao extends java.util.Observable {

    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;
    public String NomeUtilizador;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    private Socket socketServidorJogo;
    private ObjectInputStream inc;
    private ObjectOutputStream outc;

    public ObjectOutputStream getOutc() {
        return outc;
    }

    public void setOutc(ObjectOutputStream outc) {
        this.outc = outc;
    }
    

    public void setNomeUtilizador(String NomeUtilizador) {
        this.NomeUtilizador = NomeUtilizador;
    }

    public ObjectInputStream getIn() {
        return in;
    }

    public Socket getSocket() {
        return socket;
    }

    public Comunicacao() {
        try {
            socket = new Socket(IP, PORTO2);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Comunicacao: " + ex);
        }
    }

    public int registo(String nome, String username, String password) {
        try {
            RegistoUtilizador novo = new RegistoUtilizador(username, nome, password);
            out.writeObject(novo);
            out.flush();

            Integer returnedObject = (Integer) in.readObject();

            return returnedObject;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Registo: " + e);
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

            return returnedObject;

        } catch (Exception e) {
            System.out.println("erro login: " + e);
        }
        return -1;
    }

    public ObjectInputStream getObjectInputStream() {
        return in;
    }

    public void EnviaSMSTodos(String sms) {

        try {
            Mensagem envia = new Mensagem();
            envia.setDistinatario(null);
            envia.setMensagem(sms);
            envia.setRemetente(NomeUtilizador);

            out.writeObject(envia);
            out.flush();

        } catch (IOException ex) {
            System.out.println("erro EnviaSMSTodos: " + ex);
        }
    }

    public void EnviaSMSDestinatario(String sms, String Destinatario) {

        try {

            Mensagem envia = new Mensagem();
            envia.setDistinatario(Destinatario);
            envia.setMensagem(sms);
            envia.setRemetente(NomeUtilizador);

            out.writeObject(envia);
            out.flush();

        } catch (IOException ex) {
            System.out.println("erro EnviaSMSDestinatario: " + ex);
        }
    }

    public ArrayList<Mensagem> RecebeTodasMensagens() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socket.getInputStream());
        Mensagem returnedObjec = null;
        ArrayList<Mensagem> sms = new ArrayList<Mensagem>();
        do {
            returnedObjec = (Mensagem) in.readObject();
            if (returnedObjec != null) {
                sms.add(returnedObjec);
            }
        } while (returnedObjec != null);
        return sms;

    }

    public void logOut() {
        try {
            Integer novo = new Integer(CLIENT_LEFT);
            out.writeObject(novo);
            out.flush();

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
            }

            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PedePar(String Nikname) {
        try {
            FormarPar p = new FormarPar(NomeUtilizador, Nikname);
            out.writeObject(p);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EnviaConfirmacaoPar(FormarPar par) {
        try {
            par.setAceite(true);
            out.writeObject(par);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getPedidosPares() {

    }

    public void EnviaIniciodoJogo(FormarPar par) {
        try {
            socketServidorJogo = new Socket("localhost",PORTOSERVIDORJOGO);
            AcoesPartida x = new AcoesPartida(1, par.getIdPar());
            outc = new ObjectOutputStream(socketServidorJogo.getOutputStream());
            
            out.writeObject(x);
            out.flush();
            
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
}
