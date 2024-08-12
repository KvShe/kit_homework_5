package kit.homeworks.homework_5.models;

public class Philosopher implements Runnable {
    private String name;
    private Fork leftFork;
    private Fork rightFork;
    private int countEat = 3;

    public Philosopher(String name, Fork leftFork, Fork rightFork) {
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    @Override
    public void run() {
        for (int i = 0; i < countEat; i++) {
            think();
            eat();
        }

//        while (true) {
//            think();
//            eat();
//        }
    }

    private void think() {
        System.out.println(name + " - thinks");
        sleep(500);
    }

    private void eat() {
        takeForks();
        showTakeForks();
        System.out.println(name + " - eats");
        sleep(1000);

        showPutDawnForks();
    }

    private void takeForks() {
//        leftFork.getLock().lock();
//        rightFork.getLock().lock();

        boolean leftForkTaken = false;
        boolean rightForkTaken = false;

        while (true) {
            try {
                leftFork.getLock().lock();
                rightFork.getLock().lock();

                leftForkTaken = leftFork.getLock().tryLock();
                rightForkTaken = rightFork.getLock().tryLock();
            } finally {
                if (leftForkTaken && rightForkTaken) return;
                if (leftForkTaken) leftFork.getLock().unlock();
                if (rightForkTaken) rightFork.getLock().unlock();
            }

            sleep(1);
        }
    }

    private void showTakeForks() {
        System.out.println(name + " takes " + leftFork + " & " + rightFork);
    }

    private void showPutDawnForks() {
        leftFork.getLock().unlock();
        rightFork.getLock().unlock();

        System.out.println(name + " put down " + leftFork + " & " + rightFork);
    }

    private void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return name + ": left " + leftFork + ", right " + rightFork;
    }
}
