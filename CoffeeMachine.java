package machine;
import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        CoffeeSystem niceCoffee = new CoffeeSystem();
        Scanner scanner = new Scanner(System.in);
        niceCoffee.start();
        for (; !niceCoffee.exit; ) {
            niceCoffee.processInput(scanner.next());
        }
    }

}

class CoffeeSystem {
    Coffee espresso = new Coffee(250, 0, 16, 4);
    Coffee latte = new Coffee(350, 75, 20, 7);
    Coffee cappuccino = new Coffee(200, 100, 12, 6);

    int water = 400;
    int milk = 540;
    int beans = 120;
    int cups = 9;
    int money = 550;

    CoffeeMachineMenu menu = CoffeeMachineMenu.START;
    boolean exit = false;

    public void processInput(String input) {
        switch (menu) {
            case START:
                System.out.println("");
                switch (input) {
                    // Write action (buy, fill, take, remaining, exit):
                    case "buy":
                        menu = CoffeeMachineMenu.BUY;
                        buyCoffee();
                        break;
                    case "fill":
                        menu = CoffeeMachineMenu.FILL_WATER;
                        System.out.println("Write how many ml of water you want to add: ");
                        break;
                    case "take":
                        takeMoney();
                        start();
                        break;
                    case "remaining":
                        getRemaining();
                        start();
                        break;
                    case "exit":
                        exit = true;
                        break;
                }
                break;
            case BUY:
                // 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu
                switch (input) {
                    case "1":
                        makeCoffee(espresso);
                        break;
                    case "2":
                        makeCoffee(latte);
                        break;
                    case "3":
                        makeCoffee(cappuccino);
                        break;
                    case "back":
                        break;
                }
                menu = CoffeeMachineMenu.START;
                start();
                break;
            case FILL_WATER:
                fillWater(Integer.valueOf(input));
                menu = CoffeeMachineMenu.FILL_MILK;
                System.out.println("Write how many ml of milk you want to add:");
                break;
            case FILL_MILK:
                fillMilk(Integer.valueOf(input));
                menu = CoffeeMachineMenu.FILL_BEANS;
                System.out.println("Write how many grams of coffee beans you want to add:");
                break;
            case FILL_BEANS:
                fillBeans(Integer.valueOf(input));
                menu = CoffeeMachineMenu.FILL_CUPS;
                System.out.println("Write how many disposable cups of coffee you want to add:");
                break;
            case FILL_CUPS:
                fillCups(Integer.valueOf(input));
                menu = CoffeeMachineMenu.START;
                System.out.println("");
                start();
                break;
        }
    }

    public void start() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    private void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
    }

    private void makeCoffee(Coffee coffee) {
        if (water < coffee.water) {
            System.out.println("Sorry, not enough water!");
        } else if (milk < coffee.milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (beans < coffee.beans) {
            System.out.println("Sorry, not enough beans!");
        } else if (cups < 1) {
            System.out.println("Sorry, not enough cups!");
        } else {
            water -= coffee.water;
            milk -= coffee.milk;
            beans -= coffee.beans;
            money += coffee.price;
            cups--;
            System.out.println("I have enough resources, making you a coffee!");
        }
        System.out.println("");
    }

    private void fillWater(int amount) {
        water += amount;
    }

    private void fillMilk(int amount) {
        milk += amount;
    }

    private void fillBeans(int amount) {
        beans += amount;
    }

    private void fillCups(int amount) {
        cups += amount;
    }

    private void takeMoney() {
        System.out.println("I gave you $" + money);
        System.out.println("");
        money = 0;
    }

    private void getRemaining() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println("$" + money + " of money");
        System.out.println("");
    }
}

enum CoffeeMachineMenu {
    START, BUY, FILL_WATER, FILL_MILK, FILL_BEANS, FILL_CUPS
}

class Coffee {
    int water;
    int milk;
    int beans;
    int price;

    public Coffee(int water, int milk, int beans, int price) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.price = price;
    }
}