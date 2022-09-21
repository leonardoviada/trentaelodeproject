package it.unibo.trentalode.bot;

import it.unibo.trentalode.ConfigProvider;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        TelegramBotsApi botsApi;
        TrentaELodeBot tlb = new TrentaELodeBot(ConfigProvider.getInstance().getProperty("USERS_PATH"), ConfigProvider.getInstance().getProperty("RSS_PATH"));
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(tlb);
            System.out.println("Bot successfully started!");
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

        IOManager.persistFeedRSS();
        tlb.importCSV(ConfigProvider.getInstance().getProperty("CSV_PATH"));
    }
}

