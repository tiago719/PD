package vistajogo;

import vistajogo.logic.ObservableGame;
import vistajogo.ui.gui.ThreeInRowView;
import vistajogo.ui.gui.registoLogin.RegistoLoginView;

public class MainWithGui 
{

    public static void main(String[] args)
    {                
        RegistoLoginView registoLoginView=new RegistoLoginView(new ObservableGame());
    }
    
}
