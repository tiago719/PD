package Cliente;

import Cliente.logic.ObservableGame;
import Cliente.ui.gui.ThreeInRowView;
import Cliente.ui.gui.registoLogin.RegistoLoginView;

public class MainWithGui 
{

    public static void main(String[] args)
    {                
        RegistoLoginView registoLoginView=new RegistoLoginView(new ObservableGame());
        
//        ThreeInRowView v=new ThreeInRowView(new ObservableGame());
    }
    
}
