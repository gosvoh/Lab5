package net.ddns.gosvoh;

import javax.swing.*;
import java.awt.*;

public class GUIInterface {

    private Planet planet;
    private Planet.Bridge bridge;
    private Planet.River river;
    private Planet.Bridge.Sides side;
    private Hero[] heroes;

    public GUIInterface() {
        class Text extends JFrame {
            public Text() {
                JLabel textField = new JLabel();
                StringBuilder stringBuilder = new StringBuilder();
                //stringBuilder.append();
                //textField.setText();;
            }
        }

        SwingConsole.run(new Text(), 800, 600);
    }
}
