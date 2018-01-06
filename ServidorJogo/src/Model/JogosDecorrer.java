package Model;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author edu_f
 */
public class JogosDecorrer{

    //key -> idJogo | value -> thread do Jogo
    Map<Integer, Thread> jogos;

    public JogosDecorrer() {
        jogos = new HashMap<>();
    }
    
    public void addNovoJogo(int id, String nick1, String nick2, Socket socket){
        jogos.put(id, new ThreadJogos(nick1, nick2, socket));
    }
    
    public Thread getJogoDecorrer(int id){
        return jogos.get(id);    
    }
    
    
}
