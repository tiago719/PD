package classescomunicacao;
import java.rmi.*;

public interface RemoteInfoClientInterface extends Remote
{
    InfoJogo getDadosUpdated(InfoJogo dados) throws RemoteException;
}
