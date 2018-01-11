package Cliente.logic;

import ComunicacaoP.Comunicacao;
import ComunicacaoP.RecebeAtualizacoes;
import java.util.Observable;
import classescomunicacao.*;
import classescomunicacao.ModelJogo.GameData;
import classescomunicacao.ModelJogo.GameModel;
import classescomunicacao.ModelJogo.Player;
import classescomunicacao.ModelJogo.States.IStates;
import classescomunicacao.ModelJogo.Token;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jose Marinho
 *
 */
public class ObservableGame extends Observable {
    
    private GameModel gameModel;
    private Comunicacao comunicacao;
    private ArrayClienteEnviar clientes;
    private RecebeAtualizacoes threadRecebeAtualizacoes;
    
    private ArrayList<Mensagem> MensagensPrivadas;
    
    public ArrayList<Mensagem> getMensagensPrivadas() {
        return MensagensPrivadas;
    }
    
    public void setMensagensPrivadas(ArrayList<Mensagem> MensagensPrivadas) {
        this.MensagensPrivadas = MensagensPrivadas;
    }

    public ObservableGame() {
        this.gameModel = null;
        comunicacao = new Comunicacao();
    }
    
    public GameModel getGameModel() {
        return gameModel;
    }
    
    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
        
        setChanged();
        notifyObservers();
    }
    
    public GameData getGameData() {
        return gameModel.getGameData();
    }
    
    public IStates getState() {
        return gameModel.getState();
    }

    // Methods retrieve data from the game
    public String gridToString() {
        return gameModel.gridToString();
    }
    
    public int getNumPlayers() {
        return gameModel.getNumPlayers();
    }
    
    public Player getCurrentPlayer() {
        return gameModel.getCurrentPlayer();
    }
    
    public Player getNotCurrentPlayer() {
        return gameModel.getNotCurrentPlayer();
    }
    
    public Player getPlayer1() {
        return gameModel.getPlayer1();
    }
    
    public Player getPlayer2() {
        return gameModel.getPlayer2();
    }
    
    public Token getToken(int line, int column) {
        return gameModel.getToken(line, column);
    }
    
    public String grelhaToString() {
        return gameModel.gridToString();
    }
    
    public int getNumCurrentPlayer() {
        return gameModel.getNumCurrentPlayer();
    }
    
    public String getCurrentPlayerName() {
        return gameModel.getCurrentPlayerName();
    }
    
    public boolean isOver() {
        return gameModel.isOver();
    }
    
    public boolean hasWon(Player player) {
        return gameModel.hasWon(player);
    }
    
    public ArrayClienteEnviar getClientes() {
        return clientes;
    }

    // Methods that are intended to be used by the user interfaces and that are delegated in the current state of the finite state machine 
    public void setNumberPlayers(int num) {
        gameModel.setNumberPlayers(num);
        
        setChanged();
        notifyObservers();
    }
    
    public void setPlayerName(int num, String name) {
        gameModel.setPlayerName(num, name);
        
        setChanged();
        notifyObservers();
    }
    
    public void startGame() {
        gameModel.startGame();
        
        setChanged();
        notifyObservers();
    }
    
    public void placeToken(int line, int column) {
        gameModel.placeToken(line, column);
        
        setChanged();
        notifyObservers();
    }
    
    public void returnToken(int line, int column) {
        gameModel.returnToken(line, column);
        
        setChanged();
        notifyObservers();
    }
    
    public void quit() {
        gameModel.quit();
        
        setChanged();
        notifyObservers();
    }
    
    public int Regista(String username, String email, String password) {
        return comunicacao.registo(username, email, password);
    }
    
    public int Login(String username, String password) {
        int ret = comunicacao.login(username, password);
        if (ret == 1) {
//MensagensPrivadas = DevolveMensagens();
            comunicacao.setNomeUtilizador(username);
            threadRecebeAtualizacoes = new RecebeAtualizacoes(this, comunicacao.getObjectInputStream());
            threadRecebeAtualizacoes.start();
        }
        return ret;
    }
    
    public void setClientesLogados(ArrayClienteEnviar clientes) {
        this.clientes = clientes;
        setChanged();
        notifyObservers();
    }
    
    public void EnviaSMSTodos(String sms) {
        comunicacao.EnviaSMSTodos(sms);
    }

    public void EnviaSMS(String sms, String dest) {
        comunicacao.EnviaSMSDestinatario(sms, dest);
    }
    

    public Mensagem GetSMS() {
        return threadRecebeAtualizacoes.getMensagem();
    }   
    
    public void LimpaBufMensagem()
    {
        threadRecebeAtualizacoes.LimpaMensagem();
    }
  
    public void Update() {
        setChanged();
        notifyObservers();
    }
    
    public ArrayList<Mensagem> DevolveMensagens() {
        try {
            return comunicacao.RecebeTodasMensagens();
        } catch (IOException ex) {
            Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void logOut()
    {
        comunicacao.logOut();
    }
    
    public void PedePar(String Nikname)
    {
        comunicacao.PedePar(Nikname);
    }
    
    public int getSizePares()
    {
        return threadRecebeAtualizacoes.getPares().size();
    }
    
    public ArrayList<FormarPar> getPares()
    {
        return (ArrayList<FormarPar>) threadRecebeAtualizacoes.getPares();
    }
    
    public void SetPares(ArrayList<FormarPar> par)
    {
        threadRecebeAtualizacoes.setPares(par);
    }

    public void RemovePar(int i) {
        threadRecebeAtualizacoes.RemovePar(i);
    }

    public void EnviaConfirmacao(int i) {
        
        comunicacao.EnviaConfirmacaoPar(threadRecebeAtualizacoes.getPares().get(i));
    }
}
