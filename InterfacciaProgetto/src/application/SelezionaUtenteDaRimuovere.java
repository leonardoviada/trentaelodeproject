package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SelezionaUtenteDaRimuovere{
private final VBox root;
	
	public SelezionaUtenteDaRimuovere(Stage primaryStage) {
		
		GenericUserList listaUser= new GenericUserList();
		listaUser.update();
		
		root= new VBox();
		root.setAlignment(Pos.CENTER);
		root.setSpacing(7.5);
		
		Text testoSeleziona= new Text("Seleziona l'utente da modificare");
		root.getChildren().add(testoSeleziona);
		
		ObservableList<GenericUser> utenti = FXCollections.observableArrayList();
		ListView<GenericUser> menuUtenti = new ListView<GenericUser>(utenti);
		
		menuUtenti.setCellFactory(user -> new ListCell<GenericUser>() {
		    @Override
		    protected void updateItem(GenericUser item, boolean b) {
		        super.updateItem(item, b);
		        setText(item == null ? null : item.getUsername() );
		    }
		});

		menuUtenti.setEditable(true);
		menuUtenti.setMaxSize(200, 150);
		root.getChildren().add(menuUtenti);
		
		for (GenericUser  g: listaUser.getUserList().values()) {
		    if(g instanceof User) {
		    	utenti.add(g);
		    }
		}
		
		Button bottoneRimuovi= new Button("Rimuovi utente");
		root.getChildren().add(bottoneRimuovi);
		
		Text rimosso= new Text("Utente rimosso");
		root.getChildren().add(rimosso);
		rimosso.setVisible(false);
		
		
		bottoneRimuovi.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				utenti.remove(menuUtenti.getSelectionModel().getSelectedItem());
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
