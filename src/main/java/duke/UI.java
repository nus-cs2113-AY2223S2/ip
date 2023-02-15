package duke;
public class UI {

    private static final String BY = "/by";
    private static final String FROM = "/from";
    private static final String TO = "/to";
    private static final String TEXTSEPARATOR = "|";
    private static final String MARGIN = "*----------------------------*";
    // Begin Program
    public static void welcomeMessage(){
        String logo = "__________________ ##\n" +
                "_________________###*\n" +
                "______________.*#####\n" +
                "_____________*######\n" +
                "___________*#######\n" +
                "__________*########.\n" +
                "_________*#########.\n" +
                "_________*#######*##*\n" +
                "________*#########*###\n" +
                "_______*##########*__*##\n" +
                "_____*###########_____*\n" +
                "____############\n" +
                "___*##*#########\n" +
                "___*_____########\n" +
                "__________#######\n" +
                "___________*######\n" +
                "____________*#####*\n" +
                "______________*####*\n" +
                "________________*####\n" +
                "__________________*##*\n" +
                "____________________*##\n" +
                "_____________________*##.\n" +
                "____________________.#####.\n" +
                "_________________.##########\n" +
                "________________.####*__*####" ;


        System.out.println(logo);
        System.out.println(MARGIN);
        System.out.println("Hello! I'm Duke!");
        System.out.println("What can I do for you?");
        System.out.println(MARGIN);
    }
    // Terminate program
    public static void endProgram(){
        System.out.println(MARGIN);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(MARGIN);
    }


}
