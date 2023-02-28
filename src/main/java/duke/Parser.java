package duke;

public class Parser {
    public static void splitCommand(String line){
        String[] words = line.split(" ", 2);
        String command = words[0];
    }
}
