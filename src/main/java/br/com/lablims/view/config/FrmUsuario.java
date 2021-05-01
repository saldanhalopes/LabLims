package br.com.lablims.view.config;

import br.com.lablims.dao.LoginDAO;
import br.com.lablims.dao.UsuarioDAO;
import br.com.lablims.model.Usuario;
import br.com.lablims.util.AcessoSistema;
import br.com.lablims.util.DataHora;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import br.com.lablims.util.Senha;
import br.com.lablims.util.Frames;
import br.com.lablims.util.TableSorter;
import java.util.Date;

/**
 *
 * @author rafael
 */
public class FrmUsuario extends javax.swing.JFrame {

    private String ACESSO = new AcessoSistema().getAcessoSistema(this);

    /**
     * Creates new form FrmUsuario
     */
    public FrmUsuario() {
        initComponents();
        Frames.setUpFrame(this, "Usuários", false);
        readUsuarios();
        readRegistroEntrada();
        btnNovo.setEnabled(AcessoSistema.criarDados(ACESSO));
        btnEditar.setEnabled(AcessoSistema.editarDados(ACESSO));
        btnExcluir.setEnabled(AcessoSistema.deletarDados(ACESSO));
        btnImprimir.setEnabled(AcessoSistema.editarDados(ACESSO));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
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
        jPanel2 = new javax.swing.JPanel();
        pnlAuditoriaGrupos = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblUsuariosAuditoria = new javax.swing.JTable();
        txtPesquisarAuditoria = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        pnlAuditoriaGrupos1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblRegistroEntradaUsuarios = new javax.swing.JTable();
        txtPesquisarRegistroEntrada = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        cmbLimitPorPagina = new javax.swing.JComboBox<String>();
        jButton1 = new javax.swing.JButton();

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
                "Usuario_Id", "Usuário", "Turno", "Setor", "Nome Completo", "Grupo", "Falhas de Login", "Bloqueado", "Último Login", "Online", "Última Troca de Senha", "Versão"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuarios.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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
            tblUsuarios.getColumnModel().getColumn(4).setMinWidth(600);
            tblUsuarios.getColumnModel().getColumn(4).setPreferredWidth(600);
            tblUsuarios.getColumnModel().getColumn(4).setMaxWidth(800);
            tblUsuarios.getColumnModel().getColumn(5).setMinWidth(200);
            tblUsuarios.getColumnModel().getColumn(5).setPreferredWidth(200);
            tblUsuarios.getColumnModel().getColumn(5).setMaxWidth(200);
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
            tblUsuarios.getColumnModel().getColumn(10).setMinWidth(200);
            tblUsuarios.getColumnModel().getColumn(10).setPreferredWidth(200);
            tblUsuarios.getColumnModel().getColumn(10).setMaxWidth(200);
            tblUsuarios.getColumnModel().getColumn(11).setMinWidth(50);
            tblUsuarios.getColumnModel().getColumn(11).setPreferredWidth(50);
            tblUsuarios.getColumnModel().getColumn(11).setMaxWidth(50);
        }

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE)
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE)
                    .addComponent(pnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlUsuarios, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Usuários", jPanel1);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Auditoria de Usuários"));

        tblUsuariosAuditoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario_ID", "Versão", "MOD", "Usuario", "Usuario_MOD", "Pass_MOD", "Change_Pass_MOD", "Data Change_Pass", "Nome", "Nome_MOD", "Cracha", "Cracha_MOD", "Email", "Email_MOD", "Lock", "Lock_MOD", "Turno_Id", "Turno_Id_MOD", "Setor_Id", "Setor_Id_MOD", "Grupo_Id", "Grupo_Id_MOD", "Computador / AD_User ", "Usuario", "Data Modificação", "Motivo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblUsuariosAuditoria.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane3.setViewportView(tblUsuariosAuditoria);
        if (tblUsuariosAuditoria.getColumnModel().getColumnCount() > 0) {
            tblUsuariosAuditoria.getColumnModel().getColumn(0).setMinWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(0).setMaxWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(1).setMinWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(1).setPreferredWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(1).setHeaderValue("Versão");
            tblUsuariosAuditoria.getColumnModel().getColumn(2).setMinWidth(150);
            tblUsuariosAuditoria.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblUsuariosAuditoria.getColumnModel().getColumn(2).setHeaderValue("MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(3).setMinWidth(200);
            tblUsuariosAuditoria.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblUsuariosAuditoria.getColumnModel().getColumn(4).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(4).setHeaderValue("Usuario_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(5).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(5).setHeaderValue("Pass_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(6).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(6).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(6).setHeaderValue("Change_Pass_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(7).setMinWidth(200);
            tblUsuariosAuditoria.getColumnModel().getColumn(7).setPreferredWidth(200);
            tblUsuariosAuditoria.getColumnModel().getColumn(7).setMaxWidth(200);
            tblUsuariosAuditoria.getColumnModel().getColumn(7).setHeaderValue("Data Change_Pass");
            tblUsuariosAuditoria.getColumnModel().getColumn(8).setMinWidth(300);
            tblUsuariosAuditoria.getColumnModel().getColumn(8).setPreferredWidth(300);
            tblUsuariosAuditoria.getColumnModel().getColumn(9).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(9).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(9).setHeaderValue("Nome_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(10).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(10).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(10).setHeaderValue("Cracha");
            tblUsuariosAuditoria.getColumnModel().getColumn(11).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(11).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(11).setHeaderValue("Cracha_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(12).setMinWidth(400);
            tblUsuariosAuditoria.getColumnModel().getColumn(12).setPreferredWidth(400);
            tblUsuariosAuditoria.getColumnModel().getColumn(12).setHeaderValue("Email");
            tblUsuariosAuditoria.getColumnModel().getColumn(13).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(13).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(13).setHeaderValue("Email_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(14).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(14).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(14).setHeaderValue("Lock");
            tblUsuariosAuditoria.getColumnModel().getColumn(15).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(15).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(15).setHeaderValue("Lock_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(16).setMinWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(16).setPreferredWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(16).setHeaderValue("Turno_Id");
            tblUsuariosAuditoria.getColumnModel().getColumn(17).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(17).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(17).setHeaderValue("Turno_Id_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(18).setMinWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(18).setPreferredWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(18).setHeaderValue("Setor_Id");
            tblUsuariosAuditoria.getColumnModel().getColumn(19).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(19).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(19).setHeaderValue("Setor_Id_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(20).setMinWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(20).setPreferredWidth(80);
            tblUsuariosAuditoria.getColumnModel().getColumn(20).setHeaderValue("Grupo_Id");
            tblUsuariosAuditoria.getColumnModel().getColumn(21).setHeaderValue("Grupo_Id_MOD");
            tblUsuariosAuditoria.getColumnModel().getColumn(22).setMinWidth(200);
            tblUsuariosAuditoria.getColumnModel().getColumn(22).setPreferredWidth(200);
            tblUsuariosAuditoria.getColumnModel().getColumn(23).setMinWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(23).setPreferredWidth(120);
            tblUsuariosAuditoria.getColumnModel().getColumn(23).setHeaderValue("Usuario");
            tblUsuariosAuditoria.getColumnModel().getColumn(24).setMinWidth(180);
            tblUsuariosAuditoria.getColumnModel().getColumn(24).setPreferredWidth(180);
            tblUsuariosAuditoria.getColumnModel().getColumn(25).setMinWidth(500);
            tblUsuariosAuditoria.getColumnModel().getColumn(25).setPreferredWidth(500);
            tblUsuariosAuditoria.getColumnModel().getColumn(25).setHeaderValue("Motivo");
        }

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 386, Short.MAX_VALUE)
        );

        txtPesquisarAuditoria.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarAuditoriaKeyReleased(evt);
            }
        });

        jLabel6.setText("Pesquisar:");

        javax.swing.GroupLayout pnlAuditoriaGruposLayout = new javax.swing.GroupLayout(pnlAuditoriaGrupos);
        pnlAuditoriaGrupos.setLayout(pnlAuditoriaGruposLayout);
        pnlAuditoriaGruposLayout.setHorizontalGroup(
            pnlAuditoriaGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAuditoriaGruposLayout.createSequentialGroup()
                .addGroup(pnlAuditoriaGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAuditoriaGruposLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarAuditoria)))
                .addContainerGap())
        );
        pnlAuditoriaGruposLayout.setVerticalGroup(
            pnlAuditoriaGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaGruposLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAuditoriaGruposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesquisarAuditoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAuditoriaGrupos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAuditoriaGrupos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("Audit Trail", jPanel2);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Login / Logoff Usuários"));

        tblRegistroEntradaUsuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario_ID", "Usuario", "Nome", "Tipo", "Data / Hora", "Computador"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane4.setViewportView(tblRegistroEntradaUsuarios);
        if (tblRegistroEntradaUsuarios.getColumnModel().getColumnCount() > 0) {
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(0).setMinWidth(80);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(0).setPreferredWidth(80);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(0).setMaxWidth(80);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(1).setMinWidth(200);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(1).setPreferredWidth(200);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(2).setMinWidth(300);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(2).setPreferredWidth(300);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(3).setMinWidth(100);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(3).setMaxWidth(100);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(4).setMinWidth(180);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(4).setPreferredWidth(180);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(5).setMinWidth(200);
            tblRegistroEntradaUsuarios.getColumnModel().getColumn(5).setPreferredWidth(200);
        }

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 853, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 382, Short.MAX_VALUE))
        );

        txtPesquisarRegistroEntrada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPesquisarRegistroEntradaKeyReleased(evt);
            }
        });

        jLabel7.setText("Pesquisar:");

        jLabel11.setText("Visualizar:");

        cmbLimitPorPagina.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "100", "1000", "10000", "100000", "1000000", "10000000" }));
        cmbLimitPorPagina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLimitPorPaginaActionPerformed(evt);
            }
        });

        jButton1.setText("Atualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlAuditoriaGrupos1Layout = new javax.swing.GroupLayout(pnlAuditoriaGrupos1);
        pnlAuditoriaGrupos1.setLayout(pnlAuditoriaGrupos1Layout);
        pnlAuditoriaGrupos1Layout.setHorizontalGroup(
            pnlAuditoriaGrupos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlAuditoriaGrupos1Layout.createSequentialGroup()
                .addGroup(pnlAuditoriaGrupos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlAuditoriaGrupos1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisarRegistroEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmbLimitPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        pnlAuditoriaGrupos1Layout.setVerticalGroup(
            pnlAuditoriaGrupos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAuditoriaGrupos1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlAuditoriaGrupos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAuditoriaGrupos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cmbLimitPorPagina, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(jButton1))
                    .addGroup(pnlAuditoriaGrupos1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtPesquisarRegistroEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAuditoriaGrupos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAuditoriaGrupos1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );

        jTabbedPane1.addTab("Registro de Entrada", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
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
            editarUsuario();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        if (tblUsuarios.getSelectedRow() != -1) {
            if (tblUsuarios.getSelectedRow() != -1) {
                deletarUsuario();
                readUsuarios();
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
            user.setId((Long) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
            FrmUsuarioDados frm = new FrmUsuarioDados(null, true, user);
            frm.setVisible(true);
            readUsuarios();
            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        }
    }//GEN-LAST:event_tblUsuariosMouseClicked

    private void tblUsuariosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblUsuariosKeyPressed
        if (tblUsuarios.getSelectedRow() != -1) {
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                editarUsuario();
            }
            if (evt.getKeyCode() == KeyEvent.VK_DELETE) {
                deletarUsuario();
            }
        }
    }//GEN-LAST:event_tblUsuariosKeyPressed

    private void txtPesquisarUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarUsuarioKeyReleased
        TableSorter.TableSorter(tblUsuarios, txtPesquisarUsuario);
    }//GEN-LAST:event_txtPesquisarUsuarioKeyReleased

    private void txtPesquisarAuditoriaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarAuditoriaKeyReleased
        TableSorter.TableSorter(tblUsuariosAuditoria, txtPesquisarAuditoria);
    }//GEN-LAST:event_txtPesquisarAuditoriaKeyReleased

    private void txtPesquisarRegistroEntradaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisarRegistroEntradaKeyReleased
        TableSorter.TableSorter(tblRegistroEntradaUsuarios, txtPesquisarRegistroEntrada);
    }//GEN-LAST:event_txtPesquisarRegistroEntradaKeyReleased

    private void cmbLimitPorPaginaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLimitPorPaginaActionPerformed
        readRegistroEntrada();
    }//GEN-LAST:event_cmbLimitPorPaginaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        readRegistroEntrada();
    }//GEN-LAST:event_jButton1ActionPerformed

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
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FrmUsuario().setVisible(true);
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
    private javax.swing.JComboBox<String> cmbLimitPorPagina;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar.Separator jSeparator7;
    private javax.swing.JToolBar.Separator jSeparator8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pnlAuditoriaGrupos;
    private javax.swing.JPanel pnlAuditoriaGrupos1;
    private javax.swing.JPanel pnlUsuarios;
    private javax.swing.JTable tblRegistroEntradaUsuarios;
    private javax.swing.JTable tblUsuarios;
    private javax.swing.JTable tblUsuariosAuditoria;
    private javax.swing.JTextField txtPesquisarAuditoria;
    private javax.swing.JTextField txtPesquisarRegistroEntrada;
    private javax.swing.JTextField txtPesquisarUsuario;
    // End of variables declaration//GEN-END:variables

    public final void readUsuarios() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DefaultTableModel model = (DefaultTableModel) tblUsuarios.getModel();
        UsuarioDAO userDAO = new UsuarioDAO();
        model.setNumRows(0);
        try {
            for (Usuario user : userDAO.findEntities(Usuario.class)) {
                model.addRow(new Object[]{
                    user.getId(),
                    user.getUsuario(),
                    user.getTurno().getTurno(),
                    user.getSetor().getSiglaSetor(),
                    user.getNome(),
                    user.getGrupo().getGrupoNome(),
                    user.getFailedAccessCount(),
                    user.getLock(),
                    DataHora.getStringDateTime(user.getLastLogin()),
                    DataHora.getTimestampDate(user.getLastLogout()) == null ? false
                    : DataHora.getTimestampDate(user.getLastLogin()).after(
                    DataHora.getTimestampDate(user.getLastLogout())),
                    DataHora.getStringDateTime(user.getLastChangePass()),
                    user.getVersion()
                });
            }
            carregarAuditoria();
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    private void editarUsuario() {
        if (AcessoSistema.editarDados(ACESSO)) {
            try {
                setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                Usuario user = new Usuario();
                user.setId((Long) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
                FrmUsuarioDados frm = new FrmUsuarioDados(null, true, user);
                frm.setVisible(true);
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao editar dados: " + e);
            } finally {
                readUsuarios();
            }
        }
    }

    private void deletarUsuario() {
        if (AcessoSistema.deletarDados(ACESSO)) {
            try {
                int dialogResult = JOptionPane.showConfirmDialog(null,
                        "Realmente Deseja Excluir Esse Usuário?", "Aviso", JOptionPane.YES_NO_OPTION);
                if (dialogResult == 0) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    UsuarioDAO userDAO = new UsuarioDAO();
                    Senha senha = new Senha();
                    try {
                        if (senha.Salvar()) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                            userDAO.remover(Usuario.class, (Long) tblUsuarios.getValueAt(tblUsuarios.getSelectedRow(), 0));
                            setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Erro ao excuir o dado: " + e.getMessage(), "Erro ao Excluir", JOptionPane.ERROR_MESSAGE);
                    }
                    readUsuarios();
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro ao deletar dados: " + e);
            } finally {
                readUsuarios();
            }
        }
    }

    private void carregarAuditoria() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DefaultTableModel model = (DefaultTableModel) tblUsuariosAuditoria.getModel();
        UsuarioDAO userDAO = new UsuarioDAO();
        model.setNumRows(0);
        try {
            for (Usuario user : userDAO.readUsuarioAuditoria()) {
                model.addRow(new Object[]{
                    user.getId(),
                    user.getVersion(),
                    user.getAudit().getMOD() == 0 ? "Criado"
                    : (user.getAudit().getMOD() == 1 ? "Modificado" : "Removido"),
                    user.getUsuario(),
                    user.getUsuario_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getPass_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getChange_Pass_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getNome(),
                    user.getNome_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getCracha(),
                    user.getCracha_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getEmail(),
                    user.getEmail_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getLock(),
                    user.getLock_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getTurno().getId(),
                    user.getTurno_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getSetor().getId(),
                    user.getSetor_MOD() == false ? "Não Alterado" : "Alterado",
                    user.getGrupo().getId(),
                    user.getGrupo_MOD() == false ? "Não Alterado" : "Alterado",
                    DataHora.getStringDateTime(user.getLastChangePass()),
                    DataHora.getStringDateTime(user.getCreated()),
                    user.getAudit().getComputador() + " / "
                    + user.getAudit().getUserComputador(),
                    user.getAudit().getUltimaModificacaoPor(),
                    DataHora.getStringDateTime(user.getAudit().getUltimaModificacao()),
                    user.getAudit().getMotivo()
                });
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    public final void readRegistroEntrada() {
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        DefaultTableModel model = (DefaultTableModel) tblRegistroEntradaUsuarios.getModel();
        LoginDAO loginDAO = new LoginDAO();
        model.setNumRows(0);
        Integer max = Integer.parseInt(cmbLimitPorPagina.getSelectedItem().toString());
        try {
            for (Object[] objects : loginDAO.findListRegistroEntrada(max)) {
                model.addRow(new Object[]{
                    (Long) objects[0],
                    (String) objects[1],
                    (String) objects[2],
                    (Date) objects[4] == null
                    ? "Logoff"
                    : "Login",
                    (Date) objects[4] == null
                    ? DataHora.getStringDateTime((Date) objects[5])
                    : DataHora.getStringDateTime((Date) objects[4]),
                    (String) objects[3]
                });
            }
        } catch (Exception e) {
            System.out.println("Erro " + e);
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

}
