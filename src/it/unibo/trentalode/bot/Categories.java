package it.unibo.trentalode.bot;

import it.unibo.trentalode.ConfigProvider;

public enum Categories {
    POLITICS(ConfigProvider.getInstance().getProperty("POLITICS_PATH")),
    ECONOMY(ConfigProvider.getInstance().getProperty("ECONOMY_PATH")),
    TECH(ConfigProvider.getInstance().getProperty("TECH_PATH")),
    SPORT(ConfigProvider.getInstance().getProperty("SPORT_PATH")),
    SHOW(ConfigProvider.getInstance().getProperty("SHOWS_PATH")),
    CULTURE(ConfigProvider.getInstance().getProperty("CULTURE_PATH")),
    ITALY(ConfigProvider.getInstance().getProperty("ITALY_PATH")),
    WORLD(ConfigProvider.getInstance().getProperty("WORLD_PATH")),
    LATEST_NEWS(ConfigProvider.getInstance().getProperty("LATEST_PATH"));

    public final String label;

    Categories(String label) {
        this.label = label;
    }
}
