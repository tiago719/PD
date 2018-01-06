package classescomunicacao;

import java.io.Serializable;

/**
 *
 * @author edu_f
 */
public class Jogadas implements Serializable{
    String nickname;
    String peca;
    int linha, coluna;
    boolean colocar;

    public Jogadas(String nickname, String peca, int linha, int coluna, boolean colocar) {
        this.nickname = nickname;
        this.peca = peca;
        this.linha = linha;
        this.coluna = coluna;
        this.colocar = colocar;
    }
    
    
}
