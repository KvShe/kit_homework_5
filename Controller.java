package kit.homeworks.homework_5;

import kit.homeworks.homework_5.models.Fork;
import kit.homeworks.homework_5.models.Philosopher;

public class Controller {
    private static final int AMOUNT_PHILOSOPHERS = 5;
    private static Philosopher[] philosophers = new Philosopher[AMOUNT_PHILOSOPHERS];
    private static Fork[] forks = new Fork[AMOUNT_PHILOSOPHERS];

    public static void run() {
        tableLayout();

        for (Philosopher philosopher : philosophers) {
            new Thread(philosopher).start();
        }
    }

    private static void tableLayout() {
        for (int i = 0; i < forks.length; i++) {
            forks[i] = new Fork("fork - " + i);
        }

        for (int i = 0; i < philosophers.length; i++) {
            philosophers[i] = new Philosopher(
                    "Philosopher - " + i,
                    forks[i],
                    forks[(i + 1) % forks.length]
            );
        }
    }
}
