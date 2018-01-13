/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Logic.ObservableScreen;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.FormarPar;
import classescomunicacao.InfoJogo;
import classescomunicacao.Constantes;
import java.util.Observer;
import javafx.beans.Observable;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author andre
 */
public class EcraPrincipal extends javax.swing.JPanel implements Observer {

    ObservableScreen observablesreen;
    JPanel CardPanel;

    /**
     * Creates new form EcraPrincipal
     *
     * @param observablesreen
     */
    public EcraPrincipal(ObservableScreen observablesreen) {
        this.observablesreen = observablesreen;
        this.observablesreen.addObserver(this);

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "NikName", "Nome", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.setName(""); // NOI18N
        jScrollPane2.setViewportView(jTable2);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 450, 110));

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Utilizador 1", "Utilizador 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(jTable3);

        add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 230, 330, 100));

        jLabel1.setText("Pares Formados:");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 210, -1, -1));

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Utilizador 1", "Utilizador 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(jTable4);

        add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 80, 330, 100));

        jLabel2.setText("Pedidos de Par:");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, -1, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Jogador1", "Jogador2", "Vencedor", "Estado", "Termino"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 450, 130));

        jLabel3.setText("Partidas:");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, -1));

        jLabel4.setText("Jogadores:");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    void setCardPanel(JPanel PainelPrincipal) {
        CardPanel = PainelPrincipal;
    }

    public JPanel getCardPanel() {
        return CardPanel;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(java.util.Observable o, Object o1) {

        InfoJogo jogo = observablesreen.GetInfoJogo();

        DefaultTableModel dm;

        dm = (DefaultTableModel) jTable1.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
        dm = (DefaultTableModel) jTable2.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
        dm = (DefaultTableModel) jTable3.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }
        dm = (DefaultTableModel) jTable4.getModel();
        while (dm.getRowCount() > 0) {
            dm.removeRow(0);
        }

        for (FormarPar s : jogo.getPares()) {
            if (s.getAceite() == classescomunicacao.Constantes.PEDIDO_FEITO) {
                DefaultTableModel model = (DefaultTableModel) jTable4.getModel();
                Object[] row = {s.getUitlizadorQueFezPedido(), s.getUtilizadorQueResponde()};
                model.addRow(row);
            } else {
                if (s.getAceite() == classescomunicacao.Constantes.PEDIDO_ACEITE) {
                    DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
                    Object[] row = {s.getUitlizadorQueFezPedido(), s.getUtilizadorQueResponde()};
                    model.addRow(row);
                }
            }
        }

        for (ClienteEnviar s : jogo.getClientes()) {
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            Object[] row = {s.getNome(), s.getNomeUtilizador(),s.isParFormado()};
            model.addRow(row);
        }

    }

}
