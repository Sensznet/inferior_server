package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*; 
import java.net.*;
import org.json.JSONObject;
/**
 *
 * @author Derok
 */
public class LoginServer extends Server
{
    private final packageDB.SimpleQuery sq;
    LoginServer(Socket client, packageDB.SimpleQuery sq)
    {
        super(client);
        this.sq = sq;
    }
    public void run()
    {
        JSONObject request = this.getData();
        if(request instanceof JSONObject) {
            JSONObject response = new JSONObject();
            switch(request.getString("type"))  {
                case "login":
                    int id = sq.getIDbyNAME(request.getString("name"));
                    String pw = sq.getPWbyID(id);
                    if(id > 0 && pw.equals(request.getString("pw"))) {
                        response.put("status", true);
                        response.put("id", id);
                    } else {
                        response.put("status", false);
                    }
                    this.sendData(response);
                    break;
                case "register":
                    sq.createnewAcc(request.getString("name"), request.getString("pw"), request.getString("mail"));
                    response.put("status", true);
                    break;
            }
        }
    }

}
