public enum Categories {
    POLITICS("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\politica.json"),
    ECONOMY("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\economia.json"),
    TECH("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\tecnologia.json"),
    SPORT("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\sport.json"),
    SHOW("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\show.json"),
    CULTURE("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\cultura.json"),
    ITALY("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\italia.json"),
    WORLD("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\mondo.json"),
    LATEST_NEWS("C:\\Users\\acer\\Desktop\\UNI\\INFO\\TrentaELode\\DB\\latest_news.json");

    public final String label;

    private Categories(String label) {
        this.label = label;
    }
}
