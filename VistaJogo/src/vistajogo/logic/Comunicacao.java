package vistajogo.logic;

import classescomunicacao.RegistoUtilizador;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Comunicacao
{
    public static final int PORTO = 5001;
    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;

    public Comunicacao()
    {

    }

    public void registo(String nome, String email, String password)
    {
        Socket socket = null;
        try
        {
            socket = new Socket(IP, PORTO);
            socket.setSoTimeout(TIMEOUT);

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            RegistoUtilizador novo = new RegistoUtilizador(nome, email, password);
            out.writeObject(novo);
            out.flush();
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            
            Integer returnedObject=(Integer)in.readObject();
            
            System.out.println(returnedObject);

        } 
        catch (Exception e)
        {
            System.out.println(e);
        } 
        finally
        {
            if (socket != null)
            {
                try
                {
                    socket.close();
                } 
                catch (IOException ex)
                {
                    System.out.println("Erro a fechar o socket a registar.");
                    System.out.println(ex);
                }
            }
        }
    }
}
