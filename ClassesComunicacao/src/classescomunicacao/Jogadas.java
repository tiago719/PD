package classescomunicacao;

import java.io.Serializable;

/**
 *
 * @author edu_f
 */
public class Jogadas implements Serializable {

    static final long serialVersionUID = 1L;
    String nickname;
    int linha, coluna, idJogo;

    public Jogadas(String nickname, int linha, int coluna, int idJogo) {
        this.nickname = nickname;
        this.linha = linha;
        this.coluna = coluna;
        this.idJogo = idJogo;
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

    public int getIdJogo() {
        return idJogo;
    }

}
