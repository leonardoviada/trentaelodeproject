import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi;
        TrentaELodeBot tlb = new TrentaELodeBot("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\USERS.json","C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\FeedRSS.txt");
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            // Register our bot
            botsApi.registerBot(tlb);
            System.out.println("Bot successfully started!");

        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        tlb.persistFeedRSS();
        tlb.importCSV("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\newsCsv.csv");
    }


}

