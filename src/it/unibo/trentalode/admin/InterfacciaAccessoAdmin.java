package it.unibo.trentalode.admin;

import it.unibo.trentalode.bot.ConfigProvider;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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


        Text nabooBot = new Text("30LBot");
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

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Login");
        alert.setHeaderText("Credenziali errate");


        Scene scene = new Scene(root, 800, 500);
        primaryStage.setTitle("Login admin");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);


        login.setOnMouseEntered(arg0 -> login.setTextFill(Color.BLUE));

        login.setOnMouseExited(arg0 -> login.setTextFill(Color.BLACK));

        login.setOnMouseClicked(arg0 -> {
            if (!tf.getText().equals(ConfigProvider.getInstance().getProperty("ADMIN_USERNAME")) || !pw.getText().equals(ConfigProvider.getInstance().getProperty("ADMIN_PASSWORD"))) {
                alert.showAndWait();
            } else {
                new InterfacciaAzioni(primaryStage);
            }
        });

        primaryStage.show();
    }
}
