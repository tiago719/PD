/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import Model.ObservableGame;
import servidorgestao.ComunicacaoC.TrataServidorJogo;
import classescomunicacao.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.ComunicacaoC.LogicaComunicacao;

public class ServidorGestao {

    public static void main(String[] args) {
        String IpDB = new String();

        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ip Servidor Gestao: ");
        try {
            IpDB = b.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (IpDB.length() < 3) {
            IpDB = "localhost";
        }

        TrataServidorJogo thread1 = null;
        try {
            thread1 = new TrataServidorJogo(6009);
            thread1.setIpBaseDados("localhost");
        } catch (SocketException ex) {
            Logger.getLogger(ServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        thread1.start();

        ObservableGame observableGame = new ObservableGame(IpDB);
        LogicaComunicacao logicaComunicacao = new LogicaComunicacao(observableGame);
        logicaComunicacao.start();

    }

}
