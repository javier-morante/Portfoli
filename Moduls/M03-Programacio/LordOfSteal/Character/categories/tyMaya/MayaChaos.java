package Character.categories.tyMaya;
import Character.categories.Maya;

public class MayaChaos extends Maya{
    
    public MayaChaos(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);
    }
    
    public boolean chaosPassive(int dice){

        if(dice <= (getPratt()*0.5)){
            return true;
        }

        return false;

    }

}
