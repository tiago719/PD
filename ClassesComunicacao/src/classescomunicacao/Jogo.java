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
public class Jogo implements Serializable
{
    private String jogador1, jogador2, vencedor;
    private int estadoJogo;

    public Jogo(String jogador1, String jogador2, String vencedor, int estadoJogo)
    {
        this.jogador1 = jogador1;
        this.jogador2 = jogador2;
        this.vencedor = vencedor;
        this.estadoJogo = estadoJogo;
    }

    public String getJogador1()
    {
        return jogador1;
    }

    public void setJogador1(String jogador1)
    {
        this.jogador1 = jogador1;
    }

    public String getJogador2()
    {
        return jogador2;
    }

    public void setJogador2(String jogador2)
    {
        this.jogador2 = jogador2;
    }

    public String getVencedor()
    {
        return vencedor;
    }

    public void setVencedor(String vencedor)
    {
        this.vencedor = vencedor;
    }

    public int getEstadoJogo()
    {
        return estadoJogo;
    }

    public void setEstadoJogo(int estadoJogo)
    {
        this.estadoJogo = estadoJogo;
    }
    
}
