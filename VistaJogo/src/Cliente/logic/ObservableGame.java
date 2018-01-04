
package Cliente.logic;

import static Cliente.logic.Comunicacao.TIMEOUT;
import static Cliente.logic.Constants.PORTO_SERVIDOR_GESTAO;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Cliente.logic.states.IStates;
import classescomunicacao.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;

/** 
 * @author Jose Marinho
 *
 */

public class ObservableGame extends Observable
{
    private GameModel gameModel;
    private Comunicacao comunicacao;
    private ArrayList<ClienteEnviar> clientes;
    
    public ObservableGame()
    {
        gameModel = new GameModel();
        comunicacao=new Comunicacao();
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
    
    public ArrayList<ClienteEnviar> getClientes()
    {
        return clientes;
    }
    
    // Methods that are intended to be used by the user interfaces and that are delegated in the current state of the finite state machine 
    
    public void setNumberPlayers(int num)
    {
        gameModel.setNumberPlayers(num);
        
        setChanged();
        notifyObservers();
    }

    public void setPlayerName(int num, String name) 
    {
        gameModel.setPlayerName(num, name);
        
        setChanged();
        notifyObservers();
    }

    public void startGame()
    {
        gameModel.startGame();
        
        setChanged();
        notifyObservers();
    }

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

    public int Regista(String username, String email, String password) {
        return comunicacao.registo(username, email, password);
    }

    public int Login(String username, String password) {
        return comunicacao.login(username, password);
    }
    
    public void alteracaoListaClientes()
    {
        Socket socket = null;
        int a=0;
        while(a==0)//TODO: define condicao de paragem
        {
            try
            {
                socket = new Socket(Constants.HOST_SERVIDOR_GESTAO, PORTO_SERVIDOR_GESTAO);
                socket.setSoTimeout(TIMEOUT);

                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

                ArrayList<ClienteEnviar> returnedObject=(ArrayList<ClienteEnviar>)in.readObject();

                setChanged();
                notifyObservers();
                
            } 
            catch (Exception e)
            {
                System.out.println(e);
            } 
        }  
        if (socket != null)
        {
            try
            {
                socket.close();
            } 
            catch (IOException ex)
            {
                System.out.println("Erro a fechar o socket para receber atualizacoes de clientes.");
                System.out.println(ex);
            }
        }
    }
}
