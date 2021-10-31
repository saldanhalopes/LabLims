/*
 * Copyright (C) 2017 rafael.lopes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.cristalia.biblioteca.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import static javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE;

/**
 *
 * @author rafael.lopes
 */
public class Frames {

    public static void janela(JInternalFrame fr) throws PropertyVetoException {
        fr.setSelected(true);
        fr.setMaximizable(true);
        fr.setClosable(true);
        fr.setResizable(true);
        fr.setSelected(true);
    }

    public static void centralizar(JInternalFrame fr) throws PropertyVetoException {
        Dimension d = fr.getDesktopPane().getSize();
        fr.setLocation((d.width - fr.getSize().width) / 2, (d.height - fr.getSize().height) / 2);
        fr.setMaximizable(true);
        //fr.setIconifiable(true);
        fr.setMaximum(true);
        fr.setClosable(true);
        fr.setResizable(true);
        fr.setSelected(true);
    }

    public static void cascade(JDesktopPane desk) throws PropertyVetoException {
        int separation = 25; // distancia entre as janelas
        // Pega todos os frames e organiza, o ultimo fica mais em baido e mais pra cima
        int i = desk.getAllFrames().length; // quantidade de frames
        for (JInternalFrame f : desk.getAllFrames()) {
            f.setMaximum(false);
            f.setLocation(i * separation, i * separation);
            i--; //mutiplicador
        }
    }

    public static void addTelaDesk(JDesktopPane desk, JInternalFrame tela, String value) {
        try {
            for (JInternalFrame frame : desk.getAllFrames()) {
                if (frame.getClass().toString().equalsIgnoreCase(tela.getClass().toString())) {
                    if (frame.getTitle().equals(value)) {
                        frame.moveToFront();
                        frame.setSelected(true);
                        frame.show();
                        desk.repaint();
                        return;
                    }
                }
            }
            desk.add(tela);
            tela.setTitle(value);
            centralizar(tela);
            tela.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao abrir" + e);
        }
    }

    public static void carregarJFrame(Class cls) {
        FrmCarregando frmCarregar = new FrmCarregando(null, true);
        Thread th = new Thread() {
            @Override
            public void run() {
                frmCarregar.setVisible(true);
            }
        };
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    th.start();
                    JFrame frm = (JFrame) cls.newInstance();
                    frm.setVisible(true);
                    frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    frmCarregar.dispose();
                    frm.toFront();
                    frmCarregar.dispose();
                } catch (InstantiationException | IllegalAccessException ex) {
                    frmCarregar.dispose();
                    JOptionPane.showMessageDialog(null, "Erro ao abrir" + ex);
                }
            }
        };
        t1.start();
    }

    public Image getIcon() {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_lablims_48.png"));
            return icone.getImage();
        } catch (Exception e) {
            return null;
        }
    }

    public Image getIconErro() {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_erro_alerta.png"));
            return icone.getImage();
        } catch (Exception e) {
            return null;
        }
    }

    public ImageIcon getImageIconErro() {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_erro_alerta.png"));
            return icone;
        } catch (Exception e) {
            return null;
        }
    }

    public ImageIcon getImageIconConect() {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_database_accept.png"));
            return icone;
        } catch (Exception e) {
            return null;
        }
    }

    public ImageIcon getImageIconDisconect() {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_database_error.png"));
            return icone;
        } catch (Exception e) {
            return null;
        }
    }

    public ImageIcon getImageIconAlerta() {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_alerta.png"));
            return icone;
        } catch (Exception e) {
            return null;
        }
    }
    
    public ImageIcon getImageIconRemove() {
        try {
            ImageIcon icone = new ImageIcon(getClass().getResource("/Imagem/icon_remove.png"));
            return icone;
        } catch (Exception e) {
            return null;
        }
    }

    public static void fecharFrame(JDialog frm) {
        int option = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza de que deseja fechar esse registro?\nOs dados inseridos não serão salvos!",
                "Confirmação de Cancelamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            frm.dispose();
        }
    }

    public static void fecharFrame(JFrame frm) {
        int option = JOptionPane.showConfirmDialog(
                null,
                "Tem certeza de que deseja fechar esse registro?\nOs dados inseridos não serão salvos!",
                "Confirmação de Cancelamento",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        if (option == JOptionPane.YES_OPTION) {
            frm.dispose();
        }
    }

    public static void setUpFrame(JFrame frm, String title, Boolean windowClosing) {
        frm.setLocationRelativeTo(null);
        frm.setIconImage(new Frames().getIcon());
        frm.setTitle(title);
        frm.setResizable(true);
        frm.setExtendedState(JFrame.MAXIMIZED_BOTH);
        if (windowClosing) {
            frm.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            frm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(
                            null,
                            "Tem certeza de que deseja fechar esse registro?\nOs dados inseridos não serão salvos!",
                            "Confirmação de Cancelamento",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        frm.dispose();
                    }
                }
            });
        } else {
            frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    public static void setUpFrame(JDialog frm, String title, Boolean windowClosing) {
        frm.setLocationRelativeTo(null);
        frm.setIconImage(new Frames().getIcon());
        frm.setTitle(title);
        frm.setResizable(false);
        if (windowClosing) {
            frm.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            frm.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    int option = JOptionPane.showConfirmDialog(
                            null,
                            "Tem certeza de que deseja fechar esse registro?\nOs dados inseridos não serão salvos!",
                            "Confirmação de Cancelamento",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);
                    if (option == JOptionPane.YES_OPTION) {
                        frm.dispose();
                    }
                }
            });
        } else {
            frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        }
    }

    public static void setAcesso(Component btn) {
        btn.setVisible(false);
    }

}
