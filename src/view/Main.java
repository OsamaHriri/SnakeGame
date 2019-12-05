package view;

import control.SysData;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{
    System.out.println(SysData.getInstance().getHistory());
        window = primaryStage;
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            primaryStage.setMaximized(true);
            System.out.print("Hiibyee");
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}

