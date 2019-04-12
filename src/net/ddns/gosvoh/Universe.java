package net.ddns.gosvoh;

public abstract class Universe {
    private String name;

    public Universe(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
