package net.ddns.gosvoh;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GUIApp {
    public GUIApp() {
        class ButtonsTest extends JFrame {
            private JButton
                    b1 = new JButton("Button 1"),
                    exitButton = new JButton("Exit");

            private JTextField textField = new JTextField(10);

            public ButtonsTest() {
                b1.addActionListener(e -> {
                    String name = ((JButton) e.getSource()).getText();
                    textField.setText(name);
                });
                exitButton.addActionListener(e -> System.exit(0));
                setLayout(new FlowLayout());
                add(b1);
                add(exitButton);
                add(textField);
            }
        }

        class TextArea extends JFrame {
            private JButton
                    addData = new JButton("Add data"),
                    clearData = new JButton("Clear data"),
                    exitButton = new JButton("Выход");
            private JTextArea textArea = new JTextArea(20, 40);
            private Map<String, String> map = new HashMap<>();

            public TextArea() {
                map.put("Russia", "Moscow");
                map.put("US", "Washington");
                map.put("UK", "London");
                map.put("UA", "Kiev");

                addData.addActionListener(e -> {
                    for (Map.Entry me : map.entrySet())
                        textArea.append(me.getKey() + ": " + me.getValue() + "\n");
                });

                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

                clearData.addActionListener(e -> textArea.setText(""));
                exitButton.addActionListener(e -> System.exit(0));

                MyContainer container1 = new MyContainer();
                MyContainer container2 = new MyContainer();

                container1.setLayout(new FlowLayout());
                container1.add(new JScrollPane(textArea));

                container2.setLayout(new FlowLayout());
                container2.add(addData, clearData, exitButton);

                setLayout(new FlowLayout());
                add(container1);
                add(container2);
            }
        }

        class BorderLayoutTest extends JFrame {
            public BorderLayoutTest() {
                add(BorderLayout.NORTH, new JButton("North"));
                add(BorderLayout.SOUTH, new JButton("South"));
                add(BorderLayout.EAST, new JButton("East"));
                add(BorderLayout.WEST, new JButton("West"));
                add(BorderLayout.CENTER, new JButton("Center"));
            }
        }

        class FlowLayoutTest extends JFrame {
            public FlowLayoutTest() {
                setLayout(new FlowLayout());
                for (int i = 0; i < 20; i++) {
                    add(new JButton("Button " + i));
                }
            }
        }

        class GridLayoutTest extends JFrame {
            public GridLayoutTest() {
                setLayout(new GridLayout(7, 3));
                for (int i = 0; i < 20; i++) {
                    add(new JButton("Button " + i));
                }
            }
        }

        class ContainerTest extends JFrame {
            public ContainerTest() {
                MyContainer
                        container1 = new MyContainer(),
                        container2 = new MyContainer();
                Container contentPane = getContentPane();
                contentPane.setBackground(Color.RED);

                container1.setSize(100, 100);
                container1.setBackground(Color.YELLOW);
                container1.setLayout(new BoxLayout(container1, BoxLayout.Y_AXIS));
                container1.add(new JButton("This is the first button"),
                        new JButton("This is the second button"),
                        new JButton("This is the third button"));
                //container2.setBackground(Color.BLUE);

                //setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
                add(container1);
                //add(container2);
            }
        }

        class TestBackground extends JFrame {
            public TestBackground() {
                Container contentPane = getContentPane();
                contentPane.setBackground(Color.RED);
                setLayout(new FlowLayout());
            }
        }

        SwingConsole.run(new ContainerTest(), 800, 600);
    }
}
