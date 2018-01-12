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

    private String IpServidorGestao;
    private int PortoServidorGestao;
    private String IpBaseDados;
    private static int BUFSIZE = 100;

    public TrataServidorGestao(String IpServidorGestao, int PortoServidorGestao) {
        this.IpServidorGestao = IpServidorGestao;
        this.PortoServidorGestao = PortoServidorGestao;
    }

    @Override
    public void run() {
        try {
            InetAddress addr = null;
            DatagramSocket socket = null;
            
            addr = InetAddress.getByName(IpServidorGestao);
            
            socket = new DatagramSocket();
            
            socket.setSoTimeout(2 * 1000);
            
            String message = new String("1");
            
            ByteArrayOutputStream bOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(bOut);
            
            while (true) {
                try {
                    out.writeObject(message);
                    out.flush();
                    
                    DatagramPacket Packet = new DatagramPacket(bOut.toByteArray(), bOut.size(), addr, PortoServidorGestao);
                    
                    socket.send(Packet);
                    
                    Packet = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
                    
                    socket.receive(Packet);
                    
                    ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(Packet.getData(), 0, Packet.getLength()));
                    
                    String returnedObject = (String) in.readObject();
                    
                } catch (Exception erro) {
                    System.out.println("Erro: " + erro);
                }
            }
        } catch (SocketException ex) {
            Logger.getLogger(TrataServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(TrataServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TrataServidorGestao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getIpBaseDados() {
        return IpBaseDados;
    }

    public void setIpServidorGestao(String IpServidorGestao) {
        this.IpServidorGestao = IpServidorGestao;
    }

    public void setPortoServidorGestao(int PortoServidorGestao) {
        this.PortoServidorGestao = PortoServidorGestao;
    }

}
