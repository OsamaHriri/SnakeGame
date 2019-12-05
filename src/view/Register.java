package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class Register {
    @FXML
    private TextField tfemail;
    @FXML
    private TextField tfname;
    @FXML
    private TextField tflast;
    @FXML
    private Button signupbtn;
    @FXML
    private Button btnMinus;
    @FXML
    private Button btnExit;
    @FXML
    private BorderPane bp;
    @FXML
    private PasswordField pswrdtxt;
    @FXML
    private ImageView slideShow;
    int slideshowCount;
    ArrayList<Image> imageArrayList;


    @FXML
    public void handleExit(ActionEvent actionEvent) {
        ((Stage) btnExit.getScene().getWindow()).close();

    }

    @FXML
    public void handleBack(ActionEvent actionEvent) {
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

    public void fieldIsEmpty(TextField tf) {
        tf.setStyle("-fx-background-color: #FF9B9B");
        JOptionPane.showMessageDialog(null, tf + "is empty");
    }

    public void handleClicks(ActionEvent actionEvent) throws IOException, ParseException {
        if (actionEvent.getSource() == signupbtn) {
            System.out.println("sign up button pressed");
            String email = tfemail.getText();
            String name = tfname.getText();
            String last = tflast.getText();
            String password = pswrdtxt.getText();

            String regex = "[a-zA-Z0-9][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";

            if (email.isEmpty())
                fieldIsEmpty(tfemail);
            else if (name.isEmpty())
                fieldIsEmpty(tfname);
            else if (last.isEmpty())
                fieldIsEmpty(tflast);
            else if (!email.matches(regex))
                JOptionPane.showMessageDialog(null, "email aint valid");
            else if (!name.matches("[a-zA-Z]+") || name.length() > 30)
                JOptionPane.showMessageDialog(null, "first name aint valid");
            else if (!last.matches("[a-zA-Z]+") || last.length() > 30)
                JOptionPane.showMessageDialog(null, "last Fname aint valid");
            else {
                Boolean b = true;
                if (b == true) {
                    System.out.println("Added Successfully");
                } else {
                    System.out.println("all fields are valid");

                }
            }
        }
    }

}
