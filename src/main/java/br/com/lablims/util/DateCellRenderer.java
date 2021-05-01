package br.com.lablims.util;

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
public class DateCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Component tableCellRendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if ("Vermelho".equals(value)) {
            tableCellRendererComponent.setBackground(Color.RED);
        } else if ("Azul".equals(value)) {
            tableCellRendererComponent.setBackground(Color.BLUE);
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
