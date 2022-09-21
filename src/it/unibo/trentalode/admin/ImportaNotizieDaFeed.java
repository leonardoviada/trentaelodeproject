package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.IOManager;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ImportaNotizieDaFeed {
    private final VBox root;

    public ImportaNotizieDaFeed(Stage primaryStage) {


        root = new VBox();
        root.setSpacing(7.5);
        Text inserisciFeed = new Text("Questa funzionalità importa le notizie dal file RSS fornito");
        root.getChildren().add(inserisciFeed);

        Button importa = new Button("Importa");
        root.getChildren().add(importa);

        Text errore = new Text("Errore nell'importazione da feed, riprovare");
        errore.setVisible(false);
        root.getChildren().add(errore);


        root.setAlignment(Pos.CENTER);

        importa.setOnMouseClicked(arg0 -> {
            IOManager.persistFeedRSS();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stato operazione");
            alert.setHeaderText("Notizie importate da RSS");
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
