/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.ui.gui.EcraInicial;

import Cliente.logic.ObservableGame;
import static classescomunicacao.Constantes.JOGO_EM_ANDAMENTO;
import static classescomunicacao.Constantes.JOGO_FINALIZADO;
import static classescomunicacao.Constantes.JOGO_INTERROMPIDO;
import classescomunicacao.Jogo;
import java.util.Observable;

/**
 *
 * @author Tiago Coutinho
 */
public class Historico extends javax.swing.JPanel
{
    private ObservableGame observableGame;
    private String adversario,vencedor;
    private int estadoJogo;

    public Historico(ObservableGame observableGame, String jogador1, String jogador2, String vencedor, int estadoJogo)
    {
        this.observableGame=observableGame;
        
        if(jogador1.equals(observableGame.getUserName()))
            adversario=jogador2;
        else
            adversario=jogador1;
        
        this.vencedor=vencedor;
        this.estadoJogo=estadoJogo;
        
        initComponents();
        
        if(vencedor!=null)
            jAdversario.setText(vencedor);
        else
            jAdversario.setText("");
        
        if(estadoJogo==JOGO_EM_ANDAMENTO)
        {
            jEstado.setText("Em Andamento");
            jVencedor.setText("");
        }
        else if(estadoJogo==JOGO_FINALIZADO)
        {
            jEstado.setText("Terminado");
            jVencedor.setText(vencedor);
        }
        else if(estadoJogo==JOGO_INTERROMPIDO)
        {
            jEstado.setText("Interrompido");
            jVencedor.setText(vencedor);
        }
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
        jEstado = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jAdversario = new javax.swing.JLabel();
        Vencedor = new javax.swing.JLabel();
        jVencedor = new javax.swing.JLabel();

        setBackground(new java.awt.Color(153, 153, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 102));
        jLabel1.setText("Estado:");

        jEstado.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jEstado.setText("Concluída");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Adversário:");

        jAdversario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jAdversario.setText("user10");

        Vencedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        Vencedor.setForeground(new java.awt.Color(255, 204, 0));
        Vencedor.setText("Vencedor:");

        jVencedor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jVencedor.setText("user9");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jEstado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jAdversario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Vencedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jVencedor)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jEstado)
                    .addComponent(jLabel3)
                    .addComponent(jAdversario)
                    .addComponent(Vencedor)
                    .addComponent(jVencedor))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Vencedor;
    private javax.swing.JLabel jAdversario;
    private javax.swing.JLabel jEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jVencedor;
    // End of variables declaration//GEN-END:variables
}
