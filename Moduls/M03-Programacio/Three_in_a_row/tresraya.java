import java.util.Scanner;


public class tresraya {
    public static Scanner inp = new Scanner(System.in);
    public static int option = 0;
    public static void main(String[] args) {
        
        clearScreem();
        System.out.println("""
        Bienvenido al tres en raya diseñado por \033[1;32mJavier Morante\033[0m,

        Un juego en el que tienes que hacer una linia en \033[1;34mvertical\033[0m, \033[1;34mhorizontal \033[0mo \033[1;34mdiagonal \033[0mde tu simbolo ya sea \033[1;32mX \033[0mo \033[1;34mO \033[0mpara \033[1;33mganar\033[0m.

        Podras escojer el tamaño de la cuadricula con cada partida.

        En caso de que se quiera salir del juego pon \033[1;34mexit \033[0mo \033[1;34msalir \033[0men cualquier momento.

        Al terminar una partida se preguntara si quires jugar otra en caso de no querer pon no i en caso de que si pon si;

        Por favor seleccione como quere como quiere escojer su posición:
        - Pon una '\033[1;36mp\033[0m' en caso de querer por filas i columnas
        - Pon una '\033[1;36mn\033[0m'si quires selecionar por un número que deterninara la posición por ejemplo
            |\033[1;34m1\033[0m|\033[1;34m2\033[0m|\033[1;34m3\033[0m|
            |\033[1;34m4\033[0m|\033[1;34m5\033[0m|\033[1;34m6\033[0m|
            |\033[1;34m7\033[0m|\033[1;34m8\033[0m|\033[1;34m9\033[0m|
        """);
        System.out.print("\033[1;35mElije opción: \033[1;36m"); 
        option = pedirValor('o');
        game(option);
    }

    public static void game(int option) {
        clearScreem();
        int cont = 0;
        // pedir medidas de la tabla
        System.out.print("\033[1;35mTamaño de la tabla (3-10): \033[1;36m");
        int table_size = pedirValor('t');
        
        // Poner vacios
        char[][] tabla = new char[table_size][table_size]; 
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {
                tabla[i][j] = ' ';
            }
        }

