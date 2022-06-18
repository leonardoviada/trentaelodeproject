package application;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javafx.event.EventHandler;
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
		Text avviso= new Text("Attenzione! Importare solo da file con notizie complete!" + "\n" +"(La data viene assegnata al momento dell'importazione)");
		root.getChildren().add(avviso);
		
	

		TextField tf= new TextField();
		tf.setMaxWidth(200);
		root.getChildren().add(tf);

		Button importa= new Button("Importa");
		root.getChildren().add(importa);

		Text errore= new Text("Errore nell'importazione da file, controllare che il file esista e che sia nel formato corretto");
		root.getChildren().add(errore);
		errore.setVisible(false);
		importa.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				try {
					File file= new File(tf.getText());
					Scanner scan= new Scanner(file);
					while(scan.hasNextLine()) {
						String s= scan.nextLine();
						String delimiter= new String(",");
						String[] attributiNotizia= s.split(delimiter);
						Long id= new Long(attributiNotizia[0]);
						Categories categoria= Categories.valueOf(attributiNotizia[6]);
						Date data= new Date();

						News news= new News(id, attributiNotizia[1], attributiNotizia[2], attributiNotizia[3],
								attributiNotizia[4], attributiNotizia[5], categoria,data);
					

					}
					scan.close();
				} 
				catch (FileNotFoundException e) {
					errore.setVisible(true);
					e.printStackTrace();
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
