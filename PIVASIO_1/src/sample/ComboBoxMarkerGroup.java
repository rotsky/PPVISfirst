package sample;


import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class ComboBoxMarkerGroup implements ComponentLinker {
    private HBox hBox;
    AtomicInteger number = new AtomicInteger(0);


    @Override
    public void link() {
        hBox = new HBox();
        TextField countField = new TextField();
        countField.clear();


        List<CheckBox> checkBoxes = new ArrayList<>();

        Button createButton = new Button("Создать");

        Button markButton = new Button("Старт/Продолжить");

        Button unmarkButton = new Button("Стоп");


        createButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (!checkBoxes.isEmpty()) {
                    for (CheckBox cBox : checkBoxes) {
                        hBox.getChildren().remove(cBox);
                        number.set(0);
                    }
                    checkBoxes.clear();

                    for (int a = 0; a < Integer.parseInt(countField.getText()); a++) {
                        checkBoxes.add(new CheckBox());
                    }
                    hBox.getChildren().addAll(checkBoxes);
                } else {
                    for (int a = 0; a < Integer.parseInt(countField.getText()); a++) {
                        checkBoxes.add(new CheckBox());
                    }
                    hBox.getChildren().addAll(checkBoxes);
                }
            }
            });

            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {

                if (number.get() < checkBoxes.size() && !checkBoxes.get(number.get()).isSelected()) {
                    checkBoxes.get(number.get()).setSelected(true);
                    number.getAndIncrement();
                } else if (number.get() < checkBoxes.size() && checkBoxes.get(number.get()).isSelected()) {
                    checkBoxes.get(number.get()).setSelected(false);
                    number.getAndIncrement();
                } else {
                    number.set(0);
                }

            }));
        timeline.setCycleCount(Animation.INDEFINITE);

        markButton.setOnAction(e ->

            {
                timeline.play();
            });

        unmarkButton.setOnAction(e ->

            {
                timeline.stop();
            });

        hBox.setPadding(new

            Insets(15,20,40,12));
        hBox.getChildren().

            addAll(countField, createButton, markButton, unmarkButton);
        hBox.setBackground(new

            Background(new BackgroundFill(Color.PURPLE, CornerRadii.EMPTY, Insets.EMPTY)));
        hBox.setSpacing(10);
        }

        @Override
        public HBox component () {
            return this.hBox;
        }
    }
