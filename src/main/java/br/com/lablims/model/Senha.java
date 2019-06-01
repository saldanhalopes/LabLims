package br.com.lablims.model;

import java.io.Serializable;

/**
 *
 * @author rafael.lopes
 */
public class Senha implements Serializable  {
    
    private Boolean senha;

    public Senha() {
    }

    public Boolean getSenha() {
        return senha;
    }

    public void setSenha(Boolean senha) {
        this.senha = senha;
    }

}
