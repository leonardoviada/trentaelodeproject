package it.unibo.trentalode.admin;

import it.unibo.trentalode.ConfigProvider;
import it.unibo.trentalode.bot.User;
import it.unibo.trentalode.bot.UsersRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelezionaUtenteDaRimuovere {
    private final VBox root;

    public SelezionaUtenteDaRimuovere(Stage primaryStage) {

        UsersRepository listaUser = new UsersRepository(ConfigProvider.getInstance().getProperty("USERS_PATH"));
        listaUser.update();

        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(7.5);

        Text testoSeleziona = new Text("Seleziona l'utente da modificare");
        root.getChildren().add(testoSeleziona);

        ObservableList<User> utenti = FXCollections.observableArrayList();
        ListView<User> menuUtenti = new ListView<User>(utenti);

        menuUtenti.setCellFactory(user -> new ListCell<User>() {
            @Override
            protected void updateItem(User item, boolean b) {
                super.updateItem(item, b);
                setText(item == null ? null : item.getUsername());
            }
        });

        menuUtenti.setEditable(true);
        menuUtenti.setMaxSize(200, 150);
        root.getChildren().add(menuUtenti);

        for (User g : listaUser.getUserList().values()) {
            if (g != null) {
                utenti.add(g);
            }
        }

        Button bottoneRimuovi = new Button("Rimuovi utente");
        root.getChildren().add(bottoneRimuovi);

        bottoneRimuovi.setOnMouseClicked(arg0 -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stato operazione");
            alert.setHeaderText("Utente eliminato");
            alert.showAndWait();
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
