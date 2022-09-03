import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi;
        TrentaELodeBot tlb = new TrentaELodeBot("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\USERS.json");
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            // Register our bot
            botsApi.registerBot(tlb);
            System.out.println("Bot successfully started!");

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/italia--politica.xml",Categories.POLITICS,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\politica.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/economia.xml",Categories.ECONOMY,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\economia.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/tecnologia.xml",Categories.TECH,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\tecnologia.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/sport24.xml",Categories.SPORT,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\sport.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/commenti--societa.xml",Categories.SHOW,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\show.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/cultura.xml",Categories.CULTURE,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\cultura.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/italia.xml",Categories.ITALY,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\italia.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/mondo.xml",Categories.WORLD,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\mondo.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/italia--attualita.xml",Categories.LATEST_NEWS,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\latest_news.json");
    }


}

