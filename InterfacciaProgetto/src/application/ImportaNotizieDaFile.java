package application;


import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ImportaNotizieDaFile{

	private final VBox root;
	
	ImportaNotizieDaFile(){
		root= new VBox();
		root.setAlignment(Pos.CENTER);
		
		Text inserisciFile= new Text("Inserire percorso del file da cui si vuole importare notizie");
		root.getChildren().add(inserisciFile);
		
		TextField tf= new TextField();
		tf.setMaxWidth(200);
		root.getChildren().add(tf);
		
		Button importa= new Button("Importa");
		root.getChildren().add(importa);
		
		
		
	}
	
	public VBox getRoot() {
		return root;
	}
}
