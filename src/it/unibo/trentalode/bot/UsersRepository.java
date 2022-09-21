package it.unibo.trentalode.bot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UsersRepository {
    String path;
    private HashMap<String, User> userMap; //ogni chiave è l'username

    public UsersRepository(String path) {
        userMap = new HashMap<>();
        this.path = path;
        update();
    }

    // prende dal DB l'elenco degli utenti e riempia userMap<username,User>
    public void update() {
        Gson gson = new Gson();

        try {
            Reader reader = Files.newBufferedReader(Paths.get(ConfigProvider.getInstance().getProperty("USERS_PATH")));
            while (reader.ready()) {
                User[] users = gson.fromJson(reader, User[].class);
                for (User u : users) {
                    this.userMap.put(u.getUsername(), u);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void persist() {
        Iterator it;
        it = userMap.entrySet().iterator();
        FileWriter fw = null;
        PrintWriter pw = null;
        Gson gson = new Gson();
        try {
            fw = new FileWriter(path);
            pw = new PrintWriter(fw);
            JsonArray usersArray = new JsonArray();

            while (it.hasNext()) {
                Map.Entry<String, User> entry = (Map.Entry) it.next();
                JsonElement jsonElement = gson.toJsonTree(entry);
                JsonObject jsonObject = (JsonObject) jsonElement;
                usersArray.add(jsonObject.get("value"));
            }

            pw.print(usersArray);
        } catch (IOException e) {
            System.out.println("Percorso file non valido");
        } finally {
            try {
                assert fw != null;
                fw.close();
                assert pw != null;
                pw.close();
            } catch (IOException e) {
                System.out.println("Errore di chiusura");
            }
        }

    }


    public HashMap<String, User> getUserList() {
        return userMap;
    }

    public void setUserList(HashMap<String, User> userMap) {
        this.userMap = userMap;
    }
}
