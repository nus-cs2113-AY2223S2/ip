// Transform the line with "/" to common
public class transformString {
    public static String transformString(String line) {
        String transformedLine = "";
        String[] wordsListOfLine = line.split(" ");
        boolean firstTimeOccurence = true; // Indicates the occurrence time of "/"

        if (line.contains("/")) {
            for (int i = 0; i < wordsListOfLine.length; i++) {
                if (wordsListOfLine[i].startsWith("/")) {
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

        return transformedLine;
    }


}
