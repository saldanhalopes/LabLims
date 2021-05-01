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
package br.com.lablims.view.principal;

import br.com.lablims.view.coluna.FrmColunaVaga;
import br.com.lablims.view.coluna.FrmColunasCromatograficas;
import br.com.lablims.view.coluna.FrmColunaStorage;
import br.com.lablims.util.AcessoSistema;
import br.com.lablims.util.Frames;
import br.com.lablims.util.MouseEventButton;
import br.com.lablims.view.cadastro.FrmColuna;
import br.com.lablims.view.cromatografia.FrmLogCromatografos;
import br.com.lablims.view.registrosolucao.FrmRegistroSolucaoReagente;
import br.com.lablims.view.trocaturno.FrmAtaTrocaTurno;
import java.awt.Cursor;

/**
 *
 * @author rafael.lopes
 */
public class PnlLaboratorio extends javax.swing.JPanel {

    /**
     * Creates new form PnlConfigurar
     */
    public PnlLaboratorio() {
        initComponents();
        pnlColunas.setVisible(AcessoSistema.acessoDados(new AcessoSistema().getAcessoSistema(FrmColunasCromatograficas.class.getSimpleName())));
        pnlColunaStorage.setVisible(AcessoSistema.acessoDados(new AcessoSistema().getAcessoSistema(FrmColunaStorage.class.getSimpleName())));
        pnlColunaVaga.setVisible(AcessoSistema.acessoDados(new AcessoSistema().getAcessoSistema(FrmColunaVaga.class.getSimpleName())));
        pnlColunas.setVisible(AcessoSistema.acessoDados(new AcessoSistema().getAcessoSistema(FrmColuna.class.getSimpleName())));
        pnlRegistroSolucoes.setVisible(AcessoSistema.acessoDados(new AcessoSistema().getAcessoSistema(FrmRegistroSolucaoReagente.class.getSimpleName())));
        pnlLogCromatografo.setVisible(AcessoSistema.acessoDados(new AcessoSistema().getAcessoSistema(FrmLogCromatografos.class.getSimpleName())));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlColunas = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblLaboratorio = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        pnlColunaStorage = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        pnlColunaVaga = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        lblLaboratorio1 = new javax.swing.JLabel();
        pnlLogCromatografo = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        pnlRegistroSolucoes = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        pnlTrocaTurno = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        lblLaboratorio2 = new javax.swing.JLabel();

        pnlColunas.setBackground(new java.awt.Color(255, 255, 255));
        pnlColunas.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunas.setMinimumSize(new java.awt.Dimension(180, 50));
        pnlColunas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlColunasMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlColunasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlColunasMouseExited(evt);
            }
        });
        pnlColunas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_coluna.png"))); // NOI18N
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunas.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 51, 51));
        jLabel8.setText("Colunas");
        jLabel8.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunas.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 80, 20));

        lblLaboratorio.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLaboratorio.setText("Colunas Cromatográficas");

        pnlColunaStorage.setBackground(new java.awt.Color(255, 255, 255));
        pnlColunaStorage.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunaStorage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlColunaStorageMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlColunaStorageMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlColunaStorageMouseExited(evt);
            }
        });
        pnlColunaStorage.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_arquivo.png"))); // NOI18N
        jLabel22.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunaStorage.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jLabel26.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(51, 51, 51));
        jLabel26.setText("Storage");
        jLabel26.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunaStorage.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 70, -1));

        pnlColunaVaga.setBackground(new java.awt.Color(255, 255, 255));
        pnlColunaVaga.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunaVaga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlColunaVagaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlColunaVagaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlColunaVagaMouseExited(evt);
            }
        });
        pnlColunaVaga.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel24.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_location_update_32.png"))); // NOI18N
        jLabel24.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunaVaga.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jLabel27.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(51, 51, 51));
        jLabel27.setText("Vagas");
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlColunaVaga.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 70, -1));

        lblLaboratorio1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLaboratorio1.setText("Cromatógrafos");

        pnlLogCromatografo.setBackground(new java.awt.Color(255, 255, 255));
        pnlLogCromatografo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlLogCromatografo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlLogCromatografoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlLogCromatografoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlLogCromatografoMouseExited(evt);
            }
        });
        pnlLogCromatografo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_computer.png"))); // NOI18N
        jLabel28.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlLogCromatografo.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jLabel29.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 51));
        jLabel29.setText("Log");
        jLabel29.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlLogCromatografo.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 6, 120, -1));

        jLabel30.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setText("Cromatografo");
        jLabel30.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlLogCromatografo.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 120, -1));

        pnlRegistroSolucoes.setBackground(new java.awt.Color(255, 255, 255));
        pnlRegistroSolucoes.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlRegistroSolucoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlRegistroSolucoesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlRegistroSolucoesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlRegistroSolucoesMouseExited(evt);
            }
        });
        pnlRegistroSolucoes.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_tubo_ensaio_32.png"))); // NOI18N
        jLabel34.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlRegistroSolucoes.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jLabel35.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setText("Soluções");
        jLabel35.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlRegistroSolucoes.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 6, 80, 30));

        pnlTrocaTurno.setBackground(new java.awt.Color(255, 255, 255));
        pnlTrocaTurno.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        pnlTrocaTurno.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pnlTrocaTurnoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlTrocaTurnoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlTrocaTurnoMouseExited(evt);
            }
        });
        pnlTrocaTurno.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_troca_turno_32.png"))); // NOI18N
        jLabel17.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlTrocaTurno.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 40));

        jLabel18.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(51, 51, 51));
        jLabel18.setText("MP - Geral");
        jLabel18.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlTrocaTurno.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 110, -1));

        jLabel19.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(51, 51, 51));
        jLabel19.setText("Troca Turno");
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        pnlTrocaTurno.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 110, -1));

        lblLaboratorio2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblLaboratorio2.setText("Registro de Soluções");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLaboratorio1)
                            .addComponent(pnlLogCromatografo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblLaboratorio2))
                        .addContainerGap(859, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblLaboratorio)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlColunaStorage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlColunaVaga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlColunas, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnlRegistroSolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlTrocaTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblLaboratorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlColunaStorage, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColunaVaga, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlColunas, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblLaboratorio1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlLogCromatografo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblLaboratorio2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlRegistroSolucoes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTrocaTurno, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(236, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void pnlColunasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunasMouseClicked
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Frames.carregarJFrame(FrmColunasCromatograficas.class);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_pnlColunasMouseClicked

    private void pnlColunasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunasMouseEntered
        MouseEventButton.setMouseEntered(pnlColunas);
    }//GEN-LAST:event_pnlColunasMouseEntered

    private void pnlColunasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunasMouseExited
        MouseEventButton.setMouseExited(pnlColunas);
    }//GEN-LAST:event_pnlColunasMouseExited

    private void pnlColunaStorageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunaStorageMouseClicked
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Frames.carregarJFrame(FrmColunaStorage.class);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_pnlColunaStorageMouseClicked

    private void pnlColunaStorageMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunaStorageMouseEntered
        MouseEventButton.setMouseEntered(pnlColunaStorage);
    }//GEN-LAST:event_pnlColunaStorageMouseEntered

    private void pnlColunaStorageMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunaStorageMouseExited
        MouseEventButton.setMouseExited(pnlColunaStorage);
    }//GEN-LAST:event_pnlColunaStorageMouseExited

    private void pnlColunaVagaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunaVagaMouseClicked
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Frames.carregarJFrame(FrmColunaVaga.class);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_pnlColunaVagaMouseClicked

    private void pnlColunaVagaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunaVagaMouseEntered
        MouseEventButton.setMouseEntered(pnlColunaVaga);
    }//GEN-LAST:event_pnlColunaVagaMouseEntered

    private void pnlColunaVagaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlColunaVagaMouseExited
        MouseEventButton.setMouseExited(pnlColunaVaga);
    }//GEN-LAST:event_pnlColunaVagaMouseExited

    private void pnlLogCromatografoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLogCromatografoMouseClicked
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Frames.carregarJFrame(FrmLogCromatografos.class);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_pnlLogCromatografoMouseClicked

    private void pnlLogCromatografoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLogCromatografoMouseEntered
       MouseEventButton.setMouseEntered(pnlLogCromatografo);
    }//GEN-LAST:event_pnlLogCromatografoMouseEntered

    private void pnlLogCromatografoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlLogCromatografoMouseExited
         MouseEventButton.setMouseExited(pnlLogCromatografo);
    }//GEN-LAST:event_pnlLogCromatografoMouseExited

    private void pnlRegistroSolucoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRegistroSolucoesMouseClicked
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Frames.carregarJFrame(FrmRegistroSolucaoReagente.class);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_pnlRegistroSolucoesMouseClicked

    private void pnlRegistroSolucoesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRegistroSolucoesMouseEntered
        MouseEventButton.setMouseEntered(pnlRegistroSolucoes);
    }//GEN-LAST:event_pnlRegistroSolucoesMouseEntered

    private void pnlRegistroSolucoesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlRegistroSolucoesMouseExited
        MouseEventButton.setMouseExited(pnlRegistroSolucoes);
    }//GEN-LAST:event_pnlRegistroSolucoesMouseExited

    private void pnlTrocaTurnoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrocaTurnoMouseClicked
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        Frames.carregarJFrame(FrmAtaTrocaTurno.class);
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }//GEN-LAST:event_pnlTrocaTurnoMouseClicked

    private void pnlTrocaTurnoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrocaTurnoMouseEntered
        MouseEventButton.setMouseEntered(pnlTrocaTurno);
    }//GEN-LAST:event_pnlTrocaTurnoMouseEntered

    private void pnlTrocaTurnoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pnlTrocaTurnoMouseExited
        MouseEventButton.setMouseExited(pnlTrocaTurno);
    }//GEN-LAST:event_pnlTrocaTurnoMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel lblLaboratorio;
    private javax.swing.JLabel lblLaboratorio1;
    private javax.swing.JLabel lblLaboratorio2;
    private javax.swing.JPanel pnlColunaStorage;
    private javax.swing.JPanel pnlColunaVaga;
    private javax.swing.JPanel pnlColunas;
    private javax.swing.JPanel pnlLogCromatografo;
    private javax.swing.JPanel pnlRegistroSolucoes;
    private javax.swing.JPanel pnlTrocaTurno;
    // End of variables declaration//GEN-END:variables


}
