/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.ui.gui.EcraInicial;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Cliente.logic.ObservableGame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Tiago Coutinho
 */
public class RegistoLoginView extends JFrame implements Observer
{
    ObservableGame ObservableGame;
    JPanel PainelPrincipal;
    RegistoLoginPanel RegistoLoginPanel;
    EcraPrincipal EcraPrincipalPanel;
    
    public RegistoLoginView(ObservableGame o)
    {
        super("Monitor");
        
        
        ObservableGame=o;
        ObservableGame.addObserver(this);
                
        RegistoLoginPanel=new RegistoLoginPanel(ObservableGame);
        EcraPrincipalPanel=new EcraPrincipal(ObservableGame);
        CardLayout cl;
        
        PainelPrincipal=new JPanel(cl=new CardLayout());
        PainelPrincipal.add(RegistoLoginPanel, "RegistoLogin");
        PainelPrincipal.add(EcraPrincipalPanel, "EcraPrincipal");
        cl.first(PainelPrincipal);
        
        RegistoLoginPanel.setRegistoLoginView(this);
        
        RegistoLoginPanel.setCardPanel(PainelPrincipal);
        
        addComponents();
        
        setVisible(true);
        
        this.setSize(500, 400);
        this.setMinimumSize(new Dimension(650, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        validate();
        
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                try {
                    ObservableGame.logOut();
                    
                } catch (Exception ex) {
                }
                System.exit(0);
            }
        });
        

    }
    
    public void addComponents()
    {
        Container cp=getContentPane();
        
        cp.setLayout(new BorderLayout());
        cp.add(PainelPrincipal,BorderLayout.CENTER);
    }

    @Override
    public void update(Observable o, Object arg)
    {
        repaint();
    }
}
