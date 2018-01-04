package Model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrataServidorGestao extends Thread {

    public String IpDB = null, IpSG;
    public int PortoSG;
    public static final int MAX_SIZE = 10000;
    public BaseDados BD;
    
    public TrataServidorGestao(BaseDados BD) {
        this.BD = BD;
    }
    
    @Override
    public void run() {

        InetAddress serverAddr = null;
        try {
            serverAddr = InetAddress.getByName(IpSG);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TrataServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        int serverPort = PortoSG;
        DatagramSocket socket = null;
        ByteArrayOutputStream bOut;
        ObjectOutputStream out;
        DatagramPacket packet;
        ObjectInputStream in;
        String request;

        try {
            socket = new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(TrataServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (true) {
            try {
                //ENVIA HEARTBEAT
                bOut = new ByteArrayOutputStream();

                out = new ObjectOutputStream(bOut);

                out.writeObject(new Boolean(true));
                out.flush();

                packet = new DatagramPacket(bOut.toByteArray(), bOut.size(),
                        serverAddr, serverPort);
                socket.send(packet);

                //RECEBE IP 
                packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
                socket.receive(packet);

                in = new ObjectInputStream(new ByteArrayInputStream(packet.getData(), 0, packet.getLength()));

                try {
                    request = (String) (in.readObject());
                    IpDB = request;
                    BD.setIpBD(IpDB);
                } catch (ClassCastException | ClassNotFoundException e) {
                    request = null;
                }

                if (request == null) {
                    Thread.currentThread().stop();
                }
            } catch (IOException ex) {
                Logger.getLogger(TrataServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
