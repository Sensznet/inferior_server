package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Derok
 */
public class UpdateServer extends Server{
    UpdateServer(Socket client)
    {
        super(client);
       
    }
    public void run()
    {
        while(running)
        {
            //todo
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
