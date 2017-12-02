/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistajogo.ui.gui.registoLogin;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import vistajogo.logic.ObservableGame;

/**
 *
 * @author Tiago Coutinho
 */
public class RegistoLoginView extends JFrame implements Observer
{
    ObservableGame observableGame;
    RegistoLoginPanel panel;
    
    public RegistoLoginView(ObservableGame o)
    {
        super("Three In a Row");
        observableGame=o;
        observableGame.addObserver(this);
        
        panel=new RegistoLoginPanel(observableGame);
        
        addComponents();
        
        setVisible(true);
        
        this.setSize(700, 500);
        this.setMinimumSize(new Dimension(650, 450));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        validate();
    }
    
    public void addComponents()
    {
        Container cp=getContentPane();
        
        cp.setLayout(new BorderLayout());
        cp.add(panel,BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        repaint();
    }
}
