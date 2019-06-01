package br.com.lablims.view.cadastro;

import javax.persistence.EntityManager;
import javax.swing.JOptionPane;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.dao.AnaliseDAO;
import br.com.lablims.model.Analise;
import br.com.lablims.model.Senha;
import br.com.lablims.view.config.FrmSalvar;
import java.awt.Cursor;

/**
 *
 * @author rafael.lopes
 */
public class FrmAnaliseDados extends javax.swing.JDialog {

    private Analise analise;
    private boolean novoAnalise = false;

    /**
     * Creates new form FrmConfigAcesso
     *
     * @param parent
     * @param modal
     */
    public FrmAnaliseDados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Dados da Analise");
        setResizable(false);
        limparCampos();
        novoAnalise = true;
    }

    public FrmAnaliseDados(java.awt.Frame parent, boolean modal, Analise anls) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Dados da Analise");
        setResizable(false);
        analise = anls;
        limparCampos();
        carregarDados();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvarMetodo = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        pnlEditarAnalise = new javax.swing.JPanel();
        txtSiglaAnalise = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtAnalise = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDescricaoAnalise = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSalvarMetodo.setText("Ok");
        btnSalvarMetodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarMetodoActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        pnlEditarAnalise.setBorder(javax.swing.BorderFactory.createTitledBorder("Análise"));

        jLabel1.setText("Sigla:");

        jLabel2.setText("Análise:");

        jLabel5.setText("Descrição:");

        javax.swing.GroupLayout pnlEditarAnaliseLayout = new javax.swing.GroupLayout(pnlEditarAnalise);
        pnlEditarAnalise.setLayout(pnlEditarAnaliseLayout);
        pnlEditarAnaliseLayout.setHorizontalGroup(
            pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEditarAnaliseLayout.createSequentialGroup()
                        .addComponent(txtSiglaAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnalise, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE))
                    .addComponent(txtDescricaoAnalise))
                .addContainerGap())
        );
        pnlEditarAnaliseLayout.setVerticalGroup(
            pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEditarAnaliseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtSiglaAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEditarAnaliseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDescricaoAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSalvarMetodo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlEditarAnalise, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlEditarAnalise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalvarMetodo)
                    .addComponent(btnCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarMetodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarMetodoActionPerformed
        EntityManager em = ConnectionFactory.em(true);
        Senha senha = new Senha();
        try {
            if (txtSiglaAnalise.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Sigla inválida");
                txtSiglaAnalise.requestFocus();
            } else if (txtAnalise.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Análise inválido");
                txtAnalise.requestFocus();
            } else if (txtDescricaoAnalise.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Descrição inválida");
                txtDescricaoAnalise.requestFocus();
            } else if (novoAnalise) {
                FrmSalvar frmSalvar = new FrmSalvar(null, true, em, senha);
                frmSalvar.setVisible(true);
                if (senha.getSenha()) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    criarAnalise(em);
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    dispose();
                }
            } else {
                FrmSalvar frmSalvar = new FrmSalvar(null, true, em, senha);
                frmSalvar.setVisible(true);
                if (senha.getSenha()) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    atulizarAnalise(em);
                    setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                    dispose();
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        } finally {
            em.close();
        }
    }//GEN-LAST:event_btnSalvarMetodoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int option = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza de que deseja fechar esse registro?\nOs dados inseridos não serão salvos!",
                "Confirmação de Cancelamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            dispose();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAnaliseDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                FrmAnaliseDados dialog = new FrmAnaliseDados(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    public static javax.swing.JButton btnSalvarMetodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel pnlEditarAnalise;
    private javax.swing.JTextField txtAnalise;
    private javax.swing.JTextField txtDescricaoAnalise;
    private javax.swing.JTextField txtSiglaAnalise;
    // End of variables declaration//GEN-END:variables

    private void criarAnalise(EntityManager em) {
        Analise anls = new Analise();
        AnaliseDAO analiseDAO = new AnaliseDAO();
        try {
            anls.setSiglaAnalise(txtSiglaAnalise.getText().trim().toUpperCase());
            anls.setAnalise(txtAnalise.getText().trim().toUpperCase());
            anls.setDescricaoAnalise(txtDescricaoAnalise.getText().trim().toUpperCase());
            anls.setVersion(0);
            analiseDAO.salvar(em, anls);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao gravar dados: " + e);
        }
    }

    private void atulizarAnalise(EntityManager em) {
        Analise anls = new Analise();
        AnaliseDAO analiseDAO = new AnaliseDAO();
        try {
            anls.setId(analise.getId());
            anls.setSiglaAnalise(txtSiglaAnalise.getText().trim().toUpperCase());
            anls.setAnalise(txtAnalise.getText().trim().toUpperCase());
            anls.setDescricaoAnalise(txtDescricaoAnalise.getText().trim().toUpperCase());
            anls.setVersion(analise.getVersion() + 1);
            analiseDAO.salvar(em, anls);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar dados: " + e);
        }
    }

    private void carregarDados() {
        try {
            txtSiglaAnalise.setText(analise.getSiglaAnalise().toUpperCase());
            txtAnalise.setText(analise.getAnalise().toUpperCase());
            txtDescricaoAnalise.setText(analise.getDescricaoAnalise().toUpperCase());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar dados: " + ex);
        }
    }

    private void limparCampos() {
        txtSiglaAnalise.setText(null);
        txtAnalise.setText(null);
        txtDescricaoAnalise.setText(null);
    }

}
