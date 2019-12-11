package Servers;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Resources.VariableMain;
import Objects.Player;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
/**
 *
 * @author Derok
 */
public class MainServer extends Server{
    VariableMain vertmain;   
    private Player player;
    MainServer(Socket client, VariableMain vertmain)
    {
        super(client);
        this.vertmain = vertmain;
    }
    
    public void run()
    {
        while(running)
        {
            JSONObject request = this.getData();
            if(request instanceof JSONObject) {
                JSONObject response = new JSONObject();
                switch(request.getString("type")) {
                    case "initial":
                        this.player = this.vertmain.findPlayerById(request.getInt("id"));
                        response.put("objectCount", 0);
                        response.put("xpos", this.player.getXpos());
                        response.put("ypos", this.player.getYpos());
                        response.put("maxHp", this.player.getMaxHp());
                        response.put("curHp", this.player.getCurrentHp());
                        response.put("maxMana", this.player.getMaxMana());
                        response.put("curMana", this.player.getCurrentMana());
                        response.put("curExp", this.player.getCurrentExp());
                        response.put("maxExp", this.player.getMaxExp());
                        response.put("lvl", this.player.getlvl());
                        response.put("agi", this.player.getAgi());
                        response.put("int", this.player.getIntel());
                        response.put("str", this.player.getStr());
                        response.put("direction", this.player.getDirection());
                        response.put("name", this.player.getName());
                        this.sendData(response);
                        this.player.setOnline();
                        break;
                    case "refresh":
                        this.player.setXpos(request.getInt("xpos"));
                        this.player.setYpos(request.getInt("ypos"));
                        this.player.setDirection(request.getInt("direction"));
                        response.put("maxHp", this.player.getMaxHp());
                        response.put("curHp", this.player.getCurrentHp());
                        response.put("maxMana", this.player.getMaxMana());
                        response.put("curMana", this.player.getCurrentMana());
                        response.put("curExp", this.player.getCurrentExp());
                        response.put("maxExp", this.player.getMaxExp());
                        ArrayList<Player> onlinePlayers = this.vertmain.getOnlinePlayers();
                        JSONArray players = new JSONArray();
                        int count = 0;
                        for(Player player : onlinePlayers) {
                            if(player != this.player) {
                                JSONObject tplayer = new JSONObject();
                                tplayer.put("id", player.getId());
                                tplayer.put("xpos", player.getXpos());
                                tplayer.put("ypos", player.getYpos());
                                tplayer.put("direction", player.getDirection());
                                tplayer.put("name", player.getName());
                                tplayer.put("maxHp", player.getMaxHp());
                                tplayer.put("curHp", player.getCurrentHp());
                                players.put(tplayer);
                            }
                        }
                        response.put("players", players);
                        this.sendData(response);
                        break;
                    case "attributes":
                        response.put("agi", this.player.getAgi());
                        response.put("int", this.player.getIntel());
                        response.put("str", this.player.getStr());
                        this.sendData(response);
                        break;
                    case "offline":
                        this.player.setOffline();
                        this.stopThread();
                        break;
                }
            }        
        }
    }
    
    public void stopThread() {
        this.player.setOffline();
        super.stopThread();
    }
}
