package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import GUI.Oberflaeche;
import Resources.VariableChat;
import java.net.*;
import java.io.*;
/**
 *
 * @author Derok
 */
public class VerteilerChat extends Thread
{
    private int port;
    private ServerSocket serverchat;
    private Socket client;
    private VariableChat  varchat;
    private Oberflaeche flaeche;
    public VerteilerChat(int port, Oberflaeche flaeche)
    {
        this.port = port;
        this.flaeche = flaeche;
        try
        {
            varchat = new VariableChat();
            serverchat = new ServerSocket(port);
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
                client = serverchat.accept();
                flaeche.setText("Incoming Chat Connection from " + client.getInetAddress());
                new ChatServer(client, varchat);
                
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
