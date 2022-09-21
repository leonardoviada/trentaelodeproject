package it.unibo.trentalode.admin;

import it.unibo.trentalode.ConfigProvider;
import it.unibo.trentalode.bot.TrentaELodeBot;
import it.unibo.trentalode.bot.UsersRepository;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class InterfacciaAzioni {

    private final VBox elencoAzioni;

    public InterfacciaAzioni(Stage primaryStage) {
        elencoAzioni = new VBox();
        Text errore = new Text("Errore nell'eseguire una delle azioni, riprovare");
        errore.setVisible(false);
        elencoAzioni.getChildren().add(errore);
        try {
            elencoAzioni.setSpacing(7.5);
            Text title = new Text("Admin Naboo - Pannello di controllo");
            Separator hSep = new Separator();

            elencoAzioni.getChildren().add(title);
            elencoAzioni.getChildren().add(hSep);


            Button avviaBot = new Button("Avvia Bot Telegram");
            Separator hSep2 = new Separator();
            elencoAzioni.getChildren().add(avviaBot);
            elencoAzioni.getChildren().add(hSep2);

            Button aggiungiNotiziaSuDB = new Button("Aggiungi manualmente notizia sul Database");
            elencoAzioni.getChildren().add(aggiungiNotiziaSuDB);

            Button rimuoviNotiziaDaDB = new Button("Rimuovi manualmente notizia dal Database");
            elencoAzioni.getChildren().add(rimuoviNotiziaDaDB);

            Button importaNotizieDaFile = new Button("Importa notizie da file");
            elencoAzioni.getChildren().add(importaNotizieDaFile);

            Button importaNotizieDaFeed = new Button("Importa notizie da Feed RSS");
            elencoAzioni.getChildren().add(importaNotizieDaFeed);

            Button inviaMessaggioAUtenti = new Button("Manda messaggio tramite telegram a tutti gli utenti");
            elencoAzioni.getChildren().add(inviaMessaggioAUtenti);

            Button assegnaCategoriaANotizia = new Button("Assegna una categoria a una notizia");
            elencoAzioni.getChildren().add(assegnaCategoriaANotizia);

            Button esportaNotizieSuFile = new Button("Esporta solo le notizie che ti interessano su un file");
            elencoAzioni.getChildren().add(esportaNotizieSuFile);

            Button modificaNicknameUtenti = new Button("Modifica profili degli utenti");
            elencoAzioni.getChildren().add(modificaNicknameUtenti);

            Button creaUtenti = new Button("Crea nuovi utenti");
            elencoAzioni.getChildren().add(creaUtenti);

            Button rimuoviUtenti = new Button("Elimina utenti");
            elencoAzioni.getChildren().add(rimuoviUtenti);

            Button rimuoviCommentiUtenti = new Button("Rimuovi commenti");
            elencoAzioni.getChildren().add(rimuoviCommentiUtenti);

            elencoAzioni.setAlignment(Pos.CENTER);

            Scene scene = new Scene(elencoAzioni, 800, 500);
            primaryStage.setTitle("Interfaccia Admin");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);


            avviaBot.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent arg0) {
                    TelegramBotsApi botsApi;
                    TrentaELodeBot tlb = TrentaELodeBot.getInstance();
                    try {
                        botsApi = new TelegramBotsApi(DefaultBotSession.class);
                        botsApi.registerBot(tlb);
                        System.out.println("Bot successfully started!");
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                    tlb.persistFeedRSS();
                    tlb.importCSV(ConfigProvider.getInstance().getProperty("CSV_PATH"));

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Gestione bot");
                    alert.setHeaderText("Bot avviato");
                    alert.setContentText("E' ora possibile interagire con il sistema via Telegram. L'ascolto verrà terminato alla chiusura del pannello di controllo.");
                    alert.showAndWait();

                    avviaBot.setText("Bot Avviato");
                    avviaBot.setDisable(true);
                }
            });
            aggiungiNotiziaSuDB.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent arg0) {
                    AggiungiNotiziaSuDB aggiungi = new AggiungiNotiziaSuDB(primaryStage);
                    scene.setRoot(aggiungi.getRoot());
                }
            });

            rimuoviNotiziaDaDB.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    RimuoviNotiziaDaDB rimuovi = new RimuoviNotiziaDaDB(primaryStage);
                    scene.setRoot(rimuovi.getRoot());
                }

            });

            importaNotizieDaFile.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    ImportaNotizieDaFile importaNotizieDaFile = new ImportaNotizieDaFile(primaryStage);
                    scene.setRoot(importaNotizieDaFile.getRoot());
                }

            });

            importaNotizieDaFeed.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    ImportaNotizieDaFeed importaNotizieDaFeed = new ImportaNotizieDaFeed(primaryStage);
                    scene.setRoot(importaNotizieDaFeed.getRoot());
                }

            });

            inviaMessaggioAUtenti.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    InviaMessaggioAUtenti inviaMessaggio = new InviaMessaggioAUtenti(primaryStage);
                    scene.setRoot(inviaMessaggio.getRoot());
                }

            });

            assegnaCategoriaANotizia.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    AssegnaCategoriaANotizia assegnaCategoria = new AssegnaCategoriaANotizia(primaryStage);
                    scene.setRoot(assegnaCategoria.getRoot());

                }

            });


            esportaNotizieSuFile.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    EsportaNotizieSuFile esporta = new EsportaNotizieSuFile(primaryStage);
                    scene.setRoot(esporta.getRoot());

                }

            });

            modificaNicknameUtenti.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    SelezionaUtenteDaModificare modificaUtente = new SelezionaUtenteDaModificare(primaryStage);
                    scene.setRoot(modificaUtente.getRoot());
                }

            });

            rimuoviUtenti.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    SelezionaUtenteDaRimuovere rimuoviUtente = new SelezionaUtenteDaRimuovere(primaryStage);
                    scene.setRoot(rimuoviUtente.getRoot());
                }

            });

            creaUtenti.setOnMouseClicked(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent arg0) {
                    CreaUtente creaUtente = new CreaUtente(primaryStage, new UsersRepository(ConfigProvider.getInstance().getProperty("USERS_PATH")));
                    scene.setRoot(creaUtente.getRoot());
                }
            });


            primaryStage.show();
        } catch (Exception e) {
            errore.setVisible(true);
            e.printStackTrace();
        }

    }

    public VBox getRoot() {
        return elencoAzioni;
    }

}

