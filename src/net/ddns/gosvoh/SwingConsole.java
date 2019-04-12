package net.ddns.gosvoh;

import javax.swing.*;

public class SwingConsole {
    public static void run(final JFrame frame, final int width, final int height) {
        SwingUtilities.invokeLater(() -> {
            frame.setTitle(frame.getClass().getSimpleName());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(width, height);
            frame.setVisible(true);
        });
    }
}
