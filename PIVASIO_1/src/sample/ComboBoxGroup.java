package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ComboBoxGroup implements ComponentLinker {

    private HBox hBox;

    private void showAlertWithoutError() {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Предупреждение со смыслом");
        warning.setHeaderText("Сейчас бы добвлять одно и тоже..");
        warning.setContentText("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tМда...");
        warning.showAndWait();
    }

    @Override
    public void link() {
        this.hBox = new HBox();
        TextField nameField = new TextField();
        nameField.setPrefColumnCount(25);

        Button addButton = new Button();
        addButton.setText("Добавить");
        addButton.setMinSize(80, 20);

        ComboBox<String> names = new ComboBox<>();
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean key = false;
                for (int i = 0; i < names.getItems().size(); i++) {
                    if (names.getItems().get(i).equals(nameField.getText())) {
                        key = true;
                        break;
                    }
                }
                if (!key && !nameField.getText().isEmpty()) {
                    names.getItems().add(nameField.getText());
                }
                else {
                    showAlertWithoutError();
                }
            }
        });

        hBox.setPadding(new Insets(15, 20, 40, 12));
        hBox.getChildren().addAll(nameField, addButton, names);
        hBox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(10);
    }

    @Override
    public HBox component() {

        return this.hBox;
    }
}
