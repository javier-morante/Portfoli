package Character.categories.tyMaya;

import Character.categories.Maya;

public class MayaOrder extends Maya{
    
    public MayaOrder(String name ,String race,char devo, int strength, int constitution, int speed , int intelligence,int lucky, char weapon){
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
