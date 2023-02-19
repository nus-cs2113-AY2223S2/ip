import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteFile {

    public static void writeToFile(String filePath, ArrayList<Task> listOfTasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);

        for (int i = 0; i < listOfTasks.size(); ++i) {
            String[] label = listOfTasks.get(i).taskLabel.split(""); // Get the Char in taskLabel
            String charLabel = label[1];
            String name, period;
            int binIsDone = (listOfTasks.get(i).isDone ? 1 : 0);
            switch (charLabel) {
            case "T":
                fw.write(charLabel + "/" + binIsDone + "/" + listOfTasks.get(i).description + "\n");
                break;
            case "D":
                String[] splitNameDate = listOfTasks.get(i).description.split(" \\(by: ");
                name = splitNameDate[0];
                period = splitNameDate[1].replaceAll("\\)", "");
                fw.write(charLabel + "/" + binIsDone + "/" + name + "/" + period + "\n");
                break;
            case "E":
                String[] splitNameFromPeriod = listOfTasks.get(i).description.split(" \\(from: ");
                name = splitNameFromPeriod[0];
                period = splitNameFromPeriod[1].replaceAll("\\)", "");
                period = period.replace("to:", "-");
                fw.write(charLabel + "/" + binIsDone + "/" + name + "/" + period + "\n");
                break;
            }
        }
        fw.close();
    }
}

