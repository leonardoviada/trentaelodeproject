import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class User{
   String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public User(String username) {
        this.username = username;
    }

    public String toJson()
    {
        Gson gson = new Gson();
        Type fooType = new TypeToken<News>() {}.getType();
        String json = gson.toJson(this,fooType);
        return json;
    }
}