        // joc (pedir les posicions i les verificacions)
        while(cont < table_size*table_size){
            
            char player = (cont % 2 == 0)?'X':'O';
            clearScreem();
            showTable(tabla);
            tabla = players_Position(tabla,player);
            veryHorizontal(tabla, player);
            veryVertical(tabla, player);
            veryDiagonal(tabla, player);
            cont++;
            
        }
        // En cas de empat
        clearScreem();
        showTable(tabla);
        System.out.println("\033[1;93mEl juego a estado empate\033[0m");
        anotherGame();
    }

    //función que verifica la horizontal

    public static void veryHorizontal(char [][] tabla, char player) {
        int cont = 0;

        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {

                if(tabla[i][j] == ((player == 'X')?'X':'O'))cont++;

            }
            if(cont == tabla.length)win(player,tabla);
            cont = 0;
        }
    }

    //función que verifica la Vertical

    public static void veryVertical(char [][] tabla,char player) {
        int cont = 0;
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[0].length; j++) {

                if(tabla[j][i] == ((player == 'X')?'X':'O'))cont++;
                
            }
            if(cont == tabla.length)win(player,tabla);
            cont = 0;
        }
    }
    
    //función que verifica la Diagonal

    public static void veryDiagonal(char [][] tabla,char player) {
        // diagonal de izquierda a derecha
        int cont = 0;
        for (int i = 0; i < tabla.length; i++) {
            if(tabla[i][i] == ((player == 'X')?'X':'O'))cont++;
        }
        if(cont == tabla.length)win(player,tabla);
        cont = 0;
        // diagonal de derecha a izquierda
        for (int i = 0; i < tabla.length; i++) {
            int y = tabla[0].length -(1+i);
            if(tabla[i][y] == ((player == 'X')?'X':'O'))cont++;
        }
        if(cont == tabla.length)win(player,tabla);
    }

    // función para poner las posiciones en la tabla 

    public static char[][] players_Position(char[][] tabla,char player) {
        
        
        System.out.println("\033[1;96mJugador \033[1;32m"+((player == 'X')?"\033[1;32m"+player:"\033[1;94m"+player)+" \033[1;96mPonga su posición\u001B[0m");

        // opciones dependendo si es poe filar i columnas o por el número de la posición
            switch (option){
                // opción filas i columnas 
                case 1:
                    System.out.print("\033[1;35mPosición de la fila: \033[1;36m");
                    int fil = pedirValor('p')-1;

                    System.out.print("\033[1;35mPosición de la columna: \033[1;36m");
                    int col = pedirValor('p')-1;
                    System.out.print("\033[0m");
                
                    // En caso de que este ocupado o este fuera de los limites
                    if( tabla.length-1 < fil || tabla.length-1 < col || 0 > fil || 0 > col || tabla[fil][col] != ' '){
                        System.out.println("\033[1;31mPosición no valida");
                        players_Position(tabla,player);
                        return tabla;
                    }
            
                    tabla[fil][col] = ((player == 'X')?'X':'O');
                break;
                // Opción por número de posición
                case 2:
                    System.out.print("\033[1;35mNúmero de la posición: \033[1;36m");
                    int position = pedirValor('p');
                    if( 1 > position || (tabla.length * tabla.length) < position ){
                        System.out.println("\033[1;31mPosición no valida");
                        players_Position(tabla,player);
                        return tabla;
                    } 
                    int cont = 0;
                    for (int i = 0; i < tabla.length; i++) {

                        for (int j = 0; j < tabla[0].length; j++) {
                            cont++;
                            // verificar si ha llegado a la posición correcta
                            if(cont == position){
                                // verificar Si esta ocupado o no
                                switch (tabla[i][j]) {
                                    case ' ':
                                        
                                        tabla[i][j] = ((player == 'X')?'X':'O');
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

    public static void showTable(char [][] tabla) {
        for (int i = 0; i < tabla.length; i++) {
            System.out.print("\033[0m|");
            for (int j = 0; j < tabla[0].length; j++) {
                // Para printar el signo dependiendo el color
                System.out.printf("%s\033[0m|",((tabla[i][j] == 'X')?"\033[1;32m"+tabla[i][j]:"\033[1;34m"+tabla[i][j]));
                
            }
            System.out.println();
        }
    }

    // funcio per demana diferents valors

    public static int pedirValor(char optionp) {
        try{
            String num = inp.next().toLowerCase();
            

            if(num.equals("exit") || num.equals("salir")){
                System.out.print("\033[0m");
                System.exit(0);
            }

            switch (optionp) {
                case 'o':

                    if(num.equals("n") || num.equals("p")){
                    System.out.println("\033[1;33m¡¡¡GRACIAS POR JUGAR!!!\033[1;30m");
                    return (num.equals("p"))?1:2;
    
                    }else{
                        System.out.print("\033[1;36m"+num+" no es una opción porfavor introduxca (\033[1;36mn o \033[1;36mp): ");
                        return pedirValor(optionp);
                    }
                
                case 't':
                    // En caso de que el tamaño de la tabla quiera ser mallor de 10 o mejor de 3 se ejecutara
                    if(Integer.parseInt(num) > 10 || Integer.parseInt(num) < 3){
                    
                    System.out.println("\033[1;31mValor fuera de rango");
                    System.out.print("\033[1;35mPrueba otra vez: \033[1;36m");
                    return pedirValor(optionp);

                    }
                    break;
                
                case 'a':
                // Opciónes del si i no al acavar una partida
                if(num.equals("si") || num.equals("no")){

                    return (num.equals("no"))?0:1;
    
                    }else{
                        System.out.print("\033[1;36m"+num+"\033[0m no es una opción porfavor introduxca (\033[1;36msi \033[0m o \033[1;36mno\033[0m ): ");
                        return pedirValor(optionp);
                    }
                default:
                    break;
            }

            return Integer.parseInt(num);

            
        }catch(Exception e){
            //En caso de error

            System.out.println("\033[1;31mValor introducido no está correcto");
            System.out.print("\033[1;35mPrueba otra vez: \033[1;36m");
            inp.nextLine();
            return pedirValor(optionp);
        }
    }

    // función para cuando ganas

    public static void win(char player,char[][] tabla) {
        clearScreem();
        showTable(tabla);
        System.out.println("\033[1;93mPlayer "+((player == 'X')?"\033[1;32mX":"\033[1;94m O")+" \033[1;93mWIN\033[0m");
        // falta opción de volver ha hacer otra partida
        anotherGame();
    }
    
    // funcion para preguntar si quieres una partiga

    public static void anotherGame() {
        System.out.print("\033[1;37mQuieres jugar otra partida? :");
        int optiona = pedirValor('a');
        if(optiona == 0){
                System.out.println("\033[1;33m¡¡¡GRACIAS POR JUGAR!!!\033[1;30m");
                System.exit(0);

        }else{
            game(option);
        }
    }
    //función para limpiar la pantalla

    public static void clearScreem() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }
}