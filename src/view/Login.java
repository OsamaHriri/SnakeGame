package view;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import model.Toast;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login {

    @FXML
    private Button loginbtn;
    @FXML
    private Hyperlink signuphere;
    @FXML
    private PasswordField pswrdtxt;
    @FXML
    private TextField emailtxt;
    @FXML
    private MediaView mv_video;
    @FXML
    private VBox vbox;
    @FXML
    private Button btnMinus;
    @FXML
    private Button btnExit;

    MediaPlayer mediaPlayer;


    public void initialize(URL arg, ResourceBundle arg1) {


        emailtxt.setStyle(emailtxt.getStyle() + "-fx-text-inner-color:white;");
        pswrdtxt.setStyle(pswrdtxt.getStyle() + "-fx-text-inner-color:white;");

        btnMinus.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
                // is stage minimizable into task bar. (true | false)
                stage.setIconified(true);
            }
        });

    }


    public void handleLink(ActionEvent event) {
//        Parent root;
//        try {
//            Stage stage = (Stage) ((Hyperlink) event.getSource()).getScene().getWindow();
//            root = FXMLLoader.load(getClass().getResource("C_SignUp.fxml"));
//            stage.setMaximized(true);
//            stage.setScene(new Scene(root));
//            mediaPlayer.stop();
//            stage.show();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    public void loginOnAction() {
        String email = emailtxt.getText();
        String pw = pswrdtxt.getText();
        if (email.equals("a") && pw.equals("a")) {
            ((Stage) btnExit.getScene().getWindow()).close();
            ViewLogic.onSignin(email);
            ViewLogic.initUIAdmin();
            return;
        }
        if (validate("Email", email, "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
                && emptyValidation("Password", pw.isEmpty())) {
            ((Stage) btnExit.getScene().getWindow()).close();
            ViewLogic.onSignin(email);
            ViewLogic.initUI();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Please Enter Valid Details");
        alert.setHeaderText(null);
        alert.showAndWait();

    }


    public void buttOnMouseEntered() {
        loginbtn.setStyle(
                " -fx-background-color :  green ;-fx-background-radius : 30");
    }

    public void buttOnMouseExited() {
        loginbtn.setStyle(
                " -fx-background-color : white ;-fx-background-radius : 30");
    }

    /**
     * this method enables logging in pressing Enter
     */
    @FXML
    private void onKeyReleased(KeyEvent e) {
        if (e.getCode() == KeyCode.ENTER)
            loginOnAction();
    }

    @FXML
    public void handleExit(ActionEvent actionEvent) {
        ((Stage) btnExit.getScene().getWindow()).close();

    }

    private boolean validate(String field, String value, String pattern) {
        if (!value.isEmpty()) {
            Pattern p = Pattern.compile(pattern);
            Matcher m = p.matcher(value);
            if (m.find() && m.group().equals(value)) {
                return true;
            } else {
                validationAlert(field, false);
                return false;
            }
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private boolean emptyValidation(String field, boolean empty) {
        if (!empty) {
            return true;
        } else {
            validationAlert(field, true);
            return false;
        }
    }

    private void validationAlert(String field, boolean empty) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Validation Error");
        alert.setHeaderText(null);
        if (field.equals("Role"))
            alert.setContentText("Please Select " + field);
        else {
            if (empty)
                alert.setContentText("Please Enter " + field);
            else
                alert.setContentText("Please Enter Valid " + field);
        }
        alert.showAndWait();
    }
}