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

public class SelezionaCommentoDaRimuovere{
	private final VBox root;
	
	SelezionaCommentoDaRimuovere(Stage primaryStage, News news){
		root=new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(7.5);

		Text selezionaCommentoDaRimuovere= new Text("Seleziona il commento da rimuovere");
		root.getChildren().add(selezionaCommentoDaRimuovere);
		
		ObservableList<Comment> commenti = FXCollections.observableArrayList();  //elenco dei commenti della notizia
		ListView<Comment> elencoCommenti = new ListView<Comment>(news.getComments().values());
		elencoCommenti.setEditable(true);
		elencoCommenti.setMaxSize(200, 150);
		root.getChildren().add(elencoCommenti);
		
		elencoNotizie.setCellFactory(commento -> new ListCell<News>() {
		    @Override
		    protected void updateItem(Comment item, boolean b) {
		        super.updateItem(item, b);
		        setText(item == null ? null : item.getText() );
		    }
		});
		
		Button rimuoviCommento= new Button("Rimuovi commento");
		root.getChildren().add(rimuoviCommento);
		
		Text rimosso= new Text("Commento rimosso");
		rimosso.setVisible(false);
		root.getChildren().add(rimosso);
		
		rimuoviCommento.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				news.getComments().remove(elencoCommenti.getSelectionModel().getSelectedItem());
				rimosso.setVisible(true);
				
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
