package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AggiungiNotiziaSuDB  {

	private final VBox inserimentoNotiziaManualmente;

	AggiungiNotiziaSuDB(Stage primaryStage){
		//textfield con il testo della notizia da aggiungere
		inserimentoNotiziaManualmente= new VBox();
		
		try {
			Insets spazio= new Insets(0,0,10,0);

			Text inserisciTitolo= new Text("Inserisci il titolo della notizia");
			inserimentoNotiziaManualmente.getChildren().add(inserisciTitolo);
			inserimentoNotiziaManualmente.setMargin(inserisciTitolo, spazio);
			
			TextField campoTitolo= new TextField();
			campoTitolo.setMaxWidth(200);
			inserimentoNotiziaManualmente.getChildren().add(campoTitolo);
			inserimentoNotiziaManualmente.setMargin(campoTitolo, spazio);
			
			Text inserisciCorpo= new Text("Inserisci il corpo della notizia");
			inserimentoNotiziaManualmente.getChildren().add(inserisciCorpo);
			inserimentoNotiziaManualmente.setMargin(inserisciCorpo, spazio);
			
			TextField campoCorpo= new TextField();
			campoCorpo.setMaxWidth(200);
			inserimentoNotiziaManualmente.getChildren().add(campoCorpo);
			inserimentoNotiziaManualmente.setMargin(campoCorpo, spazio);
			
			Text inserisciFonte= new Text("Inserisci la fonte della notizia");
			inserimentoNotiziaManualmente.getChildren().add(inserisciFonte);
			inserimentoNotiziaManualmente.setMargin(inserisciFonte, spazio);
			
			TextField campoFonte= new TextField();
			campoFonte.setMaxWidth(200);
			inserimentoNotiziaManualmente.getChildren().add(campoFonte);
			inserimentoNotiziaManualmente.setMargin(campoFonte, spazio);
			
			Text scegliCategoria= new Text("Scegli la categoria di appartenenza della notizia");
			inserimentoNotiziaManualmente.getChildren().add(scegliCategoria);
			inserimentoNotiziaManualmente.setMargin(scegliCategoria, spazio);
			
			Button creaNotizia= new Button("Crea notizia");
			inserimentoNotiziaManualmente.getChildren().add(creaNotizia);
			
			//handle events on click
			
			inserimentoNotiziaManualmente.setAlignment(Pos.CENTER);
			
			//inserire menu a tendina con scelta categoria da enum
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		

		//id(long), title, corpo, author TUTTO STRINGA, data Date, source String, categoria Categoria

	}
	
	public VBox getRoot() {
		return inserimentoNotiziaManualmente;
	}
	

}