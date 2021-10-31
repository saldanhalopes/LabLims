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

package br.com.cristalia.colunas.principal;

import br.com.cristalia.biblioteca.dao.SistemaDAO;
import br.com.cristalia.biblioteca.model.Sistema;
import br.com.cristalia.biblioteca.util.ReadXMLFile;
import java.io.IOException;

/**
 * O <code>AbreSistema</code> implementa o carregaemnto inicial do sistema
 *
 * @author rafae.lopes
 * @version 1.00
 */
public class AbreSistema {

    /**
     * Método para carrer o Form Splash via <code>Thread</code>.
     */
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                // Define o tipo de Layout do Sistema ("Metal", "Nimbus", "Windows").
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Splash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ReadXMLFile.readConfig();
                Splash splash = new Splash();
                splash.setVisible(true);
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            Sistema sys = new SistemaDAO().findById(Sistema.class, 1L);
                            StringBuilder sb = new StringBuilder();
                            sb.append(sys.getMajorVersion());
                            sb.append(sys.getMinorVersion());
                            sb.append(sys.getPathVersion());
                            sb.append(sys.getQualifierVersion());
                            sb.append(sys.getBuilderVersao());
                            Integer version_sys = Integer.parseInt(sb.toString());
                            Integer version = Integer.parseInt(System.getProperty("VERSION").replaceAll("\\.", ""));
                            if (version_sys > version) {
                                runUpdate();
                            }
                        } catch (Exception ex) {
//                            runUpdate();
//                            System.exit(0);
                        }
                        FrmLogin dashboard = new FrmLogin(splash);
                        dashboard.setVisible(true);
                    }
                }.start();
            }
        });
    }

    private static void runUpdate() {
        final String update = "M:\\controledequalidade_compartilhada\\LabLims\\Lab_Lims_Update.jar";
        final String exe = "java -jar " + update;
        try {
            Runtime.getRuntime().exec(exe);
        } catch (IOException ex) {
            System.exit(0);
        }
        System.exit(0);
    }

}
