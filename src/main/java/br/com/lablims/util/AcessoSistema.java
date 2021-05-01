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

import br.com.lablims.dao.FormDAO;
import br.com.lablims.model.Form;

/**
 *
 * @author rafael.lopes
 */
public class AcessoSistema {

    public String getAcessoSistema(Object o) {
        String acesso = null;
        FormDAO formDAO = new FormDAO();
        try {
            Form form = formDAO.getFormByName(o.getClass().getSimpleName());
            String valor = System.getProperty("acesso");
            String[] valorComSplit = valor.split(";");
            for (String s : valorComSplit) {
                if (s.substring(0, s.indexOf("-")).equals(form.getId().toString())) {
                    acesso = s.substring(s.indexOf("-") + 1);
                }
            }
            return acesso;
        } catch (Exception e) {
            return null;
        }
    }
    
    public String getAcessoSistema(String name) {
        String acesso = null;
        FormDAO formDAO = new FormDAO();
        try {
            Form form = formDAO.getFormByName(name);
            String valor = System.getProperty("acesso");
            String[] valorComSplit = valor.split(";");
            for (String s : valorComSplit) {
                if (s.substring(0, s.indexOf("-")).equals(form.getId().toString())) {
                    acesso = s.substring(s.indexOf("-") + 1);
                }
            }
            return acesso;
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean acessoDados(String acesso) {
        if (acesso != null) {
            switch (acesso) {
                case "Total":
                    return true;
                case "Gravação":
                    return true;
                case "Atualização":
                    return true;
                case "Exclusão":
                    return true;
                case "Leitura":
                    return true;
                case "Negado":
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public static boolean carregarDados(String acesso) {
        if (acesso != null) {
            switch (acesso) {
                case "Total":
                    return true;
                case "Gravação":
                    return false;
                case "Atualização":
                    return false;
                case "Exclusão":
                    return false;
                case "Leitura":
                    return true;
                case "Negado":
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public static boolean criarDados(String acesso) {
        if (acesso != null) {
            switch (acesso) {
                case "Total":
                    return true;
                case "Gravação":
                    return true;
                case "Atualização":
                    return true;
                case "Exclusão":
                    return true;
                case "Leitura":
                    return false;
                case "Negado":
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }

    }

    public static boolean editarDados(String acesso) {
        if (acesso != null) {
            switch (acesso) {
                case "Total":
                    return true;
                case "Gravação":
                    return false;
                case "Atualização":
                    return true;
                case "Exclusão":
                    return true;
                case "Leitura":
                    return false;
                case "Negado":
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public static boolean deletarDados(String acesso) {
        if (acesso != null) {
            switch (acesso) {
                case "Total":
                    return true;
                case "Gravação":
                    return false;
                case "Atualização":
                    return false;
                case "Exclusão":
                    return true;
                case "Leitura":
                    return false;
                case "Negado":
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

}
