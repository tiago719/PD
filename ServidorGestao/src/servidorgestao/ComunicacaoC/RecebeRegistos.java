/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import Model.ModelGestaoUtilizadores;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import static servidorgestao.Comunicacao.PORTO;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebeRegistos extends Thread {

        @Override
        public void run() {
            int devolve = 0;
            ServerSocket serverSocket = null;
            try {
                serverSocket = new ServerSocket(PORTO);
                while (true) {
                    Socket nextClient = serverSocket.accept();

                    ObjectInputStream in = new ObjectInputStream(nextClient.getInputStream());

                    RegistoUtilizador returnedObject = (RegistoUtilizador) in.readObject();

                    devolve = ModelGestaoUtilizadores.AdicionaUtil(returnedObject);

                    ObjectOutputStream out = new ObjectOutputStream(nextClient.getOutputStream());

                    Integer novo = new Integer(devolve);
                    out.writeObject(novo);
                    out.flush();
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    serverSocket.close();
                } catch (IOException ex) {
                    System.out.println("Erro a fechar o socket:" + ex);
                }
            }
        }
    }
