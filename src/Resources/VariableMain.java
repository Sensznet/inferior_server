package Resources;


import Objects.Player;
import java.util.ArrayList;
import java.util.HashMap;
import packageDB.SimpleQuery;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derok
 */
public class VariableMain 
{
    private volatile static VariableMain varPlayer;
    private final HashMap<Integer, Player> players = new HashMap<>();
    
    public VariableMain()
    {
        SimpleQuery sq = SimpleQuery.getInstance();
        for(String[] player : sq.getPlayers()) {
            this.addPlayer(
                    Integer.parseInt(player[0]),
                    Integer.parseInt(player[1]),
                    Integer.parseInt(player[2]),
                    player[3],
                    Integer.parseInt(player[4]),
                    Integer.parseInt(player[5]),
                    Integer.parseInt(player[6]),
                    Integer.parseInt(player[7]),
                    Integer.parseInt(player[8]),
                    Integer.parseInt(player[9]),
                    Integer.parseInt(player[10]),
                    Integer.parseInt(player[11]),
                    false);
        }        
    }
    
    public static VariableMain getInstance() {
        if(varPlayer == null) {
            // To make thread safe 
            synchronized (VariableMain.class) 
            { 
                // check again as multiple threads 
                // can reach above step 
                if (varPlayer==null) 
                    varPlayer = new VariableMain(); 
            } 
        }
        return varPlayer;
    }
    
    public void addPlayer(int id, int xpos, int ypos, String name, int hpmom, int manamom, int lvl, int exp, int str, int agi, int intel, int direction, boolean online) {
        Player newPlayer = new Player(
                id,
                xpos,
                ypos,
                name,
                hpmom,
                manamom,
                lvl,
                exp,
                str,
                agi,
                intel,
                direction,
                false);
        this.players.put(id, newPlayer);
    }
    
    public Player getPLayer(int arrayid) {
        return this.players.get(arrayid);
    }
    
    public Player findPlayerById(int id) {
        return this.players.get(id);
    }
    
    public ArrayList<Player> getOnlinePlayers() {
        ArrayList<Player> onlinePlayers = new ArrayList<>();
        this.players.forEach((key, player) -> {
            onlinePlayers.add(player);
        });
        return onlinePlayers;
    }
}
