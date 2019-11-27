/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ssens
 */
public class CustomThread extends Thread {
    private long last = 0;
    protected Boolean running = true;
    protected boolean tick() {
        if(last + 20 < System.currentTimeMillis()) {
            last = System.currentTimeMillis();
            return true;
        }
        try {
            Thread.sleep(5);
        } catch (InterruptedException ex) {
            Logger.getLogger(WorldThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void stopThread() {
        this.running = false;
    }
}
