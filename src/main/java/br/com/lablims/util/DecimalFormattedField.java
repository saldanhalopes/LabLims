/**
 * Classe para criar campos formatados com valores em decimal Tanto para valores
 * simples, como para exibir mÃ¡scaras monetÃ¡rias ou porcentagem
 */
package br.com.lablims.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.swing.InputVerifier;
import javax.swing.JComponent;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.JTextComponent;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author rafael.lopes
 */
public class DecimalFormattedField {

    public static DefaultFormatterFactory DecimalFormattedField(Integer qtdeCasasDecimais) {
        String casasDecimais = "";
        String num;

//        NumberFormat format = DecimalFormat.getInstance();
//        format.setMinimumFractionDigits(qtdeCasasDecimais);
//        format.setMaximumFractionDigits(qtdeCasasDecimais);
//        format.setRoundingMode(RoundingMode.HALF_UP);

        for (int i = 0; i < qtdeCasasDecimais; i++) {
            casasDecimais = casasDecimais.concat("0");
        }
        if (qtdeCasasDecimais.equals(0)) {
            num = "#,##0";
        } else {
            num = "#,##0.";
        }

        NumberFormatter defaultFormatter = new NumberFormatter(new DecimalFormat(num.concat(casasDecimais)));
        NumberFormatter displayCurrencyFormatter = new NumberFormatter(new DecimalFormat(num.concat(casasDecimais)));
        NumberFormatter editCurrencyFormatter = new NumberFormatter(new DecimalFormat(num.concat(casasDecimais)));

        defaultFormatter.setValueClass(Double.class);
        displayCurrencyFormatter.setValueClass(Double.class);
        editCurrencyFormatter.setValueClass(Double.class);

        DefaultFormatterFactory factory = new DefaultFormatterFactory(defaultFormatter, displayCurrencyFormatter, editCurrencyFormatter);

        return factory;
    }
    
    public static DefaultFormatterFactory NumberFormattedField() {
        
        NumberFormat format = new DecimalFormat("##########");
        format.setMinimumFractionDigits(0);
        
        NumberFormatter defaultFormatter = new NumberFormatter(format);
        NumberFormatter displayCurrencyFormatter = new NumberFormatter(format);
        NumberFormatter editCurrencyFormatter = new NumberFormatter(format);
        defaultFormatter.setValueClass(Integer.class);
        displayCurrencyFormatter.setValueClass(Integer.class);
        editCurrencyFormatter.setValueClass(Integer.class);
        DefaultFormatterFactory factory = new DefaultFormatterFactory(defaultFormatter, displayCurrencyFormatter, editCurrencyFormatter);
        return factory;
    }
    
    public static DefaultFormatterFactory DoubleFormattedField(Integer qtdeCasasDecimais) {
        
        NumberFormat format = new DecimalFormat("#,##0");
        format.setMinimumFractionDigits(qtdeCasasDecimais);
        
        NumberFormatter defaultFormatter = new NumberFormatter(format);
        NumberFormatter displayCurrencyFormatter = new NumberFormatter(format);
        NumberFormatter editCurrencyFormatter = new NumberFormatter(format);
        defaultFormatter.setValueClass(Double.class);
        displayCurrencyFormatter.setValueClass(Double.class);
        editCurrencyFormatter.setValueClass(Double.class);
        DefaultFormatterFactory factory = new DefaultFormatterFactory(defaultFormatter, displayCurrencyFormatter, editCurrencyFormatter);
        return factory;
    }

    public static InputVerifier Verifier() {
        InputVerifier verifier = new InputVerifier() {
            @Override
            public boolean verify(JComponent input) {
                final JTextComponent source = (JTextComponent) input;
                String text = source.getText();
                try {
                    if (!text.trim().isEmpty()) {
                        return text.length() != 0;
                    } else {
                        return false;
                    }

//                        JOptionPane.showMessageDialog(null, "Can't leave.", "Error Dialog",
//                                JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
//                    JOptionPane.showMessageDialog(null, "Can't leave.", "Error Dialog",
//                            JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            }
        };
        return verifier;
    }

}
