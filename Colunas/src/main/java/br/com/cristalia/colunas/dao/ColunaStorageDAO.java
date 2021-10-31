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
package br.com.cristalia.colunas.dao;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.dao.GenenicoDAO;
import br.com.cristalia.colunas.model.ColunaStorage;
import br.com.cristalia.biblioteca.model.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class ColunaStorageDAO extends GenenicoDAO<ColunaStorage> {

    public List<ColunaStorage> findAll() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaStorage> root = cq.from(ColunaStorage.class);
            cq.orderBy(
                    cb.asc(root.get("tipo")),
                    cb.asc(root.get("numero"))
            );
            cq.select(root);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<ColunaStorage> findAll(Long setor_id) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaStorage> root = cq.from(ColunaStorage.class);
            Join<ColunaStorage, Setor> joinSetor = root.join("setor", JoinType.INNER);
            cq.where(cb.equal(joinSetor.get("id"), setor_id));
            cq.orderBy(
                    cb.asc(root.get("tipo")),
                    cb.asc(root.get("numero"))
            );
            cq.select(root);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getUltimoNumeroColunaStorage(Long setor_id, String tipo) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaStorage> root = cq.from(ColunaStorage.class);
            Join<ColunaStorage, Setor> joinSetor = root.join("setor", JoinType.INNER);
            cq.where(cb.and(
                    cb.equal(joinSetor.get("id"), setor_id),
                    cb.equal(root.get("tipo"), tipo)
            ));
            cq.orderBy(cb.desc(root.get("numero")));
            cq.select(root);
            TypedQuery<ColunaStorage> q = em.createQuery(cq);
            q.setMaxResults(1);
            return q.getSingleResult().getNumero();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Boolean checkNumeroColunaStorage(Long setor_id, String tipo, Integer numero) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaStorage> root = cq.from(ColunaStorage.class);
            Join<ColunaStorage, Setor> joinSetor = root.join("setor", JoinType.INNER);
            cq.where(cb.and(
                    cb.equal(joinSetor.get("id"), setor_id),
                    cb.equal(root.get("tipo"), tipo),
                    cb.equal(root.get("numero"), numero)
            ));
            cq.orderBy(cb.desc(root.get("numero")));
            cq.select(root);
            TypedQuery<ColunaStorage> q = em.createQuery(cq);
            q.setMaxResults(1);
            return q.getSingleResult().getId() > 0;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<ColunaStorage> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ColunaStorage> colStorages = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_coluna_storage_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_coluna_storage_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_coluna_storage_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_coluna_storage_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ColunaStorage colStorage = new ColunaStorage();
                Setor setor = new Setor();
                Audit audit = new Audit();
                colStorage.setId(rs.getLong("id"));
                setor.setId(rs.getLong("setor_id"));
                colStorage.setSetor(setor);
                colStorage.setNumero(rs.getInt("numero"));
                colStorage.setObs(rs.getString("obs"));
                colStorage.setTipo(rs.getString("tipo"));
                colStorage.setNumero_MOD(rs.getBoolean("numero_MOD"));
                colStorage.setObs_MOD(rs.getBoolean("obs_MOD"));
                colStorage.setSetor_MOD(rs.getBoolean("setor_MOD"));
                colStorage.setTipo_MOD(rs.getBoolean("tipo_MOD"));
                colStorage.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                colStorage.setAudit(audit);
                colStorages.add(colStorage);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colStorages;
    }

}
