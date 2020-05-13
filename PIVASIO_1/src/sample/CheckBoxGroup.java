package sample;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class CheckBoxGroup implements ComponentLinker {

    private HBox hBox;

    private void showAlertWithoutError() {
        Alert warning = new Alert(Alert.AlertType.WARNING);
        warning.setTitle("Предупреждение со смыслом");
        warning.setHeaderText("Разве не видно что такого нету?");
        warning.setContentText("\t\t\t\t\t\t\t\t\tМда...");
        warning.showAndWait();
    }

    @Override
    public void link() {
        hBox = new HBox();
        TextField nameField = new TextField();
        nameField.clear();

        Button markButton = new Button();
        markButton.setText("Отметить");
        CheckBox checkBox1 = new CheckBox("Один");
        CheckBox checkBox2 = new CheckBox("Два");
        CheckBox checkBox3 = new CheckBox("Три");

        List<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(checkBox1);
        checkBoxes.add(checkBox2);
        checkBoxes.add(checkBox3);
        markButton.setOnAction(event -> {
            boolean key = false;
            for (CheckBox box : checkBoxes) {
                if (nameField.getText().equals(box.getText())) {
                    box.setSelected(!box.isSelected());
                    key = true;
                }
            }
            if (!key) {
                showAlertWithoutError();
            }
        });

        hBox.setPadding(new Insets(15, 20, 40, 12));
        hBox.getChildren().addAll(nameField, markButton, checkBox1, checkBox2, checkBox3);
        hBox.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(10);
    }

    @Override
    public HBox component() {
        return this.hBox;
    }
}

