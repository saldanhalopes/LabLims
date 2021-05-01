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
package br.com.lablims.util;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.InputStream;

/**
 *
 * @author rafael.lopes
 */
public class ReadXMLFile {

    public static void readConfig() {
        try {
            InputStream file = Thread.currentThread().getClass().getResourceAsStream("/xml/config.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            Node nNode = doc.getElementsByTagName("config").item(0);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                System.setProperty("JDBC_HOST", eElement.getElementsByTagName("JDBC_HOST").item(0).getTextContent());
                System.setProperty("JDBC_PORT", eElement.getElementsByTagName("JDBC_PORT").item(0).getTextContent());
                System.setProperty("JDBC_DATA", eElement.getElementsByTagName("JDBC_DATA").item(0).getTextContent());
                System.setProperty("JDBC_USER", eElement.getElementsByTagName("JDBC_USER").item(0).getTextContent());
                System.setProperty("JDBC_PASSWORD", eElement.getElementsByTagName("JDBC_PASSWORD").item(0).getTextContent());
                System.setProperty("VERSION", eElement.getElementsByTagName("VERSION").item(0).getTextContent());

                StringBuilder sb = new StringBuilder();
                sb.append("jdbc:postgresql://"); // Drive Postgres
                sb.append(Cript.decodifica(System.getProperty("JDBC_HOST"))).append(":"); // Endereço Servidor
                sb.append(Cript.decodifica(System.getProperty("JDBC_PORT"))).append("/");// Porta
                sb.append(Cript.decodifica(System.getProperty("JDBC_DATA"))); // Banco de dados
                System.setProperty("JDBC_URL", sb.toString());

            }
        } catch (Exception e) {
            System.out.println("Erro de Leitura :" + e);
        }
    }
}
