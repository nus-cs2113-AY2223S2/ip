
import java.util.Scanner;

class Task {
    String taskName;
    boolean status;

    Task() {
        this.status = false;
    }

    public String getTaskStatus() {
        String status = " ";

        if (this.status == true) {
            status = "X";
        }
        return status;
    }

    public void mark() {
        this.status = true;
        System.out.println("目標達成！- [" + this.getTaskStatus() + "] " + this.taskName);

    }

    public void unMark() {
        this.status = false;
        System.out.println("えええ？噓つき。- [" + this.getTaskStatus() + "] " + this.taskName);
    }
}

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
                        System.out.println(i + 1 + ". [" + taskList[i].getTaskStatus() + "] " + taskList[i].taskName);
                    }
                }
            } else if (firstWord.equals("mark")) {
                taskList[Integer.parseInt(words[1]) - 1].mark(); // shld break if i type mark 0
            } else if (firstWord.equals("unmark")) {
                taskList[Integer.parseInt(words[1]) - 1].unMark(); // shld break if i type mark 0

            } else if (index < 100) {
                Task newTask = new Task();
                newTask.taskName = input;
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
