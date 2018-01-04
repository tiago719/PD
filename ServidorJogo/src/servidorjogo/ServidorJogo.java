
package servidorjogo;

import Model.BaseDados;
import Model.TrataServidorGestao;


///ARAY LIST COM MODELOS DE JOGO

public class ServidorJogo {

   
    public static void main(String[] args) {
        
        BaseDados BD = new BaseDados();
       TrataServidorGestao thread1 = new TrataServidorGestao(BD);
       thread1.IpDB = "localhost";
       thread1.IpSG = "localhost";
       thread1.PortoSG = 6005;
       thread1.start();
       
       
       
    }
    
}
