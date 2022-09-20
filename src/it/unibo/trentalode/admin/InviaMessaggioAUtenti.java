package it.unibo.trentalode.admin;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InviaMessaggioAUtenti{
	private final VBox root;

	InviaMessaggioAUtenti(Stage primaryStage){
		root= new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(7.5);

		Text inserisciMessaggio= new Text("Inserisci il messaggio da inviare agli utenti");
		root.getChildren().add(inserisciMessaggio);

		TextField campoMessaggio= new TextField();
		campoMessaggio.setMaxWidth(200);
		root.getChildren().add(campoMessaggio);

		Button inviaMessaggio= new Button("Invia Messaggio");
		root.getChildren().add(inviaMessaggio);

		Text messaggioInviato= new Text("Messaggio inviato");
		root.getChildren().add(messaggioInviato);
		messaggioInviato.setVisible(false);
		
		inviaMessaggio.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				//invia messaggio a utenti
				messaggioInviato.setVisible(true);
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
