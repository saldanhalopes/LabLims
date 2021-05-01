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
package br.com.lablims.dao;

import br.com.lablims.audit.Audit;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Analise;
import br.com.lablims.model.AnaliseTipo;
import br.com.lablims.model.Metodologia;
import br.com.lablims.model.PlanoAnalise;
import br.com.lablims.model.Setor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class PlanoAnaliseDAO extends GenenicoDAO<PlanoAnalise> {

    public List<PlanoAnalise> readPlanoAnalise(Long id) {
        EntityManager em = ConnectionFactory.em();
        try {
            Query myQuery = em.createQuery("SELECT pa FROM PlanoAnalise pa "
                    + "LEFT JOIN pa.metodologia mtd "
                    + "WHERE mtd.id = :mtd_id "
                    + "ORDER BY pa.descricao ASC")
                    .setParameter("mtd_id", id);
            return myQuery.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<PlanoAnalise> readPlanoAnaliseTodosMetodos(List<Metodologia> mtds) {
        EntityManager em = ConnectionFactory.em();
        try {
            List<Long> mtd_ids = new ArrayList<>();
            for (Metodologia mtd : mtds) {
                mtd_ids.add(mtd.getId());
            }
            Query myQuery = em.createQuery("SELECT pa FROM PlanoAnalise pa "
                    + "LEFT JOIN pa.metodologia mtd "
                    + "WHERE mtd.id IN :mtd_ids "
                    + "ORDER BY pa.descricao ASC")
                    .setParameter("mtd_ids", mtd_ids);
            return myQuery.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object[]> readPlanosAnalises() {
        EntityManager em = ConnectionFactory.em();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery();
            Root<PlanoAnalise> root = cq.from(PlanoAnalise.class);
            cq.multiselect(root.get("descricao"),
                    root.get("analise").get("id"),
                    root.get("analiseTipo").get("id"),
                    root.get("setor").get("id"),
                    cb.count(root.get("id"))
            );
            cq.orderBy(cb.asc(root.get("descricao")));
            cq.groupBy(root.get("descricao"),
                    root.get("analise").get("id"),
                    root.get("analiseTipo").get("id"),
                    root.get("setor").get("id")
            );
            TypedQuery<Object[]> q = em.createQuery(cq);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Object[]> readMetodosPlanosAnalises() {
        EntityManager em = ConnectionFactory.em();
        try {
            String sql = "SELECT CAST (tb_metodologia.id AS INTEGER), tb_metodologia.cod_metodo, "
                    + "tb_metodologia.metodo, tb_metodologia.versao, "
                    + "CAST (count(tb_plano_analise.id) AS INTEGER) "
                    + "FROM tb_metodologia "
                    + "LEFT JOIN tb_plano_analise "
                    + "ON tb_metodologia.id = tb_plano_analise.metodologia_id "
                    + "GROUP BY tb_metodologia.id "
                    + "ORDER BY tb_metodologia.cod_metodo";
            Query query = em.createNativeQuery(sql);
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        } finally {
            em.close();
        }
    }

    public List<PlanoAnalise> readPlanoAnaliseAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PlanoAnalise> mtdAnalises = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_plano_analise_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_plano_analise_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_plano_analise_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_plano_analise_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                PlanoAnalise mtdAnalise = new PlanoAnalise();
                Analise analise = new Analise();
                AnaliseTipo analiseTipo = new AnaliseTipo();
                Setor setor = new Setor();
                Metodologia mtd = new Metodologia();
                Audit audit = new Audit();
                mtdAnalise.setId(rs.getLong("id"));
                analise.setId(rs.getLong("analise_id"));
                mtdAnalise.setAnalise(analise);
                mtdAnalise.setAnalise_MOD(rs.getBoolean("analise_MOD"));
                analiseTipo.setId(rs.getLong("analise_tipo_id"));
                mtdAnalise.setAnaliseTipo(analiseTipo);
                mtdAnalise.setAnalise_MOD(rs.getBoolean("analisetipo_MOD"));
                setor.setId(rs.getLong("setor_id"));
                mtdAnalise.setSetor(setor);
                mtd.setId(rs.getLong("metodologia_id"));
                mtdAnalise.setMetodologia(mtd);
                mtdAnalise.setSetor_MOD(rs.getBoolean("setor_MOD"));
                mtdAnalise.setDescricao(rs.getString("descricao"));
                mtdAnalise.setDescricao_MOD(rs.getBoolean("descricao_MOD"));
                mtdAnalise.setLeadTimeMax(rs.getInt("lead_time_max"));
                mtdAnalise.setLeadTimeMax_MOD(rs.getBoolean("leadtimemax_MOD"));
                mtdAnalise.setLeadTimeMedio(rs.getInt("lead_time_medio"));
                mtdAnalise.setLeadTimeMedio_MOD(rs.getBoolean("leadtimemedio_MOD"));
                mtdAnalise.setLeadTimeMin(rs.getInt("lead_time_min"));
                mtdAnalise.setLeadTimeMin_MOD(rs.getBoolean("leadtimemin_MOD"));
                mtdAnalise.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                mtdAnalise.setAudit(audit);
                mtdAnalises.add(mtdAnalise);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return mtdAnalises;
    }

}
