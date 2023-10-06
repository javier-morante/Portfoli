package Character.categories;

import Character.CharacterP;

public class Dwarf extends CharacterP{
    
    public Dwarf(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);

    }

    public void scStats(){
        calPA();
        calPD();
        calPS();
        calPe();
    }
    
    protected void calPD() {

        setPd((getStrenght()+getWeapon().getWpow()+getConstution()/4));
    }

}