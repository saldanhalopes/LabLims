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
import br.com.cristalia.colunas.model.ColunaVaga;
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
public class ColunaVagaDAO extends GenenicoDAO<ColunaVaga> {

    public List<ColunaVaga> findAll() throws EntityNotFoundException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaVaga> root = cq.from(ColunaVaga.class);
            Join<ColunaVaga, ColunaStorage> joinStorage = root.join("colunaStorage", JoinType.INNER);
            cq.orderBy(
                    cb.asc(joinStorage.get("setor").get("siglaSetor")),
                    cb.asc(joinStorage.get("tipo")),
                    cb.asc(joinStorage.get("numero")),
                    cb.asc(root.get("vaga"))
            );
            cq.select(root);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (EntityNotFoundException ex) {
            return null;
        } finally {
            em.close();
        }
    }
    
    public List<Object[]> findAllVaga() throws EntityNotFoundException {
        EntityManager em = ConnectionFactory.em();
        try {
            String sql = "SELECT tb_coluna_vaga.id, "
                    + "tb_coluna_vaga.vaga, "
                    + "tb_coluna_vaga.obs, "
                    + "tb_coluna_vaga.version, "
                    + "tb_coluna_storage.tipo, "
                    + "tb_coluna_storage.numero, "
                    + "tb_setor.setor, "
                    + "tb_departamento.sigla_departamento, "
                    + "tb_coluna_util.codigo_coluna, "
                    + "tb_coluna_config.configuracao "
                    + "FROM tb_coluna_vaga "
                    + "INNER JOIN tb_coluna_storage "
                    + "ON tb_coluna_vaga.coluna_storage_id = tb_coluna_storage.id "
                    + "INNER JOIN tb_setor  "
                    + "ON tb_coluna_storage.setor_id = tb_setor.id  "
                    + "INNER JOIN tb_departamento  "
                    + "ON tb_setor.departamento_id = tb_departamento.id  "
                    + "LEFT JOIN tb_coluna_util "
                    + "ON tb_coluna_vaga.coluna_util_id = tb_coluna_util.id  "
                    + "LEFT JOIN tb_coluna "
                    + "ON tb_coluna_util.coluna_id = tb_coluna.id  "
                    + "LEFT JOIN tb_coluna_config "
                    + "ON tb_coluna.tipo_coluna = tb_coluna_config.id "
                    + "ORDER BY tb_setor.sigla_setor, tb_coluna_storage.tipo, "
                    + "tb_coluna_storage.numero, tb_coluna_vaga.vaga";
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } catch (EntityNotFoundException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object[]> findAllVagaLivre() throws EntityNotFoundException {
        EntityManager em = ConnectionFactory.em();
        try {
            String sql = "SELECT tb_coluna_vaga.id, "
                    + "tb_coluna_vaga.vaga, "
                    + "tb_coluna_vaga.obs, "
                    + "tb_coluna_vaga.version, "
                    + "tb_coluna_storage.tipo, "
                    + "tb_coluna_storage.numero, "
                    + "tb_setor.setor, "
                    + "tb_departamento.sigla_departamento, "
                    + "tb_coluna_util.codigo_coluna, "
                    + "tb_coluna_config.configuracao "
                    + "FROM tb_coluna_vaga "
                    + "INNER JOIN tb_coluna_storage "
                    + "ON tb_coluna_vaga.coluna_storage_id = tb_coluna_storage.id "
                    + "INNER JOIN tb_setor  "
                    + "ON tb_coluna_storage.setor_id = tb_setor.id  "
                    + "INNER JOIN tb_departamento  "
                    + "ON tb_setor.departamento_id = tb_departamento.id  "
                    + "LEFT JOIN tb_coluna_util "
                    + "ON tb_coluna_vaga.coluna_util_id = tb_coluna_util.id  "
                    + "LEFT JOIN tb_coluna "
                    + "ON tb_coluna_util.coluna_id = tb_coluna.id  "
                    + "LEFT JOIN tb_coluna_config "
                    + "ON tb_coluna.tipo_coluna = tb_coluna_config.id "
                    + "WHERE tb_coluna_vaga.coluna_util_id IS NULL "
                    + "ORDER BY tb_setor.sigla_setor, tb_coluna_storage.tipo, "
                    + "tb_coluna_storage.numero, tb_coluna_vaga.vaga";
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } catch (EntityNotFoundException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<ColunaVaga> findVagaByColuna(Long coluna_id) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("ColunaVaga.findVagaByColuna", ColunaVaga.class)
                    .setParameter("id", coluna_id)
                    .getResultList();
        } catch (Exception ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Integer getUltimoNumeroColunaVaga(Long storage_id) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaVaga> root = cq.from(ColunaVaga.class);
            Join<ColunaVaga, ColunaStorage> joinStorage = root.join("colunaStorage", JoinType.INNER);
            cq.where(cb.equal(joinStorage.get("id"), storage_id));
            cq.orderBy(cb.desc(root.get("vaga")));
            cq.select(root);
            TypedQuery<ColunaVaga> q = em.createQuery(cq);
            q.setMaxResults(1);
            return q.getSingleResult().getVaga();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Boolean checkNumeroColunaVaga(Long storage_id, Integer vaga) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaVaga> root = cq.from(ColunaVaga.class);
            Join<ColunaVaga, ColunaStorage> joinStorage = root.join("colunaStorage", JoinType.INNER);
            cq.where(cb.and(
                    cb.equal(root.get("colunaStorage").get("id"), storage_id),
                    cb.equal(root.get("vaga"), vaga)
            ));
            cq.orderBy(cb.desc(root.get("vaga")));
            cq.select(root);
            TypedQuery<ColunaStorage> q = em.createQuery(cq);
            q.setMaxResults(1);
            q.getSingleResult();
            return q.getMaxResults() > 0;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<ColunaVaga> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ColunaVaga> colVagas = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_coluna_vaga_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_coluna_vaga_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_coluna_vaga_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_coluna_vaga_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ColunaVaga colVaga = new ColunaVaga();
                ColunaStorage colStorage = new ColunaStorage();
                Audit audit = new Audit();
                colVaga.setId(rs.getLong("id"));
                colStorage.setId(rs.getLong("coluna_storage_id"));
                colVaga.setColunaStorage(colStorage);
                colVaga.setVaga(rs.getInt("vaga"));
                colVaga.setObs(rs.getString("obs"));
                colVaga.setVaga_MOD(rs.getBoolean("vaga_MOD"));
                colVaga.setObs_MOD(rs.getBoolean("obs_MOD"));
                colVaga.setColunaStorage_MOD(rs.getBoolean("colunaStorage_MOD"));
                colVaga.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                colVaga.setAudit(audit);
                colVagas.add(colVaga);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colVagas;
    }

}
