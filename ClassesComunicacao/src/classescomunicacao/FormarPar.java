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
    public String Nik1Util;
    public String Nik2Util;
    boolean aceite = false;
    
    public String getNik1Util() {
        return Nik1Util;
    }

    public boolean isAceite() {
        return aceite;
    }

    public void setAceite(boolean aceite) {
        this.aceite = aceite;
    }

    public FormarPar(String Nik1Util, String Nik2Util) {
        this.Nik1Util = Nik1Util;
        this.Nik2Util = Nik2Util;
        aceite = false;
    }

    public void setNik1Util(String Nik1Util) {
        this.Nik1Util = Nik1Util;
    }

    public String getNik2Util() {
        return Nik2Util;
    }

    public void setNik2Util(String Nik2Util) {
        this.Nik2Util = Nik2Util;
    }
}
