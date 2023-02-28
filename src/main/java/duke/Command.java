package duke;

import duke.exception.EmptyListError;

public class Command {

    protected String commandType;

    public Command(String commandType) {
        this.commandType = commandType;
    }

    public void execute(TaskList tasks){
        if (commandType.equals("list")){
            try {
                tasks.printList();
            } catch (EmptyListError err) {
                UI.printMessage("There is nothing inside the list");
            }
        } else if (commandType.equals("bye")) {
            UI.showByeMessage();
        }
    }
}
//
//    public void execute(){
//        String[] iW = tbr.split(" ");
//        switch (commandType) {
//            case "todo":
//                tbr = tbr.replaceFirst("todo", "").trim();
//                UI.printTodo(tbr);
//                Todo todo = new Todo(tbr, "T");
//                tasks.addToList(todo);
//                UI.printTaskList(tasks.sizeOfList());
//                updateFile();
//                break;
//            case "deadline":
//                tbr = tbr.replaceFirst("deadline", "").trim();
//                int indexOfSlash = tbr.indexOf("/");
//                String taskName = tbr.substring(0, indexOfSlash - 1);
//                String by = tbr.substring(indexOfSlash + 4);
//                UI.printDeadline(taskName, by);
//                Deadline deadline = new Deadline(taskName, "D", by);
//                tasks.addToList(deadline);
//                UI.printTaskList(tasks.sizeOfList());
//                updateFile();
//                break;
//            case "event":
//                tbr = tbr.replaceFirst("event", "").trim();
//                indexOfSlash = tbr.indexOf("/");
//                int lastIndexOfSlash = tbr.lastIndexOf("/");
//                taskName = tbr.substring(0, indexOfSlash - 1);
//                String start = tbr.substring(indexOfSlash + 6, lastIndexOfSlash - 1);
//                String end = tbr.substring(lastIndexOfSlash + 4);
//                UI.printEvent(taskName, start, end);
//                Event event = new Event(taskName, "E", start, end);
//                tasks.addToList(event);
//                UI.printTaskList(tasks.sizeOfList());
//                updateFile();
//                break;
//            case "list":
//                try {
//                    tasks.printList();
////                printList();
//                } catch (EmptyListError err) {
//                    UI.printMessage("There is nothing inside the list");
//                }
//                break;
//            case "mark":
//                int taskNum = Integer.parseInt(iW[1]) - 1;
//                UI.printMessage("Nice! I've marked this task as done:");
//                tasks.mark(taskNum);
//                System.out.println("  [" + tasks.getStatus(taskNum)+ "] " + tasks.getDescription(taskNum));
//                updateFile();
//                break;
//            case "unmark":
//                taskNum = Integer.parseInt(iW[1]) - 1;
//                UI.printMessage("OK, I've marked this task as not done yet:");
//                tasks.unmark(taskNum);
//                System.out.println("  [" + tasks.getStatus(taskNum)+ "] " + tasks.getDescription(taskNum));
//                updateFile();
//                break;
//            case "delete":
//                taskNum = Integer.parseInt(iW[1]) - 1;
//                UI.printMessage("Noted. I've removed this task:");
//                System.out.println("  " + tasks.getString(taskNum));
//                tasks.removeTask(taskNum);
//                UI.printTaskList(tasks.sizeOfList());
//                updateFile();
//                break;
//            case "bye":
//                UI.showByeMessage();
//                updateFile();
//                break;
//            default: //never reached
//                UI.printMessage("Never reached");
//        }

//    }

//    private static void updateFile() {
//        ArrayList<Task> tasksList = tasks.returnTasks();
//        try {
//            Storage.writeToFile("");
//            for (Task task : tasksList) {
//                Storage.appendToFile(task.textToSave() + System.lineSeparator());
//            }
//        } catch (IOException err) {
//            System.out.println("Something went wrong: " + err.getMessage());
//        }
//    }

//}
