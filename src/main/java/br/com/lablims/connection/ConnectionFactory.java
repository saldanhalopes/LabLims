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
package br.com.lablims.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * O <code>ConnectionFactory</code> gerencia a conexao com o banco de dados.
 *
 * @author rafae.lopes
 * @version 1.00
 */
public class ConnectionFactory {

    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();
    private static EntityManagerFactory factory;

    public ConnectionFactory() {

    }

    /**
     * Metodo implementa a conexao com o banco de dados via ojeto
     * <code>EntityManagerFactory</code>.
     *
     * @return EntityManagerFactory - Retorna um objeto EntityManagerFactory
     */
    public static EntityManagerFactory getFactory() {
        if (factory == null || !factory.isOpen()) {
            factory = Persistence.createEntityManagerFactory("LabLims_PU");
        }
        return factory;
    }

    /**
     * Cria um entity manager unico (se criar = true) para a thread e o retorna
     * em todas as demais chamadas
     *
     * @param criar boolean - Verifica se o thread retornara em todas as demais
     * chamadas
     * @return EntityManager - Retorna objeto do tipo EntityManager
     */
    public static EntityManager em(boolean criar) {
        EntityManager em = (EntityManager) threadLocal.get();
        if (em == null || !em.isOpen()) {
            if (criar) {
                em = getFactory().createEntityManager();
                threadLocal.set(em);
            }
        } else {
            if (!criar) {
                em.close();
                factory.close();
            }
        }
        return em;
    }

    /**
     * Cria um entity manager unico para a thread e o retorna em todas as
     * demais chamadas
     *
     * @return Boolean - Retorna verdadeiro
     */
    public static EntityManager em() {
        return em(true);
    }
    
    
    
    

    public static Connection getConnection() {
        String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String URL = "jdbc:sqlserver://PRTMC1939:1433;databaseName=db_prati_sgcq";
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, "db_sys", "Beast666");
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na ConexÃ£o: " + ex);
        }
    }

    public static void closeConection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro para fechar ConexÃ£o: " + ex);
        }
    }

    public static void closeConection(Connection conn, PreparedStatement stmt) {
        closeConection(conn);
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void closeConection(Connection conn, PreparedStatement stmt, ResultSet rs) {
        closeConection(conn, stmt);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
