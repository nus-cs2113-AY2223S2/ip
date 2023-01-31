import java.util.Scanner;

public class Addlist {

    public void AddList(){
        Task[] ListOfThings = new Task[100];
        Scanner newScanner = new Scanner(System.in);
        OutputUI outPutUI = new OutputUI();
        Marker marker = new Marker();
        int index = 0;
        while(true) {
            System.out.println("______________________________________________");
            String input = newScanner.next();
            System.out.println("______________________________________________");
            switch (input) {
                case "list":
                    outPutUI.printList(ListOfThings, index);
                    break;
                case "bye":
                    outPutUI.byeByeMessage();
                    System.exit((0));
                case "todo":
                    String restOfString = newScanner.nextLine();
                    Todo newToDo = new Todo(restOfString);
                    ListOfThings[index] = newToDo;
                    index += 1;
                    outPutUI.addToListTodoMessage(newToDo, index);

                case "deadline":
                    String description = "";
                    while (true) {
                        String currWord = newScanner.next();
                        if (currWord.equals("/by")) {
                            break;
                        }
                        else {
                            description = description.concat(currWord);
                            description = description.concat(" ");
                        }
                    }
                    String dueDate = newScanner.nextLine();
                    Deadline newDeadline = new Deadline(description, dueDate);
                    ListOfThings[index] = newDeadline;
                    index += 1;
                    outPutUI.addToListTodoMessage(newDeadline, index);
                    break;
                case "event":
                    String descriptionEvent = "";
                    String startDate = "";

                    while (true) {
                        String currWord = newScanner.next();
                        if (currWord.equals("/from")) {
                            break;
                        } else {
                            descriptionEvent = descriptionEvent.concat(currWord);
                            descriptionEvent = descriptionEvent.concat(" ");
                        }
                    }
                    while (true) {
                        String currWord = newScanner.next();
                        if (currWord.equals("/to")) {
                            break;
                        } else {
                            startDate = startDate.concat(currWord);
                            startDate = startDate.concat(" ");
                        }
                    }
                    String endDate = newScanner.nextLine();
                    Event newEvent = new Event(descriptionEvent, startDate, endDate);
                    ListOfThings[index] = newEvent;
                    index += 1;
                    outPutUI.addToListTodoMessage(newEvent, index);
                    break;

                case "mark":
                    int num;
                    num = newScanner.nextInt();
                    marker.markTask(ListOfThings[num - 1]);
                    outPutUI.markTaskMessage(ListOfThings[num - 1]);
                    break;

                case "unmark":
                    int numUnmarkIndex = newScanner.nextInt();
                    marker.unmarkTask(ListOfThings[numUnmarkIndex - 1]);
                    outPutUI.unmarkTaskMessage(ListOfThings[numUnmarkIndex - 1]);
                    break;

                default:
                    System.out.println(input);
            }
        }
    }
}

