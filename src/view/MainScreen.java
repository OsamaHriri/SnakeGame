package view;

import control.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import model.CustomRipple;
import model.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainScreen implements Initializable {
    @FXML
    private Button btnGame;
    @FXML
    private Button btnQuickGuide;
    @FXML
    private Button btnHistory;
    @FXML
    private Button btnQuestions;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnExit;
    @FXML
    private SplitPane splitPane;
    @FXML
    private BorderPane borderPane;
    @FXML
    private HBox titleBar;

    private List<Button> buttonList = new ArrayList<Button>();


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {

        Toast.makeText(Main.window, "Logged In Successfully", 1500, 500, 500);

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("History.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonList.add(btnGame);
        buttonList.add(btnQuickGuide);
        buttonList.add(btnHistory);
        buttonList.add(btnQuestions);
        buttonList.add(btnSignout);
        for (Button b : buttonList) {
            b.setOnMousePressed(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent event) {
                    clearClick();
                    CustomRipple customRipple = new CustomRipple();
                    customRipple.createRipple(event.getSceneX(), event.getSceneY(), borderPane, b);
                    if (event.getSource() == btnGame) {
                        btnGame.setStyle("-fx-background-color :  black");
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("Game.fxml"));
                            borderPane.setCenter(root);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (event.getSource() == btnQuickGuide) {
                        btnQuickGuide.setStyle("-fx-background-color : black");
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("QuickGuide.fxml"));
                            borderPane.setCenter(root);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (event.getSource() == btnHistory) {

                        btnHistory.setStyle("-fx-background-color : black");
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("History.fxml"));
                            borderPane.setCenter(root);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (event.getSource() == btnQuestions) {
                        btnQuestions.setStyle("-fx-background-color : black");
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("QManagement.fxml"));
                            borderPane.setCenter(root);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (event.getSource() == btnSignout) {
                        ((Stage) btnExit.getScene().getWindow()).close();
                        Stage s = ((Stage) btnExit.getScene().getWindow());
                        Parent root;
                        try {
                            root = FXMLLoader.load(getClass().getResource("Login.fxml"));
                            s.setMaximized(true);
                            s.setScene(new Scene(root));
                            s.show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }
    }

    @FXML
    public void clearClick() {
        btnGame.setStyle("-fx-background-color :  green");
        btnQuickGuide.setStyle("-fx-background-color :   green");
        btnHistory.setStyle("-fx-background-color :   green");
        btnQuestions.setStyle("-fx-background-color :   green");
        btnSignout.setStyle("-fx-background-color :  green");
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        ((Stage) btnExit.getScene().getWindow()).close();
    }



}
