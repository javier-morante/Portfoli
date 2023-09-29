package Character.categories.tyMedium;

import Character.categories.Medium;

public class MediumOrder extends Medium{
    
    public MediumOrder(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
        super(name, race, devo, strength, constitution, speed, intelligence, lucky, weapon);
    }

    public void orderPassive(){
        
        int inicialPs = getInitalPs();

        int heal = (inicialPs*10)/100;

        if(heal+getPs() < inicialPs){
            setPs(getPs()+heal);
        }
    }
}
