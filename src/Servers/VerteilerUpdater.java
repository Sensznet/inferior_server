package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import GUI.Oberflaeche;
import java.net.*;
import java.io.*;
/**
 *
 * @author Derok
 */
public class VerteilerUpdater extends Thread
{
    private int port;
    private ServerSocket serverupdate;
    private Socket client;
    private Oberflaeche flaeche;
    public VerteilerUpdater(int port, Oberflaeche flaeche)
    {
        this.port = port;
        this.flaeche = flaeche;
        try
        {
            serverupdate = new ServerSocket(port);
        }
        catch(IOException e)
        {
        }
    }
    public void run()
    {
        while(true)
        {
            try
            {
                client = serverupdate.accept();
                flaeche.setText("Incoming Update Connection from " + client.getInetAddress());
                new UpdateServer(client);
                
            }
            catch(IOException e)
            {
            }
        }
    }
    public int getPort()
    {
        return port;
    }

}
