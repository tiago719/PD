/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao;

import Model.ModelGestaoUtilizadores;
import classescomunicacao.Login;
import classescomunicacao.RegistoUtilizador;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import servidorgestao.ComunicacaoC.RecebeClientes;

/**
 *
 * @author Tiago Coutinho
 */
public class Comunicacao {

    public static final int PORTO = 5001;
    public static final int PORTO2 = 5002;
    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;

    public Comunicacao() {
    }
    
    public void Start()
    {
        RecebeClientes recebeClientes = new RecebeClientes();
        recebeClientes.start();
        while(true)
        {
            //TODO define condicao
        }
    }
}
