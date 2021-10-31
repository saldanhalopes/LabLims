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
package br.com.cristalia.biblioteca.dao;

import br.com.cristalia.biblioteca.audit.Audit;
import br.com.cristalia.biblioteca.connection.ConnectionFactory;
import br.com.cristalia.biblioteca.model.Arquivos;
import br.com.cristalia.biblioteca.model.Equipamento;
import br.com.cristalia.biblioteca.model.EscalaMedida;
import br.com.cristalia.biblioteca.model.Setor;
import br.com.cristalia.biblioteca.model.TipoEquipamento;
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
import javax.swing.JOptionPane;

/**
 *
 * @author rafael
 */
public class EquipamentoDAO extends GenenicoDAO<Equipamento> {

    public Equipamento findByTag(String tag) throws NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Equipamento.findTag", Equipamento.class)
                    .setParameter("tag", tag)
                    .getSingleResult();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Equipamento> findAll() throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Equipamento.findAll", Equipamento.class)
                    .getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Equipamento> findTagByTipo(String tipo) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            Query myQuery = em.createQuery("SELECT eq FROM Equipamento eq "
                    + "INNER JOIN eq.tipo tp "
                    + "WHERE tp.tipo = :t "
                    + "ORDER BY tp.tipo ASC")
                    .setParameter("t", tipo);
            return myQuery.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Equipamento> findTagByTipoSetor(String tipo, Long setor_id) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            Query myQuery = em.createQuery("SELECT eq FROM Equipamento eq "
                    + "INNER JOIN eq.tipo tp "
                    + "INNER JOIN eq.setor st "
                    + "WHERE tp.tipo = :t "
                    + "AND st.id = :st_id "
                    + "ORDER BY tp.tipo ASC")
                    .setParameter("t", tipo)
                    .setParameter("st_id", setor_id);
            return myQuery.getResultList();
        } catch (NoResultException ex) {
            return null;
        } finally {
            em.close();
        }
    }

    public Boolean checkCromatografoIsExits(String tag) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Equipamento.findTag", Equipamento.class)
                    .setParameter("tag", tag)
                    .getSingleResult().getTag() != null;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }

    public List<Equipamento> readAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Equipamento> equips = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_cromatografo_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_cromatografo_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_cromatografo_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_cromatografo_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Equipamento equip = new Equipamento();
                Setor setor = new Setor();
                EscalaMedida escala = new EscalaMedida();
                Arquivos certificado = new Arquivos();
                Arquivos manual = new Arquivos();
                TipoEquipamento tipo = new TipoEquipamento();
                Audit audit = new Audit();
                equip.setId(rs.getLong("id"));
                equip.setTag(rs.getString("tag"));
                equip.setDescricao(rs.getString("descricao"));
                equip.setFabricante(rs.getString("fabricante"));
                equip.setModelo(rs.getString("modelo"));
                tipo.setId(rs.getLong("tipo_equipamento_id"));
                equip.setTipo(tipo);
                equip.setObs(rs.getString("obs"));
                equip.setSerialNumber(rs.getString("serial_number"));
                setor.setId(rs.getLong("setor_id"));
                equip.setSetor(setor);
                equip.setUltimaCalibracao(rs.getDate("ultima_calibracao"));
                equip.setProximaCalibracao(rs.getDate("proxima_calibracao"));
                certificado.setId(rs.getLong("certificado_id"));
                equip.setCertificado(certificado);
                manual.setId(rs.getLong("manual_id"));
                equip.setManual(manual);
                escala.setId(rs.getLong("escala_id"));
                equip.setEscala(escala);
                equip.setProcedimento(rs.getString("procedimento"));
                equip.setAtivo(rs.getBoolean("ativo"));
                equip.setTag_MOD(rs.getBoolean("tag_MOD"));
                equip.setDescricao_MOD(rs.getBoolean("descricao_MOD"));
                equip.setFabricante_MOD(rs.getBoolean("fabricante_MOD"));
                equip.setModelo_MOD(rs.getBoolean("modelo_MOD"));
                equip.setUltimaCalibracao_MOD(rs.getBoolean("ultimacalibracao_MOD"));
                equip.setProximaCalibracao_MOD(rs.getBoolean("proximapalibracao_MOD"));
                equip.setCertificado_MOD(rs.getBoolean("certificado_MOD"));
                equip.setManual_MOD(rs.getBoolean("manual_MOD"));
                equip.setEscala_MOD(rs.getBoolean("escala_MOD"));
                equip.setProcedimento_MOD(rs.getBoolean("procedimento_MOD"));
                equip.setAtivo_MOD(rs.getBoolean("Ativo_MOD"));
                equip.setTipo_MOD(rs.getBoolean("tipo_MOD"));
                equip.setSerial_Number_MOD(rs.getBoolean("serialNumber_MOD"));
                equip.setObs_MOD(rs.getBoolean("obs_MOD"));
                equip.setSetor_MOD(rs.getBoolean("setor_MOD"));
                equip.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                equip.setAudit(audit);
                equips.add(equip);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return equips;
    }

}
