package it.unibo.trentalode.bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

public class IOManager {
    /**
     * Genera un oggetto notizia leggendo una riga del file json
     *
     * @param json
     * @return
     */
    public static News fromJson(String json) {
        Gson gson = new Gson();
        Type fooType = new TypeToken<News>() {
        }.getType();
        News n = gson.fromJson(json, fooType);
        return n;
    }

    public static HashMap<String, News> getNewsByCategory(Categories c) {
        HashMap<String, News> newsList = new HashMap<String, News>();
        BufferedReader br;
        FileReader fr;
        try {
            fr = new FileReader(c.label);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                newsList.put(fromJson(line).getId(), fromJson(line));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newsList;
    }
}
