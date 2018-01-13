/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorgestao.ComunicacaoC;

import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andre
 */
public class ThreadMonitor extends Thread {

    String ip;

    public ThreadMonitor(String ip) {
        this.ip = ip;
    }

    @Override
    public void run() {
        try {
            RemoteService Service = new RemoteService();

            try {
                LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            } catch (Exception e) {
                System.out.println("Registry is already in use on port " + Registry.REGISTRY_PORT);
            }

            // Register the service
            Naming.bind("rmi://" + ip + "/RemoteAgeService", Service);
            System.out.println("Press <Enter> to exit Monitor."); System.in.read();
            // Retira do registry a referencia ao servico
            Naming.unbind("rmi://" + ip +"/RemoteAgeService");
            // Termina o servico
            UnicastRemoteObject.unexportObject(Service, true);

        } catch (IOException | NotBoundException e) {
            System.err.println("Error - " + e);
            System.exit(1);
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(ThreadMonitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
