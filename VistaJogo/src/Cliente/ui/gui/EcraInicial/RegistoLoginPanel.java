/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.ui.gui.EcraInicial;

import java.awt.BorderLayout;

import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;
import Cliente.logic.ObservableGame;
import static Cliente.ui.gui.EcraInicial.Constants.DIM_X_LOGIN;
import static Cliente.ui.gui.EcraInicial.Constants.DIM_X_REGISTO;
import static Cliente.ui.gui.EcraInicial.Constants.DIM_Y_LOGIN;
import static Cliente.ui.gui.EcraInicial.Constants.DIM_Y_REGISTO;

public class RegistoLoginPanel extends JPanel implements Observer
{
    ObservableGame ObservableGame;
    Registo registoPanel;
    Login loginPanel;
    JPanel CardPanel;
    RegistoLoginView RegistoLoginView;
    
    public RegistoLoginPanel(ObservableGame o)
    {
        ObservableGame=o;
        
        setupComponents();
        setupLayout();
    }
    
    public void setupComponents()
    {
        registoPanel=new Registo(ObservableGame);
        loginPanel=new Login(ObservableGame);
    }
    
    public void setCardPanel(JPanel J)
    {
        CardPanel=J;
        loginPanel.setCardPanel(J);
    }
    
    public void setRegistoLoginView(RegistoLoginView v)
    {
        RegistoLoginView=v;
        loginPanel.setRegistoLoginView(v);
    }
    
    public void setupLayout()
    {   
        JPanel pEste = new JPanel();
        pEste.setMaximumSize(new Dimension(DIM_X_LOGIN, DIM_Y_LOGIN));
        pEste.setMinimumSize(new Dimension(DIM_X_LOGIN, DIM_Y_LOGIN));
        pEste.setPreferredSize(new Dimension(DIM_X_LOGIN, DIM_Y_LOGIN));
        pEste.add(loginPanel);
        
        JPanel pOeste=new JPanel();
        pOeste.setMaximumSize(new Dimension(DIM_X_REGISTO, DIM_Y_REGISTO));
        pOeste.setMinimumSize(new Dimension(DIM_X_REGISTO, DIM_Y_REGISTO));
        pOeste.setPreferredSize(new Dimension(DIM_X_REGISTO, DIM_Y_REGISTO));
        pOeste.add(registoPanel);

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
