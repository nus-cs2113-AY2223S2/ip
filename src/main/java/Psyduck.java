import java.util.Scanner;

public class Psyduck {
    public static void linePrint(){
        for (long i = 0; i < 100; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        String command;
        Scanner in = new Scanner(System.in);
        linePrint();
        System.out.println("Psyduck! Psy Psy Psyduck! (Hello! How can I help you?)");
        linePrint();
        do {
            command = in.nextLine();
            linePrint();
            System.out.println(command);
            linePrint();
        } while (command.equals("Bye") == false);
        System.out.println("Psyduck! (Buh Bye!)");
        linePrint();
    }
}
