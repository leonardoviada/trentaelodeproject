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

public class SelezionaNotizia{

	private final VBox root;

	SelezionaNotizia(Stage primaryStage){
		root= new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(7.5);

		Text selezionaNotizia= new Text("Seleziona la notizia da cui si vuole rimuovere un commento");
		root.getChildren().add(selezionaNotizia);

		//Mostra elenco notizie da database
		
		ObservableList<News> notizie = FXCollections.observableArrayList();
		ListView<News> elencoNotizie = new ListView<News>(notizie);
		elencoNotizie.setEditable(true);
		elencoNotizie.setMaxSize(200, 150);
		root.getChildren().add(elencoNotizie);
		
		elencoNotizie.setCellFactory(notizia -> new ListCell<News>() {
		    @Override
		    protected void updateItem(News item, boolean b) {
		        super.updateItem(item, b);
		        setText(item == null ? null : item.getTitle() );
		    }
		});

		Button seleziona= new Button("Seleziona la notizia");
		root.getChildren().add(seleziona);

		seleziona.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
				SelezionaCommentoDaRimuovere rimuoviCommento= new SelezionaCommentoDaRimuovere(primaryStage, elencoNotizie.getSelectionModel().getSelectedItem());
				
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

}
