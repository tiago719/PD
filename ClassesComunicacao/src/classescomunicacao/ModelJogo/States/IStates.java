package classescomunicacao.ModelJogo.States;

import java.io.Serializable;

/*
 * List of expected events when taking into account all the states.
 */
public interface IStates extends Serializable
{
   IStates placeToken(int line, int column);
   IStates returnToken(int line, int column);
   IStates quit();
}
