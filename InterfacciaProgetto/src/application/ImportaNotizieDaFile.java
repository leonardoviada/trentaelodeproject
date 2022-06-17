package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ImportaNotizieDaFile{

	private final VBox root;

	ImportaNotizieDaFile(Stage primaryStage){


		root= new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(7.5);

		Text inserisciFile= new Text("Inserire percorso del file da cui si vuole importare notizie");
		root.getChildren().add(inserisciFile);
	

		TextField tf= new TextField();
		tf.setMaxWidth(200);
		root.getChildren().add(tf);

		Button importa= new Button("Importa");
		root.getChildren().add(importa);

		Text errore= new Text("Errore nell'apertura del file, riprovare");
		root.getChildren().add(errore);
		errore.setVisible(false);
		importa.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					File file= new File(tf.getText());
					Scanner scan= new Scanner(file);
					while(scan.hasNextLine()) {
						//crea notizie dal file

					}
					scan.close();
				} 
				catch (FileNotFoundException e) {
					errore.setVisible(true);
					e.printStackTrace();
				}
			}

		});



	}

	public VBox getRoot() {
		return root;
	}
}
