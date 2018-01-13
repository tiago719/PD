package servidorgestao.ComunicacaoC;

/**
 *
 * @author Jose'
 */

import BaseDados.PesquisasGestaoUtilizadores;
import classescomunicacao.ClienteEnviar;
import classescomunicacao.FormarPar;
import classescomunicacao.InfoJogo;
import classescomunicacao.Partida;
import classescomunicacao.RemoteInfoClientInterface;
import classescomunicacao.RemoteInfoInterface;
import java.io.IOException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.*;

public class RemoteService extends UnicastRemoteObject implements RemoteInfoInterface {

    ArrayList<ClienteEnviar> Jogadores;
    ArrayList<FormarPar> Pares;
    ArrayList<Partida> Partidas;

    //Nota: nao era solicitado no enunciado manter a referencia dos clientes
    protected List<RemoteInfoClientInterface> clients;

    public RemoteService() throws RemoteException {
        clients = new ArrayList<>();
        Jogadores = new ArrayList<ClienteEnviar>();
        Pares = new ArrayList<>();
    }

    @Override
    public void GetData(RemoteInfoClientInterface clientReference) throws RemoteException {
        int i;
        InfoJogo dados;

        //Nota: nao era solicitado no enunciado manter a referencia dos clientes
        if (clientReference != null && !clients.contains(clientReference)) {
            System.out.println("New client added to list.");
            clients.add(clientReference);
        }

        dados = constructDadosJogo();

        //Nota: nao era solicitado no enunciado manter a referencia dos clientes 
        //Era suficiente fazer: if(clientReference!=null) clientReference.ageStatisticsUpdated(statistics);
        for (i = 0; i < clients.size(); i++) {
            try {
                clients.get(i).getDadosUpdated(dados);
            } catch (RemoteException e) {
                clients.remove(clients.get(i--));
                System.out.println("Unreachable client removed from list!");
            }
        }

    }

    @Override
    public InfoJogo getDadosJogo() throws RemoteException {
        return constructDadosJogo();
    }

    synchronized protected void updateData(ArrayList<FormarPar> par, ArrayList<ClienteEnviar> jogares) {
        this.Jogadores = jogares;
        this.Pares = par;
    }

    synchronized protected InfoJogo constructDadosJogo() {

        Atualizadados();
        InfoJogo t = new InfoJogo();
        t.setClientes(Jogadores);
        t.setPares(Pares);
        return t;
    }

    private void Atualizadados() {
        PesquisasGestaoUtilizadores p = new PesquisasGestaoUtilizadores();
        Pares =  p.getPares();
        Jogadores = p.getJogadoresLogados();
        Partidas = p.getPartidas();
    }

}
