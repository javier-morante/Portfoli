package Character;

import Weapons.*;


public abstract class  CharacterP {

    protected int exp; //

    private int lvl; //
    private String name; //
    private Weapon weapon;
    public String race; //
    public char devotion;

// Primary stats

    private int st;//
    private int con; //
    private int sp; //
    private int inte; //
    private int lk; //

//    Secundary stats;

    private int ps;
    private int initalPs;
    private int pd;
    private int pa;
    private int pe;    

    public CharacterP(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        initalPs = 0;
        lvl = 0;
        exp = 0;
        this.race = race;
        this.devotion = devo;

        this.name = name;
        st = strength;
        con = constitution;
        sp = speed;
        inte = intelligence;
        lk = lucky;
        
        switch (weapon){
            case 'd':
                this.weapon = new Dagger();
                break;

            case 's':
                this.weapon = new Sword();
                break;
            
            case 'w':
                this.weapon = new WarHammer();
                break;
        }

        

        scStats();
        
    }

    public abstract void scStats();

    protected void calPS(){

        ps = con + st;
        initalPs = ps;
    }

    protected void calPD(){
        pd = (st+weapon.wpow)/4;

    }

    protected void calPA(){
        pa = inte+lk+weapon.wspd;
        
        
    }

    protected void calPe(){
        pe = sp + lk + inte;

    }

    public void addexp(int quantity){
        exp = exp + quantity;
        canlvlup();
    }


    // gets

    public String getName() {
        return name;
    }

    public int getExp() {
        return exp;
    }

    public int getLvl() {
        return lvl;
    }

    public String getRace() {
        return race;
    }

    public char getDevotion() {
        return devotion;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    //  Primary stats 

    public int getStrenght() {
        return st;
    }

    public int getConstution() {
        return con;
    }

    public int getSpeed() {
        return sp;
    }

    public int getIntelligence() {
        return inte;
    }

    public int getLuck() {
        return lk;
    }

    // secundary stats

    public int getPs() {
        return ps;
    }

    public int getInitalPs() {
        return initalPs;
    }

    public int getPd() {
        return pd;
    }

    public int getPratt() {
        return pa;
    }

    public int getPresq() {
        return pe;
    }

    //sets

    // Primary stats

    public void setSt(int st) {
        this.st = st;
    }

    public void setCon(int con) {
        this.con = con;
    }

    public void setSp(int sp) {
        this.sp = sp;
    }

    public void setInte(int inte) {
        this.inte = inte;
    }

    public void setLk(int lk) {
        this.lk = lk;
    }

    // Secundary stats
    public void setPs(int ps) {
        this.ps = ps;
    }

    public void setInitalPs(int initalPs) {
        this.initalPs = initalPs;
    }

    public void setPd(int pd) {
        this.pd = pd;
    }

    public void setPa(int pa) {
        this.pa = pa;
    }

    public void setPe(int pe) {
        this.pe = pe;
    }

    protected void canlvlup(){
        boolean lvlup = false;
        switch (lvl){
            case 0 : 
                if(exp >= 100){
                    exp = 0;
                    lvl++;
                    lvlup = true;
                }
                break;
            case 1:
                if(exp >= 200){
                    exp = 0;
                    lvl++;
                    lvlup = true;
                }
                break;
            case 2 :
                if(exp >= 500){
                    exp = 0;
                    lvl++;
                    lvlup = true;
                }
                break;
            
            case 3:
                if(exp >= 1000){
                    exp = 0;
                    lvl++;
                    lvlup = true;
                }
                break;
            case 4:
                if(exp >= 2000){
                    exp = 0;
                    lvl++;
                    lvlup = true;
                }
                break;  
        }
        if (lvlup) {
            lvlupStats();
        }
    }

    protected void lvlupStats(){

    st ++;
    con ++;
    sp ++;
    inte ++;
    lk ++;

    scStats();

    }

    public void restorePS() {
        ps = initalPs;
    }

    public boolean attck(int dados) {
        
        if(dados <= pa){
            return true;
        }
        return false;
    }

    public boolean dodge(int dados) {
        if(dados <= pe){
            return true;
        }
        return false;
    }

}
