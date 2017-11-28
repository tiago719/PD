package vistajogo;

import vistajogo.logic.ObservableGame;
import vistajogo.ui.gui.ThreeInRowView;

public class MainWithGui 
{

    public static void main(String[] args)
    {                
        ThreeInRowView GUI = new ThreeInRowView(new ObservableGame());
    }
    
}
