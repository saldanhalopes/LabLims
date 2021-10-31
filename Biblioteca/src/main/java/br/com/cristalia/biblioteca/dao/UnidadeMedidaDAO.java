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
package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.UnidadeMedida;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author rafael
 */
public class UnidadeMedidaDAO extends GenenicoDAO<UnidadeMedida> {

    public UnidadeMedida findByUnidade(String unidade) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("UnidadeMedida.findByUnidade", UnidadeMedida.class)
                    .setParameter("unidade", unidade)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

}
