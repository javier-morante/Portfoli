package Character.categories.tyHuman;

import Character.categories.Chaos;
import Character.categories.Human;

public class HumanChaos extends Human implements Chaos{
    
    public HumanChaos(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);
    }

    public boolean chaosPassive(int dice){

        if(dice <= (getPratt()*0.5)){
            return true;
        }

        return false;

    }

}
