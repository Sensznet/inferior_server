package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import GUI.Oberflaeche;
import Resources.VariableMain;
import java.net.*;
import java.io.*;
/**
 *
 * @author Derok
 */
public class VerteilerMain extends Thread
{
    private int port;
    private ServerSocket servermain;
    private Socket client;
    private VariableMain  varmain;
    private Oberflaeche flaeche;
    public VerteilerMain(int port,Oberflaeche flaeche)
    {
        this.varmain = VariableMain.getInstance();
        this.port = port;
        this.flaeche = flaeche;
        try
        {
            servermain = new ServerSocket(port);
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
                client = servermain.accept();
                flaeche.setText("Incoming Main Connection from " + client.getInetAddress());
                new MainServer(client, varmain);
                
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
