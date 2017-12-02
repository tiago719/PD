/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistajogo.ui.gui.registoLogin;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import vistajogo.logic.ObservableGame;

/**
 *
 * @author Tiago Coutinho
 */
public class LoginPanel extends JPanel implements Observer
{
    ObservableGame observableGame;
    
    public LoginPanel(ObservableGame o)
    {
        observableGame=o;
        
        o.addObserver(this);
        
        setBackground(Color.GREEN);
        setupComponents();
        setupLayout();
    }
    
    public void setupComponents()
    {
        
    }
    
    public void setupLayout()
    {
        
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
