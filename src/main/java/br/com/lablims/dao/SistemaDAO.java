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

import javax.persistence.EntityManager;
import javax.persistence.Query;
import br.com.lablims.connection.ConnectionFactory;
import br.com.lablims.model.Sistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author rafael.lopes
 */
public class SistemaDAO extends GenenicoDAO<Sistema> {

    public static boolean isBancoDadosConectado() {
        EntityManager em = ConnectionFactory.em();
        boolean isConnected = false;
        try {
            String sql = "SELECT 1";
            Query query = em.createNativeQuery(sql);
            isConnected = query.getSingleResult().equals(1);
        } catch (Exception e) {
        } finally {
            em.close();
        }
        return isConnected;
    }

    public String infoBancoDados() {
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        StringBuilder sys = new StringBuilder();
        try {
            stmt = conn.prepareStatement("SELECT version() AS versao, "
                    + "current_schema AS schema, "
                    + "current_database() AS banco, "
                    + "inet_server_port() as port, "
                    + "inet_server_addr() as host");
            rs = stmt.executeQuery();
            while (rs.next()) {
                sys.append("Banco de Dados: ").append(rs.getString("host"));
                sys.append(":").append(rs.getString("port"));
                sys.append("\\").append(rs.getString("banco"));
                sys.append(" [").append(rs.getString("schema")).append("]\n");
                sys.append("Versão: ").append(rs.getString("versao"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro: " + ex);
        } finally {
            ConnectionFactory.closeConection(conn, stmt, rs);
        }
        return sys.toString();
    }
    
}
