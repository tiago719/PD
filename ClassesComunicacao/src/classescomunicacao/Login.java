/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classescomunicacao;

import java.io.Serializable;

public class Login implements Serializable
{
    static final long serialVersionUID = 1L;
    private String nome, password;

    public Login(String nome, String password)
    {
        this.nome = nome;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }  
}
