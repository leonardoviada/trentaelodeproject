package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.Categories;
import it.unibo.trentalode.bot.News;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AggiungiNotiziaSuDB {

    private final VBox root;

    AggiungiNotiziaSuDB(Stage primaryStage) {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(7.5);

        try {

            Text inserisciTitolo = new Text("Inserisci il titolo della notizia");
            root.getChildren().add(inserisciTitolo);

            TextField campoTitolo = new TextField();
            campoTitolo.setMaxWidth(200);
            root.getChildren().add(campoTitolo);

            Text inserisciAutore = new Text("Inserisci l'autore della notizia");
            root.getChildren().add(inserisciAutore);
            TextField campoAutore = new TextField();
            campoAutore.setMaxWidth(200);
            root.getChildren().add(campoAutore);

            Text inserisciCorpo = new Text("Inserisci il corpo della notizia");
            root.getChildren().add(inserisciCorpo);

            TextField campoCorpo = new TextField();
            campoCorpo.setMaxWidth(200);
            root.getChildren().add(campoCorpo);

            Text inserisciFonte = new Text("Inserisci la fonte della notizia");
            root.getChildren().add(inserisciFonte);

            TextField campoFonte = new TextField();
            campoFonte.setMaxWidth(200);
            root.getChildren().add(campoFonte);

            Text scegliCategoria = new Text("Scegli la categoria di appartenenza della notizia");
            root.getChildren().add(scegliCategoria);

            ObservableList<String> categorie = FXCollections.observableArrayList(
                    Stream.of(Categories.values())
                            .map(Enum::name)
                            .collect(Collectors.toList())
            );
            ListView<String> menuCategorie = new ListView<>(categorie);
            root.getChildren().add(menuCategorie);
            menuCategorie.setMaxSize(200, 100);

            Button creaNotizia = new Button("Crea notizia");
            root.getChildren().add(creaNotizia);
            Text aggiunta = new Text("Notizia aggiunta");
            aggiunta.setVisible(false);
            root.getChildren().add(aggiunta);

            creaNotizia.setOnMouseClicked(it -> {
                News news = new News(123, campoAutore.getText(), campoTitolo.getText(), campoCorpo.getText(), campoFonte.getText(), "https://corriere.it", Categories.LATEST_NEWS, new Date(2022, Calendar.SEPTEMBER, 21));
                aggiunta.setVisible(true);
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

            root.setAlignment(Pos.CENTER);


        } catch (Exception e) {

            e.printStackTrace();
        }


        //id(long), title, corpo, author TUTTO STRINGA, data Date, source String, categoria Categoria

    }

    public VBox getRoot() {
        return root;
    }


}