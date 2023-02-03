
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Task[] taskList = new Task[100];
        System.out.println("おかえり～　ご飯にする？お風呂にする？それとも。。。　わ・た・し？");

        int index = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            String firstWord = words[0];
            if (input.equals("bye")) {
                break;
            }

            if (input.equals("list")) {
                if (index == 0) {
                    System.out.println("何もいない。。。頭のように。。。");
                } else {
                    for (int i = 0; i < index; i++) {
                        System.out.println(i + 1 + ". [" + taskList[i].getTaskStatus() + "] " + taskList[i].description);
                    }
                }
            } else if (firstWord.equals("mark")) {
                taskList[Integer.parseInt(words[1]) - 1].mark(); // shld break if i type mark 0
            } else if (firstWord.equals("unmark")) {
                taskList[Integer.parseInt(words[1]) - 1].unMark(); // shld break if i type mark 0
            } else if (firstWord.equals("deadline") && index < 100) {
                String by = scanner.nextLine();
                Deadline newDeadline = new Deadline(input, by);
                taskList[index] = newDeadline;

                System.out.println("覚えましたよ～ " + input);
                index++;


            } else if (index < 100) {
                Task newTask = new Task(input);
                taskList[index] = newTask;

                System.out.println("覚えましたよ～ " + input);
                index++;
            } else {
                System.out.println("もういっぱい～！\" ");
            }

        }
        System.out.println("じゃねえ～");
    }
}
