package br.com.cristalia.biblioteca.util;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author rafael.lopes
 */
public class TableSorter {

    public TableSorter() {
    }

    public static void TableSorter(JTable tbl, JTextField txt) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbl.getModel());
        tbl.setRowSorter(sorter);
        String text = txt.getText().trim();
        String parametro = "\\*";
        try {
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
        } catch (Exception e) {
            sorter.setRowFilter(null);
        }
    }

    public static void TableTextSorter(JTable tbl, String text, int indice) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbl.getModel());
        tbl.setRowSorter(sorter);
        try {
            if (text.equals("Todos")) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, indice));
            }
        } catch (Exception e) {
            sorter.setRowFilter(null);
        }
    }
    
    public static void TableComboSorter(JTable tbl, JComboBox cmb, int indice) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbl.getModel());
        tbl.setRowSorter(sorter);
        String text = cmb.getSelectedItem().toString();
        try {
            if (text.equals("Todos")) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + text, indice));
            }
        } catch (Exception e) {
            sorter.setRowFilter(null);
        }
    }

    public static void RowSorter(JTable tbl, int[] keysASC, int[] keysDESC) {
        RowSorter<TableModel> sorter = new TableRowSorter<>(tbl.getModel());
        tbl.setRowSorter(sorter);
        try {
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            if (keysASC != null) {
                for (int keyASC : keysASC) {
                    sortKeys.add(new RowSorter.SortKey(keyASC, SortOrder.ASCENDING));
                }
            }
            if (keysDESC != null) {
                for (int keyDESC : keysDESC) {
                    sortKeys.add(new RowSorter.SortKey(keyDESC, SortOrder.DESCENDING));
                }
            }
            sorter.setSortKeys(sortKeys);
        } catch (Exception e) {
            sorter.setSortKeys(null);
        }
    }

    public static void ClearTableSorter(JTable tbl) {
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(tbl.getModel());
        tbl.setRowSorter(sorter);
        sorter.setRowFilter(null);
    }
}
