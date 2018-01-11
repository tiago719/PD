/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.ui.gui.EcraInicial;
 
import java.awt.Color;
import javax.swing.JOptionPane;
import Cliente.logic.ObservableGame;


/**
 *
 * @author Tiago Coutinho
 */
public class Registo extends javax.swing.JPanel
{
    ObservableGame ObservableGame;
    boolean UserFirst=true,EmailFirst=true,PassFirst=true,CPassFirst=true, Inicio=true;
    boolean UserOk = true,Emailok=true, Passok = true;
    public Registo()
    {
        initComponents();
    }

    Registo(ObservableGame ObservableGame)
    {
        this.ObservableGame=ObservableGame;
        initComponents();
        jPassword.setEchoChar((char) 0);
        jCPassword.setEchoChar((char) 0);
        jErroPass.setVisible(false);
        jErroUser.setVisible(false);
        jErroNome.setVisible(false);
        jErroCPass.setVisible(false);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents

    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        jUsername = new javax.swing.JTextField();
        jNome = new javax.swing.JTextField();
        jRegistar = new javax.swing.JButton();
        jPassword = new javax.swing.JPasswordField();
        jCPassword = new javax.swing.JPasswordField();
        jErroUser = new javax.swing.JLabel();
        jErroNome = new javax.swing.JLabel();
        jErroPass = new javax.swing.JLabel();
        jErroCPass = new javax.swing.JLabel();

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 24)); // NOI18N
        jLabel1.setText("Registar");

        jUsername.setForeground(new java.awt.Color(204, 204, 204));
        jUsername.setText("Nome de Utilizador");
        jUsername.setDisabledTextColor(new java.awt.Color(204, 204, 204));


        jUsername.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                jUsernameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {

                jUsernameFocusLost(evt);
            }
        });

        jNome.setForeground(new java.awt.Color(204, 204, 204));
        jNome.setText("Nome");


        jNome.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                jNomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {

                jNomeFocusLost(evt);
            }
        });

        jRegistar.setText("Registar");


        jRegistar.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jRegistarMouseClicked(evt);
            }
        });
        jRegistar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRegistarActionPerformed(evt);
            }
        });

        jPassword.setForeground(new java.awt.Color(204, 204, 204));
        jPassword.setText("Password");


        jPassword.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                jPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                jPasswordFocusLost(evt);
            }
        });
        jPassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {

                jPasswordActionPerformed(evt);
            }
        });

        jCPassword.setForeground(new java.awt.Color(204, 204, 204));
        jCPassword.setText("Confirmar Password");

        jCPassword.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                jCPasswordFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                jCPasswordFocusLost(evt);
            }
        });
        jCPassword.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {

                jCPasswordActionPerformed(evt);
            }
        });

        jErroUser.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jErroUser.setForeground(new java.awt.Color(153, 0, 0));
        jErroUser.setText("Utilizador no Máximo 15 Caracteres");

        jErroNome.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jErroNome.setForeground(new java.awt.Color(153, 0, 0));
        jErroNome.setText("Nome no Máximo 15 Caracteres");

        jErroPass.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jErroPass.setForeground(new java.awt.Color(153, 0, 0));
        jErroPass.setText("Palavra-Passe no Máximo 15 caracteres");

        jErroCPass.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jErroCPass.setForeground(new java.awt.Color(153, 0, 0));
        jErroCPass.setText("Palavras-Passe não Correspondem");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jUsername, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jNome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jErroUser)
                            .addComponent(jErroNome)
                            .addComponent(jErroPass)
                            .addComponent(jErroCPass)
                            .addComponent(jCPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(jRegistar)))
                        .addGap(51, 51, 51))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(87, 87, 87))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jUsername, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jErroUser)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jNome, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jErroNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jErroPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jErroCPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jRegistar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jRegistarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRegistarActionPerformed
    {//GEN-HEADEREND:event_jRegistarActionPerformed

    }//GEN-LAST:event_jRegistarActionPerformed

    private void jPasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jPasswordActionPerformed
    {//GEN-HEADEREND:event_jPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordActionPerformed

    private void jUsernameFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jUsernameFocusGained
    {//GEN-HEADEREND:event_jUsernameFocusGained
        if(Inicio)
        {
            jLabel1.requestFocus();
            Inicio=false;
            return;
        }
        if(UserFirst)
            jUsername.setText("");
        jUsername.setForeground(Color.BLACK);
        UserFirst=false;
        
    }//GEN-LAST:event_jUsernameFocusGained

    private void jNomeFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jNomeFocusGained
    {//GEN-HEADEREND:event_jNomeFocusGained
        
        if(EmailFirst)
            jNome.setText("");
        jNome.setForeground(Color.BLACK);
        EmailFirst=false;
    }//GEN-LAST:event_jNomeFocusGained

    private void jPasswordFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jPasswordFocusGained
    {//GEN-HEADEREND:event_jPasswordFocusGained
        if(PassFirst)
            jPassword.setText("");
        
        jPassword.setForeground(Color.BLACK);
        jPassword.setEchoChar('•');
        PassFirst=false;
    }//GEN-LAST:event_jPasswordFocusGained

    private void jRegistarMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jRegistarMouseClicked
    {//GEN-HEADEREND:event_jRegistarMouseClicked
        jErroUser.setVisible(false);
        jErroPass.setVisible(false);
        jErroNome.setVisible(false);
        jErroCPass.setVisible(false);
        if(jUsername.getText().equals("Nome de Utilizador") || jNome.getText().equals("Nome") || jPassword.getText().equals("Password"))
        {
            jErroCPass.setText("Preencha todos os campos");
            jErroCPass.setVisible(true);
            jErroCPass.setForeground(Color.RED);
            return;
        }
        if(!jPassword.getText().equals(jCPassword.getText()))
        {
            jErroCPass.setText("As Duas Palavras-Passe não correspondem");
            jErroCPass.setVisible(true);
            jErroCPass.setForeground(Color.RED);
            return;
        }
        
        switch(ObservableGame.Regista(jUsername.getText(), jNome.getText(), jPassword.getText()))
        {
            case 1: 
            {
                jErroCPass.setText("Registo Efetuado com Sucesso");
                jErroUser.setVisible(false);
                jErroPass.setVisible(false);
                jErroNome.setVisible(false);
                jErroCPass.setVisible(true);
                jErroCPass.setForeground(Color.GREEN);
                break;
            }
            case -1:
            {
                jErroUser.setText("Username já Existente");
                jErroUser.setVisible(true);
                break;
            }
            case -2:
            {
                jErroUser.setText("Utilizador no Máximo 15 Caracteres");
                jErroUser.setVisible(true);
                break;
            }
            case -3:
            {
                jErroNome.setText("Nome no Máximo 15 caracteres");
                jErroNome.setVisible(true);
                break;
            }
            case -4:
            {
                jErroPass.setText("Palavra-Passe no Máximo 30 caracteres");
                jErroPass.setVisible(true);
                break;
            }
            case -5:
            {
                jErroCPass.setText("Verifique a sua Conenção à Internet");
                jErroCPass.setVisible(true);
                jErroCPass.setForeground(Color.RED);
                break;
            }
                
        }
    }//GEN-LAST:event_jRegistarMouseClicked

    private void jCPasswordFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jCPasswordFocusGained
    {//GEN-HEADEREND:event_jCPasswordFocusGained
        if(CPassFirst)
            jCPassword.setText("");
        
        jCPassword.setForeground(Color.BLACK);
        jCPassword.setEchoChar('•');
        CPassFirst=false;
    }//GEN-LAST:event_jCPasswordFocusGained

    private void jCPasswordActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jCPasswordActionPerformed
    {//GEN-HEADEREND:event_jCPasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCPasswordActionPerformed

    private void jUsernameFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jUsernameFocusLost
    {//GEN-HEADEREND:event_jUsernameFocusLost
        if(UserFirst)
            return;
    }//GEN-LAST:event_jUsernameFocusLost

    private void jNomeFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jNomeFocusLost
    {//GEN-HEADEREND:event_jNomeFocusLost

    }//GEN-LAST:event_jNomeFocusLost

    private void jPasswordFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jPasswordFocusLost
    {//GEN-HEADEREND:event_jPasswordFocusLost
        
    }//GEN-LAST:event_jPasswordFocusLost

    private void jCPasswordFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_jCPasswordFocusLost
    {//GEN-HEADEREND:event_jCPasswordFocusLost
        
    }//GEN-LAST:event_jCPasswordFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField jCPassword;
    private javax.swing.JLabel jErroCPass;
    private javax.swing.JLabel jErroNome;
    private javax.swing.JLabel jErroPass;
    private javax.swing.JLabel jErroUser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jNome;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JButton jRegistar;
    private javax.swing.JTextField jUsername;
    // End of variables declaration//GEN-END:variables
}
