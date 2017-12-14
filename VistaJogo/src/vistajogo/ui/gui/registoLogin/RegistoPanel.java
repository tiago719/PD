/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistajogo.ui.gui.registoLogin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import vistajogo.logic.ObservableGame;
import static vistajogo.ui.gui.registoLogin.Constants.JTEXT_SIZE_X;
import static vistajogo.ui.gui.registoLogin.Constants.JTEXT_SIZE_Y;

/**
 *
 * @author Tiago Coutinho
 */
public class RegistoPanel extends JPanel implements Observer
{
    ObservableGame observableGame;
    JTextField nomeF, usernameF, passwordF, confirmarPasswordF;
    JLabel tituloL, nomeL, usernameL, passwordL, confirmarPasswordL;
    JButton registar;
    
    public RegistoPanel(ObservableGame o)
    {
        observableGame=o;
        observableGame.addObserver(this);

        setupComponents();
        setupLayout();
        setVisible(true);
        
    }
    
    public void setupComponents()
    {
        nomeF=new JTextField();
        //nomeF.setMaximumSize(new Dimension(Constants.DIM_Y_TEXT_FIELD, Constants.DIM_X_TEXT_FIELD));
        usernameF=new JTextField();
        usernameF.setMaximumSize(new Dimension(Constants.DIM_Y_TEXT_FIELD, Constants.DIM_X_TEXT_FIELD));   
        passwordF=new JTextField();
        passwordF.setMaximumSize(new Dimension(Constants.DIM_Y_TEXT_FIELD, Constants.DIM_X_TEXT_FIELD));   
        confirmarPasswordF=new JTextField();
        confirmarPasswordF.setMaximumSize(new Dimension(Constants.DIM_Y_TEXT_FIELD, Constants.DIM_X_TEXT_FIELD));   
        
        tituloL=new JLabel("Registar");
        tituloL.setFont(new Font("Serif", Font.BOLD, 25));
        nomeL=new JLabel("Nome:");
        usernameL=new JLabel("Username:");
        passwordL=new JLabel("Password:");
        confirmarPasswordL=new JLabel("Confirmar Password:");
        
        registar=new JButton("Registar");
        
        registar.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                observableGame.regista(usernameF.getText(),nomeF.getText(), 
                        passwordF.getText(), confirmarPasswordF.getText());
            }
        });

   }
    
    public void setupLayout()
    {
        setLayout(new BorderLayout());
        
        JPanel box=new JPanel(new BorderLayout());
        box.add(tituloL);
        box.add(nomeL);
        box.add(nomeF);
        box.add(tituloL);
        box.add(nomeL);
        box.add(nomeF);
        box.add(usernameL);
        box.add(usernameF);
        box.add(passwordL);
        box.add(passwordF);
        box.add(confirmarPasswordL);
        box.add(confirmarPasswordF);
        box.add(registar);
        add(box);
        
        validate();
    }
    
    @Override
    public void update(Observable o, Object arg)
    {
        repaint();
    }
    
}
