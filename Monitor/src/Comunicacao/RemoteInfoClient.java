package Comunicacao;
/**
 *
 * @author Jose'
 */

import classescomunicacao.InfoJogo;
import classescomunicacao.RemoteInfoClientInterface;
import classescomunicacao.RemoteInfoInterface;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

public class RemoteInfoClient extends UnicastRemoteObject implements RemoteInfoClientInterface
{

    public RemoteInfoClient() throws RemoteException 
    {
    }
    
       
    public static void main(String[] args) {
        
    }

    @Override
    public InfoJogo getDadosUpdated(InfoJogo dados) throws RemoteException {
            return dados;
    }
}
