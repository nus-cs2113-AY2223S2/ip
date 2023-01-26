import java.util.Scanner;

public class Duke {
    public static void echo(String args) {
        String lineBreak = "____________________\n";
        System.out.println(lineBreak + args + "\n" + lineBreak);
    }
    public static void main(String[] args) {
        String logo = "   _____  __     _               __         \n"
                + "  / ___/ / /_   (_)____  __  __ / /__ ____ _\n"
                + "  \\__ \\ / __ \\ / //_  / / / / // //_// __ `/\n"
                + " ___/ // / / // /  / /_/ /_/ // ,<  / /_/ / \n"
                + "/____//_/ /_//_/  /___/\\__,_//_/|_| \\__,_/  \n";
        String lineBreak = "____________________\n";
        String greetingLine = "Hello, I am Shizuka.\nHow can I be of assistance?\n";
        String exitLine = "Goodbye.\n";
        System.out.println(lineBreak + logo + lineBreak + greetingLine + lineBreak);
        Scanner in = new Scanner(System.in);
        String line;
        while (true) {
            line = in.nextLine();
            if (line.equals("bye")) {
                break;
            }
            if(line.equals("list")){
                todoList.list();
                continue;
            }
            todoList.add(line);
        }
        System.out.println(lineBreak + exitLine + lineBreak);
    }
}
