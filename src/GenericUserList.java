import java.util.ArrayList;
import java.util.HashMap;

public class GenericUserList{
    HashMap<String, GenericUser> userList; //ogni chiave è il valore di update.message.chat.id (non message.chatId)

    public GenericUserList(){
        userList = new HashMap<String, GenericUser>();
        update();
    }
    //il metodo update è da completare con codice che prenda dal DB l'elenco degli utenti e admin e riempia userList
    public void update(){
        userList.put("pippo", new User("pippo"));
        userList.put("Lillo", new Admin("Lillo"));
    }

    public HashMap<String, GenericUser> getUserList() {
        return userList;
    }

    public void setUserList(HashMap<String, GenericUser> userList) {
        this.userList = userList;
    }
}
