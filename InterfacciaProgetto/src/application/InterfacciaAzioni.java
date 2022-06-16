package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;


public class InterfacciaAzioni {

	private final VBox elencoAzioni;

	public InterfacciaAzioni(Stage primaryStage) {
		elencoAzioni= new VBox();
		try {
			Insets spazio= new Insets(0,0,7.5,0);

			Text title= new Text("Benvenuto! Queste sono le azioni disponibili");
			elencoAzioni.getChildren().add(title);
			elencoAzioni.setMargin(title, spazio);

			Button aggiungiNotiziaSuDB= new Button("Aggiungi manualmente notizia sul Database");
			elencoAzioni.getChildren().add(aggiungiNotiziaSuDB);
			elencoAzioni.setMargin(aggiungiNotiziaSuDB, spazio);
			
			
			

			Button rimuoviNotiziaDaDB= new Button("Rimuovi manualmente notizia dal Database");
			elencoAzioni.getChildren().add(rimuoviNotiziaDaDB);
			elencoAzioni.setMargin(rimuoviNotiziaDaDB, spazio);
			
			//aggiungi gestione eventi

			Button importaNotizieDaFile= new Button("Importa notizie da file");
			elencoAzioni.getChildren().add(importaNotizieDaFile);
			elencoAzioni.setMargin(importaNotizieDaFile, spazio);
			
			//aggiungi gestione eventi
			
			Button importaNotizieDaFeed= new Button("Importa notizie da Feed RSS");
			elencoAzioni.getChildren().add(importaNotizieDaFeed);
			elencoAzioni.setMargin(importaNotizieDaFeed, spazio);

			Button inviaMessaggioAUtenti= new Button("Manda messaggio tramite telegram a tutti gli utenti");
			elencoAzioni.getChildren().add(inviaMessaggioAUtenti);
			elencoAzioni.setMargin(inviaMessaggioAUtenti, spazio);

			Button assegnaCategoriaANotizia= new Button("Assegna una categoria a una notizia");
			elencoAzioni.getChildren().add(assegnaCategoriaANotizia);
			elencoAzioni.setMargin(assegnaCategoriaANotizia, spazio);

			Button esportaNotizieSuFile= new Button("Esporta solo le notizie che ti interessano su un file");
			elencoAzioni.getChildren().add(esportaNotizieSuFile);
			elencoAzioni.setMargin(esportaNotizieSuFile, spazio);

			Button modificaProfiliUtenti= new Button("Modifica, crea o elimina profili degli utenti");
			elencoAzioni.getChildren().add(modificaProfiliUtenti);
			elencoAzioni.setMargin(modificaProfiliUtenti, spazio);

			Button rimuoviCommentiUtenti= new Button("Rimuovi commenti");
			elencoAzioni.getChildren().add(rimuoviCommentiUtenti);
			elencoAzioni.setMargin(rimuoviCommentiUtenti, spazio);


			elencoAzioni.setAlignment(Pos.CENTER);


			Scene scene = new Scene(elencoAzioni,400,400);
			primaryStage.setTitle("Interfaccia Admin");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			
			
			aggiungiNotiziaSuDB.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					AggiungiNotiziaSuDB aggiungi= new AggiungiNotiziaSuDB(primaryStage);
					//Scene scene1= new Scene(aggiungi.getRoot(), 300, 300);
					//primaryStage.setScene(scene1);
					scene.setRoot(aggiungi.getRoot());
				}

			});
			
			rimuoviNotiziaDaDB.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					//AggiungiNotiziaSuDB aggiungi= new AggiungiNotiziaSuDB(primaryStage);
					//Scene scene1= new Scene(aggiungi.getRoot(), 300, 300);
					//primaryStage.setScene(scene1);
					
	
				}

			});
			
			primaryStage.show();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public VBox getRoot() {
		return elencoAzioni;
	}

}

