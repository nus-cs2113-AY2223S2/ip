import java.util.Scanner;

public class Duke {
    private static String[] list = new String[100];
    private static int itemNo = 1;

    public static void greetUser() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello, I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public static void echo() {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String line = scanner.nextLine();
            System.out.println("____________________________________________________________");
            System.out.println(line);
            System.out.println("____________________________________________________________");
            if (line.equals("bye")) {
                scanner.close();
                break;
            }
        }
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public static void addToList(String item) {
        list[itemNo] = item;
        itemNo++;
        System.out.println("added: " + item);
        System.out.println("____________________________________________________________");
    }

    public static void listItems() {
        for(int i = 1; i < itemNo; i++) {
            System.out.println(i + ". " + list[i]);
        }
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        greetUser();
        //echo();
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        while(!line.equals("bye")) {
            if (line.equals("list")) {
                listItems();
            } else {
                addToList(line);
            }
            line = scanner.nextLine();
        }
        scanner.close();
        exit();
    }
}
