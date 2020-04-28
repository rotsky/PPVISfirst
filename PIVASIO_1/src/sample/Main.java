package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        VBox root = new VBox();

        List<ComponentLinker> linkers = new ArrayList<>();
        linkers.add(new ComboBoxGroup());
        linkers.add(new SwapNamesButtonGroup());
        linkers.add(new RadioButtonGroup());
        linkers.add(new CheckBoxGroup());
        linkers.add(new TableGroup());
        linkers.add(new ComboBoxMarkerGroup());

        List<HBox> groups = new ArrayList<>();
        for (ComponentLinker linker : linkers) {
            linker.link();
            groups.add(linker.component());
        }
        root.getChildren().addAll(groups);


        Scene scene = new Scene(root, 600, 800);
        primaryStage.setTitle("PPVIS1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}