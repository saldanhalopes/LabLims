/*
 * Copyright (C) 2019 rafael.lopes
 *
 * Este programa é um software livre: você pode redistribuí-lo e / ou modificar
 * sob os termos da GNU General Public License, conforme publicado pela
 * a Free Software Foundation, seja a versão 3 da Licença, quanto
 * qualquer versão posterior.
 *
 * Este programa é distribuído na esperança de que seja útil,
 * mas SEM QUALQUER GARANTIA; sem a garantia implícita de
 * COMERCIALIZAÇÃO OU APTIDÃO PARA UM PROPÓSITO PARTICULAR. Veja o
 * GNU General Public License para obter mais detalhes.
 *
 * Você deve ter recebido uma cópia da GNU General Public License
 *  juntamente com este programa. Caso contrário, veja <http://www.gnu.org/licenses/>.
 */
package br.com.lablims.view.cadastro;

import br.com.lablims.dao.AnaliseDAO;
import br.com.lablims.model.Analise;
import br.com.lablims.util.AcessoSistema;
import br.com.lablims.util.Senha;
import br.com.lablims.util.DataHora;
import br.com.lablims.util.Frames;
import br.com.lablims.util.TableSorter;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rafael.lopes
 */
public class FrmAnalise extends javax.swing.JFrame {

    private String ACESSO = new AcessoSistema().getAcessoSistema(this);

