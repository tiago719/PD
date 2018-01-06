package ComunicacaoP;

import Cliente.logic.ObservableGame;
import classescomunicacao.Mensagem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

public class ThreadChat extends Thread {

    public Mensagem mensagem;
   ObjectInputStream in= null;

    public void setIn(ObjectInputStream in) {
        this.in = in;
    }
    ObservableGame temp;

    @Override
    public void run() {
        while (true) {

            try {
                Object r = (Object) in.readObject();
                if(r instanceof Mensagem)
                {
                    mensagem = (Mensagem)r;
                    temp.Update();
                }

            } catch (IOException ex) {
                Logger.getLogger(ThreadChat.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ThreadChat.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(ThreadChat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void Update(ObservableGame x) {
        temp = x;
    }
    
}
