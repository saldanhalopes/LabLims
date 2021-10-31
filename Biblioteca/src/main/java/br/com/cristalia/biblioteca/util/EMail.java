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
package br.com.cristalia.biblioteca.util;

import java.net.MalformedURLException;
import javax.swing.JOptionPane;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public final class EMail {

    private String e_mail = "rafael.lopes@pratidonaduzzi.com.br";
    private String name = "CQ_Desktop";
    private String host = "smtp.pratidonaduzzi.com.br";
    private String authentication = "Beast666@01";

    
    
    /**
     * envia email simples(somente texto)
     *
     * @throws EmailException
     */
    public void enviaEmailSimples() throws EmailException {
        SimpleEmail email = new SimpleEmail();
        email.setHostName("smtp.pratidonaduzzi.com.br"); // o servidor SMTP para envio do e-mail
        email.addTo("rafael.lopes@pratidonaduzzi.com.br", "Rafael"); //destinatário
        email.setFrom(e_mail, name); // remetente
        email.setSubject("Teste Email simples"); // assunto do e-mail
        email.setMsg("Este é um email enviado automaticamente pelo "
                + "Sistama CQ-Desktop para efetuar teste de "
                + "configurações de sua conta. "); //conteudo do e-mail
        email.setAuthentication(e_mail, authentication);
	//email.setSmtpPort(465);
        //email.setSSL(true);
        //email.setTLS(true);
        email.send();
        JOptionPane.showMessageDialog(null, "email enviado com sucesso!");
    }

    /**
     * envia email com arquivo anexo
     *
     * @throws EmailException
     */
    public void enviaEmailComAnexo(String arquivo, String nome_arquivo, String titulo, 
            String destinatario, String usuario, String texto) throws EmailException {
        // cria o anexo 1.
        EmailAttachment anexo1 = new EmailAttachment();
        anexo1.setPath(arquivo); //caminho do arquivo 
        anexo1.setDisposition(EmailAttachment.ATTACHMENT);
        anexo1.setDescription(titulo);
        anexo1.setName(nome_arquivo);
        // configura o email
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName(host); // o servidor SMTP para envio do e-mail
        email.addTo(destinatario, usuario); //destinatário
        email.setFrom(e_mail, name); // remetente
        email.setSubject(titulo); // assunto do e-mail
        email.setMsg(texto); //conteudo do e-mail
        email.setAuthentication(e_mail, authentication);
        // adiciona arquivo(s) anexo(s)
        email.attach(anexo1);
        // envia o email
        email.send();
    }

    /**
     * Envia email no formato HTML
     *
     * @throws EmailException
     * @throws MalformedURLException
     */
    public void enviaEmailFormatoHtml() throws EmailException, MalformedURLException {
//		HtmlEmail email = new HtmlEmail();
        // adiciona uma imagem ao corpo da mensagem e retorna seu id
//		URL url = new URL("http://www.apache.org/images/asf_logo_wide.gif");
//		String cid = email.embed(url, "Apache logo");	
//		// configura a mensagem para o formato HTML
//		email.setHtmlMsg("&lt;html&gt;Logo do Apache - <img >&lt;/html&gt;");
//		// configure uma mensagem alternativa caso o servidor não suporte HTML
//		email.setTextMsg("Seu servidor de e-mail não suporta mensagem HTML");
//		email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
//		email.addTo("teste@gmail.com", "Guilherme"); //destinatário
//		email.setFrom("teste@gmail.com", "Eu"); // remetente
//		email.setSubject("Teste -&gt; Html Email"); // assunto do e-mail
//		email.setMsg("Teste de Email HTML utilizando commons-email"); //conteudo do e-mail
//		email.setAuthentication("teste", "xxxxx");
//		email.setSmtpPort(465);
//		email.setSSL(true);
//		email.setTLS(true);
//		// envia email
//		email.send();
    }
    /**
     * @param args
     * @throws EmailException
     * @throws MalformedURLException
     */

}
