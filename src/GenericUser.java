import java.util.ArrayList;

public abstract class GenericUser {
    private String username;

    private long id;
    private ArrayList<Categories> interests;

    public GenericUser(String username, long id) {
        this.username = username;
        this.id = id;
        interests = new ArrayList<Categories>();
    }

    public GenericUser(String username) {
        this.username = username;
        interests = new ArrayList<Categories>();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<Categories> getInterests() {
        return interests;
    }

    public void setInterests(ArrayList<Categories> interests) {
        this.interests = interests;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
