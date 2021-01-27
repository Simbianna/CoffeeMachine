package coffeemachine;

public enum CoffeeType {

    ESPRESSO(25, 16, 4),
    LATTE(350, 20, 7, 75),
    CAPPUCCINO(200, 12, 6, 100);

    private int water;
    private int beans;
    private int price;
    private int milk;

    private CoffeeType(int water, int beans, int price) {
        this.water = water;
        this.beans = beans;
        this.price = price;
    }

    private CoffeeType(int water, int beans, int price, int milk) {
        this.water = water;
        this.beans = beans;
        this.price = price;
        this.milk = milk;
    }

    public int getWater() {
        return water;
    }

    public int getBeans() {
        return beans;
    }

    public int getPrice() {
        return price;
    }

    public int getMilk() {
        return milk;
    }
}
