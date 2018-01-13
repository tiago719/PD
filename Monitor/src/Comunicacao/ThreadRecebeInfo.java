/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comunicacao;

import Logic.ObservableScreen;
import classescomunicacao.InfoJogo;
import classescomunicacao.RemoteInfoInterface;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Observable;
import java.util.Scanner;

/**
 *
 * @author andre
 */
public class ThreadRecebeInfo extends Thread {
    
    public String Ip;
    ObservableScreen observablescreen;
    InfoJogo jogo;
    
    public ThreadRecebeInfo(String ip, ObservableScreen o) {
        observablescreen = o;
        Ip = ip;
    }
    
    public ThreadRecebeInfo(String Ip) {
        this.Ip = Ip;
    }
    
    @Override
    public void run() {
        String url;
        RemoteInfoClient ageClient;
        
        url = "rmi://" + Ip + "/RemoteAgeService";
        
        try {
            ageClient = new RemoteInfoClient();
            
            RemoteInfoInterface ageService = (RemoteInfoInterface) Naming.lookup(url);

            //ageService.setClientAge(23, null);
            ageService.getDadosJogo();
            
            while (true) {
                Thread.sleep(1000);
                ageService.GetData(ageClient);
                jogo = ageService.getDadosJogo();
                if(jogo != null)
                    observablescreen.Update();
            }

            // Termina o servico ageClient
            //UnicastRemoteObject.unexportObject(ageClient, true);
        } catch (RemoteException e) {
            System.out.println("Remote error - " + e);
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Error - " + e);
            System.exit(1);
        }
    }
    
    public InfoJogo getJogo() {
        return jogo;
    }
    
}
