package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Comunicacao {

    public static final int PORTO = 5001;
    public static final int PORTO2 = 5002;
    public static final int BUFSIZE = 4000;
    public static final String IP = "localhost";
    public static final int TIMEOUT = 50000;
    
    Socket socket;
    ObjectInputStream in;
    ObjectOutputStream out;

    public Comunicacao(Socket socket)
    {
        try
        {
            out = new ObjectOutputStream(socket.getOutputStream());
            in= new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex)
        {
            System.out.println("Comunicacao: " + ex);
        }
    }

    public ObjectInputStream getObjectInputStream() {
        return in;
    }
}
