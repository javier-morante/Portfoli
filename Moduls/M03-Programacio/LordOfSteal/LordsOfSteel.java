import java.util.ArrayList;
import Character.CharacterP;

import Character.categories.tyDwarf.*;
import Character.categories.tyHuman.*;
import Character.categories.tyMaya.*;
import Character.categories.tyMedium.*;

public class LordsOfSteel {
    private static Insert inp = new Insert();
    
    private static ArrayList<CharacterP> characterslist =  new ArrayList<CharacterP>();
    public static void main(String[] args) {

        characterslist.add(new HumanOrder("Eric","human",'o' ,12, 12, 12, 12, 12, 's'));
        characterslist.add(new DwarfChaos("Brokk","dwarf",'c', 18, 15, 7, 15, 5, 'w'));
        characterslist.add(new MayaOrder("Culul", "maya",'o',12, 10, 18, 10, 10, 'd'));
        characterslist.add(new MediumChaos("Anne","medium",'c',6, 12, 12, 18, 12, 'd'));

        while (true) {
            clean();
            System.out.print("""
                1. Añadir un nuevo personaje.
                2. Borrar un personaje.
                3. Editar un personatge.
                4. Mostrar personajes.
                5. Iniciar pelea.
                6. Salir.
                
                Selecciona la opcion:  """);
            int option = inp.insertMain();

            switch (option) {
                case 1:
                        clean();
                        addCharac();
                        System.out.println("\nPersonaje creado.");
                        System.out.print("Presona enter para continuar:");
                        inp.onlEnter();
                    break;
            
                case 2:
                    clean();
                    showCharact();
                    System.out.print("Pon el nombre del personaje a eliminar: ");
                    
                    int index = searchCharac(inp.insetString());
                    
                    delateCharac(index);    
                    break;

                case 3:
                    clean();
                    reStats();

                    break;

                case 4:
                    clean();
                    showCharact();
                    System.out.print("Presona enter para continuar:");
                    inp.onlEnter();
                    break;

                case 5:
                    clean();
                    showCharact();
                    int fg1;
                    int fg2;

                    System.out.println("Pon el nombre de los convatientes");
                    
                    do {
                        System.out.println("convatiente 1: ");
                        fg1 =  searchCharac(inp.insetString());


                        System.out.println("convatiente 2: ");
                        fg2 = searchCharac(inp.insetString());

                        if (fg1 == fg2) {
                            System.out.println("\nNe se puede utilizar el mismo personaje prueva otra vez");
                        }

                    } while (fg1 == fg2);
                    clean();
                    combat(characterslist.get(fg1),characterslist.get(fg2));

                    break;
                case 6:
                    System.exit(0);
                    break;
            }
        }
    }

    // show all characters

    public static void showCharact(){
        System.out.println("\nPersonajes  lvl  Exp        Vida  Daño  PrEs  PrAt        Raza  Devoc ");
        for (int i = 0; i < characterslist.size(); i++) {
            
            System.out.printf("\n%10s  %3d  %3d",characterslist.get(i).getName(),characterslist.get(i).getLvl(),characterslist.get(i).getExp());
            System.out.printf("        %4d  %4d  %4d  %4d",characterslist.get(i).getPs(),characterslist.get(i).getPd(),characterslist.get(i).getPratt(),characterslist.get(i).getPresq());
            System.out.printf("      %6s  %5s",characterslist.get(i).getRace(),(characterslist.get(i).getDevotion() == 'o')?"Orden":"Caos");
        }  
        System.out.println();
    }

    // Metodo para crear personajes

