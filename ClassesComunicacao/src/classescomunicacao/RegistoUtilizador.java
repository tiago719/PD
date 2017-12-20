/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classescomunicacao;

import java.io.Serializable;

/**
 *
 * @author Tiago Coutinho
 */
public class RegistoUtilizador implements Serializable
{
    static final long serialVersionUID = 1L;
    private String nome, username, password;

    public RegistoUtilizador(String nome, String username, String password)
    {
        this.nome = nome;
        this.username = username;
        this.password = password;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }   
}
