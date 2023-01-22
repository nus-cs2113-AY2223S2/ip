import java.util.Scanner;

public class CommandsListener {
    private static void printLines(String... lines) {
        String divider = "--------------------------------------------------------------------";
        System.out.println(divider);
        for (String line: lines) {
            System.out.println(line);
        }
        System.out.println(divider + System.lineSeparator());
       }

   public static void greet() {
       String logo =

                " ______ _   _  ___________ _     _____ _____  _   __" + "\n"
               + "/  ___| | | ||  ___| ___ \\ |   |  _  /  __ \\| | / /" + "\n"
               + "\\ `--.| |_| || |__ | |_/ / |   | | | | /  \\/| |/ /" + "\n"
                + "`--. \\  _  ||  __||    /| |   | | | | |    |    \\" + "\n"
               + "/\\__/ / | | || |___| |\\ \\| |___\\ \\_/ / \\__/\\| |\\  \\" + "\n"
               + "\\____/\\_| |_/\\____/\\_| \\_\\_____/\\___/ \\____/\\_| \\_/";


       printLines("Hello from", logo);

       printLines("Hello! I'm SHERLOCK", "What can I do for you?");
   }
   public static void listen() {
        Scanner in = new Scanner(System.in);
        String line;

         line = in.nextLine();

        if(line.equalsIgnoreCase("bye")) {
          printLines("Bye. Hope to see you again soon!");
          return;
        }
        printLines(line);
        listen();
    }
}
