package coffeemachine;

import java.util.Objects;

import static coffeemachine.CoffeeType.*;

class CoffeeMachine {

    private CoffeeMachineState currentState = CoffeeMachineState.WAITING_FOR_ACTION;

    private int water = 400;
    private int milk = 540;
    private int beans = 120;
    private int cups = 9;
    private int money = 550;

    private void setCurrentState(CoffeeMachineState state) {
        currentState = state;
    }

    CoffeeMachineState getCurrentState() {
        return currentState;
    }

    void act(String input) {
        input = Objects.requireNonNullElse(input, "");

        if ((input.equals("exit") || input.equals("buy") || input.equals("fill") || input.equals("take") || input.equals("remaining"))) {
            setCurrentState(CoffeeMachineState.valueOf(input.toUpperCase()));
        }

        switch (currentState) {
            case WAITING_FOR_ACTION: {
                waitingForAction();
                break;
            }
            case EXIT: {
                exit();
                break;
            }
            case BUY: {
                buy();
                break;
            }
            case CHOOSING_COFFEE: {
                choosingCoffee(input);
                waitingForAction();
                break;
            }
            case FILL: {
                fill();
                break;
            }
            case FILLING_WATER: {
                fillingWater(input);
                break;
            }
            case FILLING_MILK: {
                fillingMilk(input);
                break;
            }
            case FILLING_BEANS: {
                fillingBeans(input);
                break;
            }
            case FILLING_CUPS: {
                fillingCups(input);
                waitingForAction();
                break;
            }
            case TAKE: {
                take();
                waitingForAction();
                break;
            }
            case REMAINING: {
                printRemaining();
                waitingForAction();
                break;
            }
            default:
                break;
        }
    }

    private void waitingForAction() {
        System.out.println("\nWrite action (buy, fill, take, remaining, exit):");
    }

    private void buy() {
        System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        setCurrentState(CoffeeMachineState.CHOOSING_COFFEE);
    }

    private void choosingCoffee(String input) {
        setCurrentState(CoffeeMachineState.WAITING_FOR_ACTION);
        if (!input.equals("back")) {
            int waterNeeded = 0;
            int milkNeeded = 0;
            int beansNeeded = 0;
            int cupsNeeded = 1;
            int moneyNeeded = 0;

            switch (input) {
                case "1":
                    waterNeeded = ESPRESSO.getWater();
                    beansNeeded = ESPRESSO.getBeans();
                    moneyNeeded = ESPRESSO.getPrice();

                    break;
                case "2":
                    waterNeeded = LATTE.getWater();
                    beansNeeded = LATTE.getBeans();
                    milkNeeded = LATTE.getMilk();
                    moneyNeeded = LATTE.getPrice();

                    break;
                case "3":
                    waterNeeded = CAPPUCCINO.getWater();
                    beansNeeded = CAPPUCCINO.getBeans();
                    milkNeeded = CAPPUCCINO.getMilk();
                    moneyNeeded = CAPPUCCINO.getPrice();

                    break;
            }

            if (water - waterNeeded < 0) {
                System.out.println("Sorry, not enough water!");
            } else if (milk - milkNeeded < 0) {
                System.out.println("Sorry, not enough milk!");
            } else if ((beans - beansNeeded < 0)) {
                System.out.println("Sorry, not enough coffee beans!");
            } else if (cups - cupsNeeded < 0) {
                System.out.println("Sorry, not enough cups");
            } else {
                water = water - waterNeeded;
                milk = milk - milkNeeded;
                beans = beans - beansNeeded;
                cups = cups - cupsNeeded;
                money = money + moneyNeeded;
                System.out.println("I have enough resources, making you a coffee!");
            }
        }

    }

    private void fill() {
        System.out.println("Write how many ml of water do you want to add:");
        setCurrentState(CoffeeMachineState.FILLING_WATER);
    }

    private void fillingWater(String input) {
        water = water + stringToInt(input);
        System.out.println("Write how many ml of milk do you want to add:");
        setCurrentState(CoffeeMachineState.FILLING_MILK);
    }

    private void fillingMilk(String input) {
        milk = milk + stringToInt(input);
        System.out.println("Write how many grams of coffee beans do you want to add:");
        setCurrentState(CoffeeMachineState.FILLING_BEANS);
    }

    private void fillingBeans(String input) {
        beans = beans + stringToInt(input);
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        setCurrentState(CoffeeMachineState.FILLING_CUPS);
    }

    private void fillingCups(String input) {
        cups = cups + stringToInt(input);
        setCurrentState(CoffeeMachineState.WAITING_FOR_ACTION);
    }

    private void take() {
        System.out.printf("I gave you $%d\n", money);
        money = 0;
        setCurrentState(CoffeeMachineState.WAITING_FOR_ACTION);
    }

    private void exit() {
        setCurrentState(CoffeeMachineState.EXIT);
    }

    private void printRemaining() {
        System.out.printf("\nThe coffee machine has:\n" +
                        "%d of water\n" +
                        "%d of milk\n" +
                        "%d of coffee beans\n" +
                        "%d of disposable cups\n" +
                        "$%d of money\n",
                water, milk, beans, cups, money
        );
        setCurrentState(CoffeeMachineState.WAITING_FOR_ACTION);
    }

    private static int stringToInt(String input) {
        int num = 0;
        try {
            num = Integer.parseInt(input);
        } catch (NumberFormatException nfe) {
            System.out.println("Not a Number");
        }
        return num;
    }


}
