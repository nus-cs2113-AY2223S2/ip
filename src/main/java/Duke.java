
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] library = new String[100];
        int index = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("おかえり～　ご飯にする？お風呂にする？それとも。。。　わ・た・し？");
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                if (index == 0) {
                    System.out.println("何もいない。。。頭のように。。。");
                } else {
                    for (int i = 0; i < index; i++) {
                        System.out.println(i + 1 + ". " + library[i]);
                    }
                }
            } else if (index < 100) {
                library[index] = input;
                System.out.println("覚えましたよ～ " + input);
                index++;
            } else {
                System.out.println("もういっぱい～！\" ");
            }

        }
        System.out.println("じゃねえ～");
    }
}
