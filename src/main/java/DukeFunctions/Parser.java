package DukeFunctions;

import Exceptions.DukeError;
import Exceptions.MissingInputException;

/**
 * Takes in a string input and execute the command within it.
 */
public class Parser { //takes in a string input and executes the command within it


    public Parser() {

    }

    public boolean isExit = false;

    /**
     * Parses the given input command and executes the appropriate action.
     *
     * @param input The input command to parse.
     * @param tasks The list of tasks to modify based on the command.
     * @param ui    The user interface to display output.
     * @throws DukeError If there is an error parsing or executing the command.
     */
    public void parseCommand(String input, TaskList tasks, Ui ui) throws DukeError {
        String[] words = input.split(" ");
        String command = words[0];
        String inputContents = input.substring(command.length()).trim(); //

        try {
            switch (command) { // todo; put the logic for this shit in the
                case "list":

                    if (tasks.size() == 0) {
                        ui.listEmpty();

                    } else {

                        ui.listTasks(tasks);

                    }
                    break;
                case "mark":
                    int markTarget = Integer.parseInt(words[1]) - 1;
                    if (markTarget >= 0 && markTarget < tasks.size()) {
                        tasks.get(markTarget).mark();
                        ui.markAck(tasks.get(markTarget));
                    } else {
                        ui.taskDoesNotexist();
                    }
                    break;
                case "unmark":
                    int target = Integer.parseInt(words[1]) - 1;
                    if (target >= 0 && target < tasks.size()) {
                        tasks.get(target).unMark();
                        ui.unmarkAck(tasks.get(target));
                    } else {
                        ui.taskDoesNotexist();
                    }

                    break;
                case "todo":

                    // if (index < 100) {
                    Todo newTodo = new Todo(inputContents);
                    tasks.add(newTodo);
                    ui.acknowledge(newTodo);
                    //System.out.println("覚えましたよ～ (todo recorded) " + newTodo.toString());
                    // index++;
                    // }
                    break;
                case "deadline":
                    // if (index < 100) {
                    Deadline newDeadline = new Deadline(inputContents);
                    tasks.add(newDeadline);
                    ui.acknowledge(newDeadline);
                    // System.out.println("覚えましたよ～ (deadline recorded) " + newDeadline.toString());
                    // index++;
                    //  }
                    break;
                case "event":
                    // if (index < 100) {
                    Event newEvent = new Event(inputContents);
                    tasks.add(newEvent);
                    ui.acknowledge(newEvent);
                    // System.out.println("覚えましたよ～ (event recorded) " + newEvent.toString());
                    //index++;
                    // }
                    break;
                case "delete":
                    int deleteTarget = Integer.parseInt(words[1]) - 1;
                    if (deleteTarget >= 0 && deleteTarget < tasks.size()) {
                        ui.ackDelete(tasks.get(deleteTarget));
                        tasks.remove(deleteTarget);

                    } else {
                        ui.doesNotExist();
                    }
                    break;
                case "find":

                    if (words.length > 1) {
                        String keyword = words[1];
                        tasks.find(ui, keyword);
                    } else {
                        throw new DukeError("No search keyword");
                    }


                    break;
                case "clearlist":
                    tasks.clear();
                    ui.clearAck();
                    break;
                case "bye":
                    this.isExit = true;
                    break;

                default:
                    ui.badCommand();


                    break;


            }
        } catch (NullPointerException e) {
            throw new DukeError("NullPointerException");

        }


    }


}
