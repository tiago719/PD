
package Cliente.logic;

import ComunicacaoP.Comunicacao;
import static ComunicacaoP.Comunicacao.TIMEOUT;
import static Cliente.logic.Constants.PORTO_SERVIDOR_GESTAO;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Cliente.logic.states.IStates;
import ComunicacaoP.RecebeAtualizacoesClientesLogados;
import classescomunicacao.*;
import java.io.IOException;
import java.io.InputStream;
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
    private RecebeAtualizacoesClientesLogados threadRecebeAtualizacoesClientesLogados;
//    private ThreadChat tchat;
    
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
        int ret=comunicacao.login(username, password);
        if(ret==1)
        {
            threadRecebeAtualizacoesClientesLogados=new RecebeAtualizacoesClientesLogados(this,comunicacao.getObjectInputStream());
            threadRecebeAtualizacoesClientesLogados.start();
        }
        return ret;
    }
    
    public void setClientesLogados(ArrayList<ClienteEnviar> clientes)
    {
        this.clientes=clientes;                
        setChanged();
        notifyObservers();
    }
    
//     public void EnviaSMSTodos(String sms) {
//        comunicacao.EnviaSMSTodos(sms);
//    }
//
//    public void EnviaSMS(String sms, String dest) {
//        comunicacao.EnviaSMSDestinatario(sms, dest);
//    }
//    
//    public Mensagem GetSMS()
//    {
//        return tchat.getMensagem();
//    }
//    
//    public void IniciaThread()
//    {
//        tchat = new ThreadChat();
//        tchat.Update(this);
//        tchat.start();     
//    }
    
    public void Update() {
        setChanged();
        notifyObservers();
    }
}
