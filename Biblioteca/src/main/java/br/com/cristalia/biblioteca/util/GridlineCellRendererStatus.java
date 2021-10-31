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
package br.com.cristalia.biblioteca.util;

import java.awt.Color;
import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rafael.lopes
 */
public class GridlineCellRendererStatus extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if ("Vermelho".equals(value)) {
            tableCellRendererComponent.setBackground(Color.RED);
        } else if ("Azul".equals(value)) {
            tableCellRendererComponent.setBackground(Color.BLUE);
        } else if ("EM CQ".equals(value)) {
            tableCellRendererComponent.setBackground(Color.MAGENTA);
        } else if ("EM ANALISE".equals(value)) {
            tableCellRendererComponent.setBackground(Color.CYAN);
        } else if ("EM ESPERA".equals(value)) {
            tableCellRendererComponent.setBackground(Color.GRAY);
        } else if ("FINALIZADO".equals(value)) {
            tableCellRendererComponent.setBackground(Color.GREEN.darker());
        } else {
            tableCellRendererComponent.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        }
        if (value instanceof Date) {
            // Use SimpleDateFormat class to get a formatted String from Date object.
            String strDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").format((Date) value);
            // Sorting algorithm will work with model value. So you dont need to worry
            // about the renderer's display value. 
            this.setText(strDate);
        }
        return tableCellRendererComponent;
    }

}
