package GUI;


import Threads.MonsterThread;
import Resources.VariableMain;
import Objects.Monster;
import Objects.Player;
import Resources.VariableMonster;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Derok
 */
public class OnlineChecker extends Thread
{
    private VariableMain  varmain;
    private VariableMonster varMonster;
    private Oberflaeche flaeche;
    private int p=1;
    OnlineChecker(Oberflaeche flaeche)
    {
        this.flaeche = flaeche;
        varmain = VariableMain.getInstance();
        varMonster = VariableMonster.getInstance();
        this.start();
    }
    public void run()
    {
        while(true)
        {
            flaeche.resetText2();
            flaeche.resetTextMonsters();
            for(Player player : varmain.getOnlinePlayers()) {
                flaeche.setText2(player.getName(),
                    String.valueOf(player.getXpos()),
                    String.valueOf(player.getYpos()));
            }
            for(Monster monster : varMonster.getMonsters()) {
                flaeche.setTextMonsters(monster.getName(), String.valueOf(monster.getXpos()), String.valueOf(monster.getYpos()));
            }
            try
            {
                Thread.sleep (1000);
            }
            catch (InterruptedException ex)
            {}
        }
    }
}
