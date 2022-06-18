package application;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EsportaNotizieSuFile{
	private final VBox root;

	EsportaNotizieSuFile(Stage primaryStage){
		root= new VBox();
		root.setSpacing(7.5);
		root.setAlignment(Pos.CENTER);

		Text notizieEsportate= new Text("Notizie esportate correttamente");
		notizieEsportate.setVisible(false);
		Text errore= new Text("Errore nella scrittura su file, riprovare");
		errore.setVisible(false);


		Text inserisciFile= new Text("Inserire percorso del file su cui si vuole esportare le notizie");
		root.getChildren().add(inserisciFile);


		TextField tf= new TextField();
		tf.setMaxWidth(200);
		root.getChildren().add(tf);

		ObservableList<String> notizie = FXCollections.observableArrayList(
				"notizia1", "notizia2", "notizia3");
		ListView<String> menuNotizie = new ListView<String>(notizie);
		root.getChildren().add(menuNotizie);
		menuNotizie.setEditable(true);
		menuNotizie.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		menuNotizie.setMaxSize(200, 150);

		Button esportaNotizie= new Button("Esporta notizie");
		root.getChildren().add(esportaNotizie);

		root.getChildren().add(errore);
		root.getChildren().add(notizieEsportate);

		esportaNotizie.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {

				try {
					PrintWriter pw= new PrintWriter(tf.getText());
					ObservableList<String> notizieDaEsportare = menuNotizie.getSelectionModel().getSelectedItems();
					for(String s : notizieDaEsportare) {
						pw.println(s);
					}
					pw.close();
				} catch (FileNotFoundException e) {
					errore.setVisible(true);
					e.printStackTrace();
				}
				notizieEsportate.setVisible(true);
			}

		});
		
		Button tornaIndietro= new Button("Torna al menu iniziale");
		root.getChildren().add(tornaIndietro);

		tornaIndietro.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {	
				InterfacciaAzioni interfaccia= new InterfacciaAzioni(primaryStage);
				primaryStage.getScene().setRoot(interfaccia.getRoot());

			}
		});

	}

	public VBox getRoot() {
		return root;
	}


}
