package Model;

//import classescomunicacao.ModelJogo.ObservableGame;

import classescomunicacao.ModelJogo.GameModel;


/**
 *
 * @author edu_f
 */
public class ThreadJogos {

    private GameModel gameModel;
    private String nick1, nick2;
    Comunicacao comunicacao;
    int idJogo;
    JogosDecorrer jogosDecorrer;

    public ThreadJogos(String nick1, String nick2, int idJogo, JogosDecorrer jogosDecorrer, int idPar) {
        this.nick1 = nick1;
        this.nick2 = nick2;
        this.idJogo = idJogo;
        this.jogosDecorrer = jogosDecorrer;
        
        gameModel = new GameModel(nick1, nick2, idJogo);
        comunicacao = new Comunicacao(gameModel, idPar);
        comunicacao.start();
        
    }

    public GameModel getGameModel() {
        return gameModel;
    }
    
    
}
