package Servers;


import Threads.MonsterThread;
import Objects.Monster;
import Resources.VariableMonster;
import java.net.*;
import java.io.*;
import org.json.*;


/**
 *
 * @author Derok
 */
public class MonsterServer extends Server
{
    VariableMonster varMonster;
    MonsterServer(Socket client)
    {
        super(client);
        this.varMonster = VariableMonster.getInstance();
    }
    public void run()
    {
        while(running)
        {
            JSONObject request = this.getData();
            if(request instanceof JSONObject) {
            JSONObject response = new JSONObject();
                switch(request.getString("type")) {
                    case "refresh":
                        JSONArray monsters = new JSONArray();
                        for(Monster tmonster : varMonster.getMonsters()) {
                            JSONObject monster = new JSONObject();
                            monster.put("id", tmonster.getId());
                            monster.put("type", tmonster.getType());
                            monster.put("name", tmonster.getName());
                            monster.put("xpos", tmonster.getXpos());
                            monster.put("ypos", tmonster.getYpos());
                            monster.put("direction", tmonster.getDirection());
                            monster.put("maxHp", tmonster.getMaxHp());
                            monster.put("curHp", tmonster.getCurHp());
                            monsters.put(monster);
                        }
                        response.put("monsters", monsters);
                        this.sendData(response);
                        break;
                    case "offline":
                        this.stopThread();
                        break;
                }
            }
        }
    }
}