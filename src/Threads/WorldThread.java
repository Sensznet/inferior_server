/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Threads;

import Objects.Player;
import Resources.VariableMain;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author ssens
 */
public class WorldThread extends CustomThread{
    private VariableMain main;
    private int healthtick = 5;
    private Date healthlast = new Date();
    public WorldThread(VariableMain main) {
        this.main = main;
    }
    
     public void run()
    {
        while(running) {
            if(this.tick()) {
                Date current = new Date();
                if(current.getTime() - healthlast.getTime() > healthtick * 1000) {
                    healthlast = current;
                    ArrayList<Player> players = this.main.getOnlinePlayers();
                    for(Player player : players) {
                        player.recover();
                    }
                }
                
            }
        }
    }
}
