package servidorgestao.ComunicacaoC;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrataServidorJogo extends Thread {

    public String IpDB = null;
    public int PortoSG;
    public static final int MAX_SIZE = 10000;

    @Override
    public void run() {
        ByteArrayOutputStream bOut;
        ObjectOutputStream out;
        DatagramPacket packet = null;
        DatagramSocket socketHeart = null;
        ObjectInputStream in;

        Boolean request;

        try {
            socketHeart = new DatagramSocket(6001);
            packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);

        } catch (SocketException ex) {
            Logger.getLogger(TrataServidorJogo.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (true) {
            try {
                ///RECEBE HEARTBEAT
                socketHeart.receive(packet);
                in = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
                try {
                    request = (Boolean) (in.readObject());
                } catch (ClassCastException | ClassNotFoundException e) {
                    request = null;
                }

                if (request == true) {
                    //ENVIA IP
                    bOut = new ByteArrayOutputStream(MAX_SIZE);
                    out = new ObjectOutputStream(bOut);
                    Boolean HeartBeat = new Boolean(true);

                    out.writeObject(HeartBeat);

                    packet.setData(bOut.toByteArray());
                    packet.setLength(bOut.size());

                    socketHeart.send(packet);
                }

            } catch (SocketException ex) {
                Logger.getLogger(TrataServidorJogo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(TrataServidorJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
