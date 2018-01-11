package ComunicacaoP;

import classescomunicacao.*;
import static classescomunicacao.ConstantesIps.PORTO2;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Comunicacao {


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
            System.out.println("Comunicacao: " + ex);
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
            System.out.println("registo: " + e);

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
            System.out.println("erro login: " + e);
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
            System.out.println("erro EnviaSMSTodos: " + ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                System.out.println("erro EnviaSMSDestinatario finally: " + ex);
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
            System.out.println("erro EnviaSMSDestinatario: " + ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                System.out.println("erro EnviaSMSDestinatario finally: " + ex);
            }
        }
    }

 /*   public ArrayList<Mensagem> RecebeTodasMensagens() throws IOException, ClassNotFoundException {
        in = new ObjectInputStream(socketClientesLogados.getInputStream());
        Mensagem returnedObjec = null;
        ArrayList<Mensagem> sms = new ArrayList<Mensagem>();
        do {
            returnedObjec = (Mensagem) in.readObject();
            if (returnedObjec != null) {
                sms.add(returnedObjec);
            }
        } while (returnedObjec != null);
        return sms;
    }*/
    
    public Socket getSocketModeloServJogo(int idJogo){
        Socket ret = null;
        
         ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(socket.getOutputStream());
            
            out.writeObject(new Integer(idJogo));
            out.flush();
            
            
            
            ret = (Socket)in.readObject();
            out.flush();

        } catch (IOException ex) {
            System.out.println("erro EnviaSMSDestinatario: " + ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Comunicacao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                System.out.println("erro EnviaSMSDestinatario finally: " + ex);
            }
        }
        return ret;
    }

}
