package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class TableGroup implements ComponentLinker {

    private HBox hBox;

    @Override
    public void link() {
        hBox = new HBox();

        TextField valueField = new TextField();
        valueField.clear();

        TableColumn<String,Integer> tableColumnX = new TableColumn<>("X");
        tableColumnX.setMinWidth(100);
        tableColumnX.setCellValueFactory(new PropertyValueFactory<>("Key"));

        TableColumn tableColumnY = new TableColumn("Y");
        tableColumnY.setMinWidth(100);
        tableColumnY.setCellValueFactory(
                new PropertyValueFactory<>("Value"));

        ObservableList<Pair<String, String>> data = FXCollections.observableArrayList(new Pair<String, String>("1", "2"));
        TableView table = new TableView();
        table.setItems(data);
        table.getColumns().addAll(tableColumnX, tableColumnY);


        Button addToXButton = new Button();
        addToXButton.setText("->X");
        addToXButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean key = false;
                for (int i = 0; i < data.size(); i++) {
                    if (data.get(i).getKey().equals("")) {
                        Pair<String, String> temp = data.get(i);
                        data.set(i, new Pair<String, String>(valueField.getText(), temp.getValue()));
                        key = true;
                        break;
                    }
                }
                if (!key) {
                    data.add(new Pair<String, String>(valueField.getText(), ""));
                }
            }
        });

        Button fromXToYButton = new Button();
        fromXToYButton.setText("X->Y");
        fromXToYButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean key = false;
                String value = "";
                for (int i = data.size() - 1; i >= 0; i--) {
                    if (!data.get(i).getKey().isEmpty()) {
                        value = data.get(i).getKey();
                        String temp = data.get(i).getValue();
                        data.set(i, new Pair<String, String>("", temp));
                        key = true;
                        break;
                    }
                }
                if (key) {
                    boolean newKey = false;
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getValue().isEmpty()) {
                            String temp = data.get(i).getKey();
                            data.set(i, new Pair<String, String>(temp, value));
                            newKey = true;
                            break;
                        }
                    }
                    if (!newKey) {
                        data.add(new Pair<String, String>("", value));
                    }
                }
            }
        });

        Button fromYToXButton = new Button();
        fromYToXButton.setText("Y->X");
        fromYToXButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                boolean key = false;
                String value = "";
                for (int i = data.size() - 1; i >= 0; i--) {
                    if (!data.get(i).getValue().isEmpty()) {
                        value = data.get(i).getValue();
                        String temp = data.get(i).getKey();
                        data.set(i, new Pair<String, String>(temp, ""));
                        key = true;
                        break;
                    }
                }
                if (key) {
                    boolean newKey = false;
                    for (int i = 0; i < data.size(); i++) {
                        if (data.get(i).getKey().isEmpty()) {
                            String temp = data.get(i).getValue();
                            data.set(i, new Pair<String, String>(value, temp));
                            newKey = true;
                            break;
                        }
                    }
                    if (!newKey) {
                        data.add(new Pair<String, String>(value, ""));
                    }
                }
            }
        });

        hBox.setPadding(new Insets(15, 20, 40, 12));
        hBox.getChildren().addAll(valueField, addToXButton, fromXToYButton, fromYToXButton, table);
        hBox.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(10);
    }

    @Override
    public HBox component() {
        return this.hBox;
    }
}

