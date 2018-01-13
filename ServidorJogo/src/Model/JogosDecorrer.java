package Model;

import classescomunicacao.ModelJogo.GameModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author edu_f
 */
public class JogosDecorrer{

    //key -> idJogo | value -> thread do Jogo
    Map<Integer, ThreadJogos> jogos;

    public JogosDecorrer() {
        jogos = new HashMap<>();
    }
    
    public void addNovoJogo(int id, String nick1, String nick2, int idPar){
        jogos.put(idPar, new ThreadJogos(nick1, nick2, id, this, idPar));
    }

    GameModel getGameModel(int idPar) {
        return jogos.get(idPar).getGameModel();
    }
    
}
