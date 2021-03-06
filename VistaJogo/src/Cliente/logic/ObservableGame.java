package Cliente.logic;

import Cliente.ui.gui.EcraInicial.PedidoPar;
import ComunicacaoP.Comunicacao;
import ComunicacaoP.RecebeAtualizacoes;
import java.util.Observable;
import classescomunicacao.*;
import static classescomunicacao.Constantes.PEDIDO_RECUSADO;
import classescomunicacao.ModelJogo.GameData;
import classescomunicacao.ModelJogo.GameModel;
import classescomunicacao.ModelJogo.Player;
import classescomunicacao.ModelJogo.States.IStates;
import classescomunicacao.ModelJogo.Token;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jose Marinho
 *
 */
public class ObservableGame extends Observable {

    private GameModel gameModel;
    private Comunicacao comunicacao;
    private ArrayList<ClienteEnviar> clientes;
    private RecebeAtualizacoes threadRecebeAtualizacoes;
    private ArrayList<Mensagem> MensagensPrivadas;
    private FormarPar ParAtual = null;
    
    private int IdUtilizador = -1;
    private ArrayList<Jogo> historico;

    public int getIdUtilizador() {
        return IdUtilizador;
    }

    public void setIdUtilizador(int IdUtilizador) {
        this.IdUtilizador = IdUtilizador;
    }
    
    
    public FormarPar getParAtual() {
        return ParAtual;
    }

    public void setParAtual(FormarPar ParAtual) {
        this.ParAtual = ParAtual;
    }

    public ArrayList<Mensagem> getMensagensPrivadas() {
        return MensagensPrivadas;
    }

    public void setMensagensPrivadas(ArrayList<Mensagem> MensagensPrivadas) {
        this.MensagensPrivadas = MensagensPrivadas;
    }

    public ObservableGame(String ip, int Porto) {
        this.gameModel = null;
        comunicacao = new Comunicacao(this, ip, Porto);
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

    public ArrayList<ClienteEnviar> getClientes() {
        return clientes;
    }

    // Methods that are intended to be used by the user interfaces and that are delegated in the current state of the finite state machine 

    public void placeToken(int line, int column) {
        
//        gameModel.placeToken(line, column);
        comunicacao.novaJogada(line, column, this.gameModel.getIdJogo(), this.gameModel.getPlayer1().getName());

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
        if (ret > 0) {
            comunicacao.setNomeUtilizador(username);
            IdUtilizador = ret;
            threadRecebeAtualizacoes = new RecebeAtualizacoes(this, comunicacao.getObjectInputStream());
            threadRecebeAtualizacoes.start();
        }
        return ret;
    }

    public synchronized void setClientesLogados(ArrayList clientes) {
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

    public void LimpaBufMensagem() {
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

    public void logOut() {
        comunicacao.logOut();
    }

    public synchronized void PedePar(String Nikname) {
        comunicacao.PedePar(Nikname);
    }

    public synchronized int getSizePares() {
        return threadRecebeAtualizacoes.getPares().size();
    }

    public synchronized ArrayList<FormarPar> getPares() {
        return (ArrayList<FormarPar>) threadRecebeAtualizacoes.getPares();
    }

    public synchronized void SetPares(ArrayList<FormarPar> par) {
        threadRecebeAtualizacoes.setPares(par);
        
        setChanged();
        notifyObservers();
    }

    public synchronized void RemovePar(int i) {
        threadRecebeAtualizacoes.RemovePar(i);
    }

    public synchronized void EnviaConfirmacao(int i) {

        comunicacao.EnviaConfirmacaoPar(threadRecebeAtualizacoes.getPares().get(i));
    }

    public synchronized void EnviaConfirmacao(FormarPar pedidoPar, int resposta)
    {
        for(FormarPar p : threadRecebeAtualizacoes.getPares())
        {
            if(p==pedidoPar)
            {
                p.setAceite(resposta);
                comunicacao.EnviaConfirmacaoPar(p);
            }
        }
    }

   public synchronized void setPar(FormarPar formarPar) {

        ParAtual = formarPar;
        
        setChanged();
        notifyObservers();
    }

    public synchronized void EnviaInicioJogo() {
                
        if(ParAtual!=null)
            comunicacao.comunicaServidorJogo();
        
        try
        {
            sleep(1000);
        } catch (InterruptedException ex)
        {
            Logger.getLogger(ObservableGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        comunicacao.EnviaIniciodoJogo(ParAtual);
    }

    public synchronized void Desiste() {
        comunicacao.Desiste(ParAtual);
    }
    
    public int getIdJogo() {
        return this.gameModel.getIdJogo();
    }
    public synchronized void RemovePar(FormarPar pedidoPar)
    {
        threadRecebeAtualizacoes.RemovePar(pedidoPar);
    }

    public synchronized void RemoveAllPar()
    {
        for(FormarPar formarPar : threadRecebeAtualizacoes.getPedidosPares()) 
        {
            formarPar.setAceite(PEDIDO_RECUSADO);
            comunicacao.EnviaConfirmacaoPar(formarPar);
        }
        threadRecebeAtualizacoes.RemoveAllPar();
        
        setChanged();
        notifyObservers();
    }
    
    public String getUserName()
    {
        return comunicacao.getUserName();
    }

    public void removePares(ArrayList<FormarPar> temporario)
    {
        FormarPar formarPar;
        for(Iterator<FormarPar> iterator= threadRecebeAtualizacoes.getPares().iterator();iterator.hasNext();)
        {
            formarPar=iterator.next();
            for(FormarPar f:temporario)
            {
                if(formarPar==f)
                {
                    iterator.remove();
                }
            }
        }
    }

    public void abandonaPar()
    {
        comunicacao.abandonaPar(ParAtual);
    }

    public void setHistorico(ArrayList<Jogo> historico)
    {
        this.historico=historico;
    }

    public ArrayList<Jogo> getHistorico()
    {
        return historico;
    }
}
