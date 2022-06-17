import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class News {
    private long id;
    private String author;
    private String title;
    private String body;
    private String source; //la fonte da cui proviene (testate giornalistiche, siti, ecc)
    private String link; //link se la notizia è pubblicata online
    private Categories category;
    private HashMap<Integer, Comment> comments;
    private Date dateTime;
    private ArrayList<Double> rating; //rating della notizia da 0 a 10

    //costruttore minimale
    public News(long id, String author, String title, String body, Date dateTime) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.body = body;
        this.dateTime = dateTime;
        rating = new ArrayList<Double>();
        comments = new HashMap<Integer, Comment>();
    }

    //costruttore completo
    public News(long id, String author, String title, String body, String source, String link, Categories category, Date dateTime) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setCategories(Categories category) {
        this.category = category;
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
    public double ratingValue(){
        double val = 0;
        for(Double d: rating){
            val += d;
        }
        return val/rating.size();
    }

   /* public String toString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.dateTime);
        String text = this.title.toUpperCase()+"\n"+this.body+"\n\nAuthor: "+this.author+"\nDate: "+strDate+"\nSource: "+this.getSource()+"\n\nLeggi la notizia completa su "+this.getLink();
        text.concat("\n\nRating: "+this.ratingValue()+"\nCategoria: "+category.toString());
        System.out.println(text);
        return text;

    }*/
   public String toString() {
       DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
       String strDate = dateFormat.format(this.dateTime);
       //String text = this.title.toUpperCase() + "\n" + this.body + "\n\nAuthor: " + this.author + "\nDate: " + strDate;
       String text = this.title.toUpperCase()+"\n"+this.body+"\n\nAuthor: "+this.author+"\nDate: "+strDate+"\nSource: "+this.getSource()+"\n\nLeggi la notizia completa su "+this.getLink()+"\n\nRating: "+this.ratingValue()+"\nCategoria: "+category.toString()+"\nID: "+this.getId();
       return text;
   }
}
