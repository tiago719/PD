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
    int idPar, idUser;

    public AcoesPartida(int Acao, int idPar, int idUser) {
        this.Acao = Acao;
        this.idPar = idPar;
        this.idUser = idUser;
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

    public int getIdUser() {
        return idUser;
    }
    
    
    
    
}
