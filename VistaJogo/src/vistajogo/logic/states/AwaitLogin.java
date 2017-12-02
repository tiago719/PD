/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistajogo.logic.states;

import vistajogo.logic.GameData;

/**
 *
 * @author Tiago Coutinho
 */
public class AwaitLogin extends StateAdapter
{  
    public AwaitLogin(GameData g)
    {
        super(g);
    }
    
    @Override
    public IStates login(String username, String password)
    {
        throw new UnsupportedOperationException("Nao implementado");
    }
    
    @Override
    public IStates register(String username, String nome, String password)
    {
        throw new UnsupportedOperationException("Nao implementado");
    }    
}
