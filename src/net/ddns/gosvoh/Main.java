package net.ddns.gosvoh;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    private final int WIDTH = 800, HEIGHT = 600;
    Group root = new Group();

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
        Scene scene = new Scene(root, WIDTH, HEIGHT);
        stage.setTitle("Lab 5");
        stage.setScene(scene);
        stage.show();
    }
}
