package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class RadioButtonGroup implements ComponentLinker {

    private HBox hBox;

    private void showAlertWithoutError() {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Предупреждение со смыслом");
        warning.setHeaderText("Глупо как-то получается");
        warning.setContentText("\t\t\t\t\t\t\t\t\tМда...");
        warning.showAndWait();
    }

    @Override
    public void link() {
        this.hBox = new HBox();
        TextField nameField = new TextField();
        nameField.clear();

        Button button = new Button();
        button.setText("Выбрать");

        RadioButton firstName = new RadioButton("Раз");
        RadioButton secondName = new RadioButton("Двас");
        RadioButton thirdName = new RadioButton("Трис");

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (nameField.getText().equals(firstName.getText())) {
                    firstName.setSelected(true);
                    secondName.setSelected(false);
                    thirdName.setSelected(false);
                }
                else if (nameField.getText().equals(secondName.getText())) {
                    firstName.setSelected(false);
                    secondName.setSelected(true);
                    thirdName.setSelected(false);
                }
                else if(nameField.getText().equals(thirdName.getText())) {
                    firstName.setSelected(false);
                    secondName.setSelected(false);
                    thirdName.setSelected(true);
                }
                else {
                    showAlertWithoutError();
                }
            }
        });

        hBox.setPadding(new Insets(15, 20, 40, 12));
        hBox.getChildren().addAll(nameField, button, firstName, secondName, thirdName);
        hBox.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(10);
    }

    @Override
    public HBox component() {
        return this.hBox;
    }
}

