package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ImportaNotizieDaFeed{
	private final VBox root;

	public ImportaNotizieDaFeed(Stage primaryStage) {


		root= new VBox();
		root.setSpacing(7.5);
		Text inserisciFeed= new Text("Inserisci il feed da cui importare notizie");
		root.getChildren().add(inserisciFeed);

		TextField campoTitolo= new TextField();
		campoTitolo.setMaxWidth(200);
		root.getChildren().add(campoTitolo);

		Button importa= new Button("Importa");
		root.getChildren().add(importa);

		Text notizieImportate= new Text("Notizie importate");
		notizieImportate.setVisible(false);
		root.getChildren().add(notizieImportate);
		
		Text errore= new Text("Errore nell'importazione da feed, riprovare");
		errore.setVisible(false);
		root.getChildren().add(errore);
		
		
		root.setAlignment(Pos.CENTER);

		importa.setOnMouseClicked(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent arg0) {
				try {
					//importa notizie da feed
					notizieImportate.setVisible(true);
			}
				catch(Exception e) {
					errore.setVisible(true);
				}
					
					
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
