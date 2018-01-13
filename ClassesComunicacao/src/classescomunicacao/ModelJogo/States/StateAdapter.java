package classescomunicacao.ModelJogo.States;

import classescomunicacao.ModelJogo.Constants;
import classescomunicacao.ModelJogo.GameData;
import java.io.Serializable;

public class StateAdapter implements IStates, Constants, Serializable
{
    private GameData game;

    public StateAdapter(GameData g)
    {
        this.game =g;
    }

    public GameData getGame() 
    {
        return game;
    }
    
    public void setGame(GameData game) 
    {
        this.game = game;
    }



    @Override
    public IStates placeToken(int linha, int coluna){ return this;}

    @Override
    public IStates returnToken(int linha, int coluna){ return this;}

    @Override
    public IStates quit(){ return this;}


    
}
