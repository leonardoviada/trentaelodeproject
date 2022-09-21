package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.ConfigProvider;
import it.unibo.trentalode.bot.User;
import it.unibo.trentalode.bot.UsersRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelezionaUtenteDaModificare {
    private final VBox root;

    public SelezionaUtenteDaModificare(Stage primaryStage) {

        UsersRepository listaUser = new UsersRepository(ConfigProvider.getInstance().getProperty("USERS_PATH"));
        listaUser.update();

        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(7.5);

        Text testoSeleziona = new Text("Seleziona l'utente da modificare");
        root.getChildren().add(testoSeleziona);

        ObservableList<User> utenti = FXCollections.observableArrayList();
        ListView<User> menuUtenti = new ListView<User>(utenti);
        menuUtenti.setEditable(true);
        menuUtenti.setMaxSize(200, 150);
        root.getChildren().add(menuUtenti);

        menuUtenti.setCellFactory(user -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean b) {
                super.updateItem(item, b);
                setText(item == null ? null : item.getUsername());
            }
        });

        for (User g : listaUser.getUserList().values()) {
            if (g != null) {
                utenti.add(g);
            }
        }

        Text inserisciNuovoNickname = new Text("Inserisci il nuovo nickname dell'utente");
        root.getChildren().add(inserisciNuovoNickname);

        TextField campoNickname = new TextField();
        campoNickname.setMaxWidth(200);
        root.getChildren().add(campoNickname);


        Button bottoneSeleziona = new Button("Modifica nickname");
        root.getChildren().add(bottoneSeleziona);

        Text cambiato = new Text("Nickname cambiato");
        root.getChildren().add(cambiato);
        cambiato.setVisible(false);

        bottoneSeleziona.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                menuUtenti.getSelectionModel().getSelectedItem().setUsername(campoNickname.getText());
                //cambia nome utente nel database
                cambiato.setVisible(true);
            }
        });

        Button tornaIndietro = new Button("Torna al menu iniziale");
        root.getChildren().add(tornaIndietro);

        tornaIndietro.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                InterfacciaAzioni interfaccia = new InterfacciaAzioni(primaryStage);
                primaryStage.getScene().setRoot(interfaccia.getRoot());


            }


        });
    }

    public VBox getRoot() {
        return root;
    }


}
