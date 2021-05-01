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

import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Campanha;
import br.com.lablims.model.LogCromatografo;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

/**
 *
 * @author rafael
 */
public class LogCromatografoDAO extends GenenicoDAO<LogCromatografo> {

    public Date findLastDataInicio(Long campanha_id) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<LogCromatografo> root = cq.from(LogCromatografo.class);
            Join<LogCromatografo, Campanha> joinCampanha = root.join("campanha", JoinType.INNER);
            cq.where(cb.equal(joinCampanha.get("id"), campanha_id));
            cq.orderBy(cb.desc(root.get("id")));
            cq.select(root);
            TypedQuery<LogCromatografo> q = em.createQuery(cq);
            q.setMaxResults(1);
            return q.getSingleResult().getDataInicio();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }

    }

}
