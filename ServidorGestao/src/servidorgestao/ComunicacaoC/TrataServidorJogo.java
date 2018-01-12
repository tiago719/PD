package servidorgestao.ComunicacaoC;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrataServidorJogo extends Thread {

    public static final int MAX_SIZE = 5000;
    public static final String HEARTBEAT = "1";

    private String IpBaseDados;
    private String IpServidorJogo;
    private int Porta;
    private double HeartBeatAntigo = -1;

    private DatagramSocket socket;
    private DatagramPacket packet;

    public TrataServidorJogo(int listeningPort) throws SocketException {
        Porta = listeningPort;
        socket = null;
        packet = null;
        socket = new DatagramSocket(Porta);
    }

    public String waitDatagram() throws IOException {
        String request = null;

        if (socket == null) {
            return null;
        }

        packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
        socket.receive(packet);

        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(packet.getData(), 0, packet.getLength()));

        try {
            request = (String) in.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TrataServidorJogo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return request;

    }

    @Override
    public void run() {
        String receivedMsg, timeMsg;
        Calendar calendar;

        if (socket == null) {
            return;
        }

        while (true) {

            try {
                receivedMsg = waitDatagram();
                socket.setSoTimeout(3 * 1000);

                if (receivedMsg == null) {
                    continue;
                }
                if (HeartBeatAntigo == -1) {
                    HeartBeatAntigo = Double.parseDouble(receivedMsg);
                } else if (Double.parseDouble(receivedMsg) != HeartBeatAntigo && HeartBeatAntigo != -1) {
                    continue;
                }

                String Ip = new String(IpBaseDados);

                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bOut);

                out.writeObject(Ip);
                out.flush();

                packet.setData(bOut.toByteArray());
                packet.setLength(bOut.size());

                socket.send(packet);
            } catch (SocketException ex) {
                Logger.getLogger(TrataServidorJogo.class.getName()).log(Level.SEVERE, null, ex);

            } catch (IOException ex) {
                HeartBeatAntigo = -1;
                try {
                    socket.setSoTimeout(0);
                } catch (SocketException ex1) {
                    Logger.getLogger(TrataServidorJogo.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }

        }
    }

    public String getIpBaseDados() {
        return IpBaseDados;
    }

    public void setIpBaseDados(String IpBaseDados) {
        this.IpBaseDados = IpBaseDados;
    }

    public String getIpServidorJogo() {
        return IpServidorJogo;
    }

    public void setIpServidorJogo(String IpServidorJogo) {
        this.IpServidorJogo = IpServidorJogo;
    }

    public int getPorta() {
        return Porta;
    }

    public void setPorta(int Porta) {
        this.Porta = Porta;
    }

    public void closeSocket() {
        if (socket != null) {
            socket.close();
        }
    }

}
