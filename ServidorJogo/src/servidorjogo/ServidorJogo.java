package servidorjogo;

import Model.BaseDados;
import Model.JogosDecorrer;
import Model.TrataServidorGestao;
import Model.TrataUtilizador;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorJogo {

    public static void main(String[] args) {

        BaseDados BD = new BaseDados();
        JogosDecorrer jogosDecorrer = new JogosDecorrer();
        TrataUtilizador trataUtilizador = new TrataUtilizador(jogosDecorrer);
        trataUtilizador.start();
        String IpDB = new String();
         Scanner scanner = new Scanner(System.in); 
        int porto;

        BufferedReader b = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Ip Servidor Gestao: ");
        try {
            IpDB = b.readLine();
        } catch (IOException ex) {
            Logger.getLogger(ServidorJogo.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (IpDB.length() < 3) {
            IpDB = "localhost";
        }

        System.out.println("Porto: ");
        porto = scanner.nextInt();

        if (porto == 0) {
            porto = 6009;
        }

        TrataServidorGestao thread1 = new TrataServidorGestao(IpDB.trim(), porto);
        thread1.start();

    }

}
