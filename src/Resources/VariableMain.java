package Resources;


import Objects.Player;
import java.util.ArrayList;

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
    private ArrayList<Player> players = new ArrayList<>();
    
    public VariableMain(ArrayList<String[]> players)
    {
        for(String[] player : players) {
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
        this.players.add(newPlayer);
    }
    
    public Player getPLayer(int arrayid) {
        return this.players.get(arrayid);
    }
    
    public Player findPlayerById(int id) {
        for(Player player : this.players) {
            if(player.getId() == id) {
                return player;
            }
        }
        return null;
    }
    
    public ArrayList<Player> getOnlinePlayers() {
        ArrayList<Player> onlinePlayers = new ArrayList<Player>();
        for(Player player : this.players) {
            if(player.isOnline()) {
                onlinePlayers.add(player);
            }
        }
        return onlinePlayers;
    }
}
