package duke;

public class Ui {
    public static final String LINE = "    ____________________________________________________________";
    public static void greeting() {

        System.out.println(LINE);
        System.out.println("     Hello! I'm Duke");
        System.out.println("     What can I do for you?");
        System.out.println(LINE);
    }


    public static void showNotFoundError(){
        System.out.println("Failed to locate the file");
    }
}
