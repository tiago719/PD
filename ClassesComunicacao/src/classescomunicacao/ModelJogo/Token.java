package classescomunicacao.ModelJogo;

import java.io.Serializable;

public class Token implements Serializable
{
    
    private Player player;

    public Token(Player player) 
    {
        this.player = player;
    }

    public Player getPlayer()
    {
        return player;
    }

    @Override
    public String toString()
    {
        return "" + player.getName().charAt(0);
    }
    
}
