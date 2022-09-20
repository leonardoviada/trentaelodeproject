package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.Categories;
import it.unibo.trentalode.bot.IOManager;
import it.unibo.trentalode.bot.News;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RimuoviNotiziaDaDB {

    private final VBox root;

    RimuoviNotiziaDaDB(Stage primaryStage) {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(7.5);

        Text seleziona1 = new Text("Seleziona la notizia che vuoi rimuovere.");
        root.getChildren().add(seleziona1);
        Text seleziona2 = new Text("Di seguito mostrati i titoli delle notizie disponibili:");
        root.getChildren().add(seleziona2);


        Collection<News> values = IOManager.getNewsByCategory(Categories.LATEST_NEWS).values();
        Stream<News> newsStream = values.stream();
        ObservableList<String> titoli = FXCollections.observableArrayList(newsStream.map(News::getTitle).collect(Collectors.toList()));
        ListView<String> menuNotizie = new ListView<String>(titoli);
        root.getChildren().add(menuNotizie);
        menuNotizie.setEditable(true);
        menuNotizie.setMaxSize(200, 150);

        Button rimuoviNotizia = new Button("Rimuovi notizia");
        root.getChildren().add(rimuoviNotizia);

        Text rimossa = new Text("Notizia rimossa");
        rimossa.setVisible(false);
        root.getChildren().add(rimossa);


        rimuoviNotizia.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent arg0) {

                String notiziaDaRimuovere = menuNotizie.getSelectionModel().getSelectedItem();
                titoli.remove(notiziaDaRimuovere);
                //rimuovi notizia dal database
                rimossa.setVisible(true);
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


	
