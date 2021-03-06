package classescomunicacao.ModelJogo;

import classescomunicacao.ModelJogo.States.AwaitBeginning;
import classescomunicacao.ModelJogo.States.AwaitPlacement;
import classescomunicacao.ModelJogo.States.IStates;
import java.io.Serializable;

/**
 *
 * @author Jose Marinho
 */
public class GameModel implements Serializable {

    private GameData gameData;
    private IStates state;
    int idJogo;//, idPar;

    public GameModel(String nickName1, String nickName2, int idJogo/*, int idPar*/) {
        this.idJogo = idJogo;
//        this.idPar = idPar;
        gameData = new GameData(nickName1, nickName2);
        
        setState(new AwaitPlacement(gameData));
        gameData.initialize();
        
    }

    public int getIdJogo() {
        return idJogo;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public IStates getState() {
        return state;
    }

    private void setState(IStates state) {
        this.state = state;
    }

    // Methods retrieve data from the game
    public String gridToString() {
        return gameData.gridToString();
    }

    public int getNumPlayers() {
        return gameData.getNumPlayers();
    }

    public Player getCurrentPlayer() {
        return gameData.getCurrentPlayer();
    }

    public Player getNotCurrentPlayer() {
        return gameData.getNotCurrentPlayer();
    }

    public Player getPlayer1() {
        return gameData.getPlayer1();
    }

    public Player getPlayer2() {
        return gameData.getPlayer2();
    }

    public Token getToken(int line, int column) {
        return gameData.getToken(line, column);
    }

    public String grelhaToString() {
        return gameData.gridToString();
    }

    public int getNumCurrentPlayer() {
        return gameData.getNumCurrentPlayer();
    }

    public String getCurrentPlayerName() {
        return gameData.getCurrentPlayer().getName();
    }

    public boolean isOver() {
        return gameData.isOver();
    }

    public boolean hasWon(Player player) {
        return gameData.hasWon(player);
    }

    // Methods that are intended to be used by the user interfaces and that are delegated in the current state of the finite state machine 

    public synchronized void placeToken(int line, int column) {
        setState(getState().placeToken(line, column));
    }

    public void returnToken(int line, int column) {
        setState(getState().returnToken(line, column));
    }

    public void quit() {
        setState(getState().quit());
    }

}
