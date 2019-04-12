package net.ddns.gosvoh;

public enum StickType {
    WOODEN("Деревянная", 5, 10),
    IRON("Железная", 20, 50),
    ROCK("Каменная", 10, 50),
    PLASTIC("Пластиковая", 3, 10),
    SHISHKA("Шишка", 1, 5);

    private String name;
    private double minWeight, maxWeight;

    StickType(String type, double minWeight, double maxWeight) {
        this.name = type;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public String getName() {
        return name;
    }
}
