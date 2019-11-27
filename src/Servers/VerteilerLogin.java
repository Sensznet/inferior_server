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
public class VerteilerLogin extends Thread
{
    private int port;
    private ServerSocket serverlogin;
    private Socket client;
    private Oberflaeche flaeche;
    private packageDB.SimpleQuery sq;
    public VerteilerLogin(int port, Oberflaeche flaeche, packageDB.SimpleQuery sq)
    {
        this.sq = sq;
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
