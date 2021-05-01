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

import br.com.lablims.util.Frames;
import java.awt.Cursor;

/**
 * O <code>Splash</code> implementa tela de carregaemnto inicial do sistema
 *
 * @author rafae.lopes
 * @version 1.00
 */
public class Splash extends javax.swing.JFrame implements Runnable {

    /**
     * Método para criar o Form Splash()
     */
    public Splash() {
        initComponents();
        setIconImage(new Frames().getIcon());
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCarregando = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();
        lblLogo = new javax.swing.JLabel();
        lblLogo1 = new javax.swing.JLabel();
        lblFundo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCarregando.setText("Carregando...");
        getContentPane().add(lblCarregando, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 350, -1, 30));

        jProgressBar1.setIndeterminate(true);
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 375, 580, 20));

        lblLogo.setBackground(new java.awt.Color(215, 215, 215));
        lblLogo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_lablims_100.png"))); // NOI18N
        lblLogo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLogo.setIconTextGap(0);
        getContentPane().add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 0, 90, 110));

        lblLogo1.setBackground(new java.awt.Color(215, 215, 215));
        lblLogo1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblLogo1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblLogo1.setOpaque(true);
        getContentPane().add(lblLogo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 50, 40, 70));

        lblFundo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagem/icon_background_lab_lims.png"))); // NOI18N
        getContentPane().add(lblFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para criar o Form Splash()
     */
    @Override
    public void run() {
        synchronized (this) {
            /* Create and display the dialog */
            new Splash().setVisible(true);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JProgressBar jProgressBar1;
    public static javax.swing.JLabel lblCarregando;
    public static javax.swing.JLabel lblFundo;
    public static javax.swing.JLabel lblLogo;
    public static javax.swing.JLabel lblLogo1;
    // End of variables declaration//GEN-END:variables

}
