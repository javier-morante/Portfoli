import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Date;

public class ReadWrite implements ConsoleColors{
    static String down;
    static String separe;
    File fileUser;
    File fileHistory;
    
    

    public  ReadWrite(String fileNameUsers, String fileNameHistory) {
        down = "\n";
        separe = "-----------";


        fileUser = new File(fileNameUsers);

        fileHistory = new File(fileNameHistory);
    }

    public void writeUsers(HashMap<String,Integer[]> userPoints) throws IOException{
        FileWriter fw = new FileWriter(fileUser);
        BufferedWriter bw = new BufferedWriter(fw);

        for (String key : userPoints.keySet()) {
            bw.write(separe+down);
            bw.write(key+":"+userPoints.get(key)[0]+":"+userPoints.get(key)[1]+":"+userPoints.get(key)[2]+down);
        }
        bw.write(separe+down);
        bw.close();

    }

    public HashMap<String,Integer[]> readUsers() throws IOException {
        HashMap<String,Integer[]> users = new HashMap<String,Integer[]>();
        try {
            FileReader fr = new FileReader(fileUser);
            BufferedReader br = new BufferedReader(fr);
            String data;

            while((data = br.readLine())!=null){

                if (data.indexOf(":") >= 0) {
                String[] userPoint = data.split(":");
                Integer[] pwl = {Integer.parseInt(userPoint[1]),Integer.parseInt(userPoint[2]),Integer.parseInt(userPoint[3])};
                users.put(userPoint[0],pwl);
                }
            }
            
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return users;
    }

    public void writeHistory(String winner, String losser,boolean tie) throws IOException{
        FileWriter fw = new FileWriter(fileHistory,true);
        BufferedWriter bw = new BufferedWriter(fw);
        Date actDate = new Date();
        if (tie) {
            bw.write(actDate+"-E-"+winner+"-"+losser+down);
        }else{
            bw.write(actDate+"-W-"+winner+"-"+losser+down);
        }
        bw.close();
    }

    public void showHistory() throws IOException{
        try {
            FileReader fr = new FileReader(fileHistory);
            BufferedReader br = new BufferedReader(fr);
            String data;

            while ((data = br.readLine())!=null) {

                String[] datas = data.split("-");
                
                if(data.indexOf("-") != -1 )
                System.out.println(white_BOLD+separe+separe+separe+separe+separe+separe);
                switch (datas[1]) {
                    case "W":
                        System.out.printf(green_UNDERLINED+"%s"+reset+white_BOLD+" -> "+cyan_BOLD+"%s "+white_BOLD+"ha "+yellow_BOLD+"ganado"+white_BOLD+" a "+cyan_BOLD+"%s\n",datas[0],datas[2],datas[3]);
                        break;
                
                        case "E":
                            System.out.printf(green_UNDERLINED+"%s "+reset+white_BOLD+"-> "+cyan_BOLD+"%s "+white_BOLD+"i "+cyan_BOLD+"%s "+white_BOLD+"han empatado\n",datas[0],datas[2],datas[3]);
                        break;
                }
            }
            System.out.println(white_BOLD+separe+separe+separe+separe+separe+separe);
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        
        
    }
}