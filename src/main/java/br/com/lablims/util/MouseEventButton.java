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
package br.com.lablims.util;

import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author rafael.lopes
 */
public class MouseEventButton {

    public static void setMouseEntered(JPanel pnl) {
        pnl.setBackground(Color.LIGHT_GRAY);
    }

    public static void setMouseExited(JPanel pnl) {
        pnl.setBackground(Color.WHITE);
    }

}
