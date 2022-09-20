package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.Categories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AssegnaCategoriaANotizia {
    public final VBox root;

    AssegnaCategoriaANotizia(Stage primaryStage) {
        root = new VBox();
        root.setSpacing(7.5);
        root.setAlignment(Pos.CENTER);

        Text scegliNotizia = new Text("Seleziona la notizia a cui assegnare la categoria");
        root.getChildren().add(scegliNotizia);

        ObservableList<String> notizie = FXCollections.observableArrayList(
                "notizia1", "notizia2", "notizia3");
        ListView<String> menuNotizie = new ListView<>(notizie);
        menuNotizie.setEditable(true);
        menuNotizie.setMaxSize(200, 150);
        root.getChildren().add(menuNotizie);


        Text scegliCategoria = new Text("Seleziona la categoria da assegnare");
        root.getChildren().add(scegliCategoria);


        ObservableList<String> categorie = FXCollections.observableArrayList(
                Stream.of(Categories.values())
                        .map(Enum::name)
                        .collect(Collectors.toList())
        );
        ListView<String> menuCategorie = new ListView<>(categorie);
        menuCategorie.setEditable(true);
        menuCategorie.setMaxSize(200, 150);
        root.getChildren().add(menuCategorie);


        Button assegnaCategoria = new Button("Assegna Categoria");
        root.getChildren().add(assegnaCategoria);
        Text assegnata = new Text("Categoria assegnata");
        assegnata.setVisible(false);
        root.getChildren().add(assegnata);

        assegnaCategoria.setOnMouseClicked(it -> {
            assegnata.setVisible(true);
        });

        Button tornaIndietro = new Button("Torna al menu iniziale");
        root.getChildren().add(tornaIndietro);

        tornaIndietro.setOnMouseClicked(it -> {
            InterfacciaAzioni interfaccia = new InterfacciaAzioni(primaryStage);
            primaryStage.getScene().setRoot(interfaccia.getRoot());
        });
    }

    public VBox getRoot() {
        return root;
    }
}


