package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.User;
import it.unibo.trentalode.bot.UsersRepository;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreaUtente {
    private final VBox root;

    CreaUtente(Stage primaryStage, UsersRepository listaUtenti) {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(7.5);

        Text inserisciNickname = new Text("Inserire il nickname dell'utente da creare");
        root.getChildren().add(inserisciNickname);

        TextField tf = new TextField();
        tf.setMaxWidth(200);
        root.getChildren().add(tf);

        Button bottoneCrea = new Button("Crea utente");
        root.getChildren().add(bottoneCrea);

        Text aggiunto = new Text("Utente creato");
        root.getChildren().add(aggiunto);
        aggiunto.setVisible(false);


        bottoneCrea.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {
                listaUtenti.getUserList().put(tf.getText(), new User(tf.getText()));
                aggiunto.setVisible(true);

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
