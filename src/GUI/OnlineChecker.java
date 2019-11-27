package GUI;


import Threads.MonsterThread;
import Resources.VariableMain;
import Objects.Monster;
import Objects.Player;

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
    private Oberflaeche flaeche;
    private MonsterThread monsterthread;
    private int p=1;
    OnlineChecker(Oberflaeche flaeche, VariableMain  varmain, MonsterThread monsterthread)
    {
        this.flaeche = flaeche;
        this.varmain = varmain;
        this.monsterthread = monsterthread;
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
            for(Monster monster : monsterthread.getMonsters()) {
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
