package application;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RimuoviNotiziaDaDB{
	
	private final VBox root;
	
	RimuoviNotiziaDaDB(){
		root= new VBox();
		root.setAlignment(Pos.CENTER);
		
		Text seleziona= new Text("Seleziona la notizia che vuoi rimuovere, di seguito mostrati i titoli delle notizie disponibili");
		root.getChildren().add(seleziona);
		
		//mostra elenco dei titoli delle notizie come bottoni
		
		//quando il bottone viene premuto cambia la scena in "Notizia elimiiata"
		
		
	}

	public VBox getRoot() {
		return root;
	}
	
	
}


	
