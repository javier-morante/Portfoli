import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Tresraya implements ConsoleColors{
    private static Scanner inp = new Scanner(System.in);
    private static int option = 0;
    private static HashMap<String,Integer[]> usersPoints;
    private static HashMap<Character,String> players = new HashMap<Character,String>();

    private static ReadWrite persis = new ReadWrite("users.txt", "history.txt");

    public static void main(String[] args) throws IOException {

        usersPoints = persis.readUsers();

        menu();

    }

    public static void menu() throws IOException {
        while (true) {
            clearScreem();
            System.out.println(white_BOLD+"""
                BENVINGUTS AL 3 EN RATLLA

                Tria la opció que vols:

                1.- Jugar
                2-  Ver Ranking
                3.- Ver historal de partidas
                0.- Salir
                    """);
        System.out.print(purple_BOLD+"opcion: "+cyan_BOLD);
        int option = pedirValor('m');
        switch (option) {
            case 1:
                player();
                break;
            case 2:
                ranking();
                break;
            case 3:
                historic();
                break;
            case 0:
            persis.writeUsers(usersPoints);
            System.exit(0);
        }
        }
    }

    //Ensenyar Historial

    public static void historic() throws IOException{
        clearScreem();
        System.out.println(blue_BOLD+"Historial de partidas");
        persis.showHistory();
        enter();
        clearScreem();
        menu();
    }

    //Ensenyar ranking

    public static void ranking() {
        HashMap<String,Integer[]> cpUsers = new HashMap<String,Integer[]>();
        ArrayList<String> users = new ArrayList<String>();
        cpUsers.putAll(usersPoints);
        clearScreem();
        System.out.println("Ranking");

        int indexs = cpUsers.size();

        for (int i = 0; i < indexs; i++) {
            int v = 0;
            String vs = null;
            for (String v2 : cpUsers.keySet()) {
                
                if(v <= cpUsers.get(v2)[0]){
                    vs = v2;
                    v = cpUsers.get(vs)[0];
                }
            }
            cpUsers.remove(vs);
            users.add(vs);
        }

        sortWinRate(users);

        System.out.printf(green_BOLD+"%9s "+cyan_BOLD+"%10s  "+red_BOLD+"%6s\n","POSITION","NAME","POINTS");
        for (int i = 0; i < users.size(); i++) {
            System.out.printf(green_BOLD+"%8d. "+cyan_BOLD+"%10s  "+red_BOLD+"%6d\n",(i+1),users.get(i),usersPoints.get(users.get(i))[0]);
        }

        enter();
    }

    // Juego

    public static void game(String player1 , String player2) throws IOException{
        
        players.put('X', player1);
        players.put('O', player2);
        clearScreem();
        System.out.println("""

                Por favor seleccione como quere como quiere escojer su posición:
                - Pon una '\033[1;36mp\033[0m' en caso de querer por filas i columnas
                - Pon una '\033[1;36mn\033[0m'si quieres seleccionar por un número que deterninara la posición por ejemplo
                    |\033[1;34m1\033[0m|\033[1;34m2\033[0m|\033[1;34m3\033[0m|
                    |\033[1;34m4\033[0m|\033[1;34m5\033[0m|\033[1;34m6\033[0m|
                    |\033[1;34m7\033[0m|\033[1;34m8\033[0m|\033[1;34m9\033[0m|
                """);
        System.out.print("\033[1;35mElije opción: \033[1;36m");
        option = pedirValor('o');

        clearScreem();
        int cont = 0;
        // pedir medidas de la tabla
        System.out.print(purple_BOLD+"Tamaño de la tabla (3-10): \033[1;36m");
        int table_size = pedirValor('t');

        // Poner vacios
        char[][] tabla = new char[table_size][table_size];
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {
                tabla[i][j] = ' ';
            }
        }

        // joc (pedir les posicions i les verificacions)
        while (cont < table_size * table_size) {
            clearScreem();
            char player = (cont % 2 == 0) ? 'X' : 'O';
            showTable(tabla);
            tabla = players_Position(tabla, player);
            veryHorizontal(tabla, player);
            veryVertical(tabla, player);
            veryDiagonal(tabla, player);

            cont++;

        }

        // En cas de empat
        clearScreem();
        usersPoints.get(players.get('X'))[0] += 1;
        usersPoints.get(players.get('O'))[0] += 1;
        persis.writeHistory(player1, player2, true);
        persis.writeUsers(usersPoints);
        showTable(tabla);
        System.out.println("\033[1;93mEl juego a estado empate\033[0m");
        anotherGame();
    }

    // función que verifica la horizontal

    public static void veryHorizontal(char[][] tabla, char player) throws IOException{
        int cont = 0;

        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {

                if (tabla[i][j] == player)
                    cont++;

            }
            if (cont == tabla.length)
                win(player, tabla);
            cont = 0;
        }
    }

    // función que verifica la Vertical

    public static void veryVertical(char[][] tabla, char player) throws IOException{
        int cont = 0;
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {

                if (tabla[j][i] == player)
                    cont++;

            }
            if (cont == tabla.length)
                win(player, tabla);
            cont = 0;
        }
    }

    // función que verifica la Diagonal

    public static void veryDiagonal(char[][] tabla, char player) throws IOException{
        // diagonal de izquierda a derecha
        int cont = 0;
        for (int i = 0; i < tabla.length; i++) {
            if (tabla[i][i] == player)
                cont++;
        }
        if (cont == tabla.length)
            win(player, tabla);
        cont = 0;
        // diagonal de derecha a izquierda
        for (int i = 0; i < tabla.length; i++) {
            int y = tabla[0].length - (1 + i);
            if (tabla[i][y] == player)
                cont++;
        }
        if (cont == tabla.length)
            win(player, tabla);
    }

    // función para poner las posiciones en la tabla

    public static char[][] players_Position(char[][] tabla, char player) {

        System.out.println(
                cyan_BOLD+"Jugador \033[1;32m" + ((player == 'X') ? "\033[1;32m" + players.get(player) : "\033[1;94m" + players.get(player))
                        + " \033[1;96mPonga su posición\u001B[0m");

        // opciones dependendo si es poe filar i columnas o por el número de la posición
        switch (option) {
            // opción filas i columnas
            case 1:
                System.out.print("\033[1;35mPosición de la fila: \033[1;36m");
                int fil = pedirValor('p') - 1;

                System.out.print("\033[1;35mPosición de la columna: \033[1;36m");
                int col = pedirValor('p') - 1;
                System.out.print("\033[0m");

                // En caso de que este ocupado o este fuera de los limites
                if (tabla.length - 1 < fil || tabla.length - 1 < col || 0 > fil || 0 > col || tabla[fil][col] != ' ') {
                    System.out.println("\033[1;31mPosición no valida");
                    players_Position(tabla, player);
                    return tabla;
                }

                tabla[fil][col] = player;
                break;
            // Opción por número de posición
            case 2:
                System.out.print("\033[1;35mNúmero de la posición: \033[1;36m");
                int position = pedirValor('p');
                if (1 > position || (tabla.length * tabla.length) < position) {
                    System.out.println("\033[1;31mPosición no valida");
                    players_Position(tabla, player);
                    return tabla;
                }
                int cont = 0;
                for (int i = 0; i < tabla.length; i++) {

                    for (int j = 0; j < tabla[0].length; j++) {
                        cont++;
                        // verificar si ha llegado a la posición correcta
                        if (cont == position) {
                            // verificar Si esta ocupado o no
                            switch (tabla[i][j]) {
                                case ' ':

                                    tabla[i][j] = player;
                                    return tabla;

                                default:
                                    System.out.println("\033[1;31mPosición ocupada");
                                    return players_Position(tabla, player);
                            }

                        }
                    }
                }
                break;

        }
        return tabla;

    }

    // función para mostrar la tabla

    public static void showTable(char[][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("\033[0m|");
            for (int j = 0; j < tabla[0].length; j++) {
                // Para printar el signo dependiendo el color
                System.out.printf("%s\033[0m|",
                        ((tabla[i][j] == 'X') ? green_BOLD + tabla[i][j] : blue_BOLD + tabla[i][j]));

            }
            System.out.println();
        }
    }

    // funcio per demana diferents valors

    public static int pedirValor(char optionp) {
        try {
            String num = inp.nextLine().toLowerCase();

            if (num.equals("exit") || num.equals("salir")) {
                persis.writeUsers(usersPoints);
                System.out.print("\033[0m");
                System.exit(0);
            }

            switch (optionp) {
                case 'o':

                    if (num.equals("n") || num.equals("p")) {
                        System.out.println("\033[1;33m¡¡¡GRACIAS POR JUGAR!!!\033[1;30m");
                        return (num.equals("p")) ? 1 : 2;

                    } else {
                        System.out.print("\033[1;36m" + num
                                + " no es una opción porfavor introduxca (\033[1;36mn o \033[1;36mp): ");
                        return pedirValor(optionp);
                    }

                case 't':
                    // En caso de que el tamaño de la tabla quiera ser mallor de 10 o mejor de 3 se
                    // ejecutara
                    if (Integer.parseInt(num) > 10 || Integer.parseInt(num) < 3) {

                        System.out.println("\033[1;31mValor fuera de rango");
                        System.out.print("\033[1;35mPrueba otra vez: \033[1;36m");
                        return pedirValor(optionp);

                    }
                    break;

                case 'a':
                    // Opciónes del si i no al acavar una partida
                    if (num.equals("si") || num.equals("no")) {

                        return (num.equals("no")) ? 0 : 1;

                    } else {
                        System.out.print("\033[1;36m" + num
                                + "\033[0m no es una opción porfavor introduxca (\033[1;36msi \033[0m o \033[1;36mno\033[0m ): ");
                        return pedirValor(optionp);
                    }
                case 'm': 
                if (Integer.parseInt(num) > 3 || Integer.parseInt(num) < 0 ) {

                    System.out.println("\033[1;31mValor fuera de rango");
                    System.out.print("\033[1;35mPrueba otra vez: \033[1;36m");
                    return pedirValor(optionp);

                }
                break;

                default:
                    break;
            }

            return Integer.parseInt(num);

        } catch (Exception e) {
            // En caso de error

            System.out.println("\033[1;31mValor introducido no está correcto");
            System.out.print("\033[1;35mPrueba otra vez: \033[1;36m");
            // inp.nextLine();
            return pedirValor(optionp);
        }
    }

    //metode per agafar els noms

    public static String plNames() throws IOException{
        String name = inp.nextLine();
        if (name.equals("exit") || name.equals("salir")) {
            persis.writeUsers(usersPoints);
            System.out.print("\033[0m");
            System.exit(0);
        }

        // per comprovar si existeix l'usuari

        boolean userExist = false;
        for (String key : usersPoints.keySet()) {
            if (name.equals(key)){
                userExist =  true;
                break;
            }
        }

        if (!userExist) {
            Integer[] newUSer = {0,0,0};
            usersPoints.put(name,newUSer);
            System.out.println(blue_BOLD+"Bienvenido "+green_BOLD+name);
        }

        return name;

    }

    // función para cuando ganas

    public static void win(char player, char[][] tabla) throws IOException{
        clearScreem();
        
        // escriure en l'arxiu de history
        persis.writeHistory(((player == 'X') ? players.get('X'):players.get('O')), ((player == 'O') ? players.get('X'):players.get('O')), false);
        String key = players.get(player);

        // donar punts
        usersPoints.get(key)[0]+=3;
        // poser wins i derrotas
        usersPoints.get(key)[1]+=1;

        usersPoints.get((player == 'O') ? players.get('X'):players.get('O'))[2]+= 1;

        showTable(tabla);
        System.out.println("\033[1;93mPlayer " + ((player == 'X') ? "\033[1;32m" + key : "\033[1;94m" + key)
                + " \033[1;93mWIN\033[0m");
        
        // guardar
        persis.writeUsers(usersPoints);

        anotherGame();
    }

    // funcion para preguntar si quieres una partiga

    public static void anotherGame() throws IOException{
        System.out.print("\033[1;37mQuieres jugar otra partida? :");
        int optiona = pedirValor('a');
        if (optiona == 0) {
            menu();

        } else {
            game(players.get('X'),players.get('O'));
        }
    }

    // enter para continuar

    public static void enter() {
        System.out.print(white_BOLD+"\nEnter para continuar:"+reset);
        inp.nextLine();
    }
    
    // función para limpiar la pantalla

    public static void clearScreem() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    // method player to assign player to game

    public static void player() throws IOException{
        clearScreem();
        System.out.print(purple_BOLD+"Jugador 1: "+cyan_BOLD);
        String player1 = plNames();

        System.out.println(blue_BOLD+"Eres el jugador de las "+green_BOLD+"X"+reset);

        System.out.print(purple_BOLD+"\nJugador 2: "+cyan_BOLD);
        String player2 = plNames();

        while (player2.equals(player1)) {
            System.out.print(red_BOLD +"No se puede tener el mismo jugador pon otro :");
            player2 = plNames();
        }
        System.out.println(blue_BOLD+"Eres el jugador de las "+blue_BOLD+"O"+reset);

        enter();
        clearScreem();
        game(player1,player2);
        
    }

    public static void sortWinRate(ArrayList<String> users){
        String tmp;
        for (int i = 0; i < users.size(); i++) {

            for (int j = 0; j < (users.size()-1); j++) {

                if (usersPoints.get(users.get(j))[0] == usersPoints.get(users.get(j+1))[0]) {
                    int plw1 = winrateCalc(usersPoints.get(users.get(j))[1],usersPoints.get(users.get(j))[2]);
                    int plw2 =winrateCalc(usersPoints.get(users.get(j+1))[1],usersPoints.get(users.get(j+1))[2]);

                    if (plw1 < plw2) {
                        tmp = users.get(j);
                        users.set(j, users.get(j+1));
                        users.set(j+1, tmp);
                    }
                }
            }
        }
    }

    public static int winrateCalc(int win, int lose) {
        try {
        int total = win+lose;

        return (win*100)/total;
        } catch (Exception e) {
            return 0;
        }
        
    }
}