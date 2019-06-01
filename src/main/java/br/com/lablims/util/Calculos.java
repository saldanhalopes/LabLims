/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lablims.util;

import java.awt.Component;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import javax.swing.JFormattedTextField;
import javax.swing.JPanel;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

/**
 *
 * @author rafael.lopes
 */
public class Calculos {

    public Calculos() {
    }

    public static ArrayList<Double> Janelas(JPanel[] pnls) {
        ArrayList<Double> values = new ArrayList();
        for (JPanel pnl : pnls) {
            for (Component c : pnl.getComponents()) {
                if (c instanceof JFormattedTextField) {
                    JFormattedTextField f = (JFormattedTextField) c;
                    if (f.getValue() != null) {
                        values.add((Double) f.getValue());
                    }
                }
            }
        }
        try {
            return values;
        } catch (Exception e) {
            return null;
        }
    }

    public static String Arred(Double value, Integer qtdeCasasDecimais) {
        try {
            BigDecimal resultado = new BigDecimal(value).setScale(qtdeCasasDecimais, RoundingMode.HALF_UP);
            return value <= 0.0 ? null : resultado.toString();
        } catch (Exception e) {
            return null;
        }

    }

    public static Double Soma(ArrayList<Double> values) {
        try {
            Double resultado = StatUtils.sum(values.stream().mapToDouble(Double::doubleValue).toArray());
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }
    
    public static Double Media(ArrayList<Double> values) {

        try {
            Double resultado = StatUtils.mean(values.stream().mapToDouble(Double::doubleValue).toArray());
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double Min(ArrayList<Double> values) {

        try {
            Double resultado = StatUtils.min(values.stream().mapToDouble(Double::doubleValue).toArray());
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double Max(ArrayList<Double> values) {

        try {
            Double resultado = StatUtils.max(values.stream().mapToDouble(Double::doubleValue).toArray());
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double Porcentagem(ArrayList<Double> values, Double value) {
        try {
            Double resultado = value * 100 / Media(values);
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double MinPorcentagem(ArrayList<Double> values) {
        try {
            Double resultado = Min(values) * 100 / Media(values);
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double MaxPorcentagem(ArrayList<Double> values) {
        try {
            Double resultado = Max(values) * 100 / Media(values);
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double DesvioPadrao(ArrayList<Double> values) {
        try {
            Double resultado = new StandardDeviation().evaluate(values.stream().mapToDouble(Double::doubleValue).toArray());
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

    public static Double DesvioPadraoRelativo(ArrayList<Double> values) {
        try {
            Double resultado = (new StandardDeviation().evaluate(values.stream().mapToDouble(Double::doubleValue).toArray()))
                    * 100 / StatUtils.mean(values.stream().mapToDouble(Double::doubleValue).toArray());
            return resultado.isNaN() ? 0.0 : resultado;
        } catch (Exception e) {
            return null;
        }
    }

}
