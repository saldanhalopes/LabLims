/*
 * Copyright (C) 2018 rafael.lopes
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
package br.com.cristalia.biblioteca.util;

import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rafael.lopes
 */
public class Reports {

    public static void imprimir(String report, HashMap map) {
        try {
            int option = JOptionPane.showConfirmDialog(
                    null, "Deseja imprimir esse registro?",
                    "Confirmação de Impressão", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (option == JOptionPane.YES_OPTION) {
                FrmCarregando frm = new FrmCarregando(null, true);
                Thread th = new Thread() {
                    @Override
                    public void run() {
                        frm.setVisible(true);
                    }
                };
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        try {
                            th.start();
                            Connection conn = ConnectionFactory.getConnection();
                            InputStream jasperFile = Thread.currentThread().getClass().getResourceAsStream("/Reports/" + report);
                            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperFile);
                            JasperPrint jpt = JasperFillManager.fillReport(jasperReport, map, conn);
                            JasperViewer jv = new JasperViewer(jpt, false);
                            jv.setLocationRelativeTo(null);
                            jv.setVisible(true);
                            frm.dispose();
                            jv.toFront();
                        } catch (Exception ex) {
                            frm.dispose();
                            JOptionPane.showMessageDialog(null, "Erro ao chamar o relatório!\nErro: " + ex);
                        }
                    }
                };
                t1.start();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao imprimir dados: " + e);
        }
    }

}
