package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.Categories;
import it.unibo.trentalode.bot.IOManager;
import it.unibo.trentalode.bot.News;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RimuoviNotiziaDaDB {

    private final VBox root;
    private Categories selectedCategory = Categories.LATEST_NEWS;

    RimuoviNotiziaDaDB(Stage primaryStage) {
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setSpacing(7.5);


        Text labelCategoria = new Text("Seleziona la categoria");
        root.getChildren().add(labelCategoria);
        ComboBox<String> comboBox = new ComboBox<String>(FXCollections.observableArrayList(
                Stream.of(Categories.values())
                        .map(Enum::name)
                        .collect(Collectors.toList())
        ));
        comboBox.setValue(Categories.LATEST_NEWS.toString());
        root.getChildren().add(comboBox);

        Text seleziona1 = new Text("Seleziona la notizia che vuoi rimuovere.");
        root.getChildren().add(seleziona1);
        Text seleziona2 = new Text("Di seguito mostrati i titoli delle notizie disponibili:");
        root.getChildren().add(seleziona2);

        Collection<News> values = IOManager.getNewsByCategory(selectedCategory).values();
        Stream<News> newsStream = values.stream();
        ObservableList<String> titoli = FXCollections.observableArrayList(newsStream.map(News::getTitle).collect(Collectors.toList()));
        ListView<String> menuNotizie = new ListView<String>(titoli);
        root.getChildren().add(menuNotizie);
        menuNotizie.setEditable(true);
        menuNotizie.setMaxSize(600, 400);

        Button rimuoviNotizia = new Button("Rimuovi selezione");
        rimuoviNotizia.setDisable(true);
        root.getChildren().add(rimuoviNotizia);

        menuNotizie.setOnMouseClicked(arg0 -> {
            if (menuNotizie.getSelectionModel().getSelectedItem() != null)
                rimuoviNotizia.setDisable(false);
        });


        rimuoviNotizia.setOnMouseClicked(arg0 -> {
            String titoloDaRimuovere = menuNotizie.getSelectionModel().getSelectedItem();

            /*News newsDaRimuovere = newsStream
                    .filter(n -> Objects.equals(n.getTitle(), titoloDaRimuovere))
                    .findFirst()
                    .get();*/

            titoli.remove(titoloDaRimuovere);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Stato operazione");
            alert.setHeaderText("Notizia rimossa");
            alert.showAndWait();
        });

        Button tornaIndietro = new Button("Torna al menu iniziale");
        root.getChildren().add(tornaIndietro);

        comboBox.setOnAction(event -> {
            this.selectedCategory = Categories.valueOf(comboBox.getValue());
            Collection<News> values1 = IOManager.getNewsByCategory(selectedCategory).values();
            Stream<News> newsStream1 = values.stream();
            ObservableList<String> titoli1 = FXCollections.observableArrayList(newsStream.map(News::getTitle).collect(Collectors.toList()));
            menuNotizie.setSelectionModel((MultipleSelectionModel<String>) titoli1);
        });

        tornaIndietro.setOnMouseClicked(arg0 -> {
            InterfacciaAzioni interfaccia = new InterfacciaAzioni(primaryStage);
            primaryStage.getScene().setRoot(interfaccia.getRoot());
        });
    }

    public VBox getRoot() {
        return root;
    }


}


	
