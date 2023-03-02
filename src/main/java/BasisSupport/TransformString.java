package BasisSupport;
import Support.Time;

public class TransformString {
    // This class is to transform the line with "/" to common
    public static final String DASH = "/";
    public static String transformString(String line) {
        String transformedLine;
        String[] wordsListOfLine = line.split(" ");
        boolean firstTimeOccurence = true; // Indicates the occurrence time of "/"

        // In order to provide brackets and colon to the content
        if (line.contains(DASH)) {
            for (int i = 0; i < wordsListOfLine.length; i++) {
                if (wordsListOfLine[i].startsWith(DASH)) {
                    if (firstTimeOccurence) {
                        wordsListOfLine[i] = "(" + wordsListOfLine[i].substring(1) + ":";
                        firstTimeOccurence = false;
                    } else {
                        wordsListOfLine[i] = wordsListOfLine[i].substring(1) + ":";
                    }
                }
            }
            transformedLine = String.join(" ", wordsListOfLine) + ")";
        } else {
            transformedLine = line;
        }

        if (transformedLine.contains(DASH)) {

            // The two index to obtain where we should substitute the time
            int substituteStart = 0;
            int substituteEnd = 0;

            // In order to print Time in a better format
            // We only accept two formats of time
            // For the format, it should be either day/month/year or day/month/year time
            String[] originalList = transformedLine.split(DASH);
            int index = 0;
            String[] timeList = new String[originalList.length];
            for (String time: originalList) {
                time = time.replaceAll("[^0-9]", "");
                // Sometimes day and month can be only 1 digit
                if (time.length() == 1) {
                    time = "0" + time;
                }
                timeList[index] = time;
                index++;
            }

            String dateDisplay = "";

            if (timeList[2].length() == 4) {
                // It means that the format is day/month/year
                Time date = new Time(timeList[2], timeList[1], timeList[0]);
                dateDisplay = date.transformLocalDate();
                // The length of day matters
                if (timeList[0].startsWith("0")) {
                    substituteStart = transformedLine.indexOf(timeList[0].substring(1) + DASH
                            + timeList[1] + DASH + timeList[2]);
                    substituteEnd = substituteStart + 9;
                } else {
                    substituteStart = transformedLine.indexOf(timeList[0] + DASH
                            + timeList[1] + DASH + timeList[2]);
                    substituteEnd = substituteStart + 10;
                }

            } else {
                // It means that the format is day/month/year time
                Time datetime = new Time(timeList[2].substring(0,4), timeList[1], timeList[0], timeList[2].substring(4));
                dateDisplay = datetime.transformLocalDateTime();

                // The length of day matters
                if (timeList[0].startsWith("0")) {
                    substituteStart = transformedLine.indexOf(timeList[0].substring(1) + DASH
                            + timeList[1] + DASH + timeList[2].substring(0,4) + " " + timeList[2].substring(4));
                    substituteEnd = substituteStart + 14;
                } else {
                    substituteStart = transformedLine.indexOf(timeList[0] + DASH
                            + timeList[1] + DASH + timeList[2].substring(0,4) + " " + timeList[2].substring(4));
                    substituteEnd = substituteStart + 15;
                }

            }

            transformedLine = transformedLine.substring(0, substituteStart) + dateDisplay
                    + transformedLine.substring(substituteEnd);
        }

        return transformedLine;
    }
}