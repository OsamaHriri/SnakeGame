package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import model.CustomRipple;
import model.Toast;
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

public class MainPageController implements Initializable {
    @FXML
    private Button btnOverview;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnOffers;
    @FXML
    private Button btnSettings;
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
            root = FXMLLoader.load(getClass().getResource("Game.fxml"));
            borderPane.setCenter(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        buttonList.add(btnOverview);
        buttonList.add(btnOrders);
        buttonList.add(btnOffers);
        buttonList.add(btnSettings);
        buttonList.add(btnSignout);

    }

    @FXML
    public void clearClick() {
        btnOverview.setStyle("-fx-background-color :   #1A394C");
        btnOrders.setStyle("-fx-background-color :   #1A394C");
        btnOffers.setStyle("-fx-background-color :   #1A394C");
        btnSettings.setStyle("-fx-background-color :   #1A394C");
        btnSignout.setStyle("-fx-background-color :  #1A394C");
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        ((Stage) btnExit.getScene().getWindow()).close();
    }

}
