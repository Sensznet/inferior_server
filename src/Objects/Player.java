/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author ssens
 */
public class Player {
    private int id;
    private int xpos;
    private int ypos;
    private String name;
    private int hpmom;
    private int manamom;
    private int lvl;
    private int exp;
    private int str;
    private int agi;
    private int intel ;
    private int direction;
    private boolean online;
    public Player(int id, int xpos, int ypos, String name, int hpmom, int manamom, int lvl, int exp, int str, int agi, int intel, int direction, boolean online) {
        this.id = id;
        this.xpos = xpos;
        this.ypos = ypos;
        this.name = name;
        this.hpmom = hpmom;
        this.manamom = manamom;
        this.lvl = lvl;
        this.exp = exp;
        this.str = str;
        this.agi = agi;
        this.intel = intel;
        this.direction = direction;
        this.online = online;
    }
    public int getId() {
        return this.id;
    }
    public int getXpos()
    {
        return this.xpos;
    }
    public void setXpos(int xpos)
    {
        this.xpos = xpos;
    }
    public int getYpos()
    {
        return this.ypos;
    }
    public void setYpos(int ypos)
    {
        this.ypos = ypos;
    }
    public void setOnline()
    {
        this.online=true;
    }
    public void setOffline()
    {
        this.online=false;
    }
    public String getName()
    {
        return this.name;
    }
    public boolean isOnline()
    {
        return this.online;
    }
    public int getCurrentHp()
    {
        return this.hpmom;
    }
    public void setCurrentHp(int hpmom)
    {
        this.hpmom = hpmom;
    }
    public int getMaxHp()
    {
        return 200 + (this.lvl * 10) + (this.str * 3);
    }
    public int getMaxMana()
    {
        return 200 + (this.lvl * 10) + (this.intel * 3);
    }
    public int getCurrentMana()
    {
        return this.manamom;
    }
    public void setCurrentMana(int mana)
    {
        this.manamom= mana;
    }
    public int getlvl()
    {
        return lvl;
    }
    public void setlvl(int lvl)
    {
        this.lvl = lvl;
    }
    public int getCurrentExp()
    {
        return this.exp;
    }
    public void setCurrentExp(int exp)
    {
        this.exp = exp;
    }
    public int getMaxExp()
    {
        return (int)(Math.pow(lvl+1, 3));
    }
    public int getStr()
    {
        return this.str;
    }
    public void setStr(int str)
    {
        this.str= str;
    }
    public int getAgi()
    {
        return this.agi;
    }
    public void setAgi(int agi)
    {
        this.agi = agi;
    }
    public int getIntel()
    {
        return intel;
    }
    public void setIntel(int intel)
    {
        this.intel= intel;
    }
    public void setDirection(int direction)
    {
        this.direction = direction;
    }
    public int getDirection()
    {
        return direction;
    }
}
