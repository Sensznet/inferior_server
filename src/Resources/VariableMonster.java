/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Resources;

import Objects.Monster;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ssens
 */
public class VariableMonster {
    private volatile static VariableMonster varMonster;
    ArrayList<Monster> monsters = new ArrayList<>();
    
    public VariableMonster() {
        int xpos;
        int ypos;
        try {
            BufferedImage collisionImage = ImageIO.read(new File("./build/classes/wasser1.bmp"));
            for(int i=0; i<10; i++) {
                xpos = (int)(Math.random()*300)+1000;
                ypos = (int)(Math.random()*300)+1000;
                this.monsters.add(new Monster(1,"Wolf", xpos, ypos, 1, 50, 50, collisionImage));
            }
        } catch (IOException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static VariableMonster getInstance() {
        if(varMonster == null) {
            // To make thread safe 
            synchronized (VariableMonster.class) 
            { 
                // check again as multiple threads 
                // can reach above step 
                if (varMonster==null) 
                    varMonster = new VariableMonster(); 
            } 
        }
        return varMonster;
    }
    
    public ArrayList<Monster> getMonsters() {
        return this.monsters;
    }
}
