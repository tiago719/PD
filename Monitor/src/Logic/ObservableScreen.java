/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Logic;

import classescomunicacao.InfoJogo;
import Comunicacao.ThreadRecebeInfo;
import java.util.Observer;

/**
 *
 * @author andre
 */
public class ObservableScreen extends java.util.Observable {

    String IpServidorGestao;
    ThreadRecebeInfo r;

    public ObservableScreen() {

    }

    public void ComecaRecebeInfo() {
        r = new ThreadRecebeInfo(IpServidorGestao, this);
        r.start();
    }

    public String getIpServidorGestao() {
        return IpServidorGestao;
    }

    public void setIpServidorGestao(String IpServidorGestao) {
        this.IpServidorGestao = IpServidorGestao;
    }

    public void Update() {
        setChanged();
        notifyObservers();
    }

    public InfoJogo GetInfoJogo() {
        return r.getJogo();
    }

}
