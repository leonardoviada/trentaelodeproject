public enum Categories {
    POLITICS("politica.json"),
    ECONOMY("economia.json"),
    TECH("tecnologia.json"),
    SPORT("sport.json"),
    SHOW("show.json"),
    CULTURE("cultura.json"),
    ITALY("italia.json"),
    WORLD("mondo.json"),
    LATEST_NEWS("latest_news.json");

    public final String label;

    Categories(String label) {
        this.label = label;
    }
}
