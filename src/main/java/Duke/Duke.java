package Duke;

import Duke.Tasks.Task;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//public class Duke {
//    public static final int LENGTH_DEADLINE = 8;
//    public static final int LENGTH_TODO = 4;
//    public static final int LENGTH_EVENT = 5;
//    public static String relativePath = ".\\lists.txt";
//
//    public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("--------------------------------");
//        System.out.println("Hello! I'm Duke.Duke");
//        System.out.println("What can I do for you?");
//        System.out.println("--------------------------------");
//        Task[] lists = new Task[100];
//        int index=0;
////        try{
////            initializeList(lists,relativePath);
////            index = getIndexOfList(lists,relativePath);
////        } catch (FileNotFoundException e) {
////            System.out.println("File not found");
////        }
//
//        while (true) {
//            String line = in.nextLine();
//            if (line.equals("bye")) {
//                System.out.println("--------------------------------");
//                System.out.println("Bye. Hope to see you again soon!");
//                System.out.println("--------------------------------");
//                break;
//            } else if (line.substring(0, 4).equals("mark")) {
//                try {
//                    String number = line.substring(5, 6);
//                    int markIndex = Integer.parseInt(number);
//                    lists[markIndex - 1].isDone = true;
//                    writeToFile(relativePath, lists, index);
//                } catch (NumberFormatException e) {
//                    System.out.println("Oops! Mark should be followed by a number. " +
//                            "(A valid index number should be separated by a space after the mark)");
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Oops! Mark index out of bound! " +
//                            "(A valid index number should be separated by a space after the mark)");
//                } catch (NullPointerException e) {
//                    System.out.println("Oops! Mark index out of bound! " +
//                            "(A valid index number should be separated by a space after the mark)");
//                } catch (IOException e) {
//                    System.out.println("Something went wrong: " + e.getMessage());
//                }
//            } else if (line.contains("unmark")) {
//                try {
//                    String number = line.substring(7, 8);
//                    int unMarkIndex = Integer.parseInt(number);
//                    lists[unMarkIndex - 1].isDone = false;
//                    writeToFile(relativePath, lists, index);
//                } catch (NumberFormatException e) {
//                    System.out.println("Oops! Unmark should be followed by a number. " +
//                            "(A valid index number should be separated by a space after the unmark)");
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Oops! Unmark index out of bound! " +
//                            "(A valid index number should be separated by a space after the unmark)");
//                } catch (NullPointerException e) {
//                    System.out.println("Oops! Unmark index out of bound! " +
//                            "(A valid index number should be separated by a space after the unmark)");
//                } catch (IOException e) {
//                    System.out.println("Something went wrong: " + e.getMessage());
//                }
//            } else if (line.equals("list")) {
//                System.out.println("list:");
//                for (int i = 0; i < index; i++) {
//                    System.out.println("\t" + (i + 1) + "." + lists[i].toString());
//                }
//            } else if (line.startsWith("delete")) {
//                try {
//                    String number = line.substring(7, 8);
//                    int deleteIndex = Integer.parseInt(number);
//                    if (deleteIndex == index) {
//                        printDeleteMessage(lists, deleteIndex - 1, index);
//                        index = index - 1;
//                    } else if (deleteIndex >= 1 && deleteIndex <= index - 1) {
//                        printDeleteMessage(lists, deleteIndex - 1, index);
//                        for (int i = deleteIndex - 1; i < index - 1; i++) {
//                            lists[i] = lists[i + 1];
//                        }
//                        index = index - 1;
//                    } else {
//                        System.out.println("Oops! Delete should be followed by a valid number. " +
//                                "(A valid index number should be separated by a space after the delete)");
//                    }
//                    writeToFile(relativePath, lists, index);
//                } catch (NumberFormatException e) {
//                    System.out.println("Oops! Unmark should be followed by a number. " +
//                            "(A valid index number should be separated by a space after the delete)");
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("Oops! Unmark index out of bound! " +
//                            "(A valid index number should be separated by a space after the delete)");
//                } catch (NullPointerException e) {
//                    System.out.println("Oops! Unmark index out of bound! " +
//                            "(A valid index number should be separated by a space after the delete)");
//                } catch (IOException e) {
//                    System.out.println("Something went wrong: " + e.getMessage());
//                }
//            } else {
//                if (line.substring(0, LENGTH_TODO).equals("todo")) {
//                    lists[index] = new Todo(line.substring(LENGTH_TODO + 1));
//                    printMessage(lists, index);
//                    index++;
//                    try {
//                        writeToFile(relativePath, lists, index);
//                    } catch (IOException e) {
//                        System.out.println("Something went wrong: " + e.getMessage());
//                    }
//
//                } else if (line.substring(0, LENGTH_DEADLINE).equals("deadline")) {
//                    int breakingPoint = line.indexOf("/by");
//                    String description = line.substring(LENGTH_DEADLINE + 1, breakingPoint);
//                    String by = line.substring(breakingPoint + 4);
//                    lists[index] = new Deadline(description, by);
//                    printMessage(lists, index);
//                    index++;
//                    try {
//                        writeToFile(relativePath, lists, index);
//                    } catch (IOException e) {
//                        System.out.println("Something went wrong: " + e.getMessage());
//                    }
//                } else if (line.substring(0, LENGTH_EVENT).equals("event")) {
//                    int breakingPoint_1 = line.indexOf("/from");
//                    int breakingPoint_2 = line.indexOf("/to");
//                    String description = line.substring(LENGTH_EVENT + 1, breakingPoint_1);
//                    String start = line.substring(breakingPoint_1 + 6, breakingPoint_2);
//                    String end = line.substring(breakingPoint_2 + 4);
//                    lists[index] = new Event(description, start, end);
//                    printMessage(lists, index);
//                    index++;
//                    try {
//                        writeToFile(relativePath, lists, index);
//                    } catch (IOException e) {
//                        System.out.println("Something went wrong: " + e.getMessage());
//                    }
//                } else {
//                    System.out.println("Sorry I can't get your message! Please try again.");
//                }
//
//            }
//
//        }
//    }
//
//    public static void printMessage(Task[] Tasks, int taskIndex) {
//        System.out.println("--------------------------------");
//        System.out.println("Got it. I've added this task:");
//        System.out.println(Tasks[taskIndex].toString());
//        System.out.println("Now you have " + (taskIndex + 1) + " task(s) in the list");
//        System.out.println("--------------------------------");
//    }
//
//    public static void printDeleteMessage(Task[] Tasks, int deleteIndex, int totalIndex) {
//        System.out.println("--------------------------------");
//        System.out.println("Noted. I've removed this task:");
//        System.out.println(Tasks[deleteIndex].toString());
//        System.out.println("Now you have " + (totalIndex - 1) + " task(s) in the list");
//        System.out.println("--------------------------------");
//    }
//
//    public static void writeToFile(String filePath, Task[] Tasks, int index) throws IOException {
//        File f = new File(filePath);
//        if (!f.exists()) {
//            f.createNewFile();
//            System.out.println("File created: " + f.getName());
//        }
//        FileWriter fw = new FileWriter(filePath);
//        for (int i = 0; i < index; i++) {
//            fw.write((i + 1) + "." + Tasks[i].toString() + '\n');
//        }
//        fw.close();
//
//
//    }
//
//    public static void initializeList(Task[] lists, String filePath) throws FileNotFoundException {
//        File f=new File(filePath);
//        int index=0;
//        if(f.exists()){
//            Scanner s=new Scanner(f);
//            while(true){
//                String line=s.toString();
//                if (line.substring(3,4).equals("T")) {
//                    lists[index] = new Todo(line.substring(9));
//                    printMessage(lists, index);
//                    index++;
//                    try {
//                        writeToFile(relativePath, lists, index);
//                    } catch (IOException e) {
//                        System.out.println("Something went wrong: " + e.getMessage());
//                    }
//
//                } else if (line.substring(3,4).equals("D")) {
//                    int breakingPoint = line.indexOf("/by");
//                    String description = line.substring(9, breakingPoint);
//                    String by = line.substring(breakingPoint + 4);
//                    lists[index] = new Deadline(description, by);
//                    printMessage(lists, index);
//                    index++;
//                    try {
//                        writeToFile(relativePath, lists, index);
//                    } catch (IOException e) {
//                        System.out.println("Something went wrong: " + e.getMessage());
//                    }
//                } else if (line.substring(3,4).equals("E")) {
//                    int breakingPoint_1 = line.indexOf("/from");
//                    int breakingPoint_2 = line.indexOf("/to");
//                    String description = line.substring(9, breakingPoint_1);
//                    String start = line.substring(breakingPoint_1 + 6, breakingPoint_2);
//                    String end = line.substring(breakingPoint_2 + 4);
//                    lists[index] = new Event(description, start, end);
//                    printMessage(lists, index);
//                    index++;
//                    try {
//                        writeToFile(relativePath, lists, index);
//                    } catch (IOException e) {
//                        System.out.println("Something went wrong: " + e.getMessage());
//                    }
//                }
//                s.nextLine();
//                if(!s.hasNext()){
//                    break;
//                }
//            }
//        }
//    }
////    public static int getIndexOfList(Task[] lists,String filePath) throws FileNotFoundException{
////        File f=new File(filePath);
////        int index=0;
////        if(f.exists()){
////            Scanner s=new Scanner(f);
////            while(s.hasNext()){
////                if(s.toString().equals(" ")){
////                    break;
////                }
////                index=index+1;
////                s.nextLine();
////            }
////        }
////        System.out.println(index);
////        return index;
////   }
//}
public class Duke{

    private Storage storage;
    public static Task[] lists = new Task[100];
    public static int index=0;

    public static void readLines(String line) throws IOException {
        while(!line.equals("bye")){
            String[] input=line.split(" ",2);
            String command=input[0];
            switch (command){
            case "mark":
                TaskList.markTask(lists,input);
                Storage.writeToFile(lists,index);
                break;
            case "unmark":
                TaskList.unmarkTask(lists,input);
                Storage.writeToFile(lists,index);
                break;
            case "todo":
                TaskList.addTodo(lists,input,index);
                index++;
                Storage.writeToFile(lists,index);
                break;
            case "event":
                TaskList.addEvent(lists,input,index);
                index++;
                Storage.writeToFile(lists,index);
                break;
            case "deadline":
                TaskList.addDeadline(lists,input,index);
                index++;
                Storage.writeToFile(lists,index);
                break;
            case "delete":
                TaskList.deleteTask(lists,input,index);
                index--;
                Storage.writeToFile(lists,index);
                break;
            case "list":
                Ui.printList(lists,index);
                break;
            case "help":
                Ui.printHelp();
                break;
            default:
                Ui.printError();
                break;
            }
            line=Ui.nextLine();
        }
    }

    public void run() throws IOException {
        Ui.welcomeMessage();
        storage=new Storage();
        index=Storage.initializeList(lists);
        String line=Ui.initializeLine();
        readLines(line);
        Ui.goodbyeMessage();
    }
    public static void main(String[] args) throws IOException {
        try {
            new Duke().run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}