package Model.ModelJogo.States;

import java.io.Serializable;

/*
 * List of expected events when taking into account all the states.
 */
public interface IStates extends Serializable
{
   IStates setNumberPlayers(int num);
   IStates setName(int num, String name);
   IStates startGame();
   IStates placeToken(int line, int column);
   IStates returnToken(int line, int column);
   IStates quit();
   IStates login(String username, String password);
   IStates register(String username, String nome, String password);
}