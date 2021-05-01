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

import br.com.lablims.dao.SegurancaDAO;
import br.com.lablims.model.Seguranca;
import br.com.lablims.view.config.FrmSalvar;
import java.io.Serializable;

/**
 *
 * @author rafael.lopes
 */
public class Senha implements Serializable {

    private Boolean senha;

    public Senha() {
    }

    public Senha(Boolean senha) {
        this.senha = senha;
    }

    public Boolean getSenha() {
        return senha;
    }

    public void setSenha(Boolean senha) {
        this.senha = senha;
    }

    public Boolean Salvar() {
        FrmSalvar frmSalvar = new FrmSalvar(null, true, this);
        try {
            SegurancaDAO segurancaDAO = new SegurancaDAO();
            if (segurancaDAO.findByTipo(Seguranca.VALIDAR).getNumero() == 0) {
                setSenha(true);
            } else {
                frmSalvar.setVisible(true);
            }
        } catch (Exception ex) {
            frmSalvar.dispose();
            setSenha(false);
        }
        return getSenha();
    }
}
