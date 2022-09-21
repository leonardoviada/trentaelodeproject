package it.unibo.trentalode.bot;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class News {
    private String id;
    private String author;
    private String title;
    private String body;
    /**
     * La fonte da cui proviene (testate giornalistiche, siti, ecc)
     */
    private String source;
    /**
     * link se la notizia è pubblicata online
     */
    private String link;
    private Categories category;
    private HashMap<Integer, Comment> comments;
    private Date dateTime;
    /**
     * Rating della notizia da 0 a 10
     */
    private ArrayList<Double> rating;


    /**
     * Costruttore completo
     * l'id in formato intero viene trasformato in stringa aggiungendo in testa alla stringa la lettera corrispondente alla categoria
     */
    public News(int id, String author, String title, String body, String source, String link, Categories category, Date dateTime) {
        if (id < 0) {
            id = -1 * id;
        }
        switch (category) {
            case POLITICS:
                this.id = "P" + id;
                break;
            case ECONOMY:
                this.id = "E" + id;
                break;
            case TECH:
                this.id = "T" + id;
                break;
            case SPORT:
                this.id = "S" + id;
                break;
            case SHOW:
                this.id = "s" + id;
                break;
            case CULTURE:
                this.id = "C" + id;
                break;
            case ITALY:
                this.id = "I" + id;
                break;
            case WORLD:
                this.id = "W" + id;
                break;
            case LATEST_NEWS:
                this.id = "L" + id;
                break;
        }

        this.author = author;
        this.title = title;
        this.body = body;
        this.source = source;
        this.link = link;
        this.category = category;
        this.dateTime = dateTime;
        rating = new ArrayList<Double>();
        comments = new HashMap<Integer, Comment>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Categories getCategories() {
        return category;
    }

    public void setCategories(Categories category) {
        this.category = category;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public HashMap<Integer, Comment> getComments() {
        return comments;
    }

    public void setComments(HashMap<Integer, Comment> comments) {
        this.comments = comments;
    }

    public ArrayList<Double> getRating() {
        return rating;
    }

    public void setRating(ArrayList<Double> rating) {
        this.rating = rating;
    }

    // restituisce il valore medio della somma dei voti presi dall'arraylist voti
    public double ratingValue() {
        double val = 0;
        for (Double d : rating) {
            val += d;
        }
        return val / rating.size();
    }

    public String toString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.dateTime);
        String text = this.title.toUpperCase() + "\n" + this.body + "\n\nAuthor: " + this.author + "\nDate: " + strDate + "\nSource: " + this.getSource() + "\n\nLeggi la notizia completa su " + this.getLink() + "\n\nRating: " + this.ratingValue() + "\nCategoria: " + category.toString() + "\nID: " + this.getId();
        return text;
    }

    // Trasforma l'oggetto notizia corrente in una stringa pronta per essere scritta sul file json
    public String toJson() {
        Gson gson = new Gson();
        Type fooType = new TypeToken<News>() {
        }.getType();
        String json = gson.toJson(this, fooType);
        return json;
    }

    //fa append della notizia in formato .json al file della categoria corretta
    public void persist() {
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(this.getCategories().label, true);
            pw = new PrintWriter(fw);
            pw.println(this.toJson());
            System.out.println("Notizia " + this.getTitle() + " aggiunta");
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
}
