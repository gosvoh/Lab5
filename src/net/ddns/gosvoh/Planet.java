package net.ddns.gosvoh;

import java.util.ArrayList;
import java.util.Objects;

public class Planet extends Universe {
    private String name;

    public Planet(String name) {
        super(name);
        this.name = name;
    }

    public String welcomeMessage() {
        return ("Добро пожаловать в " + this.name);
    }
//////////////////////////////////   STATIC INNER CLASS   /////////////////////////////////
    public static class Bridge {
        private String name;

        public Bridge(String name) {
            this.name = name;
        }

        public String welcomeMessage() {
           return ("Вы прибыли на " + this.name);
        }

        public String getName() {
            return this.name;
        }
//////////////////////////////////   STATIC INNER CLASS   /////////////////////////////////
        public enum Sides {
            LEFT("Левая сторона"),
            RIGHT("Правая сторона");

            private String sideName;

            Sides(String sideName) {
                this.sideName = sideName;
            }

            public String getSideName() {
                return sideName;
            }
        }
    }
////////////////////////////////   NON-STATIC INNER CLASS   ///////////////////////////////
    public class River {
        private String name;
        private double speed = 5f;
        private ArrayList<Stick> sticks = new ArrayList<Stick>();

        public River(String name) {
            this.name = name;
        }

        public River(String name, double speed) {
            this.name = name;
            this.speed = speed;
        }

        public void setSpeed(double speed) {
            this.speed = speed;
        }

        public double getSpeed() {
            return speed;
        }

        public void addStick(Stick stick) {
            sticks.add(stick);
        }

        public Stick[] getSticks() {
            return sticks.toArray(new Stick[0]);
        }

        public Stick popStick(Stick stick) {
            Stick toReturn;
            if (sticks.contains(stick) && sticks.size() != 0) {
                toReturn = sticks.get(sticks.indexOf(stick));
                sticks.remove(stick);
                return toReturn;
            } else {
                return null;
            }
        }

        public String welcomeMessage() {
            return ("Под вами находится " + this.name);
        }

        @Override
        public String toString() {
            return "River{" +
                    "name='" + name + '\'' +
                    ", speed=" + speed +
                    ", sticks=" + sticks +
                    '}';
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, speed, sticks);
        }
    }

}
