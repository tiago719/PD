package Model;

import classescomunicacao.ModelJogo.ObservableGame;

/**
 *
 * @author edu_f
 */
public class ThreadJogos {

    private ObservableGame observableGame;
    private String nick1, nick2;
    Comunicacao comunicacao;
    int idJogo;
    JogosDecorrer jogosDecorrer;

    public ThreadJogos(String nick1, String nick2, int idJogo, JogosDecorrer jogosDecorrer) {
        this.nick1 = nick1;
        this.nick2 = nick2;
        this.idJogo = idJogo;
        this.jogosDecorrer = jogosDecorrer;
        
        observableGame = new ObservableGame(nick1, nick2, idJogo);
        comunicacao = new Comunicacao(observableGame);
        
    }
}
