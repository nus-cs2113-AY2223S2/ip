package Duke;

public class TaskToStringConverter {
    static String convertTaskToCommandString(Task task) {
        String[] info = task.toString().split(" ");
        for (int i = 0; i < info.length; ++i) {
            if (info[i].equals("(by:")) {
                info[i] = "/by";
            } else if (info[i].equals("(from:")) {
                info[i] = "/from";
            } else if (info[i].equals("to:")) {
                info[i] = "/to";
            } else if (info[i].charAt(0) == '[') {
                info[i] = "";
            } else if (info[i].equals("]")) {
                info[i] = "";
            } else if (info[i].charAt(info[i].length() - 1) == ']'
                    || info[i].charAt(info[i].length() - 1) == ')') {
                info[i] = info[i].substring(0, info[i].length() - 1);
            }
        }
        String command = task.getType() + " ";
        for (String word : info) {
            if (!word.equals("")) command += word + " ";
        }
        command += "/done " + (task.isCompleted() ? "done" : "notdone");
        return command;
    }

    static String removeCompletionStatusFromStr(String taskInStr) {
        int counter = taskInStr.length() - 1;
        while (taskInStr.charAt(counter) != '/') {
            counter--;
        }
        return taskInStr.substring(0, counter - 1);
    }
}