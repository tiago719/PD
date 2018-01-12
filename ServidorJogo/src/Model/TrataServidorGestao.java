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
import classescomunicacao.Constantes.*;

public class TrataServidorGestao extends Thread {

    private static int BUFSIZE = 100;
    private String IpServidorGestao;
    private int PortoServidorGestao;
    private String IpBaseDados;
    double HeartBeatnumero;

    public TrataServidorGestao(String IpServidorGestao, int PortoServidorGestao) {
        this.HeartBeatnumero = Math.random() * 1000;
        this.IpServidorGestao = IpServidorGestao;
        this.PortoServidorGestao = PortoServidorGestao;
    }

    @Override
    public void run() {

        InetAddress addr = null;
        DatagramSocket socket = null;

        try {

            addr = InetAddress.getByName(IpServidorGestao);

            socket = new DatagramSocket();
            String message = new String();
            message =""+ HeartBeatnumero;

            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bOut);
            while (true) {
                out.writeObject(message);
                out.flush();

                DatagramPacket Packet = new DatagramPacket(bOut.toByteArray(), bOut.size(), addr, PortoServidorGestao);

                socket.send(Packet);

                Packet = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);

                socket.receive(Packet);

                ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(Packet.getData(), 0, Packet.getLength()));

                String returnedObject = (String) in.readObject();
                IpBaseDados = returnedObject;

                System.out.println("Ip: " + returnedObject);
                Thread.sleep(1000);
            }

        } catch (Exception erro) {
            System.out.println("Erro: " + erro);

        } finally {
            socket.close();
        }

    }

}
