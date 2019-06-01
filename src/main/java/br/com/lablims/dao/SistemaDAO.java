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
package br.com.lablims.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Sistema;

/**
 *
 * @author rafael.lopes
 */
public class SistemaDAO extends GenenicoDAO<Sistema> {

    public static boolean isBancoDadosConectado(EntityManager em) {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        boolean isConnected = false;
        try {
            String sql = "SELECT 1";
            Query query = em.createNativeQuery(sql);
            isConnected = query.getSingleResult().equals(1);
        } catch(Exception e) {
        }
        return isConnected;
    }

}
