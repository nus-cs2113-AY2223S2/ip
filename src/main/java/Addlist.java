import java.util.InputMismatchException;
import java.util.Scanner;

public class Addlist {

    public void AddList(){
        Task[] ListOfThings = new Task[100];
        Scanner newScanner = new Scanner(System.in);
        OutputUI outPutUI = new OutputUI();
        Marker marker = new Marker();
        int index = 0;
        while(true) {
            try {
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
                        try {
                            String restOfString = newScanner.nextLine();
                            if (restOfString.equals("")) {
                                throw new DukeException(outPutUI.nullInputErrorMessage("Description cannot be Empty"));
                            }
                            else {
                                Todo newToDo = new Todo(restOfString);
                                ListOfThings[index] = newToDo;
                                index += 1;
                                outPutUI.addToListTodoMessage(newToDo, index);
                                break;
                            }
                        }
                        catch (DukeException e) {
                            System.out.println(e.getMessage());
                            break;
                        }
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
                        if (description.equals(""))
                        {
                            System.out.println(outPutUI.nullInputErrorMessage("Description cannot be empty :<, please write a description after the word deadline"));
                            break;
                        } else if (dueDate.equals("") ) {
                            System.out.println(outPutUI.nullInputErrorMessage("DueDate cannot be empty :<, please write a the description of a deadline"));
                            break;
                        }
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
                            if (endDate.equals("")) {
                                throw new DukeException(outPutUI.nullInputErrorMessage("End Date cannot be empty :<"));
                            } else {
                                Event newEvent = new Event(descriptionEvent, startDate, endDate);
                                ListOfThings[index] = newEvent;
                                index += 1;
                                outPutUI.addToListTodoMessage(newEvent, index);
                            }

                        break;
                    case "mark":
                            int num;
                            try {
                                num = newScanner.nextInt();
                                if (index == 0) {
                                    System.out.println("There are no items in the list! Please input an item for Pikapi :3");
                                    break;
                                } else if (num > index) {
                                    System.out.println("You do not have that many items yet, Pikapi requests a number less than or equal to " + index + " after the word mark");
                                    break;
                                } else if (num <= 0) {
                                    System.out.println("PIKAPII you dont have negative number of tasks silly, please input a number between 1 and " + index + " after the word mark");
                                    break;
                                } else {
                                    marker.markTask(ListOfThings[num - 1]);
                                    outPutUI.markTaskMessage(ListOfThings[num - 1]);
                                    break;
                                }
                            } catch(InputMismatchException e) {
                                System.out.println("PIKAPII thats not a number! Please input a number after you type mark!");
                                //this skip prevents the final catch error (where the cases fall through until command is unrecognized) from being played if there is an incorrect message
                                newScanner.skip("[a-zA-Z1-9]*");
                            }
                            break;
                    case "unmark":
                        try {
                            while (true) {
                                int numUnmarkIndex = newScanner.nextInt();
                                if (numUnmarkIndex > index) {
                                    System.out.println("You do not have that many items yet, Pikapi requests a correct number less than or equal to " + index + " after the word unmark");
                                    break;
                                } else if (numUnmarkIndex < 0) {
                                    int lowerBound;
                                    if (index == 0) {
                                        lowerBound = 0;
                                    }
                                    else {
                                        lowerBound = 1;
                                    }
                                    System.out.println("PIKAPII you dont have negative number of tasks silly, please input a number between " + lowerBound  + " and " + index + " after the word unmark");
                                    break;
                                } else {
                                    marker.unmarkTask(ListOfThings[numUnmarkIndex - 1]);
                                    outPutUI.unmarkTaskMessage(ListOfThings[numUnmarkIndex - 1]);
                                    break;
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("PIKAPII thats not a number! Please input a number after you type unmark!");
                            newScanner.skip("[a-zA-Z1-9]*");
                        }
                        break;
                    default:
                            System.out.println(input);
                            throw new DukeException("Pikapi is unable to find that command, please type in a correct command");
                }
            }catch(DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
