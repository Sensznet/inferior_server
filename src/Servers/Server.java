package Servers;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssens
 */
public class Server extends Thread {
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private Thread runner;
    protected Boolean running = true;
    Server(Socket client) {
        this.client = client;
        try
        {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
        }
        catch(IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        runner = new Thread(this);
        runner.start();
    }
    
    protected void sendData(JSONObject obj) {
        try {
            this.out.writeUTF(obj.toString());
        } catch (IOException ex) {
            if(!ex.getMessage().equals("Connection reset")) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            this.stopThread();
        }
    }
    
    protected JSONObject getData() {
        JSONObject obj = null;
        try {
            String data = in.readUTF();
            obj = new JSONObject(data);
        } catch (IOException ex) {
            if(!ex.getMessage().equals("Connection reset")) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            }
            this.stopThread();
        }
        return obj;
    }
    
    public void stopThread() {
        this.running = false;
        try {
            this.in.close();
            this.out.close();
            this.client.close();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
