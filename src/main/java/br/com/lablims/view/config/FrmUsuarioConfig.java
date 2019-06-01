package br.com.lablims.view.config;

import br.com.lablims.dao.UsuarioDAO;
import br.com.lablims.model.Usuario;
import br.com.lablims.util.DataHora;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Senha;
import javax.swing.ImageIcon;

/**
 *
 * @author rafael
 */
public class FrmUsuarioConfig extends javax.swing.JFrame {

    /**
     * Creates new form FrmT
     */
    public FrmUsuarioConfig() {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_sgcq_48.png"));
        setIconImage(icone.getImage());
        setTitle("Usuários");
        readUsuarios();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnNovo = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnEditar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnExcluir = new javax.swing.JButton();
        jSeparator8 = new javax.swing.JToolBar.Separator();
        btnAtualizar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnImprimir = new javax.swing.JButton();
        jSeparator7 = new javax.swing.JToolBar.Separator();
        btnOnline = new javax.swing.JToggleButton();
        pnlUsuarios = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUsuarios = new javax.swing.JTable();
        txtPesquisarUsuario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();

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
        jToolBar1.add(jSeparator5);

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_print.png"))); // NOI18N
        btnImprimir.setText("Imprimir");
        btnImprimir.setFocusable(false);
        btnImprimir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        jToolBar1.add(btnImprimir);
        jToolBar1.add(jSeparator7);

        btnOnline.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_globo.png"))); // NOI18N
        btnOnline.setText("Online");
        btnOnline.setFocusable(false);
        btnOnline.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnOnline.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnOnline.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOnlineActionPerformed(evt);
            }
        });
        jToolBar1.add(btnOnline);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Usuários"));

        tblUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario_Id", "Usuário", "Turno", "Setor", "Nome Completo", "Grupo", "Falhas de Login", "Bloqueado", "Último Login", "Online"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setSurrendersFocusOnKeystroke(true);
        tblUsuarios.getTableHeader().setReorderingAllowed(false);
        tblUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuariosMouseClicked(evt);
            }
        });
        tblUsuarios.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblUsuariosKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuarios);
        if (tblUsuarios.getColumnModel().getColumnCount() > 0) {
            tblUsuarios.getColumnModel().getColumn(0).setMinWidth(80);
            tblUsuarios.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblUsuarios.getColumnModel().getColumn(0).setMaxWidth(80);
            tblUsuarios.getColumnModel().getColumn(1).setMinWidth(150);
            tblUsuarios.getColumnModel().getColumn(1).setPreferredWidth(150);
            tblUsuarios.getColumnModel().getColumn(1).setMaxWidth(150);
            tblUsuarios.getColumnModel().getColumn(2).setMinWidth(120);
            tblUsuarios.getColumnModel().getColumn(2).setPreferredWidth(120);
            tblUsuarios.getColumnModel().getColumn(2).setMaxWidth(120);
            tblUsuarios.getColumnModel().getColumn(3).setMinWidth(80);
            tblUsuarios.getColumnModel().getColumn(3).setPreferredWidth(80);
            tblUsuarios.getColumnModel().getColumn(3).setMaxWidth(80);
            tblUsuarios.getColumnModel().getColumn(5).setMinWidth(120);
            tblUsuarios.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblUsuarios.getColumnModel().getColumn(5).setMaxWidth(120);
            tblUsuarios.getColumnModel().getColumn(6).setMinWidth(100);
            tblUsuarios.getColumnModel().getColumn(6).setPreferredWidth(100);
            tblUsuarios.getColumnModel().getColumn(6).setMaxWidth(100);
            tblUsuarios.getColumnModel().getColumn(7).setMinWidth(80);
            tblUsuarios.getColumnModel().getColumn(7).setPreferredWidth(80);
            tblUsuarios.getColumnModel().getColumn(7).setMaxWidth(80);
            tblUsuarios.getColumnModel().getColumn(8).setMinWidth(150);
            tblUsuarios.getColumnModel().getColumn(8).setPreferredWidth(150);
            tblUsuarios.getColumnModel().getColumn(8).setMaxWidth(150);
            tblUsuarios.getColumnModel().getColumn(9).setMinWidth(80);
            tblUsuarios.getColumnModel().getColumn(9).setPreferredWidth(80);
            tblUsuarios.getColumnModel().getColumn(9).setMaxWidth(80);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        txtPesquisarUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarUsuarioKeyReleased(evt);
            }
        });

        jLabel4.setText("Pesquisar:");

        javax.swing.GroupLayout pnlUsuariosLayout = new javax.swing.GroupLayout(pnlUsuarios);
        pnlUsuarios.setLayout(pnlUsuariosLayout);
        pnlUsuariosLayout.setHorizontalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlUsuariosLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarUsuario))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlUsuariosLayout.setVerticalGroup(
            pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsuariosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlUsuariosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 1000, Short.MAX_VALUE)
            .addComponent(pnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        FrmUsuarioDados frm = new FrmUsuarioDados(null, true);
        frm.setVisible(true);
        readUsuarios();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        if (tblUsuarios.getSelectedRow() != -1) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Usuario user = new Usuario();
            user.setId((Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
            FrmUsuarioDados frm = new FrmUsuarioDados(null, true, user);
            frm.setVisible(true);
            readUsuarios();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tblUsuarios.getSelectedRow() != -1) {
            if (tblUsuarios.getSelectedRow() != -1) {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Realmente Deseja Excluir Esse Usuário?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    deletarUsuario();
                    readUsuarios();
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        readUsuarios();
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        Thread t1 = new Thread() {
            @Override
            public void run() {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        };
        t1.start();
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnOnlineActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOnlineActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        TableRowSorter sorter = new TableRowSorter<>(model);
        tblUsuarios.setRowSorter(sorter);
        if (btnOnline.isSelected()) {
            sorter.setRowFilter(RowFilter.regexFilter("true", 9));
        } else {
            sorter.setRowFilter(null);
        }
    }//GEN-LAST:event_btnOnlineActionPerformed

    private void tblUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuariosMouseClicked
        if (evt.getClickCount() == 2) {
            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
            Usuario user = new Usuario();
            user.setId((Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
            FrmUsuarioDados frm = new FrmUsuarioDados(null, true, user);
            frm.setVisible(true);
            readUsuarios();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void tblUsuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblUsuariosKeyPressed
        if (tblUsuarios.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Usuario user = new Usuario();
                user.setId((Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
                FrmUsuarioDados frm = new FrmUsuarioDados(null, true, user);
                frm.setVisible(true);
                readUsuarios();
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Realmente Deseja Excluir Esse Usuário?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    deletarUsuario();
                    readUsuarios();
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            }
        }
    }//GEN-LAST:event_tblUsuariosKeyPressed

    private void txtPesquisarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarUsuarioKeyReleased
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        TableRowSorter sorter = new TableRowSorter<>(model);
        tblUsuarios.setRowSorter(sorter);
        String text = txtPesquisarUsuario.getText().trim();
        String parametro = "*";
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else if (text.indexOf(parametro) > 0) {
            ArrayList<RowFilter<Object, Object>> andFilters = new ArrayList<>();
            andFilters.add(RowFilter.regexFilter("(?i)" + text.substring(0, text.indexOf(parametro)).trim().toUpperCase()));
            andFilters.add(RowFilter.regexFilter("(?i)" + text.substring(text.indexOf(parametro) + 1, text.length()).trim().toUpperCase()));
            sorter.setRowFilter(RowFilter.andFilter(andFilters));
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }//GEN-LAST:event_txtPesquisarUsuarioKeyReleased

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmSalvar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               new FrmUsuarioConfig().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    public static javax.swing.JButton btnEditar;
    public static javax.swing.JButton btnExcluir;
    public static javax.swing.JButton btnImprimir;
    public static javax.swing.JButton btnNovo;
    private javax.swing.JToggleButton btnOnline;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTextField txtPesquisarUsuario;
    // End of variables declaration//GEN-END:variables

    public final void readUsuarios() {
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        UsuarioDAO userDAO = new UsuarioDAO();
        model.setNumRows(0);
        EntityManager em = ConnectionFactory.em(true);
        try {
            for (Usuario user : userDAO.findEntities(em, Usuario.class)) {
                model.addRow(new Object[]{
                    user.getId(),
                    user.getUsuario(),
                    user.getTurno(),
                    user.getSetor().getSiglaSetor(),
                    user.getNome(),
                    user.getGrupo().getGrupoNome(),
                    user.getFailed_access_count(),
                    user.getLock(),
                    DataHora.getStringDateTime(user.getLastlogin()),
                    DataHora.getTimestampDate(user.getLastlogout()) == null ? false
                    : DataHora.getTimestampDate(user.getLastlogin()).after(
                    DataHora.getTimestampDate(user.getLastlogout()))
                });
            }
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    private void deletarUsuario() {
//        ConfigUsuarioAcesso Acesso = new ConfigUsuarioAcesso();
//        if (Acesso.verificarDeletarDadosUsuario()) {
        Usuario u = new Usuario();
        UsuarioDAO userDAO = new UsuarioDAO();
        EntityManager em = ConnectionFactory.em(true);
        Senha senha = new Senha();
        try {
            FrmSalvar frmSalvar = new FrmSalvar(null, true, em, senha);
            frmSalvar.setVisible(true);
            if (senha.getSenha()) {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                userDAO.remover(em, Usuario.class, (Integer) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atulalizar dados: " + e);
        } finally {
            em.close();
        }
//        }
    }

}
