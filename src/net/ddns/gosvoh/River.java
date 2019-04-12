package net.ddns.gosvoh;

import java.util.ArrayList;
import java.util.Objects;

/*
public class River extends Planet {
    private String name;
    private float speed = 5f;
    private ArrayList<Stick> sticks = new ArrayList<Stick>();

    public River(String name) {
        super(name);
        this.name = name;
    }

    public River(String name, float speed) {
        super(name);
        this.name = name;
        this.speed = speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpeed() {
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
        if (sticks.contains(stick)) {
            toReturn = sticks.get(sticks.indexOf(stick));
            sticks.remove(stick);
            return toReturn;
        } else {
            return null;
        }
    }

    @Override
    public void welcomeMessage() {
        System.out.println("Под вами находится " + this.name);
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
*/
