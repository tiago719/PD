package classescomunicacao;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author andre
 */
public class InfoJogo implements Serializable {

    ArrayList<ClienteEnviar> Clientes;
    ArrayList<FormarPar> Pares;
    ArrayList<Partida> Partidas;

    public InfoJogo() {
        this.Clientes = new ArrayList<ClienteEnviar>();
        this.Pares = new ArrayList<FormarPar>();
        this.Partidas = new ArrayList<Partida>();
    }

    public ArrayList<Partida> getPartidas() {
        return Partidas;
    }

    public void setPartidas(ArrayList<Partida> Partidas) {
        this.Partidas = Partidas;
    }

    
    public ArrayList<ClienteEnviar> getClientes() {
        return Clientes;
    }

    public void setClientes(ArrayList<ClienteEnviar> Clientes) {
        this.Clientes = Clientes;
    }

    public ArrayList<FormarPar> getPares() {
        return Pares;
    }

    public void setPares(ArrayList<FormarPar> Pares) {
        this.Pares = Pares;
    }

    @Override
    public String toString() {
        return "ola";
    }
}
