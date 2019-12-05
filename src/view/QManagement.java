package view;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import model.Question;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class QManagement implements Initializable {

    @FXML
    Button btnDelete;
    @FXML
    ComboBox<String> ComboDelete;
    @FXML
    Button btnUpdate;
    @FXML
    ComboBox<String> ComboUpdate;
    @FXML
    TextField UpdateQuestionBody;
    @FXML
    TextField UpdateAnswer1;
    @FXML
    TextField UpdateAnswer2;
    @FXML
    TextField UpdateAnswer3;
    @FXML
    TextField UpdateAnswer4;
    @FXML
    Button btnInsert;
    TextField InsertQuestionBody;
    @FXML
    TextField InsertAnswer1;
    @FXML
    TextField InsertAnswer2;
    @FXML
    TextField InsertAnswer3;
    @FXML
    TextField InsertAnswer4;





    public void glow(MouseEvent mouse) {
        BorderPane b = (BorderPane) mouse.getSource();
        DropShadow borderGlow = new DropShadow();
        borderGlow.setColor(Color.GREEN);
        borderGlow.setHeight(83.52);
        borderGlow.setRadius(43.6075);
        borderGlow.setSpread(0.6);
        borderGlow.setWidth(92.91);
        b.setEffect(borderGlow);

    }



    @FXML
    public void clearGlow(MouseEvent mouse) {
        BorderPane b = (BorderPane) mouse.getSource();
        b.setEffect(null);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  ComboDelete.add
    }
}
