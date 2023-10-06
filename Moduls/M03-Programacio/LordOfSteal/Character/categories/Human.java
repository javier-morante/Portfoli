package Character.categories;
import Character.CharacterP;

public class Human extends CharacterP{
    public Human(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);

    }

    public void scStats(){
        calPA();
        calPD();
        calPS();
        calPe();
    }
    
    protected void calPS() {

        setPs(getConstution()+getStrenght()+getIntelligence());

        setInitalPs(getPs());
    }
}