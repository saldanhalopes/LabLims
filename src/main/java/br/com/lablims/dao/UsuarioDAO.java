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
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Grupo;
import br.com.lablims.model.Setor;
import br.com.lablims.model.Turno;
import br.com.lablims.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * O <code>UsuarioDAO</code> implementa o DAO para a classe Usuario
 *
 * @author rafae.lopes
 * @version 1.00
 * @see br.com.lablims.model.Usuario
 */
public class UsuarioDAO extends GenenicoDAO<Usuario> {
    
    public Usuario buscaUsuario(Usuario user) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Usuario.findById", Usuario.class)
                    .setParameter("id", user.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public Usuario buscaUsuarioLogin(Usuario user) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Usuario.findLogin", Usuario.class)
                    .setParameter("usuario", user.getUsuario())
                    .setParameter("pass", user.getPass())
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public Usuario buscaUsuarioByName(Usuario user) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Usuario.findByUsuario", Usuario.class)
                    .setParameter("usuario", user.getUsuario())
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public Usuario findByName(String user) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Usuario.findByUsuario", Usuario.class)
                    .setParameter("usuario", user)
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public void salvarFalhaLogin(Usuario user, Boolean falha) throws TransactionRequiredException {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, user.getId());
            usuario.setFailedAccessCount(falha
                    ? (usuario.getFailedAccessCount() == null ? 0
                            : usuario.getFailedAccessCount()) + 1 : 0);
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public void salvarBloqueioUsuario(Usuario user) throws TransactionRequiredException {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, user.getId());
            usuario.setLock(true);
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public void atualizarLoginUsuario(Usuario user) throws Exception {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            String update = "UPDATE Usuario u SET "
                    + "u.lastLogin = :lastLogin "
                    + "WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("lastLogin", new Date())
                    .setParameter("id", user.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public void atualizarLogoffUsuario(Usuario user) throws TransactionRequiredException {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            if (em.find(Usuario.class, user.getId()) == null) {
                throw new TransactionRequiredException("Erro ao Atualizar!");
            }
            String update = "UPDATE Usuario u SET "
                    + "u.lastLogout = :lastLogout "
                    + "WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("lastLogout", new Date())
                    .setParameter("id", user.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public void atualizarTrocaSenha(Usuario user) throws TransactionRequiredException {
        EntityManager em = ConnectionFactory.em();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, user.getId());
            usuario.setPass(user.getPass());
            usuario.setChangePass(false);
            usuario.setLastChangePass(new Date());
            em.merge(usuario);
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        } finally {
            em.close();
        }
    }
    
    public static Boolean checkUserIsExits(Usuario user) throws EntityNotFoundException, NoResultException {
        EntityManager em = ConnectionFactory.em();
        try {
            return em.createNamedQuery("Usuario.findByUsuario", Usuario.class)
                    .setParameter("usuario", user.getUsuario())
                    .getSingleResult().getId() > 0;
        } catch (NoResultException ex) {
            return false;
        } finally {
            em.close();
        }
    }
    
    public List<Usuario> readUsuarioAuditoria() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> users = new ArrayList<>();
        try {
            stmt = conn.prepareStatement("SELECT tba_usuario_auditoria.*, "
                    + "tbc_revinfo_auditoria.computador, "
                    + "tbc_revinfo_auditoria.ultima_modificacao, "
                    + "tbc_revinfo_auditoria.user_computador, "
                    + "tbc_revinfo_auditoria.modificado_por, "
                    + "tbc_revinfo_auditoria.motivo "
                    + "FROM tba_usuario_auditoria  "
                    + "INNER JOIN tbc_revinfo_auditoria "
                    + "ON tba_usuario_auditoria.REV = tbc_revinfo_auditoria.id "
                    + "ORDER BY tba_usuario_auditoria.REV DESC ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Usuario user = new Usuario();
                Turno turno = new Turno();
                Setor setor = new Setor();
                Grupo grupo = new Grupo();
                Audit audit = new Audit();
                user.setId(rs.getLong("id"));
                user.setUsuario(rs.getString("usuario"));
                user.setChangePass(rs.getBoolean("change_pass"));
                user.setLastChangePass(rs.getDate("last_change_pass"));
                user.setCracha(rs.getString("cracha"));
                user.setCreated(rs.getDate("created"));
                user.setEmail(rs.getString("email"));
                user.setLock(rs.getBoolean("lock"));
                user.setNome(rs.getString("nome"));
                turno.setId(rs.getLong("turno_id"));
                setor.setId(rs.getLong("setor_id"));
                grupo.setId(rs.getLong("grupo_id"));
                user.setTurno(turno);
                user.setSetor(setor);
                user.setGrupo(grupo);
                user.setUsuario_MOD(rs.getBoolean("usuario_MOD"));
                user.setChange_Pass_MOD(rs.getBoolean("cracha_MOD"));
                user.setCracha_MOD(rs.getBoolean("cracha_MOD"));
                user.setEmail_MOD(rs.getBoolean("email_MOD"));
                user.setLock_MOD(rs.getBoolean("lock_MOD"));
                user.setNome_MOD(rs.getBoolean("nome_MOD"));
                user.setTurno_MOD(rs.getBoolean("turno_MOD"));
                user.setSetor_MOD(rs.getBoolean("setor_MOD"));
                user.setGrupo_MOD(rs.getBoolean("grupo_MOD"));
                user.setPass_MOD(rs.getBoolean("pass_MOD"));
                user.setVersion(rs.getInt("version"));
                audit.setMOD(rs.getInt("REVTYPE"));
                audit.setComputador(rs.getString("computador"));
                audit.setUserComputador(rs.getString("user_computador"));
                audit.setUltimaModificacao(rs.getTimestamp("ultima_modificacao"));
                audit.setUltimaModificacaoPor(rs.getString("modificado_por"));
                audit.setMotivo(rs.getString("motivo"));
                user.setAudit(audit);
                users.add(user);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return users;
    }
    
}
