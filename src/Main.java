
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CoffeeMachine machine1 = new CoffeeMachine();
        Scanner scanner = new Scanner(System.in);
        String action = "";

        System.out.println("Write action (buy, fill, take, remaining, exit):");
        while (machine1.getCurrentState() != CoffeeMachineState.EXIT) {
            action = scanner.next();
            machine1.act(action);
        }
    }
}
