
package classescomunicacao.ModelJogo;

import classescomunicacao.ModelJogo.States.IStates;
import java.util.Observable;

/** 
 * @author Jose Marinho
 *
 */

public class ObservableGame extends Observable
{
    private GameModel gameModel;
    int idJogo;
    
    public ObservableGame(String nickName1,String nickName2, int idJogo)
    {
        this.idJogo = idJogo;
        gameModel = new GameModel(nickName1, nickName2, idJogo);
    }
    
    public int getIdJogo() {
        return idJogo;
    }

    public GameModel getGameModel()
    {
        return gameModel;
    }

    public void setGameModel(GameModel gameModel)
    {        
        this.gameModel = gameModel;
        
        setChanged();
        notifyObservers();
    }   
    
    public GameData getGameData() {
        return gameModel.getGameData();
    }

    public IStates getState()
    {
        return gameModel.getState();
    }        
    
     // Methods retrieve data from the game
    
    public String gridToString()
    {
        return gameModel.gridToString();
    }                    

    public int getNumPlayers()
    {
        return gameModel.getNumPlayers();
    }
    
    public Player getCurrentPlayer() 
    {
        return gameModel.getCurrentPlayer();
    }

    public Player getNotCurrentPlayer() 
    {
        return gameModel.getNotCurrentPlayer();
    }
    
    public Player getPlayer1()
    {
        return gameModel.getPlayer1();
    }

    public Player getPlayer2()
    {
        return gameModel.getPlayer2();
    }

    public Token getToken(int line, int column) 
    {
        return gameModel.getToken(line, column);
    }
    
    public String grelhaToString()
    {
        return gameModel.gridToString();
    }

    public int getNumCurrentPlayer()
    {
        return gameModel.getNumCurrentPlayer();
    }

    public String getCurrentPlayerName()
    {
        return gameModel.getCurrentPlayerName();
    }
    
    public boolean isOver() 
    {
        return gameModel.isOver();
    }
    
    public boolean hasWon(Player player) 
    {
        return gameModel.hasWon(player);
    }
    
    // Methods that are intended to be used by the user interfaces and that are delegated in the current state of the finite state machine 
    
    public void placeToken(int line, int column)
    {
        gameModel.placeToken(line, column);
        
        
        setChanged();
        notifyObservers();
    }

    public void returnToken(int line, int column)
    {
        gameModel.returnToken(line, column);
        
        setChanged();
        notifyObservers();
    }

    public void quit()
    {
        gameModel.quit();
        
        setChanged();
        notifyObservers();
    }

    public void Update() {
        setChanged();
        notifyObservers();
    }
}
