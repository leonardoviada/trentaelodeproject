import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.ArrayList;
import java.util.Date;

public class TrentaELodeBot extends TelegramLongPollingBot {

    private GenericUserList guList;

    public TrentaELodeBot(){
        super();
        guList = new GenericUserList();
    }

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
    public void manageCommands(Update update){
        String text = update.getMessage().getText().toLowerCase();

        if(!guList.getUserList().containsKey(update.getMessage().getChat().getUserName())){
            String name = update.getMessage().getChat().getUserName();
            guList.getUserList().putIfAbsent(name, new User(name));
            sendMessageToUser(update.getMessage().getChatId(),"Nuovo utente registrato correttamente!");
        }
        else if(text.charAt(0) == '/'){
            switch (text) {
                case "/politica":
                    ArrayList<News> newsListP = getNewsByCategory(Categories.POLITICS);
                    for(News n: newsListP){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/economia":
                    ArrayList<News>  newsListE = getNewsByCategory(Categories.ECONOMY);
                    for(News n: newsListE){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/tech":
                    ArrayList<News> newsListT = getNewsByCategory(Categories.TECH);
                    for(News n: newsListT){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/sport":
                    ArrayList<News> newsListS = getNewsByCategory(Categories.SPORT);
                    for(News n: newsListS){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/show":
                    ArrayList<News> newsListSh = getNewsByCategory(Categories.SHOW);
                    for(News n: newsListSh){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/cultura":
                    ArrayList<News> newsListC = getNewsByCategory(Categories.CULTURE);
                    for(News n: newsListC){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/italia":
                    ArrayList<News> newsListIt = getNewsByCategory(Categories.ITALY);
                    for(News n: newsListIt){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/mondo":
                    ArrayList<News> newsListW = getNewsByCategory(Categories.WORLD);
                    for(News n: newsListW){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;
                case "/latest":
                    ArrayList<News> newsListLN = getNewsByCategory(Categories.LATEST_NEWS);
                    for(News n: newsListLN){
                        sendMessageToUser(update.getMessage().getChatId(), n.toString());
                    }
                    break;

                default:
                    sendMessageToUser(update.getMessage().getChatId(), "Comando invalido, riprova");
            }
        }
        //sintassi per commento: <id=1234><commento>testo
        else if(text.contains("<commento>") && text.contains("<id=")){
            String s = text.substring(0,text.indexOf(">")+1);
            CharSequence char_id = s.subSequence(4,s.length()-1);
            String comment = text.substring(s.indexOf(">")+11); //11 sono i caratteri di ><commento> che vanno eliminati
            long id = Long.parseLong(char_id.toString());
            News n = getNewsById(id);
            Comment c = new Comment(update.getMessage().getChat().getUserName(), comment, new Date(System.currentTimeMillis()));
            n.getComments().put(n.getComments().size(), c);
            sendMessageToUser(update.getMessage().getChatId(), "Commento inserito correttamente");
        }
        //sintassi per rating: <id=1234><voto>6,5
        else  if(text.contains("<voto>") && text.contains("<id=")){
            text = text.replace(',','.');
            String s = text.substring(0,text.indexOf(">")+1);
            CharSequence char_id = s.subSequence(4,s.length()-1);
            Double voto = Double.parseDouble(text.substring(s.indexOf(">")+7)); //11 sono i caratteri di ><voto> che vanno eliminati
            long id = Long.parseLong(char_id.toString());
            News n = getNewsById(id);
            System.out.println(n.getTitle());
            n.getRating().add(voto);
            System.out.println("si");
            sendMessageToUser(update.getMessage().getChatId(), "Votazione effettuata");
        }
    }



    public ArrayList<News> getNewsByCategory(Categories c){

        //provvisorio solo per test ATTENZIONE
        ArrayList<News> list = new ArrayList<News>();
        String link = "https://www.ilfattoquotidiano.it/2022/06/17/gazprom-continua-a-far-pressione-sullue-a-eni-meta-delle-forniture-richieste-bloomberg-se-si-ferma-nord-stream-1-inverno-a-rischio/6630143/";

        list.add(new News(1234, "Pipo","cronaca di oggi","fvfsavfavvfav\ndfvsvdsfv", "Ansa", link,Categories.LATEST_NEWS,new Date(System.currentTimeMillis())));
        list.add(new News(3472, "Lollo","auto e moto","fvfsavfavvfav\ndfvsvdsfv", "Ansa", link,Categories.ECONOMY,new Date(System.currentTimeMillis())));
        list.add(new News(5032, "Mario","cinema stasera","fvfsavfavvfav\ndfvsvdsfv", "La Stampa", link,Categories.SHOW,new Date(System.currentTimeMillis())));

        return list;
    }

    public News getNewsById(long id){
        String link = "https://www.ilfattoquotidiano.it/2022/06/17/gazprom-continua-a-far-pressione-sullue-a-eni-meta-delle-forniture-richieste-bloomberg-se-si-ferma-nord-stream-1-inverno-a-rischio/6630143/";
        News n = new News(5032, "Mario","cinema stasera","fvfsavfavvfav\ndfvsvdsfv", "Ansa", link,Categories.LATEST_NEWS,new Date(System.currentTimeMillis()));
        return n;
    }
}