package classescomunicacao;

import java.io.Serializable;

/**
 *
 * @author edu_f
 */
public class Jogadas implements Serializable{
    String nickname;
    int linha, coluna;
    boolean colocar;

    public Jogadas(String nickname, String peca, int linha, int coluna, boolean colocar) {
        this.nickname = nickname;
        this.linha = linha;
        this.coluna = coluna;
        this.colocar = colocar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }

    public boolean isColocar() {
        return colocar;
    }

    public void setColocar(boolean colocar) {
        this.colocar = colocar;
    }
    
    
    
}
