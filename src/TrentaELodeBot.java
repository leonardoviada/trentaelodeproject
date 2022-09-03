import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.syndication.io.FeedException;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.*;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.net.URL;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

public class TrentaELodeBot extends TelegramLongPollingBot {

    private UserList userMap; //lista di User

    public TrentaELodeBot(String path){
        super();
        userMap = new UserList(path);
    }

   // metodo che riceve l'oggetto update dalla chat di telegram e
   // invoca manageCommands() per interpretare il messagio ricevuto ed eseguire azioni
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            manageCommands(update);
        }

    }
    @Override
    public String getBotUsername() {
        // TODO
        return "trenta_e_lode_bot";
    }
    @Override
    public String getBotToken() {
        return "5551855563:AAFGvCGz4biEKB9q_ctSs6tVLKY2MU02zKI";
    }


    //metodo che prende in entrata l'id della chat di telegram con l'utente, l'oggetto News e un valore boolean
    // e invia un messaggio su telegram all'utente in riposta a un comando ricevuto.
    //Il valore boolean serve per specificare se è ncessario inserire i bottoni "commenta","vota","leggi commenti" sotto al messaggio
    public void sendMessageToUser(long chat_id, News news, boolean commentable){
            SendMessage message = new SendMessage();
            message.setChatId(Long.toString(chat_id));
            message.setText(news.toString()); //news.toString restituisce il testo già formattato per essere spedito all'utente via telegram (vedi classe News)
            if(commentable){
                addButton(message,news); //aggiunge i 3 bottoni al messaggio; passiamo anche l'attributo news perché ci serve l'id ella notizia da inserire nel testo originato alla presione dei bottoni
            }
            try {
                execute(message); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
    }

    //overload del metodo precedente, ma senza l'attributo boolean in quanto non si vogliono aggiungere i bottoni al messaggio
    public void sendMessageToUser(long chat_id, String text){
        SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
        message.setChatId(Long.toString(chat_id));
        message.setText(text);
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    //riceve in input l'oggetto del messaggio ricevuto dall'utente telegram, ne estrae il contenuto testuale e altre info sull'utente (chat_id, username, ecc)
    public void manageCommands(Update update){
        String text = update.getMessage().getText();

        //se l'utente non era registrato, allora lo aggiunge alla lista utenti
        if(!userMap.getUserList().containsKey(update.getMessage().getChat().getUserName())){
            String name = update.getMessage().getChat().getUserName();
            userMap.getUserList().putIfAbsent(name, new User(name));
            userMap.persist();
            System.out.println("Percorso file non valido2");
            sendMessageToUser(update.getMessage().getChatId(),"Nuovo utente registrato correttamente!");
        }
        else if(text.charAt(0) == '/'){
            HashMap<String, News> newsMap = null;
            Iterator it = null;
            switch (text) {
                case "/politica":
                    newsMap = getNewsByCategory(Categories.POLITICS);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/economia":
                    newsMap = getNewsByCategory(Categories.ECONOMY);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/tech":
                    newsMap = getNewsByCategory(Categories.TECH);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/sport":
                    newsMap = getNewsByCategory(Categories.SPORT);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/show":
                    newsMap = getNewsByCategory(Categories.SHOW);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/cultura":
                    newsMap = getNewsByCategory(Categories.CULTURE);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/italia":
                    newsMap = getNewsByCategory(Categories.ITALY);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/mondo":
                    newsMap = getNewsByCategory(Categories.WORLD);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;
                case "/latest":
                    newsMap = getNewsByCategory(Categories.LATEST_NEWS);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry)it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(),true);
                    }
                    break;

                default:
                    sendMessageToUser(update.getMessage().getChatId(), "Comando invalido, riprova");
            }
        }
        //sintassi per commento: <id=1234><commento>testo
        else if(text.contains("<commento>") && text.contains("<id=")){
            String s = text.substring(19,text.indexOf(">")+1);
            CharSequence char_id = s.subSequence(4,s.length()-1);
            String comment = text.substring(text.indexOf(">")+11); //11 sono i caratteri di ><commento> che vanno eliminati
            String id = char_id.toString();
            Comment c = new Comment(update.getMessage().getChat().getUserName(), comment, new Date(System.currentTimeMillis()));
            HashMap<String, News> map = getNewsMapById(id);
            map.get(id).getComments().put(c.hashCode(), c);
            updateJson(map, map.get(id).getCategories());
            sendMessageToUser(update.getMessage().getChatId(), "Commento inserito correttamente");
        }
        //sintassi per rating: <id=1234><voto>6,5
        else  if(text.contains("<voto>") && text.contains("<id=")){
            text = text.replace(',','.');
            String s = text.substring(19,text.indexOf(">")+1);
            CharSequence char_id = s.subSequence(4,s.length()-1);
            String id = char_id.toString();
            Double voto = Double.parseDouble(text.substring(text.indexOf(">")+7)); //11 sono i caratteri di ><voto> che vanno eliminati
            HashMap<String, News> map = getNewsMapById(id);
            map.get(id).getRating().add(voto);
            updateJson(map, map.get(id).getCategories());
            sendMessageToUser(update.getMessage().getChatId(), "Votazione effettuata");
        }
        else if(text.contains("visualizza_commenti") && text.contains("<id=")) {
            String s = text.substring(19, text.indexOf(">") + 1);
            CharSequence char_id = s.subSequence(4, s.length() - 1);
            String id = char_id.toString();
            HashMap<String, News> map = getNewsMapById(id);
            HashMap<Integer, Comment> comments = map.get(id).getComments();
            String testo = "";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            if (comments.isEmpty() == false) {
                testo += "\nCOMMENTI:";
                Iterator it = null;
                it = comments.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Comment> entry = (Map.Entry) it.next();
                    testo += "\n" + entry.getValue().getOwner() + ": " + entry.getValue().getText() + "\n" + dateFormat.format(entry.getValue().getDateTime()) + "\n";
                }
                sendMessageToUser(update.getMessage().getChatId(), testo);
            }
        }
        else{
            sendMessageToUser(update.getMessage().getChatId(), "Comando invalido, riprova");
        }
    }

    public void updateJson(HashMap<String, News> map, Categories cat){
        Iterator it = null;
        it = map.entrySet().iterator();
        FileWriter fw = null;
        PrintWriter pw = null;
        try{
            fw = new FileWriter(cat.label); //true per fare append, false o niente per sovrascrivere
            pw = new PrintWriter(fw);
            while (it.hasNext()) {
                Map.Entry<String, News> entry = (Map.Entry)it.next();
                pw.println(entry.getValue().toJson());
            }
        } catch (IOException e) {
            System.out.println("Percorso file non valido");
        }
        finally{
            try{
                fw.close();
            } catch (IOException e){System.out.println("Errore di chiusura");}
            pw.close();
        }
    }

    public SendMessage addButton(SendMessage message, News n){
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List <List <InlineKeyboardButton>> rowsInline = new ArrayList <> ();
        List<InlineKeyboardButton> rowInline = new ArrayList <> ();
        List<InlineKeyboardButton> rowInline1 = new ArrayList <> ();

        InlineKeyboardButton commenta = new InlineKeyboardButton();
        InlineKeyboardButton vota = new InlineKeyboardButton();
        InlineKeyboardButton vediCommenti = new InlineKeyboardButton();
        commenta.setText("Commenta");
        commenta.setSwitchInlineQueryCurrentChat("<id="+n.getId()+"><commento>inserisci qui il commento");
        vota.setText("Vota");
        vota.setSwitchInlineQueryCurrentChat("<id="+n.getId()+"><voto>8,5");
        vediCommenti.setSwitchInlineQueryCurrentChat("<id="+n.getId()+">visualizza_commenti");
        vediCommenti.setText("Visualizza i commenti");

        rowInline.add(commenta);
        rowInline.add(vota);
        rowInline1.add(vediCommenti);


        rowsInline.add(rowInline);
        rowsInline.add(rowInline1);
        markupInline.setKeyboard(rowsInline);

        message.setReplyMarkup(markupInline);
        return message;
    }


    //DA SISTEMARE CON FEED RSS

    public HashMap<String, News> getNewsByCategory(Categories c){
        HashMap<String, News> newsList = new HashMap<String, News>();
        BufferedReader br = null;
        FileReader fr = null;
            try {
                fr = new FileReader(c.label);
                br = new BufferedReader(fr);
                String line = br.readLine();
                while(line!=null) {
                    newsList.put(fromJson(line).getId(),fromJson(line));
                    line = br.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            return newsList;
    }

    public News getNewsById(String id){
        HashMap<String, News> map;
        Iterator it = null;
        char categoryLetter = id.charAt(0);
        switch (categoryLetter){
            case 'P':
                map = getNewsByCategory(Categories.POLITICS);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 'E':
                map = getNewsByCategory(Categories.ECONOMY);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 'T':
                map = getNewsByCategory(Categories.TECH);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 'S':
                map = getNewsByCategory(Categories.SPORT);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 's':
                map = getNewsByCategory(Categories.SHOW);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 'C':
                map = getNewsByCategory(Categories.CULTURE);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 'I':
                map = getNewsByCategory(Categories.ITALY);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 'W':
                map = getNewsByCategory(Categories.WORLD);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            case 'L':
                map = getNewsByCategory(Categories.LATEST_NEWS);
                it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<String, News> entry = (Map.Entry)it.next();
                    if(entry.getKey().equals(id)){
                        return entry.getValue();
                    }
                }
                break;
            default:
                return null;
        }
        return null;
    }

    public HashMap<String, News> getNewsMapById(String id){
        HashMap<String, News> list;
        char categoryLetter = id.charAt(0);
        switch (categoryLetter){
            case 'P':
                list = getNewsByCategory(Categories.POLITICS);
                return list;
            case 'E':
                list = getNewsByCategory(Categories.ECONOMY);
                return list;
            case 'T':
                list = getNewsByCategory(Categories.TECH);
                return list;

            case 'S':
                list = getNewsByCategory(Categories.SPORT);
                return list;

            case 's':
                list = getNewsByCategory(Categories.SHOW);
                return list;

            case 'C':
                list = getNewsByCategory(Categories.CULTURE);
                return list;

            case 'I':
                list = getNewsByCategory(Categories.ITALY);
                return list;

            case 'W':
                list = getNewsByCategory(Categories.WORLD);
                return list;

            case 'L':
                list = getNewsByCategory(Categories.LATEST_NEWS);
                return list;

            default:
                return null;
        }
    }

    public News fromJson(String json) {
        Gson gson = new Gson();
        Type fooType = new TypeToken<News>() {}.getType();
        News n = gson.fromJson(json,fooType);
        return n;
    }

    public void persistFeedRSS(String myUrl, Categories cat, String path) {
        URL url = null;
        XmlReader reader = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        News news = null;

        //try {
        try {
            url = new URL(myUrl);
        } catch (MalformedURLException e) {
            System.out.println("Attenzione, URL non valido");
        }
        try {
            reader = new XmlReader(url);
        } catch (IOException e) {
            System.out.println("Attenzione, IOExc");
        }
        try {
            fw = new FileWriter(new File(path)); //true per fare append, false o niente per sovrascrivere
        } catch (IOException e) {
            System.out.println("Attenzione, IOExc2");
        }
        pw = new PrintWriter(fw);

        SyndFeed feed = null;
        try {
            feed = new SyndFeedInput().build(reader);
        } catch (FeedException e) {
            throw new RuntimeException(e);
        }
        for (Iterator<SyndEntry> i = feed.getEntries().iterator(); i.hasNext();) {
                //Iteriamo tutte le voci presenti nel nostro feed e ne stampiano le proprietà fondmentali
                SyndEntry entry = i.next();
                int hash = entry.hashCode();
                if(hash<0){
                    hash = -1*hash;
                }
                news = new News(hash, entry.getAuthor(), entry.getTitle(), entry.getDescription().getValue(), "Feed RSS", entry.getLink(), cat, entry.getPublishedDate());
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