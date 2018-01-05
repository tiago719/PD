/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import Model.TrataServidorJogo;
import classescomunicacao.*;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorGestao {
    
    public static void main(String[] args) {
        
        TrataServidorJogo thread1 = new TrataServidorJogo();
        thread1.IpDB = "localhost";
        thread1.PortoSG = 6000;
        thread1.start();
        
        
        Comunicacao comunicacao =new Comunicacao();
        
        comunicacao.Start();
        
        while(true)
        {
            
        }
    }
    
}
