package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.net.*;
/**
 *
 * @author Derok
 */
public class UpdateServer extends Thread{
    private int version=0, oldversion=0;
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private Thread runner;
    UpdateServer(Socket client)
    {
        this.client = client;
        try
        {
            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());
            runner = new Thread(this);
            runner.start();
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
                BufferedReader test =
                new BufferedReader(new FileReader("./version.txt"));
                String tmp = test.readLine();
                version = Integer.parseInt(tmp); 
                test.close();
                out.writeInt(version);
                oldversion = in.readInt();
                if(version!=oldversion)
                {
                    FileInputStream fileIn = new FileInputStream
                    ("./build/classes/build.zip");
                    byte[] buffer = new byte[1024];
                    while (fileIn.available() > 0) {
                    out.write(buffer, 0, fileIn.read(buffer));
                    }
                    fileIn.close();
                }
                this.stop();
            }
            catch(IOException e)
            {
            }
        }
    }
}
