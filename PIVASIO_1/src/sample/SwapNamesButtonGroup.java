package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class SwapNamesButtonGroup implements ComponentLinker {

    private HBox hBox;

    @Override
    public void link() {
        hBox = new HBox();
        TextField nameField = new TextField();
        nameField.clear();

        Button setNameButton = new Button();
        setNameButton.setText("Имя второй кнопки");
        Button swapNamesButton = new Button();
        swapNamesButton.setText("Обмен наименованиями");
        setNameButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                swapNamesButton.setText(nameField.getText());
            }
        });

        swapNamesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String temp = setNameButton.getText();
                setNameButton.setText(swapNamesButton.getText());
                swapNamesButton.setText(temp);
            }
        });

        hBox.setPadding(new Insets(15, 20, 40, 12));
        hBox.getChildren().addAll(nameField, setNameButton, swapNamesButton);
        hBox.setBackground(new Background(new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(10);
    }

    @Override
    public HBox component() {
        return this.hBox;
    }
}

