/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author ssens
 */
public class Monster {
    private int id;
    private int xpos;
    private int ypos;
    private int type;
    private int direction;
    private String name;
    private int maxHp;
    private int curHp;
    private int moveduration = 0;
    private int sleepduration;
    private final BufferedImage collisionImage;
    private int xmove;
    private int ymove;
    private static final AtomicInteger count = new AtomicInteger(0); 
    
    
    public Monster(int type, String name, int xpos, int ypos, int direction, int maxHp, int curHp, BufferedImage collisionImage) {
        this.type = type;
        this.name = name;
        this.xpos = xpos;
        this.ypos = ypos;
        this.direction = direction;
        this.collisionImage = collisionImage;
        this.initialSleep();
        this.id = count.incrementAndGet();
        this.maxHp = maxHp;
        this.curHp = curHp;
    }
    
    public int getId() {
        return this.id;
    }

    public int getXpos() {
        return this.xpos;
    }

    public int getYpos() {
        return this.ypos;
    }
    
    public void initialRandomMove() {
        this.moveduration = (int)(10 + (Math.random() * 50));
        this.setDirection((int)(Math.random()*3)-1, (int)(Math.random()*3)-1);
    }
    
    public void initialSleep() {
        this.sleepduration = (int)(20 + (Math.random() * 60));
    }
    
    public void setDirection(int x, int y) {
        if(x < 0 && y < 0) {
            direction = 8;
        } else if(x == 0 && y < 0) {
            direction = 1;
        } else if(x > 0 && y < 0) {
            direction = 2;
        } else if(x < 0 && y == 0) {
            direction = 7;
        } else if(x > 0 && y == 0) {
            direction = 3;
        } else if(x < 0 && y > 0) {
            direction = 6;
        } else if(x == 0 && y > 0) {
            direction = 5;
        } else if(x > 0 && y > 0) {
            direction = 4;
        }
        this.xmove = x;
        this.ymove = y;
    }
    
    public void move() {
        int tempx = this.xpos + this.xmove;
        int tempy = this.ypos + this.ymove;
        if(this.collisionImage.getRGB(tempx+550, tempy+420) < -1) {
        } else {
            this.xpos = tempx;
            this.ypos = tempy;
        }
        moveduration--;
    }
    
    public void sleep() {
        sleepduration--;
    }
    
    public int getMoveDuration() {
        return this.moveduration;
    }
    
    public int getSleepDuration() {
        return this.sleepduration;
    }

    public int getType() {
        return this.type;
    }

    public int getDirection() {
        return this.direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public String getName() {
        return this.name;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public int getCurHp() {
        return curHp;
    }

    public void setCurHp(int curHp) {
        this.curHp = curHp;
    }
    
    
}
