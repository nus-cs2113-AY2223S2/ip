import java.util.ArrayList;

public class TasksList {
    public static ArrayList<Task> tasks;

    public TasksList() {
        tasks = new ArrayList<>();
    }



    static void processList() {
        if (tasks.size() == 0) {
            System.out.println("Nothing to do for now!Take a break!");
        } else {
            System.out.println("Here's your plan for a productive day!");
        }
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d.[%s] [%s] %s ", i + 1,
                    tasks.get(i).getTypeIcon(), tasks.get(i).getStatusIcon(), tasks.get(i).getDescription()
            ));
        }
    }

        public static void addTask(Task task) {
            tasks.add(task);
            Storage.writeToFile();
            UI.printSize();
        }

        public static void deleteTask(int index) {
                UI.printDeleteMessage();
                UI.printMarking(index);
                tasks.remove(index);
                UI.printSize();
                Storage.writeToFile();

            
        }


    public static void addMark(String remainingWords,int i) {
        UI.printAddMark();
        Task.markAsDone(tasks.get(Integer.parseInt(remainingWords) - 1));
        UI.printMarking(i);
        Storage.writeToFile();
    }

    public static void unMark(String remainingWords,int i) {
        UI.printUnmark();
        Task.markAsNotDone(tasks.get(Integer.parseInt(remainingWords) - 1));
        UI.printMarking(i);
        Storage.writeToFile();
    }
}


