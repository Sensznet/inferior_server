package Resources;

import Servers.ChatServer;
import java.util.ArrayList;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derok
 */
public class VariableChat {
    ArrayList <ChatServer> servers = new ArrayList<>();
    public VariableChat()
    {
    }
    public void setmassage(String massage)
    {
        for(ChatServer server : servers) {
            server.writeMessage(massage);
        }
        
    }
    
    public void registerServer(ChatServer server) {
        servers.add(server);
    }
    
    public void unregisterServer(ChatServer server) {
        servers.remove(server);
    }
}
