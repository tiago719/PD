package ComunicacaoP;

import Cliente.logic.ObservableGame;
import classescomunicacao.*;
import static classescomunicacao.ConstantesIps.PORTO2;
import static classescomunicacao.Constantes.CLIENT_LEFT;
import static classescomunicacao.ConstantesIps.PORTOSERVIDORJOGO;
import classescomunicacao.ModelJogo.GameModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;

public class Comunicacao extends java.util.Observable {

    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;
    private String NomeUtilizador;

    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private Socket socketServidorJogo;
    private ObjectOutputStream outc;

    private Socket socketModeloJogo;
    private ObjectOutputStream outSocketModeloJogo;
    private ObjectInputStream inSocketModeloJogo;

    private ObjectInputStream inc;

    FormarPar par;
    ObservableGame observableGame;

    Thread threadLeJogadas;

    public Comunicacao(ObservableGame observableGame) {

        this.observableGame = observableGame;
        try {
            socket = new Socket(IP, PORTO2);
            out = new ObjectOutputStream(socket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            System.out.println("Comunicacao: " + ex);
        }
    }

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

            if (returnedObject == 1) {
                NomeUtilizador = novo.getNome();
            }

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
            p.setAceite(Constantes.PEDIDO_FEITO);
            out.writeObject(p);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EnviaConfirmacaoPar(FormarPar par) {
        try {
            out.writeObject(par);
            out.flush();

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void EnviaIniciodoJogo(FormarPar par) {
        try {
            AcoesPartida x = new AcoesPartida(1, par.getIdPar());

            outc.writeObject(x);
            outc.flush();

            this.par = par;
            socketModeloJogo = new Socket(IP, 5000 + par.getIdPar());
            threadLeJogadas = new threadLeJogadas(observableGame, socketModeloJogo);
            threadLeJogadas.start();
//            GameModel gameModel = (GameModel) inc.readObject();
//            observableGame.setGameModel(gameModel);
//            observableGame.Update();
//            if (gameModel.getIdJogo() > 0) {

//                outSocketModeloJogo = new ObjectOutputStream(socketModeloJogo.getOutputStream());
//                inSocketModeloJogo = new ObjectInputStream(socketModeloJogo.getInputStream());
//            }
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void abandonaPar(FormarPar formarPar)
    {
        try
        {
            formarPar.setAceite(Constantes.PEDIDO_RECUSADO);
            out.writeObject(formarPar);
            out.flush();
        } catch (IOException ex)
        {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Desiste(FormarPar ParAtual) {
        try {

            AcoesPartida x = new AcoesPartida(3, ParAtual.getIdPar(), NomeUtilizador);

            outc.writeObject(x);
            outc.flush();

        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getUserName() {
        return NomeUtilizador;
    }

    public void novaJogada(int line, int column, int idJogo, String nickName) {
        try {

            outSocketModeloJogo.writeObject(new Jogadas(nickName, line, column, idJogo));
            outSocketModeloJogo.flush();
        } catch (IOException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void comunicaServidorJogo()
    {         
        try
        {
            socketServidorJogo = new Socket(IP, PORTOSERVIDORJOGO);
            outc = new ObjectOutputStream(socketServidorJogo.getOutputStream());
            outc.flush();
            inc = new ObjectInputStream(socketServidorJogo.getInputStream());
        } catch (IOException ex)
        {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}