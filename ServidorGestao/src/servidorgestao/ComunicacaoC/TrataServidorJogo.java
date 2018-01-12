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

    public static final int MAX_SIZE = 5000;
    public static final String HEARTBEAT = "1";

    private String IpBaseDados;
    private DatagramSocket socket;
    private DatagramPacket packet; //para receber os pedidos e enviar as respostas

    public TrataServidorJogo(String IpBaseDados) throws SocketException {
        packet = null;
        socket = new DatagramSocket();
        this.IpBaseDados = IpBaseDados;
    }

    @Override
    public void run() {
        String receivedMsg;

        if (socket == null) {
            return;
        }

        while (true) {

            try {
                receivedMsg = waitDatagram();

                if (receivedMsg == null) {
                    continue;
                }

                ByteArrayOutputStream bOut = new ByteArrayOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(bOut);

                out.writeObject(IpBaseDados);
                out.flush();

                packet.setData(bOut.toByteArray());
                packet.setLength(bOut.size());

                socket.send(packet);
            } catch (IOException ex) {
                Logger.getLogger(TrataServidorJogo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public void setIpBaseDados(String IpBaseDados) {
        this.IpBaseDados = IpBaseDados;
    }

}
