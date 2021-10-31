/*
 * Copyright (C) 2018 rafael
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.cristalia.colunas.dao;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.dao.GenenicoDAO;
import br.com.cristalia.biblioteca.model.Analise;
import br.com.cristalia.biblioteca.model.Arquivos;
import br.com.cristalia.colunas.model.Coluna;
import br.com.cristalia.colunas.model.ColunaConfig;
import br.com.cristalia.colunas.model.ColunaUtil;
import br.com.cristalia.colunas.model.ColunaVaga;
import br.com.cristalia.biblioteca.model.Metodologia;
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
public class ColunaUtilDAO extends GenenicoDAO<ColunaUtil> {

    public List<ColunaUtil> findListColunaUtilAtivos() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaUtil> root = cq.from(ColunaUtil.class);
            cq.where(cb.isNull(root.get("dataDescarte")));
            cq.orderBy(cb.desc(root.get("id")));
            cq.select(root);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public List<ColunaUtil> findListColunaUtil(Integer maxResults,
            String conditional) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaUtil> root = cq.from(ColunaUtil.class);
            if (conditional != null) {
                switch (conditional) {
                    case "Ativos":
                        cq.where(cb.isNull(root.get("dataDescarte")));
                        break;
                    case "Inativos":
                        cq.where(cb.isNotNull(root.get("dataDescarte")));
                        break;
                }
            }
            cq.orderBy(cb.desc(root.get("id")));
            cq.select(root);
            Query q = em.createQuery(cq);
            if (maxResults != null) {
                q.setMaxResults(maxResults);
            }
            return q.getResultList();
        } catch (Exception ex) {
            throw new Exception(ex);
        } finally {
            em.close();
        }
    }

    public Integer getUltimaColuna(String tipo_coluna) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaUtil> root = cq.from(ColunaUtil.class);
            Join<ColunaUtil, Coluna> joinColuna = root.join("coluna", JoinType.INNER);
            Join<Coluna, ColunaConfig> joinColunaConfig = joinColuna.join("tipoColuna", JoinType.INNER);
            cq.where(cb.equal(joinColunaConfig.get("configuracao"), tipo_coluna));
            cq.orderBy(cb.desc(root.get("id")));
            cq.select(root);
            TypedQuery<ColunaUtil> q = em.createQuery(cq);
            q.setMaxResults(1);
            return q.getSingleResult().getCodigoColuna();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public ColunaUtil findListColunaUtilWithAnexo(Long col_id) {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<ColunaUtil> root = cq.from(ColunaUtil.class);
            root.fetch("anexos", JoinType.INNER);
            cq.where(cb.equal(root.get("id"), col_id));
            cq.select(root);
            TypedQuery<ColunaUtil> q = em.createQuery(cq);
            return q.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<ColunaUtil> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ColunaUtil> colsUtil = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_coluna_util_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_coluna_util_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_coluna_util_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_coluna_util_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                ColunaUtil colUtil = new ColunaUtil();
                Coluna col = new Coluna();
                Setor setor = new Setor();
                Metodologia metodologia = new Metodologia();
                Analise analise = new Analise();
                Arquivos arquivo = new Arquivos();
                ColunaVaga vaga = new ColunaVaga();
                Audit audit = new Audit();
                colUtil.setId(rs.getLong("id"));
                colUtil.setCodigoColuna(rs.getInt("codigo_coluna"));
                colUtil.setDataAtivacao(rs.getDate("data_ativacao"));
                colUtil.setDataDescarte(rs.getDate("data_descarte"));
                colUtil.setDataVerificacao(rs.getDate("data_verificacao"));
                colUtil.setSerialNumber(rs.getString("serial_number"));
                colUtil.setObs(rs.getString("obs"));
                col.setId(rs.getLong("coluna_id"));
                colUtil.setColuna(col);
                setor.setId(rs.getLong("setor_id"));
                colUtil.setSetor(setor);
                metodologia.setId(rs.getLong("metodologia_id"));
                colUtil.setMetodologia(metodologia);
                analise.setId(rs.getLong("analise_id"));
                colUtil.setAnalise(analise);
                vaga.setId(rs.getLong("vaga_id"));
                colUtil.setVaga(vaga);
                arquivo.setId(rs.getLong("certificado_id"));
                colUtil.setCertificado(arquivo);
                colUtil.setAnalise_MOD(rs.getBoolean("analise_MOD"));
                colUtil.setCodigoColuna_MOD(rs.getBoolean("codigoColuna_MOD"));
                colUtil.setColuna_MOD(rs.getBoolean("coluna_MOD"));
                colUtil.setDataAtivacao_MOD(rs.getBoolean("dataAtivacao_MOD"));
                colUtil.setDataDescarte_MOD(rs.getBoolean("dataDescarte_MOD"));
                colUtil.setDataVerificacao_MOD(rs.getBoolean("dataVerificacao_MOD"));
                colUtil.setMetodologia_MOD(rs.getBoolean("metodologia_MOD"));
                colUtil.setSerialNumber_MOD(rs.getBoolean("serialNumber_MOD"));
                colUtil.setSetor_MOD(rs.getBoolean("setor_MOD"));
                colUtil.setVaga_MOD(rs.getBoolean("vaga_MOD"));
                colUtil.setCertificado_MOD(rs.getBoolean("certificado_MOD"));
                colUtil.setObs_MOD(rs.getBoolean("obs_MOD"));
                colUtil.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                colUtil.setAudit(audit);
                colsUtil.add(colUtil);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return colsUtil;
    }

}
