/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lablims.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 *
 * @author rafael.lopes
 */
public class FieldFormatLimit extends PlainDocument {

    private int iMaxLength = 0;
    public static final int TUDO = 0;
    public static final int LETRAS_NUMEROS_MAIUSCULO = 1;
    public static final int LETRAS_NUMEROS_MINUSCULO = 2;
    public static final int NUMEROS = 3;
    public static final int LETRAS_MAIUSCULO = 4;
    public static final int LETRAS_MINUSCULO = 5;
    public static final int Letras = 6;
    public static final int Letras_Numeros = 7;

    private int tipo;
    Pattern texto;

    /**
     *
     * @param maxlen
     * @param tp
     */
    public FieldFormatLimit(int maxlen, int tp) {
        super();
        iMaxLength = maxlen;
        tipo = tp;
    }

    @Override
    public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
        if (str == null) {
            return;
        }

        if (iMaxLength <= 0) {// aceitara qualquer no. de caracteres
            super.insertString(offset, str, attr);
            return;
        }

        // 0 = TUDO
        // 1 - SOMENTE LETRAS E NÚMEROS EM MAIÚSCULO
        // 2 - somente letras e números em minúsculo
        // 3 - SOMENTE NÚMEROS
        // 4 - SOMENTE LETRAS MAIÚSCULO
        // 5 - SOMENTE LETRAS minúsculo
        switch (tipo) {
            case 1:
                str = formatar(str.toUpperCase(), 1);
                break;
            case 2:
                str = formatar(str.toLowerCase(), 1);
                break;
            case 3:
                str = formatar(str, 2);
                break;
            case 4:
                str = formatar(str.toUpperCase(), 3);
                break;
            case 5:
                str = formatar(str.toLowerCase(), 3);
                break;
            case 6:
                str = formatar(str, 5);
                break;
            case 7:
                str = formatar(str, 4);
                break;
        }

        int ilen = (getLength() + str.length());

        if (ilen <= iMaxLength) // se o comprimento final for menor...
        {
            super.insertString(offset, str, attr); // aceita str
        } else {
            if (getLength() == iMaxLength) {
                return; // nada a fazer
            }
            String newStr = str.substring(0, (iMaxLength - getLength()));

            super.insertString(offset, newStr, attr);
        }

    }

    public String formatar(String entrada, Integer valor) {
        // 1 - somente letras e números
        // 2 - somente numeros
        // 3 - somente letras
        if (valor == 1) {
            texto = Pattern.compile("[0-9a-z]", Pattern.CASE_INSENSITIVE);
        } else if (valor == 2) {
            texto = Pattern.compile("[0-9]", Pattern.CASE_INSENSITIVE);
        } else if (valor == 3) {
            texto = Pattern.compile("[a-z]", Pattern.CASE_INSENSITIVE);
        }else if (valor == 4) {
            texto = Pattern.compile("[0-9a-z]");
        }else if (valor == 5) {
            texto = Pattern.compile("[a-z]");
        }

        Matcher encaixe = texto.matcher(entrada);
        StringBuilder saida = new StringBuilder();
        while (encaixe.find()) {
            saida.append(encaixe.group());
        }
        return saida.toString();
    }

}
