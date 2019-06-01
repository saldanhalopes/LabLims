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
public class FieldLimit extends PlainDocument {

    private int iMaxLength = 0;
    private String tipo;
    Pattern texto;

    public FieldLimit(int maxlen, String tp) {
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

        // A = TUDO
        // B - SOMENTE LETRAS E NÚMEROS EM MAIÚSCULO
        // C - somente letras e números em minúsculo
        // D - SOMENTE NÚMEROS
        // E - SOMENTE LETRAS MAIÚSCULO
        // F - SOMENTE LETRAS minúsculo
        switch (tipo) {
            case "B":
                str = formatar(str.toUpperCase(), 1);
                break;
            case "C":
                str = formatar(str.toLowerCase(), 1);
                break;
            case "D":
                str = formatar(str, 2);
                break;
            case "E":
                str = formatar(str.toUpperCase(), 3);
                break;
            case "F":
                str = formatar(str.toLowerCase(), 3);
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
        }

        Matcher encaixe = texto.matcher(entrada);
        StringBuilder saida = new StringBuilder();
        while (encaixe.find()) {
            saida.append(encaixe.group());
        }
        return saida.toString();
    }


}
