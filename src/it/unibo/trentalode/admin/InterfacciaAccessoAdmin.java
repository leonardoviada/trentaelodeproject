package it.unibo.trentalode.admin;

import it.unibo.trentalode.ConfigProvider;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class InterfacciaAccessoAdmin extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();
        root.setSpacing(7.5);


        Text nabooBot = new Text("NabooBot");
        nabooBot.setFont(Font.font("Alegreya Web Safe Font", FontPosture.ITALIC, 24));
        nabooBot.setFill(Color.BLUE);
        root.getChildren().add(nabooBot);

        Text benvenuto = new Text("Benvenuto! Effettua il login come admin");
        root.getChildren().add(benvenuto);

        root.setMargin(benvenuto, new Insets(40, 0, 10, 0));


        Text t1 = new Text("Inserire il nickname dell'admin");
        TextField tf = new TextField();
        tf.setMaxWidth(200);
        root.getChildren().add(t1);
        root.getChildren().add(tf);

        Text t2 = new Text("Inserire la password");
        PasswordField pw = new PasswordField();
        pw.setMaxWidth(200);
        root.getChildren().add(t2);
        root.getChildren().add(pw);

        Button login = new Button("Login");
        root.getChildren().add(login);
        root.setAlignment(Pos.CENTER);
        Text errorText = new Text("Credenziali errate: riprovare");
        errorText.setVisible(false);
        root.getChildren().add(errorText);

        Scene scene = new Scene(root, 300, 300);
        primaryStage.setTitle("Login admin");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);


        login.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                login.setTextFill(Color.RED);
            }
        });

        login.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                login.setTextFill(Color.BLACK);
            }
        });

        login.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent arg0) {
                if (!tf.getText().equals(ConfigProvider.getInstance().getProperty("ADMIN_USERNAME")) || !pw.getText().equals(ConfigProvider.getInstance().getProperty("ADMIN_PASSWORD"))) {
                    errorText.setVisible(true);
                } else {
                    InterfacciaAzioni azioni = new InterfacciaAzioni(primaryStage);
                    scene.setRoot(azioni.getRoot());
                }
            }
        });
        
        primaryStage.show();
    }
}
