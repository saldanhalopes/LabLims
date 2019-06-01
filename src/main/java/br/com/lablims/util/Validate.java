/*
 * Copyright (C) 2018 rafael.lopes
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rafael.lopes
 */
public class Validate {

    public boolean isEmail(String email) {
        Pattern pat = Pattern.compile("^[\\w\\\\\\+]+(\\.[\\w\\\\]+)*@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$");
        Matcher mat = pat.matcher(email);
        return mat.find();
    }



}
