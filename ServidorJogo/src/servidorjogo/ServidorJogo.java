package servidorjogo;

import Model.BaseDados;
import Model.JogosDecorrer;
import Model.TrataServidorGestao;
import Model.TrataUtilizador;

///ARAY LIST COM MODELOS DE JOGO
public class ServidorJogo {

    public static void main(String[] args) {

        BaseDados BD = new BaseDados();
        JogosDecorrer jogosDecorrer = new JogosDecorrer();
        TrataUtilizador trataUtilizador = new TrataUtilizador(jogosDecorrer);
        trataUtilizador.start();
        TrataServidorGestao thread1 = new TrataServidorGestao(BD);
        thread1.IpDB = "localhost";
        thread1.IpSG = "localhost";
        thread1.PortoSG = 6005;
        thread1.start();

    }

}
