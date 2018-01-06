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

//public class ThreadChat extends Thread {
//
//    public Mensagem mensagem;
//    Socket socket = null;
//    ObservableGame temp;
//
//    @Override
//    public void run() {
//
//        while (true) {
//            ObjectInputStream in = null;
//            try {
//                in = new ObjectInputStream(socket.getInputStream());
//                Mensagem mensagem = (Mensagem) in.readObject();
//
//                temp.Update();
//
//            } catch (IOException ex) {
//                Logger.getLogger(ThreadChat.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (ClassNotFoundException ex) {
//                Logger.getLogger(ThreadChat.class.getName()).log(Level.SEVERE, null, ex);
//            } finally {
//                try {
//                    in.close();
//                } catch (IOException ex) {
//                    Logger.getLogger(ThreadChat.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
//
//    public Mensagem getMensagem() {
//        return mensagem;
//    }
//
//    public void Update(ObservableGame x) {
//        temp = x;
//    }
//}