    public static void addCharac(){

        System.out.print("""
        \nSelecciona la raza
        1. Human
        2. Enano
        3. Maya
        4. Medium
        Seleciona la opcion: """);

        int option = inp.insert4op();

        System.out.print("""
        \nSelecciona la devoción
        1. Orden
        2. Caos
        Seleciona la opcion: """);
        char devotion = inp.insertDevotion();

        System.out.print("\nPon el nombre del personaje: ");

        String name = uniqueName(inp.insetString());

        System.out.println("\nPon las estadistcas.");
        int[] stats = pstats(0);

        System.out.print("""
            \nPon el arma que quires
            1. Daga.
            2. Espada
            3. Martillo de gerra
            
            Selecciona la opcion (1-3): """);
        char weapon = inp.insertWeapon();

        switch (option) {
            case 1:
                if(devotion == 'o'){
                    characterslist.add(new HumanOrder(name,"human",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }else{
                    characterslist.add(new HumanChaos(name,"human",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }
                return;

            case 2:
                if(devotion == 'o'){
                    characterslist.add(new DwarfOrder(name,"dwarf",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }else{
                    characterslist.add(new DwarfChaos(name,"dwarf",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }
                return;

            case 3:
                if(devotion == 'o'){
                    characterslist.add(new MayaOrder(name,"maya",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }else{
                    characterslist.add(new MayaChaos(name,"maya",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }
                return;

            case 4:
                if(devotion == 'o'){
                    characterslist.add(new MediumOrder(name,"medium",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }else{
                    characterslist.add(new MediumChaos(name,"medium",devotion,stats[0],stats[1],stats[2],stats[3],stats[4],weapon));
                }
                return;
        }

    }

    // to put the stats // poner cuantos puntos faltan por poner

    public static int[] pstats(int lvl) {
        int totalpoint = 60+(5*lvl);
        System.out.println("Estadisticas del 3 al 18");
        
        System.out.println("\nTienes "+totalpoint+" por poner.");
        System.out.print("Pon la Fuerza: ");
        
        int strength = inp.insertStats();
        totalpoint -= strength;

        System.out.println("\nTienes "+totalpoint+" por poner.");
        System.out.print("Pon la Constitucion: ");

        int con = inp.insertStats();
        totalpoint -= con;

        System.out.println("\nTienes "+totalpoint+" por poner.");
        System.out.print("Pon la Velocidad: ");

        int speed = inp.insertStats();
        totalpoint -= speed;

        System.out.println("\nTienes "+totalpoint+" por poner.");
        System.out.print("Pon la Inteligencia: ");

        int inte = inp.insertStats();
        totalpoint -= inte;

        System.out.println("\nTienes "+totalpoint+" por poner.");
        System.out.print("Pon la Suerte: ");

        int luck = inp.insertStats();
        totalpoint -= luck;

        int total = strength + con + speed + inte + luck;

        if (total != (60+(5*lvl))) {
            System.out.println("La cantidad de puntos no ha sido suficiento o a sobre pasado");
            return pstats(lvl);
        }

        int[] stats = {strength,con,speed,inte,luck};
        return stats;
    }

    // delete character

    public static void delateCharac(int indexCharac) {

        characterslist.remove(indexCharac);
        showCharact();
    }

    // To search character by name

    public static int searchCharac(String charname){

        for (int i = 0; i < characterslist.size(); i++) {
            if (characterslist.get(i).getName().equals(charname)) {
                return i;
            }
        }

        System.out.print("Nombre no encontrado prueva otra vez: ");
        charname = inp.insetString();
        return searchCharac(charname);
    }

    // For unique character Names

    public static String uniqueName (String charname){
        boolean unique = true;

        for (int i = 0; i < characterslist.size(); i++) {
            if (characterslist.get(i).getName().equals(charname)) {
                unique = false;
                break;
            }
        }
        if (!unique) {
            System.out.print("Ya existe el personaje prueva otra vez: ");
            charname = inp.insetString();
            return uniqueName(charname);
        }
        return charname;
    }

    // reconfugure stats

    public static void reStats(){

        System.out.println("\nReasignar estadisticas");

        showCharact();

        System.out.print("\nPon el nombre del personaje: ");
        int index = searchCharac(inp.insetString());

        int[] stats = pstats(characterslist.get(index).getLvl());

        characterslist.get(index).setSt(stats[0]);
        characterslist.get(index).setCon(stats[1]);
        characterslist.get(index).setSp(stats[2]);
        characterslist.get(index).setInte(stats[3]);
        characterslist.get(index).setLk(stats[4]);
        characterslist.get(index).scStats();
    }

    // combat

    public static void combat(CharacterP cv1, CharacterP cv2){

    while(cv1.getPs() > 1 && cv2.getPs() > 1){
            turn(cv1,cv2);
            if(cv1.getPs() < 1 || cv2.getPs() < 1) break;
            turn(cv2,cv1);
    }

    if (cv1.getPs() < 1) {
        System.out.println(cv2.getName()+" ha ganado\n");
        cv2.addexp(cv1.getInitalPs());

    }else if(cv2.getPs() < 1){
        System.out.println(cv1.getName()+" ha ganado\n");
        cv2.addexp(cv1.getInitalPs());
    }

    System.out.print("Enter para continuar: ");
    inp.onlEnter();

    cv1.restorePS();
    cv1.restorePS();
    }
    
    // turn

    public static void turn(CharacterP cvf , CharacterP cvs) {
            boolean attck = cvf.attck(dado(3));
            boolean dodge = cvs.dodge(dado(3));
            System.out.println("------------------------------------------------------------------------");
            // mejorar sistema de prints
        if (attck) {
            System.out.println(cvf.getName()+" a intentado atacar.");
            if (dodge) {
                System.out.println("Pero "+cvs.getName()+" a podido esquivar.");
                chaosPassive(cvf, cvs);
            }else{
                System.out.println(cvf.getName()+" ataca a "+cvs.getName()+" y le quita "+cvf.getPd());
                orderPassive(cvf);
                int cvsPs = cvs.getPs();
                cvs.setPs(cvsPs-cvf.getPd());
            }
        }else{
            System.out.println(cvf.getName()+" no ha podido atacar a "+cvs.getName());
        }
        System.out.println();
        System.out.println(cvf.getName()+" tiene "+cvf.getPs()+" de vida");
        System.out.println(cvs.getName()+" tiene "+cvs.getPs()+" de vida");

        System.out.println("------------------------------------------------------------------------");
    }

    // Dados

    public static int dado(int tiradas) {
        int result = 0;;
        for (int i = 0; i < tiradas; i++) {
            result += (int)Math.floor(Math.random() * (25- 1 + 1) + 1);
        }
        return result;
    }

    // pasivas del orden i caos
    
    public static void orderPassive(CharacterP cvf) {
        if (cvf.getDevotion() == 'o') {

            System.out.println(cvf.getName()+" se ha curado "+((cvf.getInitalPs()*10)/100)+" de vida");
            switch (cvf.getRace()) {
                case "human":
                    ((HumanOrder)cvf).orderPassive();
                    break;
                case "dwarf":
                    ((DwarfOrder)cvf).orderPassive();
                    break;
                case "maya":
                    ((MayaOrder)cvf).orderPassive();
                    break;
                case "madium":
                    ((MediumOrder)cvf).orderPassive();
                    break;
            }
        }
    }

    public static void chaosPassive(CharacterP cvf , CharacterP cvs) {
        if(cvs.getDevotion()== 'c'){

            boolean cattack = false;
            switch (cvs.getRace()) {
                case "human":
                    cattack = ((HumanChaos)cvs).chaosPassive(dado(3));
                    break;
                case "dwarf":
                    cattack = ((DwarfChaos)cvs).chaosPassive(dado(3));
                    break;
                case "maya":
                    cattack = ((MayaChaos)cvs).chaosPassive(dado(3));
                    break;
                case "medium":
                    cattack = ((MediumChaos)cvs).chaosPassive(dado(3));
                    break;
            }

            if (cattack) {
                System.out.println(cvs.getName()+" Ha utilizado su devocion contra "+cvf.getName()+" y le quita "+cvs.getPd());
                int cvfPs = cvf.getPs();
                cvf.setPs(cvfPs-cvs.getPd());
            }
        }
    }

    // limpiar pantalla

    public static void clean() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}