package Threads;


import Objects.Monster;
import Resources.VariableMonster;

/**
 *
 * @author Derok
 */
public class MonsterThread extends CustomThread{
    VariableMonster varMonster;
    
    
    public MonsterThread()
    {
        varMonster = VariableMonster.getInstance();
    }
    public void run()
    {
        while(running)
        {
            if(this.tick()) {
                for(Monster monster : varMonster.getMonsters()) {
                    if(monster.getSleepDuration() > 0) {
                        monster.sleep();
                        if(monster.getSleepDuration() <= 0) {
                            monster.initialRandomMove();
                        }
                    } else if(monster.getMoveDuration() > 0) {
                        monster.move();
                        if(monster.getMoveDuration() <= 0) {
                            monster.initialSleep();
                        }
                    }
                }
            }     
        }
    }
    
   
}