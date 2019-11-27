package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Resources.VariableChat;
import java.net.*;
import org.json.JSONObject;
/**
 *
 * @author Derok
 */
public class ChatServer extends Server{
    private final VariableChat varchat;
    ChatServer(Socket client, VariableChat varchat)
    {
        super(client);
        this.varchat = varchat;
    }
    @Override
    public void run()
    {
        this.varchat.registerServer(this);
        while(running)
        {
            JSONObject request = this.getData();
            if(request instanceof JSONObject) {
                switch(request.getString("type")) {
                    case "write":
                        this.varchat.setmassage(request.getString("text"));
                        break;
                    case "offline":
                        this.stopThread();
                        break;
                }
            }
        }
    }
    
    public void writeMessage(String text) {
        JSONObject response = new JSONObject();
        response.put("type", "message");
        response.put("text", text);
        this.sendData(response);
    }
    
    @Override
    public void stopThread() {
        this.varchat.unregisterServer(this);
        super.stopThread();
    }
}
