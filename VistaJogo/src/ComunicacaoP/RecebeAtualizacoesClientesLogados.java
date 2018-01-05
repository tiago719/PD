/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ComunicacaoP;

import Cliente.logic.ObservableGame;
import classescomunicacao.ClienteEnviar;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Tiago Coutinho
 */
public class RecebeAtualizacoesClientesLogados extends Thread
{
    ObservableGame observableGame;
    ObjectInputStream in;
    
    public RecebeAtualizacoesClientesLogados(ObservableGame observableGame, ObjectInputStream in)
    {
        this.observableGame=observableGame;
        this.in=in;
    }
    
    @Override
    public void run()
    {
        int a=0;
        while(a==0)//TODO: define condicao de paragem
        {
            try
            {
                ArrayList<ClienteEnviar> returnedObject=(ArrayList<ClienteEnviar>)in.readObject();
                observableGame.setClientesLogados(returnedObject);                
            } 
            catch (Exception e)
            {
                System.out.println(e);
            } 
        }  
    }
}
