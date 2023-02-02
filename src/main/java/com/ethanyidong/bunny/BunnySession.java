package com.ethanyidong.bunny;

import java.util.ArrayList;
import java.util.Arrays;

public class BunnySession {
    private final static String DIVIDER = "____________________________________________________________";
    private final static String GLOBAL_INDENTATION = "\t";
    private final static String MESSAGE_INDENTATION = " ";

    private ArrayList<Task> taskList;

    public BunnySession() {
        this.taskList = new ArrayList<>();
    }

    public void printMessage(String message) {
        this.printMessage(Arrays.asList(message.split("\n")));
    }

    public void printMessage(Iterable<String> messageLines) {
        String output = "";
        output += GLOBAL_INDENTATION + DIVIDER + "\n";
        for (String line : messageLines) {
            output += GLOBAL_INDENTATION + MESSAGE_INDENTATION + line + "\n";
        }
        output += GLOBAL_INDENTATION + DIVIDER + "\n";

        System.out.print(output);
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    public int numTasks() {
        return this.taskList.size();
    }
}
