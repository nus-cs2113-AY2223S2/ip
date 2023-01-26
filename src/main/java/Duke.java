import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        DukeMessage.greet();

        String line;
        Scanner in = new Scanner(System.in);
        DukeMessage.lineBreak();
        line = in.nextLine();

            while (!line.equals("bye")){

                System.out.println(line);
                DukeMessage.lineBreak();
                line = in.nextLine();

            }

        DukeMessage.goodbye();

    }
}