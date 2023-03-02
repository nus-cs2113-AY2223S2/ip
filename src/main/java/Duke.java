//package Duke.java;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) throws DukeException, IOException {
        String logo = "Hello! I'm Duke\n" + "What can I do for you?\n";
        System.out.println(logo);
        Scanner in = new Scanner(System.in);
        String response;
        String filePath = "C:\\Users\\user\\OneDrive - National University of Singapore\\" +
                "Desktop\\NUS AY 2021-2025\\Semester 4\\CS2113\\duke.txt";
        List<Task> list = new ArrayList<>();
//        loadData(list);
        while (true) {
            saveData(list, filePath);
            response = in.nextLine();
            if (response.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            try {
                if (response.equals("list")) {
                    int size = list.size();
                    if (size == 0) {
                        System.out.println("Bro.... You have nothing on your list. Add something to your list to be productive");
                        continue;
                    }
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < size; i++) {
                        System.out.println((i + 1) + "." + list.get(i).toString());
                    }
                    continue;
                } else if (response.startsWith("mark")) {
                    int tasknumber = Integer.parseInt(response.split(" ")[1]);
                    if (tasknumber > 0 && tasknumber <= list.size()) {
                        list.get(tasknumber - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(list.get(tasknumber - 1).toString());
                    } else {
                        System.out.println("I cannot find which task you have selected. Please choose another one");
                        continue;
                    }
                    continue;
                } else if (response.startsWith("unmark")) {
                    int tasknumber = Integer.parseInt(response.split(" ")[1]);
                    if (tasknumber > 0) {
                        list.get(tasknumber - 1).markAsNotDone();
                        System.out.println("Never-mind!! Unmarking");
                        System.out.println(list.get(tasknumber - 1).toString());
                    } else {
                        System.out.println("I cannot find which task you have selected. Please choose another one");
                        continue;
                    }
                    continue;
                } else if (response.startsWith("deadline")) {
                    try {
                        String splitresponse = response.split(" ", 2)[1];
                        String descresponse = splitresponse.split("/by")[0];
                        String dateresponse = splitresponse.split("/by")[1];
                        list.add(new Deadline(descresponse, dateresponse));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }
                } else if (response.startsWith("event")) {
                    try {
                        String splitresponse = response.split(" ", 2)[1];
                        String descresponse = splitresponse.split("/from")[0];
                        String timeresponse = splitresponse.split("/from")[1];
                        String fromresponse = timeresponse.split("/to")[0];
                        String toresponse = timeresponse.split("/to")[1];
                        list.add(new Event(descresponse, fromresponse, toresponse));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }
                } else if (response.startsWith("todo")) {
                    try {
                        String description = response.split(" ", 2)[1];
                        list.add(new Todo(description));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }
                } else if (response.startsWith("delete")) {
                    try {
                        int tasknumber = Integer.parseInt(response.split(" ")[1]);
                        if (tasknumber > 0 && tasknumber <= list.size()) {
                            System.out.println("Deleting.....Done!!");
                            System.out.println(list.get(tasknumber - 1).toString());
                            list.remove(tasknumber - 1).markAsDone();
                        } else {
                            System.out.println("I cannot find which task you have selected. Please choose another one");
                            continue;
                        }
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }


                } else if (response.startsWith("find")) {
                    try {
                        String splitresponse = response.split(" ", 2)[1];
                        String descresponse = splitresponse.split("/from")[0];
                        int size = list.size();
                        int amountfound = 0;
                        System.out.println("Here are the matching tasks in your list:");
                        for (int index = 0; index < size; index++) {
                            if (list.get(index).toString().contains(descresponse)) {
                                amountfound = amountfound + 1;
                                System.out.println((amountfound) + "." + list.get(index).toString());
                            }
                        }
                        continue;
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException();
                    }


                } else {
                    throw new DukeException();
                }
                int size = list.size();
                System.out.println("Got it. I've added this task:");
                System.out.println(list.get(size - 1).toString());
                System.out.println("Now you have " + size + " tasks in the list");
            } catch (DukeException e) {
                System.out.println("Illegal Command");
            }
        }

    }

    private static void saveData(List<Task> list, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : list) {
            String taskInformation = t.toString();
            String taskType = taskInformation.substring(1, 2);
            String taskStatus = (taskInformation.substring(4, 5).equals("V") ? "Done" : "Not Done");
            String taskDescription = taskInformation.substring(7);
            if (taskDescription.contains("(by:")) {
                String taskDate = taskDescription.substring(taskDescription.indexOf("(by: ") + 5,
                        taskDescription.indexOf(")", taskDescription.indexOf("(by: ")));
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("(by:") - 1);
                fw.write(taskType + "|" + taskStatus + "|" + taskDescription + "| by:" + taskDate);
            } else if (taskDescription.contains("(at:")) {
                String taskDate = taskDescription.substring(taskDescription.indexOf("(at: ") + 5,
                        taskDescription.indexOf(")", taskDescription.indexOf("(at: ")));
                taskDescription = taskDescription.substring(0, taskDescription.indexOf("(at:") - 1);
                fw.write(taskType + "|" + taskStatus + "|" + taskDescription + "|" + taskDate);
            } else {
                fw.write(taskType + "|" + taskStatus + "|" + taskDescription);
            }
            fw.write("\n");
        }
        fw.close();
    }
}

