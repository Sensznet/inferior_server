package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import GUI.Oberflaeche;
import Threads.MonsterThread;
import java.net.*;
import java.io.*;
/**
 *
 * @author Derok
 */
public class VerteilerMonster extends Thread
{
    private int port;
    private ServerSocket servermob;
    private Socket client;
    private Oberflaeche flaeche;
    public VerteilerMonster(int port, Oberflaeche flaeche)
    {
        this.port = port;
        this.flaeche = flaeche;
        try
        {
            servermob = new ServerSocket(port);
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
                client = servermob.accept();
                flaeche.setText("Incoming Mob Connection from " + client.getInetAddress());
                new MonsterServer(client);
                
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