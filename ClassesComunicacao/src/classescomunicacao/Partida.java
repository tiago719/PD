/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classescomunicacao;

/**
 *
 * @author andre
 */
public class Partida {
    String Vencedor;
    String NikU1,NikU2;
    int Terminou;
    int Interrompido;

    public Partida(String Vencedor, String NikU1, String NikU2, int Terminou, int Interrompido) {
        this.Vencedor = Vencedor;
        this.NikU1 = NikU1;
        this.NikU2 = NikU2;
        this.Terminou = Terminou;
        this.Interrompido = Interrompido;
    }

    public String getVencedor() {
        return Vencedor;
    }

    public void setVencedor(String Vencedor) {
        this.Vencedor = Vencedor;
    }

    public String getNikU1() {
        return NikU1;
    }

    public void setNikU1(String NikU1) {
        this.NikU1 = NikU1;
    }

    public String getNikU2() {
        return NikU2;
    }

    public void setNikU2(String NikU2) {
        this.NikU2 = NikU2;
    }

    public int getTerminou() {
        return Terminou;
    }

    public void setTerminou(int Terminou) {
        this.Terminou = Terminou;
    }

    public int getInterrompido() {
        return Interrompido;
    }

    public void setInterrompido(int Interrompido) {
        this.Interrompido = Interrompido;
    }
    
    
}
