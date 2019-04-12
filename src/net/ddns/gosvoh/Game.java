package net.ddns.gosvoh;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {


    public Game() {
        class writeOnNewLine {
            public writeOnNewLine() {
                System.out.println();
            }

            public writeOnNewLine(String message) {
                System.out.println(message);
            }
        }
////////////////////////////////   INITIALIZERS   /////////////////////////////////////////
        Planet planet = new Planet("Планета глупости");
        Planet.River river = planet.new River("Река глупости");
        Planet.Bridge bridge = new Planet.Bridge("Мост глупости");
        Planet.Bridge.Sides side = Planet.Bridge.Sides.LEFT;
        ArrayList<Hero> heroes = new ArrayList<>();

        System.out.println("Поприветствуем нашиъ героев! (Enter для подтверждения, Ctrl+D для выхода)");
        do {
            System.out.print("Введите имя героя: ");
            String heroNameIn = ConsoleInput();

            if (!heroNameIn.isEmpty()) {
                heroes.add(new Hero(heroNameIn));
            } else break;
        } while (true);

        System.out.println();
        System.out.println(planet.welcomeMessage());
        System.out.println(river.welcomeMessage());
        System.out.println(bridge.welcomeMessage());
        System.out.println("Вы на " + side.getSideName() + " объекта " + bridge.getName());
        System.out.println(heroes.size());

        heroes.forEach(hero -> hero.setSide(side));

        System.out.println();
        System.out.println("Давайте выберем палочки героев!");
        heroes.forEach(hero -> {
            System.out.println("Выберите палочку герою:");
            int iterator = 1;
            for (StickType st : StickType.values()) {
                System.out.println(iterator + ") " + st.getName());
                iterator++;
            }
            StickType stickType = StickType.WOODEN;
            boolean stopChooseStickType = false;
            do {
                String stickTypeIn = ConsoleInput();

                switch (stickTypeIn) {
                    case "1":
                        stickType = StickType.WOODEN;
                        stopChooseStickType = true;
                        break;
                    case "2":
                        stickType = StickType.IRON;
                        stopChooseStickType = true;
                        break;
                    case "3":
                        stickType = StickType.ROCK;
                        stopChooseStickType = true;
                        break;
                    case "4":
                        stickType = StickType.PLASTIC;
                        stopChooseStickType = true;
                        break;
                    case "5":
                        stickType = StickType.SHISHKA;
                        stopChooseStickType = true;
                        break;
                    default:
                        System.out.print("Введите только номер палочки: ");
                }
            } while (!stopChooseStickType);

            hero.chooseStick(stickType);
        });

        announcer.say("Начнём же игру!");

        heroes.forEach(hero -> {
            try {
                hero.throwStick(river);
                hero.changeBridgeSide();
            } catch (throwStickCheckedException e) {
                new writeOnNewLine(e.getMessage());
                announcer.say("Игра окончена! Не все игроки готовы!");
                return;
            }
        });

        System.out.println();

        double maxSpeed = Float.MAX_VALUE;
        for (int i = 0; i < river.getSticks().length; i++) {
            double currentStickSpeed = river.getSticks()[i].getWeight() / river.getSpeed();
            if (maxSpeed > currentStickSpeed) {
                maxSpeed = currentStickSpeed;
                heroes.forEach(hero -> hero.isWinner(false));
                river.getSticks()[i].getOwner().isWinner(true);
            }
        }

        long timeToWait = (long) (river.getSpeed() - maxSpeed);
        try {
            Thread.sleep(timeToWait * 1000);
        } catch (Exception e) {
            return;
        }

        heroes.forEach(hero -> {
            hero.removeStick(river);
            hero.setSide(side);
        });

        heroes.forEach(hero -> {
            System.out.println(hero.getName() + ": Wins: " + hero.getWinsCounter() + " Loses: " + hero.getLoseCounter());
            if (hero.getWinsOrLoses() > 0)
                System.out.println(" Общее количество побед: " + hero.getWinsOrLoses());
            else System.out.println(" Общее количество проигрышей: " + Math.abs(hero.getWinsOrLoses()));
        });
    }

    private Humans announcer = new Humans() {
        String name = "Announcer";

        @Override
        public void say(String message) {
            System.out.println(name + ": - \"" + message + "\"");
        }

        @Override
        public String getName() {
            return name;
        }
    };

    private String ConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine())
            return scanner.nextLine();
        scanner.close();
        return "";
    }
}
