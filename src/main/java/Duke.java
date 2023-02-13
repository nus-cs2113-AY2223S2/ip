import DukeFunctions.Event;
import DukeFunctions.Todo;
import DukeFunctions.Deadline;
import Exceptions.MissingInputException;

import java.util.Scanner;

public class Duke {

    static int index = 0;
    static Todo[] TodoList = new Todo[100];

    public static void parseCommand(String input, String[] words, String command) {
        String inputContents = input.substring(command.length()).trim(); //

        try {
            switch (command) {
                case "list":
                    if (index == 0) {
                        System.out.println("何もいない。。。頭のように。。。");
                    } else {
                        for (int i = 0; i < index; i++) {
                            System.out.println(i + 1 + ". [" + TodoList[i].getType() + "]" + "[" + TodoList[i].getIsDone() + "] " + TodoList[i].toString());
                        }
                    }
                    break;
                case "mark":
                    int markTarget = Integer.parseInt(words[1]) - 1;
                    if (markTarget >= 0 && markTarget < index) {
                        TodoList[markTarget].mark();
                    } else {
                        System.out.println("バカにさせないで。その目標は実在しません。");
                    }
                    break;
                case "unmark":
                    int target = Integer.parseInt(words[1]) - 1;
                    if (target >= 0 && target < index) {
                        TodoList[target].unMark();
                    } else {
                        System.out.println("バカにさせないで。その目標は実在しません。");
                    }
                    break;
                case "todo":
                    if (index < 100) {
                        Todo newTodo = new Todo(inputContents);
                        TodoList[index] = newTodo;
                        System.out.println("覚えましたよ～ " + newTodo.toString());
                        index++;
                    }
                    break;
                case "deadline":
                    if (index < 100) {
                        Deadline newDeadline = new Deadline(inputContents);
                        TodoList[index] = newDeadline;
                        System.out.println("覚えましたよ～ " + newDeadline.toString());
                        index++;
                    }
                    break;
                case "event":
                    if (index < 100) {
                        Event newEvent = new Event(inputContents);
                        TodoList[index] = newEvent;
                        System.out.println("覚えましたよ～ " + newEvent.toString());
                        index++;
                    }
                    break;

//                default:
//                    if (index < 100) {
//                        Todo newTodo = new Todo(input);
//                        TodoList[index] = newTodo;
//
//                        System.out.println("覚えましたよ～ " + input);
//                        index++;
//                    } else {
//                        System.out.println("もういっぱい～！");
//                    }
//                    break;
            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException しちゃった！");
        } catch (MissingInputException e) {
            System.out.println("ええ、何か忘れそう?");
        }


    }

    public static void main(String[] args) {

        System.out.println("おかえり～　ご飯にする？お風呂にする？それとも。。。　わ・た・し？");


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] words = input.split(" ");
            String command = words[0];

            if (input.equals("bye")) {
                break;
            }

            parseCommand(input, words, command);

        }
        System.out.println("じゃねえ～");
    }
}
