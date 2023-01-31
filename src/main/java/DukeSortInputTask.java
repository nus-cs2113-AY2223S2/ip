public class DukeSortInputTask {
    public static void dukeSortInputTask(DukeTasks[] list, Integer taskLength, String taskName) {
        String[] taskType = taskName.split(" ", 2);
        String[] taskDescription;
        if (taskType[0].equals("todo")) {
            list[taskLength] = new DukeToDos(taskType[1]);
        } else if (taskType[0].equals("deadline")) {
            taskDescription = taskType[1].split("/by", 2);
            list[taskLength] = new DukeDeadlines(taskDescription[0], taskDescription[1]);
        } else if (taskType[0].equals("event")) {
            taskDescription = taskType[1].split("/from", 2);
            list[taskLength] = new DukeEvents(taskDescription[0], taskDescription[1]);
        } else {
            System.out.println("Opps. An error occurred and you have not started on error handling.");
        }
    }
}
