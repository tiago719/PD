package servidorjogo;

import Model.BaseDados;
import Model.JogosDecorrer;
import Model.TrataServidorGestao;
import Model.TrataUtilizador;

public class ServidorJogo {

    public static void main(String[] args) {

        BaseDados BD = new BaseDados();
        JogosDecorrer jogosDecorrer = new JogosDecorrer();
        TrataUtilizador trataUtilizador = new TrataUtilizador(jogosDecorrer);
        trataUtilizador.start();
        
        TrataServidorGestao thread1 = new TrataServidorGestao("localhost",6009);
        thread1.start();

    }

}
