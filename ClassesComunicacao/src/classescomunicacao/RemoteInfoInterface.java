package classescomunicacao;

import java.rmi.*;

public interface RemoteInfoInterface extends Remote
{
    public void GetData( RemoteInfoClientInterface clientReference) throws RemoteException;
    InfoJogo getDadosJogo() throws RemoteException;
}