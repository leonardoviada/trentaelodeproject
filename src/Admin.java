import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Admin extends GenericUser{
    public Admin(String username) {
        super(username);
    }

    public void addNews(News n){
        n.toJson();
    }
    public void removeNews(News n){

    }
    public void removeComment(Comment c){

    }
}
