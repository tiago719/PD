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
public class FormarPar implements Serializable
{
    private String utilizadorQueFezPedido;
    private String utilizadorQueResponde;
    private int aceite;
    int IdPar;

    public int getIdPar() {
        return IdPar;
    }

    public void setIdPar(int IdPar) {
        this.IdPar = IdPar;
    }
    
    public String getUitlizadorQueFezPedido() {
        return utilizadorQueFezPedido;
    }

    public int getAceite() {
        return aceite;
    }

    public void setAceite(int aceite) {
        this.aceite = aceite;
    }
    
    public FormarPar(String Nik1Util, String Nik2Util, int aceite) {
        this.utilizadorQueFezPedido = Nik1Util;
        this.utilizadorQueResponde = Nik2Util;
        this.aceite = aceite;
    }

    public FormarPar(String Nik1Util, String Nik2Util) {
        this(Nik1Util,Nik2Util,0);
    }

    public void setUitlizadorQueFezPedido(String Nik1Util) {
        this.utilizadorQueFezPedido = Nik1Util;
    }

    public String getUtilizadorQueResponde() {
        return utilizadorQueResponde;
    }

    public void setUtilizadorQueResponde(String Nik2Util) {
        this.utilizadorQueResponde = Nik2Util;
    }
}
