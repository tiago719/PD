package Model;

import java.util.ArrayList;

/**
 *
 * @author edu_f
 */
public class JogosDecorrer{

    //key -> idJogo | value -> thread do Jogo
    ArrayList<ThreadJogos> jogos;

    public JogosDecorrer() {
        jogos = new ArrayList<>();
    }
    
    public void addNovoJogo(int id, String nick1, String nick2){
        jogos.add(new ThreadJogos(nick1, nick2, id, this));
    }
    
}
