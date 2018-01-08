/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cliente.ui.gui.EcraInicial;

import java.util.Observable;
import java.util.Observer;
import Cliente.logic.ObservableGame;
import classescomunicacao.ArrayClienteEnviar;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.Mensagem;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Vector;
import javafx.scene.control.Cell;
import javax.swing.AbstractCellEditor;
import javax.swing.Action;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Tiago Coutinho
 */
public class EcraPrincipal extends javax.swing.JPanel implements Observer {

    private ObservableGame observableGame;
    private ButtonColumn botoesFormarPar, botoesEnviarSms;
    private DefaultTableModel modeloTabela;
    private ArrayList<Mensagem> mensagensClientes = new ArrayList<>();

    public EcraPrincipal(ObservableGame o) {
        observableGame = o;
        observableGame.addObserver(this);

        initComponents();
        modeloTabela = new DefaultTableModel();
        botoesFormarPar = new ButtonColumn(jTableUtilizadores, 3);
        botoesFormarPar.setText("Formar Par");
        botoesEnviarSms = new ButtonColumn(jTableUtilizadores, 4);
        botoesEnviarSms.setText("Enviar Mensagem");
        jTableUtilizadores.getColumn("Jogar").setCellRenderer(botoesFormarPar);
        jTableUtilizadores.getColumn("Jogar").setCellEditor(botoesFormarPar);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableUtilizadores = new javax.swing.JTable();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jButton1 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextPane2 = new javax.swing.JTextPane();
        jTextField2 = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();

        jTableUtilizadores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome de Utilizador", "Nome", "Estado", "Jogar", "Enviar Mensagem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableUtilizadores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableUtilizadores);

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jScrollPane2.setViewportView(jTextPane1);

