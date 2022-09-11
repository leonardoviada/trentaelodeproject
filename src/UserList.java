import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserList{
    private HashMap<String, User> userMap; //ogni chiave è l'username
    String path;

    public UserList(String path){
        userMap = new HashMap<String, User>();
        this.path = path;
        update();
    }
    // prende dal DB l'elenco degli utenti e riempia userMap<username,User>
    public void update(){
        Gson gson = new Gson();
        Type fooType = new TypeToken<User>() {}.getType();
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while(line!=null) {
                User u = gson.fromJson(line,fooType);
                this.userMap.put(u.getUsername(),u);
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }  finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void persist(){
        Iterator it;
        it = userMap.entrySet().iterator();
        FileWriter fw = null;
        PrintWriter pw = null;
        try{
            fw = new FileWriter(path);
            pw = new PrintWriter(fw);
            while (it.hasNext()) {
                Map.Entry<String, User> entry = (Map.Entry)it.next();
                pw.println(entry.getValue().toJson());
            }
        } catch (IOException e) {
            System.out.println("Percorso file non valido");
        }
        finally{
            try{
                fw.close();
                pw.close();
            } catch (IOException e){System.out.println("Errore di chiusura");}
        }

    }



    public HashMap<String, User> getUserList() {
        return userMap;
    }

    public void setUserList(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }
}
