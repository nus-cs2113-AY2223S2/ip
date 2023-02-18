import DukeFunctions.Event;
import DukeFunctions.Todo;
import DukeFunctions.Deadline;
import Exceptions.MissingInputException;


import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Todo> TodoList = new ArrayList<>();

    public static void parseCommand(String input, String[] words, String command) {
        String inputContents = input.substring(command.length()).trim(); //

        try {
            switch (command) {
                case "list":

                    if (TodoList.size() == 0) {
                        System.out.println("何もいない。。。頭のように。。。(list is empty)");
                    } else {
                        for (int i = 0; i < TodoList.size(); i++) {
                            System.out.println(i + 1 + ". [" + TodoList.get(i).getType() + "]" + "[" + TodoList.get(i).getIsDone() + "] " + TodoList.get(i).toString());
                        }
                    }
                    break;
                case "mark":
                    int markTarget = Integer.parseInt(words[1]) - 1;
                    if (markTarget >= 0 && markTarget < TodoList.size()) {
                        TodoList.get(markTarget).mark();
                    } else {
                        System.out.println("バカにさせないで。その目標は実在しません。(that task does not exist)");
                    }
                    break;
                case "unmark":
                    int target = Integer.parseInt(words[1]) - 1;
                    if (target >= 0 && target < TodoList.size()) {
                        TodoList.get(target).unMark();
                    } else {
                        System.out.println("バカにさせないで。その目標は実在しません。(that task does not exist)");
                    }
                    break;
                case "todo":

                    // if (index < 100) {
                    Todo newTodo = new Todo(inputContents);
                    TodoList.add(newTodo);
                    System.out.println("覚えましたよ～ (todo recorded) " + newTodo.toString());
                    // index++;
                    // }
                    break;
                case "deadline":
                    // if (index < 100) {
                    Deadline newDeadline = new Deadline(inputContents);
                    TodoList.add(newDeadline);
                    System.out.println("覚えましたよ～ (deadline recorded) " + newDeadline.toString());
                    // index++;
                    //  }
                    break;
                case "event":
                    // if (index < 100) {
                    Event newEvent = new Event(inputContents);
                    TodoList.add(newEvent);
                    System.out.println("覚えましたよ～ (event recorded) " + newEvent.toString());
                    //index++;
                    // }
                    break;
                case "delete":
                    int deleteTarget = Integer.parseInt(words[1]) - 1;
                    if (deleteTarget >= 0 && deleteTarget < TodoList.size()) {
                        System.out.println("deleted this task: ");
                        System.out.println(TodoList.get(deleteTarget).toString());
                        TodoList.remove(deleteTarget);

                    } else {
                        System.out.println("バカにさせないで。その目標は実在しません。");
                    }
                    break;
                case "clearlist":
                    TodoList.clear();
                    System.out.println("EVERYTHING IS GONE");
                    break;

                default:
                    System.out.println("i dont recognize that command");

//                        Todo newTodo = new Todo(input);

                    break;


            }
        } catch (NullPointerException e) {
            System.out.println("NullPointerException しちゃった！");
        } catch (MissingInputException e) {
            System.out.println("ええ、何か忘れそう?");
        }

        try {
            //update file
            StringBuilder output = new StringBuilder();
            for (int i = 0; i < TodoList.size(); i++) {
                output.append(i + 1).append(". [")
                        .append(TodoList.get(i).getType()).append("][")
                        .append(TodoList.get(i).getIsDone()).append("] ")
                        .append(TodoList.get(i).toString()).append("\n");
            }
            String fileText = output.toString();


            FileWriter fw = new FileWriter("memory.txt");
            fw.write(fileText);
            fw.close();
        } catch (IOException e) {
            System.out.println("bruh");
        }


    }

    public static void main(String[] args) {

        System.out.println("おかえり～　ご飯にする？お風呂にする？それとも。。。　わ・た・し？ (start)");
        try {
            File f = new File("memory.txt");


            if (!f.exists()) {
                System.out.println("making a new memory file");
                FileWriter fw = new FileWriter("memory.txt");
                fw.close();
            } else {
                Scanner scanner = new Scanner(f);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    // get type
                    String type = line.substring(line.indexOf("[") + 1, line.indexOf("]"));
                    //get status
                    String status = line.substring(line.indexOf("[", line.indexOf("[") + 1) + 1, line.indexOf("]", line.indexOf("]") + 1));

                    String description;
                    if (type.equals("T")) {
                        description = line.substring(line.indexOf("]") + 5);
                        Todo newTodo = new Todo(description);
                        TodoList.add(newTodo);

                    } else {
                        int start = line.indexOf("]") + 5;
                        int end = line.indexOf("(") - 1;
                        description = line.substring(start, end);
                    }
                    String by = null;
                    String from = null;
                    String to = null;
                    if (type.equals("D")) {
                        int start = line.indexOf("by:") + 3;
                        int end = line.indexOf(")", line.indexOf("by:"));
                        by = line.substring(start, end);
                        String deadlineInput = description + "/by" + by;
                        Deadline newDeadline = new Deadline(deadlineInput);
                        TodoList.add(newDeadline);


                    } else if (type.equals("E")) {
                        int fromIndex = line.indexOf("from:");
                        int toIndex = line.indexOf("to:");
                        from = line.substring(fromIndex + 5, line.indexOf("to:", fromIndex));
                        to = line.substring(toIndex + 3, line.indexOf(")", toIndex));

                        String eventInput = description + "/from" + from + "/to" + to;
                        Event newEvent = new Event(eventInput);
                        TodoList.add(newEvent);


                    }

                }

                scanner.close();

            }


        } catch (IOException e) {
            System.out.println("bruh");
        } catch (MissingInputException e) {
            System.out.println("ええ、何か忘れそう?");
        }


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
        System.out.println("じゃねえ～ (bye)");
    }
}
