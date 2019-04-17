package net.ddns.gosvoh;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        if (args.length == 0) {
            launch();
            //new Game();
        } else if (args[0].matches("-cli"))
            new Game();
        else
            launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        final int WIDTH = 800, HEIGHT = 600;
        Group group = new Group();
        group.getChildren().add(new Button("Test"));

        Scene scene = new Scene(group, WIDTH, HEIGHT);
        stage.setTitle("Lab 5");
        stage.setScene(scene);
        stage.show();
    }
}
