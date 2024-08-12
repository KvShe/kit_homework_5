package kit.homeworks.homework_5.models;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private String name;
    private Lock lock;

    public Fork(String name) {
        this.name = name;
        this.lock = new ReentrantLock();
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return name;
    }
}
