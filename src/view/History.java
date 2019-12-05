package view;


import javafx.fxml.FXML;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class History {

    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    @FXML
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

}
