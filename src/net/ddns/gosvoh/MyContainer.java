package net.ddns.gosvoh;

import java.awt.*;

public class MyContainer extends Container {
    public void add(Component... components) {
        for (Component c : components) {
            add(c);
        }
    }
}
