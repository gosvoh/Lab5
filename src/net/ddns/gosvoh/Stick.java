package net.ddns.gosvoh;

import java.util.Objects;

public class Stick {
    private Hero owner;
    private double weight;
    private StickType stickType;

    public Stick(StickType stickType) {
        this.stickType = stickType;
        calculateWeight();
    }

    public void setOwner(Hero owner) {
        this.owner = owner;
    }

    public double getWeight() {
        return weight;
    }

    public Hero getOwner() {
        return owner;
    }

    public void calculateWeight() {
        weight = stickType.getMinWeight() + Math.random() * (stickType.getMaxWeight() - stickType.getMinWeight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, stickType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (obj.getClass() != this.getClass())
            return false;
        Stick otherStick = (Stick) obj;
        return this.owner == otherStick.owner;
    }

    @Override
    public String toString() {
        return "Stick{" +
                "weight=" + weight +
                ", stickType=" + stickType +
                ", hash=" + hashCode() +
                '}';
    }
}
