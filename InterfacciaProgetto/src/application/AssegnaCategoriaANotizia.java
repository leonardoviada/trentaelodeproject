package application;
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

public class AssegnaCategoriaANotizia{
	public final VBox root;

	AssegnaCategoriaANotizia(Stage primaryStage){
		root= new VBox();

		root.setSpacing(7.5);
		root.setAlignment(Pos.CENTER);

		Text scegliNotizia= new Text("Seleziona la notizia a cui assegnare la categoria");
		root.getChildren().add(scegliNotizia);

		ObservableList<String> notizie = FXCollections.observableArrayList(
				"notizia1", "notizia2", "notizia3");
		ListView<String> menuNotizie = new ListView<String>(notizie);
		menuNotizie.setEditable(true);
		menuNotizie.setMaxSize(200, 150);
		root.getChildren().add(menuNotizie);
		

		Text scegliCategoria= new Text("Seleziona la categoria da assegnare");
		root.getChildren().add(scegliCategoria);


		ObservableList<String> categorie = FXCollections.observableArrayList(
				"categoria1", "categoria2", "categoria3");
		ListView<String> menuCategorie = new ListView<String>(categorie);
		menuCategorie.setEditable(true);
		menuCategorie.setMaxSize(200, 150);
		root.getChildren().add(menuCategorie);
		
		
		Button assegnaCategoria= new Button("Assegna Categoria");
		root.getChildren().add(assegnaCategoria);
		Text assegnata= new Text("Categoria assegnata");
		assegnata.setVisible(false);
		root.getChildren().add(assegnata);
		
		assegnaCategoria.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
			//assegna alla notizia la categoria selezionata
				assegnata.setVisible(true);
				

			}

		});
	}

	public VBox getRoot() {
		return root;
	}
}


