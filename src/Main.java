import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi;
        TrentaELodeBot tlb = new TrentaELodeBot();
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            // Register our bot
            botsApi.registerBot(tlb);
            System.out.println("Bot successfully started!");

            /*String news = tlb.getNewsById(5032).toJson();
            System.out.println(news);
            News n = tlb.getNewsById(5032).fromJson(news);
            System.out.println(n.toString());*/

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        tlb.persistFeedRSS("http://www.repubblica.it/rss/politica/rss2.0.xml",Categories.POLITICS,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\politica.json");
        tlb.persistFeedRSS("http://www.repubblica.it/rss/economia/rss2.0.xml",Categories.ECONOMY,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\economia.json");
        tlb.persistFeedRSS("http://www.repubblica.it/rss/tecnologia/rss2.0.xml",Categories.TECH,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\tecnologia.json");
        tlb.persistFeedRSS("http://www.repubblica.it/rss/sport/rss2.0.xml",Categories.SPORT,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\sport.json");
        tlb.persistFeedRSS("http://www.repubblica.it/rss/rubriche/rss2.0.xml",Categories.SHOW,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\show.json");
        tlb.persistFeedRSS("http://www.repubblica.it/rss/spettacoli_e_cultura/rss2.0.xml",Categories.CULTURE,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\cultura.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/italia.xml",Categories.ITALY,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\italia.json");
        tlb.persistFeedRSS("http://www.repubblica.it/rss/esteri/rss2.0.xml",Categories.WORLD,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\mondo.json");
        tlb.persistFeedRSS("https://www.ilsole24ore.com/rss/italia--attualita.xml",Categories.LATEST_NEWS,"C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\latest_news.json");

    }


}

