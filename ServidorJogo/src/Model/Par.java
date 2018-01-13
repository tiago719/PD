/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author edu_f
 */
public class Par {

    Socket user1, user2;
    ObjectOutputStream out1, out2;
    ObjectInputStream in1, in2;

    public Par(Socket user1, Socket user2) {
        this.user1 = user1;
        this.user2 = user2;
        out1 = out2 = null;
        in1 = in2 = null;
    }

    public synchronized Socket getUser1() {
        return user1;
    }

    public synchronized void setUser1(Socket user1) {
        this.user1 = user1;
        try {
            out1 = new ObjectOutputStream(user1.getOutputStream());
            in1 = new ObjectInputStream(user1.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Par.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized Socket getUser2() {
        return user2;
    }

    public synchronized void setUser2(Socket user2) {
        this.user2 = user2;
        try {
            out2 = new ObjectOutputStream(user2.getOutputStream());
            in2 = new ObjectInputStream(user2.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Par.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ObjectOutputStream getOut1() {
        return out1;
    }

    public ObjectOutputStream getOut2() {
        return out2;
    }

    public ObjectInputStream getIn1() {
        return in1;
    }

    public ObjectInputStream getIn2() {
        return in2;
    }
    
    

}
