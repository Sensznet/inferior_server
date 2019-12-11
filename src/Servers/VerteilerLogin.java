package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import GUI.Oberflaeche;
import java.net.*;
import java.io.*;
import packageDB.SimpleQuery;
/**
 *
 * @author Derok
 */
public class VerteilerLogin extends Thread
{
    private int port;
    private ServerSocket serverlogin;
    private Socket client;
    private Oberflaeche flaeche;
    private SimpleQuery sq;
    public VerteilerLogin(int port, Oberflaeche flaeche)
    {
        this.sq = SimpleQuery.getInstance();
        this.port = port;
        this.flaeche = flaeche;
        try
        {
            serverlogin = new ServerSocket(port);
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
                client = serverlogin.accept();
                flaeche.setText("Incoming Login Connection from " + client.getInetAddress());
                new LoginServer(client, sq);
                
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
