/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistajogo.ui.gui.registoLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import vistajogo.logic.ObservableGame;
import static vistajogo.ui.gui.registoLogin.Constants.DIM_X_LOGIN;
import static vistajogo.ui.gui.registoLogin.Constants.DIM_X_REGISTO;
import static vistajogo.ui.gui.registoLogin.Constants.DIM_Y_LOGIN;
import static vistajogo.ui.gui.registoLogin.Constants.DIM_Y_REGISTO;

/**
 *
 * @author Tiago Coutinho
 */
public class RegistoLoginPanel extends JPanel implements Observer
{
    ObservableGame observableGame;
    RegistoPanel registoPanel;
    LoginPanel loginPanel;
    
    public RegistoLoginPanel(ObservableGame o)
    {
        observableGame=o;
        
        setupComponents();
        setupLayout();
    }
    
    public void setupComponents()
    {
        registoPanel=new RegistoPanel(observableGame);
        loginPanel=new LoginPanel(observableGame);
    }
    
    public void setupLayout()
    {   
        JPanel pEste = new JPanel();
        pEste.setMaximumSize(new Dimension(DIM_X_LOGIN, DIM_Y_LOGIN));
        pEste.setMinimumSize(new Dimension(DIM_X_LOGIN, DIM_Y_LOGIN));
        pEste.setPreferredSize(new Dimension(DIM_X_LOGIN, DIM_Y_LOGIN));
        pEste.setLayout(new BorderLayout());
        pEste.setBorder(new LineBorder(Color.GRAY));
        pEste.add(new LoginPanel(observableGame));
        
        JPanel pOeste=new JPanel();
        pOeste.setMaximumSize(new Dimension(DIM_X_REGISTO, DIM_Y_REGISTO));
        pOeste.setMinimumSize(new Dimension(DIM_X_REGISTO, DIM_Y_REGISTO));
        pOeste.setPreferredSize(new Dimension(DIM_X_REGISTO, DIM_Y_REGISTO));
        pOeste.setLayout(new BorderLayout());
        pOeste.setBorder(new LineBorder(Color.RED));
        pOeste.add(new RegistoPanel(observableGame));

        JPanel center=new JPanel(new BorderLayout(100,0));
        center.add(pOeste, BorderLayout.WEST);
        center.add(pEste, BorderLayout.EAST);
        
        add(center);
        
        validate();
        
    }

    @Override
    public void update(Observable o, Object arg)
    {
        repaint();
    }
}
