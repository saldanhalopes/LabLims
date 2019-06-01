package br.com.lablims.util;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael.lopes
 */
public class TableSorter {

    public TableSorter() {
    }

    public static void Sorter(JTable tbl, JTextField txt) {
        DefaultTableModel model = (DefaultTableModel) tbl.getModel();
        TableRowSorter sorter = new TableRowSorter<>(model);
        tbl.setRowSorter(sorter);
        String text = txt.getText().trim();
        String parametro = "\\*";
        String[] texts = text.split(parametro);
        if (text.length() == 0) {
            sorter.setRowFilter(null);
        } else if (texts.length > 0) {
            ArrayList<RowFilter<Object, Object>> andFilters = new ArrayList<>();
            for (String texto : texts) {
                andFilters.add(RowFilter.regexFilter("(?i)" + texto));
            }
            sorter.setRowFilter(RowFilter.andFilter(andFilters));
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
        }
    }

}
