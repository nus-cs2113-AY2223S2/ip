import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Task {
    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    public void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    public enum TaskType {
        TODO, DEADLINE, EVENT
    }
    public static ArrayList<String> items = new ArrayList<String>();
    public static ArrayList<Boolean> marked = new ArrayList<Boolean>();
    public static ArrayList<TaskType> tasks = new ArrayList<TaskType>();
    
    public Task() {
    }
    public void readFile(String filePath) throws FileNotFoundException{
        try {
                FileReader fr = new FileReader(filePath);
                String saved_text = "";
            
            int i;
            while ((i = fr.read()) != -1) {
                if ((char) i == '\n') {
                    String[] strArray = saved_text.split(" \\| ");
                    if (strArray[0].equals("TODO")) {
                        tasks.add(TaskType.TODO);
                    }
                    else if (strArray[0].equals("DEADLINE")) {
                        tasks.add(TaskType.DEADLINE);
                    }
                    else if (strArray[0].equals("EVENT")) {
                        tasks.add(TaskType.EVENT);
                    }
                    if (strArray[1].equals("1")) {
                        marked.add(true);
                    }
                    else if (strArray[1].equals("0")) {
                        marked.add(false);
                    }
                    items.add(strArray[2]);
                    saved_text = "";
                }
                else {
                    saved_text += (char) i;
                }
            } 
            fr.close();
        }
        catch (IOException | NumberFormatException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void setDone(String input) {
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, true);
        System.out.println("Nice! I've marked this task as done:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[X] " + items.get(num-1));
    }
    public void setNotDone(String input) {
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        marked.set(num-1, false);
        System.out.println("Ok, I've marked this task as not done yet:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[ ] " + items.get(num-1));
    }
    //getters
    public void getItems() {
        System.out.println("Here are the tasks in your list:\n");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i+1) + ". " + "[" + tasks.get(i).toString().charAt(0) + "]" +"[" + (marked.get(i) ? "X" : "") + "] " + items.get(i));
        }
    }

    public void delete(String input){
        String[] strArray = input.split(" ");
        int num = Integer.parseInt(strArray[1]);
        System.out.println("Noted. I've removed this task:\n" + "[" + tasks.get(num-1).toString().charAt(0) + "]" + "[" + (marked.get(num-1) ? "X" : "") + "] " + items.get(num-1));
        items.remove(num-1);
        System.out.println("Now you have " + items.size() + " tasks in the list.");
        marked.remove(num-1);
        tasks.remove(num-1);
    }
    public void save() {
        try {
            Files.createDirectories(Paths.get("data"));
            writeToFile("data/duke.txt", "");
            String file = "";
            for (int i = 0; i < items.size(); i++) {
                file = (file  + tasks.get(i).toString() + " | " + (marked.get(i) ? "1" : "0") + " | " + items.get(i) + "\n");
            }
            writeToFile("data/duke.txt", file);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }
    public void print() {
        String type = "[" + tasks.get(this.tasks.size()-1).toString().charAt(0) + "]"
;       String checkbox = this.marked.get(this.marked.size()-1) ? "[X] " : "[ ] ";
        String item = this.items.get(this.items.size()-1);
        System.out.println("Got it. I've added this task: \n" + type + checkbox + item + "\n" + "Now you have " + items.size() + " tasks in the list.");
    }
}
