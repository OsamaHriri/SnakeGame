package view;
import control.Controller;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class Game {
    @FXML
    private Button startbtn;
public static Scene scene;
public static Stage stage;
    @FXML
    private Pane canvas;



    public void initialize(URL arg, ResourceBundle arg1) {
    }

     @FXML
    public void handleStartGame(ActionEvent actionEvent) {


         Parent root;
         try {
             root = FXMLLoader.load(getClass().getResource("MainGameView.fxml"));
             stage = new Stage();

             scene = (new Scene(root, 600, 600));
             scene.getRoot().requestFocus();
             stage.setScene(scene);

             stage.show();

         }
         catch (IOException e) {
             e.printStackTrace();
         }
         Controller startGame = Controller.getInstance();

         System.out.print("Controller intiated");
    }

    public Scene getScene() {
        return scene;
    }
}
