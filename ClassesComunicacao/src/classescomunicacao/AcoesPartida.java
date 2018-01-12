/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classescomunicacao;

import java.io.Serializable;

/**
 *
 * @author edu_f
 */
public class AcoesPartida implements Serializable {

    static final long serialVersionUID = 1L;
    int Acao;
    int idPar;
    String UserName;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    /**
     * @param Acao 1: Novo Jogo | 2: Concluir Jogo | 3: Cancelar Jogo
     * @param UserName Username do Jogador que cria Acao
     * @param idPar ID da BD do PAR
     */
    public AcoesPartida(int Acao, int idPar, String UserName) {
        this.Acao = Acao;
        this.idPar = idPar;
        this.UserName = UserName;
    }

    public AcoesPartida(int Acao, int idPar) {
        this.Acao = Acao;
        this.idPar = idPar;
    }


    /**
     *
     * @return 1: Novo Jogo | 2: Concluir Jogo | 3: Cancelar Jogo
     */
    public int getAcao() {
        return Acao;
    }

    public int getIdPar() {
        return idPar;
    }

    
    
    
}
