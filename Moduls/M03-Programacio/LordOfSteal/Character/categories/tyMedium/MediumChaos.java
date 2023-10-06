package Character.categories.tyMedium;

import Character.categories.Medium;

public class MediumChaos extends Medium{
    
    public MediumChaos(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);

    }

    public boolean chaosPassive(int dice){

        if(dice <= (getPratt()*0.5)){
            return true;
        }

        return false;

    }
}
