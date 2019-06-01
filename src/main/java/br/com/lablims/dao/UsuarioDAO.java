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

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TransactionRequiredException;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Usuario;

/**
 * O <code>UsuarioDAO</code> implementa o DAO para a classe Usuario
 *
 * @author rafae.lopes
 * @version 1.00
 * @see br.com.lablims.model.Usuario
 */
public class UsuarioDAO extends GenenicoDAO<Usuario> {

    public Usuario buscaUsuario(EntityManager em, Usuario user) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            return em.createNamedQuery("Usuario.findById", Usuario.class)
                    .setParameter("id", user.getId())
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public Usuario buscaUsuarioLogin(EntityManager em, Usuario user) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            return em.createNamedQuery("Usuario.findLogin", Usuario.class)
                    .setParameter("usuario", user.getUsuario())
                    .setParameter("pass", user.getPass())
                    .getSingleResult();
        } catch (Exception ex) {
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public Usuario buscaUsuarioByName(EntityManager em, Usuario user) throws EntityNotFoundException, NoResultException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            return em.createNamedQuery("Usuario.findByUsuario", Usuario.class)
                    .setParameter("usuario", user.getUsuario())
                    .getSingleResult();
        } catch (NoResultException ex) {
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public void salvarFalhaLogin(EntityManager em, Usuario user, Boolean falha) throws TransactionRequiredException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            em.getTransaction().begin();
            if (em.find(Usuario.class, user.getId()) == null) {
                throw new TransactionRequiredException("Erro ao Atualizar!");
            }
            String update = "UPDATE Usuario u SET u.failed_access_count = :failed_access_count WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("failed_access_count", falha
                                    ? (user.getFailed_access_count() == null ? 0
                                            : user.getFailed_access_count()) + 1 : 0)
                    .setParameter("id", user.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public void salvarBloqueioUsuario(EntityManager em, Usuario user) throws TransactionRequiredException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            em.getTransaction().begin();
            if (em.find(Usuario.class, user.getId()) == null) {
                throw new TransactionRequiredException("Erro ao Atualizar!");
            }
            String update = "UPDATE Usuario u SET u.lock = :lock WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("lock", true)
                    .setParameter("id", user.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public void atualizarLoginUsuario(EntityManager em, Usuario user) throws Exception {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            em.getTransaction().begin();
            String update = "UPDATE Usuario u SET "
                    + "u.lastlogin = :lastlogin "
                    + "WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("lastlogin", new Date())
                    .setParameter("id", user.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public void atualizarLogoffUsuario(EntityManager em, Usuario user) throws TransactionRequiredException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            em.getTransaction().begin();
            if (em.find(Usuario.class, user.getId()) == null) {
                throw new TransactionRequiredException("Erro ao Atualizar!");
            }
            String update = "UPDATE Usuario u SET "
                    + "u.lastlogout = :lastlogout "
                    + "WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("lastlogout", new Date())
                    .setParameter("id", user.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public void atualizarTrocaSenha(EntityManager em, Usuario user) throws TransactionRequiredException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            em.getTransaction().begin();
            if (em.find(Usuario.class, user.getId()) == null) {
                throw new TransactionRequiredException("Erro ao Atualizar!");
            }
            String update = "UPDATE Usuario u SET "
                    + "u.pass = :pass, "
                    + "u.change_pass = :change_pass "
                    + "WHERE u.id = :id";
            em.createQuery(update)
                    .setParameter("pass", user.getPass())
                    .setParameter("change_pass", false)
                    .setParameter("id", user.getId())
                    .executeUpdate();
            em.getTransaction().commit();
        } catch (TransactionRequiredException ex) {
            em.getTransaction().rollback();
            throw new TransactionRequiredException("Erro ao Atualizar!");
        }
    }

    public static Boolean checkUserIsExits(EntityManager em, Usuario user) throws EntityNotFoundException, NoResultException {
        if (!em.isOpen()) {
            em = ConnectionFactory.em(true);
        }
        try {
            return em.createNamedQuery("Usuario.findByUsuario", Usuario.class)
                    .setParameter("usuario", user.getUsuario())
                    .getSingleResult().getId() > 0;
        } catch (NoResultException ex) {
            return false;
        }
    }

}
