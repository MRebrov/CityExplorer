package ru.netcracker.registration.other;

public class TaskPrint implements Runnable {

    private String changed = "";

    public TaskPrint(String changed) {
        this.changed = changed;
    }

    @Override
    public void run() {
        System.out.println("Runnable configurable task "+ changed);
    }
}