    /**
     * Creates new form FrmAnalises
     */
    public FrmAnalise() {
        initComponents();
        Frames.setUpFrame(this, "Análises", false);
        readAnalise();
        btnNovo.setEnabled(AcessoSistema.criarDados(ACESSO));
        btnEditar.setEnabled(AcessoSistema.editarDados(ACESSO));
        btnExcluir.setEnabled(AcessoSistema.deletarDados(ACESSO));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlAnalises = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        btnNovo = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnExcluir = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnAtualizar = new javax.swing.JButton();
        pnlAnalise = new javax.swing.JPanel();
        txtPesquisar = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAnalise = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        pnlAuditoriaAnalise = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblAnaliseAuditoria = new javax.swing.JTable();
        txtPesquisarAuditoria = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_add.png"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setFocusable(false);
        btnNovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNovo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });
        jToolBar1.add(btnNovo);
        jToolBar1.add(jSeparator6);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_edit.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.setFocusable(false);
        btnEditar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEditar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnEditar);
        jToolBar1.add(jSeparator1);

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_close.png"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setFocusable(false);
        btnExcluir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnExcluir.setPreferredSize(new java.awt.Dimension(60, 60));
        btnExcluir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnExcluir);
        jToolBar1.add(jSeparator8);

        btnAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_atualizar.png"))); // NOI18N
        btnAtualizar.setText("Atualizar");
        btnAtualizar.setFocusable(false);
        btnAtualizar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAtualizar.setPreferredSize(new java.awt.Dimension(60, 60));
        btnAtualizar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAtualizar);

        txtPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar:");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Análises"));

        tblAnalise.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Analsie_Id", "Sigla", "Análise", "Descrição", "Versão"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAnalise.setSurrendersFocusOnKeystroke(true);
        tblAnalise.getTableHeader().setReorderingAllowed(false);
        tblAnalise.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblAnaliseMouseClicked(evt);
            }
        });
        tblAnalise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblAnaliseKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblAnalise);
        if (tblAnalise.getColumnModel().getColumnCount() > 0) {
            tblAnalise.getColumnModel().getColumn(0).setMinWidth(100);
            tblAnalise.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblAnalise.getColumnModel().getColumn(0).setMaxWidth(100);
            tblAnalise.getColumnModel().getColumn(1).setMinWidth(100);
            tblAnalise.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblAnalise.getColumnModel().getColumn(2).setMinWidth(300);
            tblAnalise.getColumnModel().getColumn(2).setPreferredWidth(300);
            tblAnalise.getColumnModel().getColumn(3).setMinWidth(400);
            tblAnalise.getColumnModel().getColumn(3).setPreferredWidth(400);
            tblAnalise.getColumnModel().getColumn(4).setMinWidth(80);
            tblAnalise.getColumnModel().getColumn(4).setPreferredWidth(80);
            tblAnalise.getColumnModel().getColumn(4).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 863, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlAnaliseLayout = new javax.swing.GroupLayout(pnlAnalise);
        pnlAnalise.setLayout(pnlAnaliseLayout);
        pnlAnaliseLayout.setHorizontalGroup(
            pnlAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnaliseLayout.createSequentialGroup()
                .addGroup(pnlAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAnaliseLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, 813, Short.MAX_VALUE))
                    .addGroup(pnlAnaliseLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlAnaliseLayout.setVerticalGroup(
            pnlAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAnalise, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlAnalise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlAnalises.addTab("Análises", jPanel1);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Auditoria de Analises"));

        tblAnaliseAuditoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Analise_Id", "Versão", "MOD", "Sigla", "Sigla_MOD", "Analise", "Analise_MOD", "Descrição", "Descrição_MOD", "Computador / AD_User ", "Usuario", "Data Modificação", "Motivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblAnaliseAuditoria.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(tblAnaliseAuditoria);
        if (tblAnaliseAuditoria.getColumnModel().getColumnCount() > 0) {
            tblAnaliseAuditoria.getColumnModel().getColumn(0).setMinWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(0).setMaxWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(1).setMinWidth(80);
            tblAnaliseAuditoria.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblAnaliseAuditoria.getColumnModel().getColumn(1).setMaxWidth(80);
            tblAnaliseAuditoria.getColumnModel().getColumn(2).setMinWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(2).setMaxWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(3).setMinWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(4).setMinWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(5).setMinWidth(300);
            tblAnaliseAuditoria.getColumnModel().getColumn(5).setPreferredWidth(300);
            tblAnaliseAuditoria.getColumnModel().getColumn(6).setMinWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(7).setMinWidth(400);
            tblAnaliseAuditoria.getColumnModel().getColumn(7).setPreferredWidth(400);
            tblAnaliseAuditoria.getColumnModel().getColumn(8).setMinWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(8).setPreferredWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(9).setMinWidth(150);
            tblAnaliseAuditoria.getColumnModel().getColumn(9).setPreferredWidth(150);
            tblAnaliseAuditoria.getColumnModel().getColumn(10).setMinWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(10).setPreferredWidth(100);
            tblAnaliseAuditoria.getColumnModel().getColumn(11).setMinWidth(120);
            tblAnaliseAuditoria.getColumnModel().getColumn(11).setPreferredWidth(120);
            tblAnaliseAuditoria.getColumnModel().getColumn(11).setMaxWidth(120);
            tblAnaliseAuditoria.getColumnModel().getColumn(12).setMinWidth(800);
            tblAnaliseAuditoria.getColumnModel().getColumn(12).setPreferredWidth(800);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );

        txtPesquisarAuditoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarAuditoriaKeyReleased(evt);
            }
        });

        jLabel5.setText("Pesquisar:");

        javax.swing.GroupLayout pnlAuditoriaAnaliseLayout = new javax.swing.GroupLayout(pnlAuditoriaAnalise);
        pnlAuditoriaAnalise.setLayout(pnlAuditoriaAnaliseLayout);
        pnlAuditoriaAnaliseLayout.setHorizontalGroup(
            pnlAuditoriaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAuditoriaAnaliseLayout.createSequentialGroup()
                .addGroup(pnlAuditoriaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAuditoriaAnaliseLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarAuditoria)))
                .addContainerGap())
        );
        pnlAuditoriaAnaliseLayout.setVerticalGroup(
            pnlAuditoriaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAuditoriaAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAuditoriaAnalise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAuditoriaAnalise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        pnlAnalises.addTab("Audit Trail", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAnalises, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlAnalises)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        abrir();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblAnalise.getSelectedRow() != -1) {
            atualizar();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tblAnalise.getSelectedRow() != -1) {
            deletar();
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        readAnalise();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void txtPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarKeyReleased
        TableSorter.TableSorter(tblAnalise, txtPesquisar);
    }//GEN-LAST:event_txtPesquisarKeyReleased

    private void txtPesquisarAuditoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarAuditoriaKeyReleased
        TableSorter.TableSorter(tblAnaliseAuditoria, txtPesquisarAuditoria);
    }//GEN-LAST:event_txtPesquisarAuditoriaKeyReleased

    private void tblAnaliseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblAnaliseMouseClicked
        if (evt.getClickCount() == 2) {
            if (tblAnalise.getSelectedRow() != -1) {
                atualizar();
            }
        }
    }//GEN-LAST:event_tblAnaliseMouseClicked

    private void tblAnaliseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblAnaliseKeyReleased
        if (tblAnalise.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletar();
            }
        }
    }//GEN-LAST:event_tblAnaliseKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmAnalise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAnalise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAnalise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAnalise.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmAnalise().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    public static javax.swing.JButton btnEditar;
    public static javax.swing.JButton btnExcluir;
    public static javax.swing.JButton btnNovo;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pnlAnalise;
    private javax.swing.JTabbedPane pnlAnalises;
    private javax.swing.JPanel pnlAuditoriaAnalise;
    private javax.swing.JTable tblAnalise;
    private javax.swing.JTable tblAnaliseAuditoria;
    private javax.swing.JTextField txtPesquisar;
    private javax.swing.JTextField txtPesquisarAuditoria;
    // End of variables declaration//GEN-END:variables

    public final void readAnalise() {
        DefaultTableModel model = (DefaultTableModel) tblAnalise.getModel();
        AnaliseDAO analiseDAO = new AnaliseDAO();
        model.setNumRows(0);
        try {
            for (Analise analise : analiseDAO.findEntities(Analise.class)) {
                model.addRow(new Object[]{
                    analise.getId(),
                    analise.getSiglaAnalise(),
                    analise.getAnalise(),
                    analise.getDescricaoAnalise(),
                    analise.getVersion()
                });
            }
            carregarAuditoria();
        } catch (Exception e) {
        }
    }

    private void abrir() {
        try {
            new FrmAnaliseDados(null, true).setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e);
        } finally {
            readAnalise();
        }
    }

    private void atualizar() {
        if (AcessoSistema.editarDados(ACESSO)) {
            AnaliseDAO analiseDAO = new AnaliseDAO();
            try {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Analise analise = analiseDAO.findById(Analise.class, (Long) tblAnalise.getValueAt(tblAnalise.getSelectedRow(), 0));
                new FrmAnaliseDados(null, true, analise).setVisible(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + e);
            } finally {
                readAnalise();
            }
        }
    }

    private void deletar() {
        if (AcessoSistema.deletarDados(ACESSO)) {
            AnaliseDAO analiseDAO = new AnaliseDAO();
            Senha senha = new Senha();
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null, "Realmente deseja Excluir esse registro?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    if (senha.Salvar()) {
                        Analise analise = analiseDAO.findById(Analise.class, (Long) tblAnalise.getValueAt(tblAnalise.getSelectedRow(), 0));
                        analiseDAO.remover(Analise.class, analise.getId());
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao excuir o dado: " + e.getMessage(), "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
            } finally {
                readAnalise();
            }
        }
    }

    private void carregarAuditoria() {
        DefaultTableModel model = (DefaultTableModel) tblAnaliseAuditoria.getModel();
        AnaliseDAO analiseDAO = new AnaliseDAO();
        model.setNumRows(0);
        try {
            for (Analise analise : analiseDAO.readAnaliseAuditoria()) {
                model.addRow(new Object[]{
                    analise.getId(),
                    analise.getVersion(),
                    analise.getAudit().getMOD() == 0 ? "Criado" : (analise.getAudit().getMOD() == 1 ? "Modificado" : "Removido"),
                    analise.getAnalise(),
                    analise.getAnalise_MOD() == false ? "Não Alterado" : "Alterado",
                    analise.getSiglaAnalise(),
                    analise.getSigla_Analise_MOD() == false ? "Não Alterado" : "Alterado",
                    analise.getDescricaoAnalise(),
                    analise.getDescricao_Analise_MOD() == false ? "Não Alterado" : "Alterado",
                    analise.getAudit().getComputador() + " / "
                    + analise.getAudit().getUserComputador(),
                    analise.getAudit().getUltimaModificacaoPor(),
                    DataHora.getStringDateTime(analise.getAudit().getUltimaModificacao()),
                    analise.getAudit().getMotivo()
                });
            }
        } catch (Exception e) {
        }
    }

}
