package shizuka;

import java.io.IOException;

public class Command {
    private final String keyword;
    private String[] args;

    public Command(String keyword) {
        this.keyword = keyword;
    }

    public Command(String keyword, int taskNum) {
        this.keyword = keyword;
        this.args = new String[1];
        this.args[0] = Integer.toString(taskNum);
    }

    public Command(String keyword, String args) {
        this.keyword = keyword;
        this.args = new String[1];
        this.args[0] = args;
    }
    public Command(String keyword, String[] args) {
        this.keyword = keyword;
        this.args = args;
    }

    public void execute(TodoList list0) {
        switch (keyword) {
        case "bye":
            Shizuka.isExit = true;
            break;
        case "list":
            list0.list();
            break;
        case "mark":
            try {
                list0.mark(Integer.parseInt(args[0]));
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
        case "unmark":
            try {
                list0.unmark(Integer.parseInt(args[0]));
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
        case "todo":
            try {
                list0.addTodo(args[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
            break;
        case "deadline":
            try {
                list0.addDeadline(args[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
            break;
        case "event":
            try {
                list0.addEvent(args[0]);
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
            break;
        case "delete":
            try {
                list0.deleteTask(Integer.parseInt(args[0]));
            } catch (ArrayIndexOutOfBoundsException e) {
                UI.noArgsError();
                break;
            }
        case "save":
            try {
                Storage.save(list0.listWriter());
                UI.saveSuccess();
                break;
            } catch (IOException e) {
                UI.ioError();
                break;
            }
        case "find":
            list0.find(args[0]);
            break;
        default:
            UI.parseError();
        }
    }
}
