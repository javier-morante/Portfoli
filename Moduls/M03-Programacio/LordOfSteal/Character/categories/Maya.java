package Character.categories;

import Character.CharacterP;

public class Maya extends CharacterP{
    
    public Maya(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);

    }


    public void scStats(){
        calPA();
        calPD();
        calPS();
        calPe();
    }

    protected void calPA() {

        //pa = inte+lk+weapon.wspd+sp;

        setPa(getIntelligence() + getLuck() + getWeapon().getWspd() + getSpeed());
        
    }
}
