package duke;

import java.io.FileWriter;
import java.io.IOException;

public class Task {
    private String name;
    private boolean isDone;
    private char taskType;

    public boolean isIsDone() {
        return this.isDone;
    }

    public char getTaskType() {
        return this.taskType;
    }

    public void setTaskType(char taskType) {
        this.taskType = taskType;
    }

    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String toString() {
        if (this.isDone == true) {
            return " [T][X]" + this.name;
        } else {
            return " [T][ ]" + this.name;
        }
    }

    public void print() {
        if (this.isDone == false) {
            System.out.println("." + this.toString());
        } else {
            System.out.println("." + this.toString());
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    public void save() {
        String filePath = "C:/repos/IP/src/main/java/duke/load.txt";
        try {
            writeToFile(filePath, "T " + this.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
