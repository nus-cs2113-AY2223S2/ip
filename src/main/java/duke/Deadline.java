package duke;


import java.io.FileWriter;
import java.io.IOException;
public class Deadline extends Task {
    protected String by;

    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        this.by = by;
    }

    public String toString() {
        if (this.getIsDone() == true) {
            return " [D][X]" + this.getName() + " (by: " + this.by + ")";
        } else {
            return " [D][ ]" + this.getName() + " (by: " + this.by + ")";
        }
    }

    public void print() {
        if (this.isIsDone() == false) {
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
            writeToFile(filePath, "D " + this.toString() + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

}
