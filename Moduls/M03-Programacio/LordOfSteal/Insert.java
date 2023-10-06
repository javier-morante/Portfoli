import java.util.Scanner;

public class Insert {
    private Scanner inp;

    public Insert(){

        inp = new Scanner(System.in);
    }

    public int insertMain(){
        try {
            int data = Integer.parseInt(inp.nextLine());

            if(data < 0 || data > 6){
                System.out.print("Fuera de rango (0-6): ");
                return insertMain();
            }
            return data;
        } catch (Exception e) {
            System.out.print("Valor incorrecto solo del 1 al 6:");
            return insertMain();
        }
    }

    public int insert4op(){
        int data = 0;
        try {
                data = Integer.parseInt(inp.nextLine());

                if(data < 0 || data > 4){
                    System.out.print("Fuera de rango (0-4): ");
                    return insert4op();
                }

        } catch (Exception e) {
            System.out.print("Valor incorrecto solo del 1 al 4:");
            return insert4op();
        }
        return data;
    }

    public char insertDevotion(){
        try {
            int data = Integer.parseInt(inp.nextLine());
            switch (data) {
                case 1:
                    return 'o';
        
                case 2:
                    return 'c';
            
                default:

                    return insertDevotion();
                    
            }

        } catch (Exception e) {
            System.out.print("Valor incorrecto solo del 1 al 2: ");
            return insertDevotion();
        }

    }

    public String insetString(){

        String name = inp.nextLine();

        return name;
    }

    public int insertStats(){
        try {
            
            int data = Integer.parseInt(inp.nextLine());

            if (data < 3 || data > 18) {
                System.out.print("Fuera de rango (3-18): ");
                return insertStats();
            }

            return data;

        } catch (Exception e) {
            System.out.print("Datos incorrectos prueva otra vez: ");
            return insertStats();
        }
    }

    public char insertWeapon(){
        try {
            int option = Integer.parseInt(inp.nextLine());

            if (option < 1 || option > 3) {
                return insertWeapon();
            }

            char weapon = ' ';

            switch (option){
                case 1:
                    weapon = 'd';
                    return weapon;

                case 2: 
                    weapon = 's';
                    return weapon;    

                case 3:
                    weapon = 'w';
                    return weapon;
            }
            return weapon;

        } catch (Exception e) {
            return insertWeapon();
        }

    }

    public void onlEnter() {
        inp.nextLine();
    }
}