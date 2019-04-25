package net.ddns.gosvoh;

import java.util.Objects;

public class Hero implements Humans {

    private String name;
    private Planet.Bridge.Sides side;
    private Stick stick;
    private StickType stickType;
    private boolean isWinner = false;
    private int winsCounter = 0, loseCounter = 0;

    public Hero(String name) {
        this.name = name;
    }

    public Hero(String name, StickType stickType) {
        this.name = name;
        this.stick = new Stick(stickType);
    }

    public Hero(String name, Stick stick) {
        this.name = name;
        this.stick = stick;
    }

    public void routine(String routine) {
        System.out.println(this.name + " решил " + routine);
    }

    public void sayTo(String message, Object target) {
        System.out.print(this.name + " говорит ");
        if (target.getClass() == Hero.class) {
            System.out.println("герою " + target.toString() + " " + message);
        } else {
            System.out.println("объекту " + target.toString() + " " + message);
        }
    }

    public void setSide(Planet.Bridge.Sides side) {
        this.side = side;
    }

    public void changeBridgeSide() {
        if (side == null)
            side = Planet.Bridge.Sides.LEFT;
        if (Math.random() < 0.01f) {
            throw new changeSideUncheckedException("Ой, " + this.name + " споткнулся и упал с моста, вызывайте скорую!");
        }
        if (side == Planet.Bridge.Sides.LEFT) {
            side = Planet.Bridge.Sides.RIGHT;
        } else {
            side = Planet.Bridge.Sides.LEFT;
        }

        System.out.println(this.name + " перебежал на " + side.getSideName());
    }

    public void chooseStick(StickType stickType) {
        this.stick = new Stick(stickType);
    }

    public void throwStick(Planet.River river) throws throwStickCheckedException {
        if (Math.random() < 0.05f)
            throw new throwStickCheckedException("Упс! " + this.name + " сломал палочку и был дисквалифицирован!");

        if (this.stick == null) {
            this.stick = new Stick(StickType.WOODEN);
        }
        this.stick.setOwner(this);
        river.addStick(stick);
        System.out.println(this.name + " бросил палочку в " + river.getName());
    }

    public void removeStick(Planet.River river) {
        if (river.popStick(this.stick) == null) {
            return;
        }
        System.out.println(this.name + " забрал свою палочку.");
    }

    public int getStickHash() {
        return stick.hashCode();
    }

    public double getStickWeight() {
        return stick.getWeight();
    }

    public boolean isWinner() {
        return this.isWinner;
    }

    public void isWinner(boolean isWinner) {
        this.isWinner = isWinner;
    }

    public void incrementWinsCounter() {
        this.winsCounter = this.winsCounter + 1;
    }

    public int getWinsCounter() {
        return winsCounter;
    }

    public void incrementLoseCounter() {
        this.loseCounter = this.loseCounter + 1;
    }

    public int getLoseCounter() {
        return loseCounter;
    }

    public int getWinsOrLoses() {
        return winsCounter - loseCounter;
    }

    public Stick getStick() {
        return this.stick;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void say(String message) {
        System.out.println(this.name + " говорит " + message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, side, stick);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Hero{");
        sb.append("name='").append(name).append('\'');
        sb.append(", side=").append(side);
        sb.append(", stick=").append(stick);
        sb.append(", isWinner=").append(isWinner);
        sb.append(", winsCounter=").append(winsCounter);
        sb.append(", loseCounter=").append(loseCounter);
        sb.append('}');
        return sb.toString();
    }
}
