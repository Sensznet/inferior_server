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
    private volatile static VariableChat varChat;
    ArrayList <ChatServer> servers = new ArrayList<>();
    public VariableChat()
    {
    }
    
    public static VariableChat getInstance() {
        if(varChat == null) {
            // To make thread safe 
            synchronized (VariableMain.class) 
            { 
                // check again as multiple threads 
                // can reach above step 
                if (varChat==null) 
                    varChat = new VariableChat(); 
            } 
        }
        return varChat;
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
