package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.Categories;
import it.unibo.trentalode.bot.IOManager;
import it.unibo.trentalode.bot.News;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collection;
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

        Collection<News> values = IOManager.getNewsByCategory(Categories.LATEST_NEWS).values();
        Stream<News> newsStream = values.stream();
        ObservableList<String> titoli = FXCollections.observableArrayList(newsStream.map(News::getTitle).collect(Collectors.toList()));
        ListView<String> menuNotizie = new ListView<String>(titoli);
        root.getChildren().add(menuNotizie);
        menuNotizie.setEditable(true);
        menuNotizie.setMaxSize(600, 400);


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

        assegnaCategoria.setOnMouseClicked(it -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stato operazione");
            alert.setHeaderText("Categoria associata aggiornata");
            alert.showAndWait();
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


