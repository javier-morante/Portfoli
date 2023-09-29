package Character.categories;

import Character.CharacterP;

public class Medium extends CharacterP{
    public Medium(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);

    }

    public void scStats(){
        calPA();
        calPD();
        calPS();
        calPe();
    }
    
    protected void calPe() {
        // pe = sp + lk + inte+st;

        setPe(getSpeed() + getLuck() + getIntelligence() + getStrenght());
    }
}
