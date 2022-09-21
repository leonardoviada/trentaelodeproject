package it.unibo.trentalode.bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IOManager {
    public static final HashMap<Categories, String> feedRSSMap = new HashMap<>();

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

    /**
     * legge il feed RSS da myUrl, genera oggetti News per ogni notizia del feed e li scrive su un file json al percorso path
     * la categoria serve per aggiungere la lettera corretta in testa all'id della notizia, nel formato lettera maiuscola+intero hash positivo, es. E1246433
     */
    public static void persistFeedRSS() {
        URL url = null;
        XmlReader reader = null;
        String myUrl;
        Categories cat;
        FileWriter fw = null;
        PrintWriter pw;
        News news;

        Iterator it;
        it = IOManager.feedRSSMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Categories, String> en = (Map.Entry) it.next();
            myUrl = en.getValue();
            cat = en.getKey();

            try {
                url = new URL(myUrl);
            } catch (MalformedURLException e) {
                System.out.println("Attenzione, URL non valido");
            }
            try {
                assert url != null;
                reader = new XmlReader(url);
            } catch (IOException e) {
                System.out.println("Attenzione, IOExc");
            }
            try {
                fw = new FileWriter(cat.label);
            } catch (IOException e) {
                System.out.println("Attenzione, IOExc2");
            }
            assert fw != null;
            pw = new PrintWriter(fw);

            SyndFeed feed;
            try {
                feed = new SyndFeedInput().build(reader);
            } catch (FeedException e) {
                throw new RuntimeException(e);
            }
            for (SyndEntry entry : (Iterable<SyndEntry>) feed.getEntries()) {
                //Iteriamo tutte le voci presenti nel nostro feed e ne stampiano le proprietà fondmentali
                news = new News(entry.hashCode(), entry.getAuthor(), entry.getTitle(), entry.getDescription().getValue(), "Feed RSS", entry.getLink(), cat, entry.getPublishedDate());
                pw.println(news.toJson());
            }
            //Chiudiamo lo stream precedentemente aperto.
            if (reader != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    System.out.println("Attenzione,IOExc3");
                }
                pw.close();
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Attenzione, IOExc4");
                }
            }
        }
    }

    /**
     * Legge da file l'elenco di feed RSS a cui collegarsi
     *
     * @param path
     */
    public static void uploadFeedRSSMap(String path) {
        BufferedReader br = null;
        FileReader fr = null;
        try {
            fr = new FileReader(path);
            br = new BufferedReader(fr);
            String line = br.readLine();
            while (line != null) {
                feedRSSMap.put(Categories.valueOf(line.substring(0, line.indexOf(","))), line.substring(line.indexOf(",") + 1));
                line = br.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
