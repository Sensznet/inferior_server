package Threads;


import Objects.Monster;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Derok
 */
public class MonsterThread extends CustomThread{
    private int xpos, ypos, mobart, direction;
    private String mobname;
    private int i=0, x, y, k;
    private boolean collside = false;
    ArrayList<Monster> monsters = new ArrayList<>();
    public MonsterThread()
    {
        try {
            BufferedImage collisionImage = ImageIO.read(new File("./build/classes/wasser1.bmp"));
            for(int i=0; i<10; i++) {
                xpos = (int)(Math.random()*300)+400;
                ypos = (int)(Math.random()*300)+400;
                this.monsters.add(new Monster(1,"Wolf", xpos, ypos, 1, 50, 50, collisionImage));
            }
        } catch (IOException ex) {
            System.out.println("was here4");
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void run()
    {
        while(running)
        {
            if(this.tick()) {
                for(Monster monster : this.monsters) {
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
    
    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }
}