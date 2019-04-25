package net.ddns.gosvoh;

import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import com.google.gson.Gson;

import java.io.File;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class Main extends Application {
    public static void main(String[] args) {
        if (args.length == 0) {
            launch();
            //new Game();
        } else
            switch (args[0]) {
                case "-cli":
                    new HeroesCollection();
                    System.exit(0);
                case "-game":
                    new Game();
                    System.exit(0);
                default:
                    launch(args);
            }
        if (args[0].matches("-game")) {
            new Game();
            System.exit(0);
        } else
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