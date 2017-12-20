/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import classescomunicacao.*;
import java.io.BufferedReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author edu_f
 */
public class ServidorGestao {
    
    public static void main(String[] args) {
        
        Comunicacao comunicacao=new Comunicacao();
        
        comunicacao.recebeRegistos();
        comunicacao.recebeLogins();
       
    }
    
}