        jButton1.setText("->");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jList1);

        jScrollPane4.setViewportView(jTextPane2);

        jButton2.setText("->");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 592, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(31, 31, 31))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3)
                        .addGap(57, 57, 57))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (jTextField1.getText().length() != 0) {
            observableGame.EnviaSMSTodos(jTextField1.getText());
     
            
            Mensagem temp = new Mensagem(jTextField1.getText(),null,"Eu");
            jTextField1.setText("");
            
            mensagensClientes.add(temp);
            
            jTextPane2.setText(jTextPane2.getText() + "\n" + temp.getRemetente() + ": " + temp.getMensagem());
            jTextPane2.setCaretPosition(jTextPane2.getText().length() - 1);       
   
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (jTextField2.getText().length() != 0) {
            observableGame.EnviaSMS(jTextField2.getText(), jList1.getSelectedValue());
            jTextField2.setText("");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked

    }//GEN-LAST:event_jButton1MouseClicked

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed

        if (evt.getKeyCode() == 10) {
            if (jTextField1.getText().length() != 0) {
                observableGame.EnviaSMSTodos(jTextField1.getText());
                jTextField1.setText("");
            }
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked

        jTextPane2.setText("");
        for (Mensagem s : mensagensClientes) {
            if (s.getRemetente().equals(jList1.getSelectedValue())) {
                jTextPane2.setText(jTextPane2.getText() + "\n" + s.getRemetente() + ": " + s.getMensagem());
                jTextPane2.setCaretPosition(jTextPane2.getText().length() - 1);
            }
        }

    }//GEN-LAST:event_jList1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTableUtilizadores;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextPane jTextPane2;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Observable o, Object arg) {
        String parFormado;
        modeloTabela = (DefaultTableModel) (jTableUtilizadores.getModel());
        modeloTabela.setRowCount(0);

        if (observableGame.getClientes() == null) {
            return;
        }

        for (ClienteEnviar cliente : observableGame.getClientes().getClientes()) {
            if (cliente.getNome().equals("Nome User 3")) {
                parFormado = "Tem par";
            } else {
                parFormado = "Não tem par";
            }
            modeloTabela.addRow(new Object[]{
                cliente.getNomeUtilizador(), cliente.getNome(), parFormado
            });
        }

        Mensagem sms = observableGame.GetSMS();
        if (sms.getDistinatario() == null) {
            jTextPane1.setText(jTextPane1.getText() + "\n" + sms.getRemetente() + ": " + sms.getMensagem());
            jTextPane1.setCaretPosition(jTextPane1.getText().length() - 1);
        } else {
            mensagensClientes.add(sms);
            if (sms.getRemetente().equals(jList1.getSelectedValue())) {
                jTextPane2.setText(jTextPane2.getText() + "\n" + sms.getRemetente() + ": " + sms.getMensagem());
                jTextPane2.setCaretPosition(jTextPane2.getText().length() - 1);
            } else {
                for (int i = 0; i < jList1.getModel().getSize(); i++) {
                    if (jList1.getModel().getElementAt(i).equals(sms.getRemetente())) {
                        return;
                    }
                }
                DefaultListModel<String> dlm = new DefaultListModel<String>();
                for (int i = 0; i < jList1.getModel().getSize(); i++) {
                    dlm.addElement(jList1.getModel().getElementAt(i));
                }
                dlm.addElement(sms.getRemetente());
                jList1.setModel(dlm);

            }
        }

    }

    public class ButtonColumn extends AbstractCellEditor
            implements TableCellRenderer, TableCellEditor, MouseListener {

        private JTable table;
        private Action action;
        private int mnemonic;
        private Border originalBorder;
        private Border focusBorder;

        private JButton renderButton;
        private JButton editButton;
        private Object editorValue;
        private boolean isButtonColumnEditor;

        public ButtonColumn(JTable table, int column) {
            this.table = table;
            renderButton = new JButton();
            editButton = new JButton();
            editButton.setFocusPainted(false);
            originalBorder = editButton.getBorder();
            setFocusBorder(new LineBorder(Color.BLUE));

            TableColumnModel columnModel = table.getColumnModel();
            columnModel.getColumn(column).setCellRenderer(this);
            columnModel.getColumn(column).setCellEditor(this);
            table.addMouseListener(this);
        }

        public void setEnable(boolean val) {
            renderButton.setEnabled(val);
        }

        public Border getFocusBorder() {
            return focusBorder;
        }

        public void setFocusBorder(Border focusBorder) {
            this.focusBorder = focusBorder;
            editButton.setBorder(focusBorder);
        }

        public int getMnemonic() {
            return mnemonic;
        }

        public void setMnemonic(int mnemonic) {
            this.mnemonic = mnemonic;
            renderButton.setMnemonic(mnemonic);
            editButton.setMnemonic(mnemonic);
        }

        @Override
        public Component getTableCellEditorComponent(
                JTable table, Object value, boolean isSelected, int row, int column) {
            return editButton;
        }

        @Override
        public Object getCellEditorValue() {
            return editorValue;
        }

        public void setText(String text) {
            renderButton.setText(text);
        }

        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            if (isSelected) {
                renderButton.setForeground(table.getSelectionForeground());
                renderButton.setBackground(table.getSelectionBackground());
            } else {
                renderButton.setForeground(table.getForeground());
                renderButton.setBackground(UIManager.getColor("Button.background"));
            }

            if (hasFocus) {
                renderButton.setBorder(focusBorder);
            } else {
                renderButton.setBorder(originalBorder);
            }

            return renderButton;
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (table.getSelectedColumn() == 4) {
                Object Nikname = table.getModel().getValueAt(table.getSelectedRow(), 0);
                for (int i = 0; i < jList1.getModel().getSize(); i++) {
                    if (jList1.getModel().getElementAt(i).trim().equals(Nikname.toString().trim())) {
                        return;
                    }
                }

                DefaultListModel<String> dlm = new DefaultListModel<String>();
                for (int i = 0; i < jList1.getModel().getSize(); i++) {
                    dlm.addElement(jList1.getModel().getElementAt(i));
                }
                dlm.addElement(Nikname.toString());
                jList1.setModel(dlm);
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {

        }

    }
}
