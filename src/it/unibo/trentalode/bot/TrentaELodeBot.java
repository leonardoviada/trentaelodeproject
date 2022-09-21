package it.unibo.trentalode.bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Singleton del bot
 */
public class TrentaELodeBot extends TelegramLongPollingBot {

    private static TrentaELodeBot instance;
    private final UsersRepository userMap;

    protected TrentaELodeBot(String pathUser, String pathFeedRSS) {
        super();
        userMap = new UsersRepository(pathUser);
        IOManager.uploadFeedRSSMap(pathFeedRSS);
    }

    public static TrentaELodeBot getInstance() {
        if (TrentaELodeBot.instance == null)
            TrentaELodeBot.instance = new TrentaELodeBot(ConfigProvider.getInstance().getProperty("USERS_PATH"), ConfigProvider.getInstance().getProperty("RSS_PATH"));
        return TrentaELodeBot.instance;
    }

    /**
     * Metodo che riceve l'oggetto "update" dalla chat di telegram e
     * invoca manageCommands() per interpretare il messagio ricevuto ed eseguire azioni
     */
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText())
            manageCommands(update);
    }

    @Override
    public String getBotUsername() {
        return ConfigProvider.getInstance().getProperty("BOT_USERNAME");
    }

    @Override
    public String getBotToken() {
        return ConfigProvider.getInstance().getProperty("BOT_TOKEN");
    }


    /**
     * Metodo che prende in entrata l'id della chat di telegram con l'utente, l'oggetto News e un valore boolean
     * e invia un messaggio su telegram all'utente in riposta a un comando ricevuto.
     * Il valore boolean serve per specificare se è ncessario inserire i bottoni "commenta","vota","leggi commenti" sotto al messaggio
     */
    public void sendMessageToUser(long chat_id, News news, boolean commentable) {
        SendMessage message = new SendMessage();
        message.setChatId(Long.toString(chat_id));
        message.setText(news.toString()); // news.toString restituisce il testo già formattato per essere spedito all'utente via telegram (vedi classe News)
        if (commentable)
            addButtons(message, news); //aggiunge i 3 bottoni al messaggio; passiamo anche l'attributo news perché ci serve l'id ella notizia da inserire nel testo originato alla presione dei bottoni

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * Overload del metodo precedente, ma senza l'attributo boolean in quanto non si vogliono aggiungere i bottoni al messaggio
     */
    public void sendMessageToUser(long chat_id, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(Long.toString(chat_id));
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    /**
     * Riceve in input l'oggetto del messaggio ricevuto dall'utente telegram, ne estrae il contenuto testuale e altre info sull'utente (chat_id, username, ecc)
     */
    public void manageCommands(Update update) {
        String text = update.getMessage().getText();
        System.out.println("Comando " + text + " da utente " + update.getMessage().getChat().getUserName());
        if ((text.equals(ConfigProvider.getInstance().getProperty("CMD_SIGNIN")))) {
            if (userMap.getUserList().containsKey(update.getMessage().getChat().getUserName())) {
                sendMessageToUser(update.getMessage().getChatId(), "Utente già registrato");
                return;
            }

            String name = update.getMessage().getChat().getUserName();
            userMap.getUserList().putIfAbsent(name, new User(name));
            userMap.persist();
            sendMessageToUser(update.getMessage().getChatId(), "Nuovo utente registrato correttamente!");

        } else if (text.charAt(0) == '/') {
            HashMap<String, News> newsMap;
            Iterator it;
            switch (text) {
                case "/start":
                    sendMessageToUser(update.getMessage().getChatId(), "Benvenuto in 30L bot!");
                    return;
                case "/politica":
                    newsMap = IOManager.getNewsByCategory(Categories.POLITICS);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/economia":
                    newsMap = IOManager.getNewsByCategory(Categories.ECONOMY);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/tech":
                    newsMap = IOManager.getNewsByCategory(Categories.TECH);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/sport":
                    newsMap = IOManager.getNewsByCategory(Categories.SPORT);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/show":
                    newsMap = IOManager.getNewsByCategory(Categories.SHOW);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/cultura":
                    newsMap = IOManager.getNewsByCategory(Categories.CULTURE);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/italia":
                    newsMap = IOManager.getNewsByCategory(Categories.ITALY);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/mondo":
                    newsMap = IOManager.getNewsByCategory(Categories.WORLD);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;
                case "/latest":
                    newsMap = IOManager.getNewsByCategory(Categories.LATEST_NEWS);
                    it = newsMap.entrySet().iterator();
                    while (it.hasNext()) {
                        Map.Entry<String, News> entry = (Map.Entry) it.next();
                        sendMessageToUser(update.getMessage().getChatId(), entry.getValue(), true);
                    }
                    break;

                default:
                    sendMessageToUser(update.getMessage().getChatId(), "Comando invalido, riprova");
            }
        }

        // sintassi per commento: <id=1234><commento>testo
        else if (text.contains("<commento>") && text.contains("<id=")) {
            String s = text.substring(19, text.indexOf(">") + 1);
            CharSequence char_id = s.subSequence(4, s.length() - 1);
            String comment = text.substring(text.indexOf(">") + 11); //11 sono i caratteri di ><commento> che vanno eliminati
            String id = char_id.toString();
            Comment c = new Comment(update.getMessage().getChat().getUserName(), comment, new Date(System.currentTimeMillis()));
            HashMap<String, News> map = getNewsMapById(id);
            map.get(id).getComments().put(c.hashCode(), c);
            updateJson(map, map.get(id).getCategories());
            sendMessageToUser(update.getMessage().getChatId(), "Commento inserito correttamente");
        }

        // sintassi per rating: <id=1234><voto>6,5
        else if (text.contains("<voto>") && text.contains("<id=")) {
            text = text.replace(',', '.');
            String s = text.substring(19, text.indexOf(">") + 1);
            CharSequence char_id = s.subSequence(4, s.length() - 1);
            String id = char_id.toString();
            Double voto = Double.parseDouble(text.substring(text.indexOf(">") + 7)); //11 sono i caratteri di ><voto> che vanno eliminati
            HashMap<String, News> map = getNewsMapById(id);
            map.get(id).getRating().add(voto);
            updateJson(map, map.get(id).getCategories());
            sendMessageToUser(update.getMessage().getChatId(), "Votazione effettuata");
        } else if (text.contains("visualizza_commenti") && text.contains("<id=")) {
            String s = text.substring(19, text.indexOf(">") + 1);
            CharSequence char_id = s.subSequence(4, s.length() - 1);
            String id = char_id.toString();
            HashMap<String, News> map = getNewsMapById(id);
            HashMap<Integer, Comment> comments = map.get(id).getComments();
            StringBuilder testo = new StringBuilder();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            if (!comments.isEmpty()) {
                testo.append("\nCOMMENTI:");
                Iterator it;
                it = comments.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<Integer, Comment> entry = (Map.Entry) it.next();
                    testo.append("\n").append(entry.getValue().getOwner()).append(": ").append(entry.getValue().getText()).append("\n").append(dateFormat.format(entry.getValue().getDateTime())).append("\n");
                }
                sendMessageToUser(update.getMessage().getChatId(), testo.toString());
            }
        } else {
            sendMessageToUser(update.getMessage().getChatId(), "Comando invalido, riprova");
        }
    }

    /**
     * metodo che sovrascrive il file json che contiene l'elenco di notizie della categoria passata come parametro,
     * in modo salvare l'aggiunta di commenti e voti alle notizie; viene invocato in manageCommands()
     */
    public void updateJson(HashMap<String, News> map, Categories cat) {
        Iterator it;
        it = map.entrySet().iterator();
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            fw = new FileWriter(cat.label);
            pw = new PrintWriter(fw);
            while (it.hasNext()) {
                Map.Entry<String, News> entry = (Map.Entry) it.next();
                pw.println(entry.getValue().toJson());
            }
        } catch (IOException e) {
            System.out.println("Percorso file non valido");
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                System.out.println("Errore di chiusura");
            }
            assert pw != null;
            pw.close();
        }
    }


    /**
     * Metodo che aggiunge i bottoni sotto i messaggi telegram
     */
    public SendMessage addButtons(SendMessage message, News n) {
        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();

        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline1 = new ArrayList<>();

        InlineKeyboardButton commenta = new InlineKeyboardButton();
        InlineKeyboardButton vota = new InlineKeyboardButton();
        InlineKeyboardButton vediCommenti = new InlineKeyboardButton();
        commenta.setText("Commenta");
        commenta.setSwitchInlineQueryCurrentChat("<id=" + n.getId() + "><commento> ");
        vota.setText("Vota");
        vota.setSwitchInlineQueryCurrentChat("<id=" + n.getId() + "><voto>8,5");
        vediCommenti.setSwitchInlineQueryCurrentChat("<id=" + n.getId() + ">visualizza_commenti");
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


    /**
     * Ritorna l'elenco di notizie in cui si trova la news che contiene l'id passato come parametro
     * Riconosce la categoria corretta dalla prima lettera dell'id passato come parametro
     */
    public HashMap<String, News> getNewsMapById(String id) {
        HashMap<String, News> list;
        char categoryLetter = id.charAt(0);
        switch (categoryLetter) {
            case 'P':
                list = IOManager.getNewsByCategory(Categories.POLITICS);
                return list;
            case 'E':
                list = IOManager.getNewsByCategory(Categories.ECONOMY);
                return list;
            case 'T':
                list = IOManager.getNewsByCategory(Categories.TECH);
                return list;

            case 'S':
                list = IOManager.getNewsByCategory(Categories.SPORT);
                return list;

            case 's':
                list = IOManager.getNewsByCategory(Categories.SHOW);
                return list;

            case 'C':
                list = IOManager.getNewsByCategory(Categories.CULTURE);
                return list;

            case 'I':
                list = IOManager.getNewsByCategory(Categories.ITALY);
                return list;

            case 'W':
                list = IOManager.getNewsByCategory(Categories.WORLD);
                return list;

            case 'L':
                list = IOManager.getNewsByCategory(Categories.LATEST_NEWS);
                return list;

            default:
                return null;
        }
    }
}