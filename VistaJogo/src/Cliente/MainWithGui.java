package Cliente;

import Cliente.logic.ObservableGame;
import Cliente.ui.gui.ThreeInRowView;
import Cliente.ui.gui.EcraInicial.RegistoLoginView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MainWithGui 
{

    public static void main(String[] args)
    {   
        try {
            BufferedReader b = new BufferedReader(new InputStreamReader(System.in));
            Scanner scanner = new Scanner(System.in); 
            
            String ip;
            int porto=0;
            System.out.println("Ip Servidor Gestao: ");
            ip = b.readLine();
           
            if(ip.length() < 3)
            {
                ip = "localhost";
            }
           
            System.out.println("Porto: ");
            porto = scanner.nextInt();
            
            if(porto == 0)
            {
                porto = 5002;
            }
            RegistoLoginView registoLoginView=new RegistoLoginView(new ObservableGame(ip,porto));
            //ThreeInRowView v=new ThreeInRowView(new ObservableGame());
        } catch (IOException ex) {
            Logger.getLogger(MainWithGui.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
