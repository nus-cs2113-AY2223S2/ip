package Duke.InputCheckingPackage;
import Duke.Constants.Constants;

import java.util.Objects;

public class InputChecking {
    public static boolean checkDeadlineInput(String command) {
        command = command.toLowerCase();
        String[] words = command.split(" ");
        char Slash = words[words.length - 1].charAt(0);
        int compareSlash = Character.compare(Slash, '/');
        if (compareSlash != 0) {
            System.out.println("ERROR!  INVALID FORMAT! \nThe deadline function is used in the following format:");
            System.out.println("deadline your_item_name /(due date or time)\n");
            return false;
        }

        String keyword = command.substring(0, 8);
        String deadlineName = command.substring(0, command.indexOf("/"));
        String[] deadlineNameWords = deadlineName.split(" ");
        int nameLength = deadlineNameWords.length;

        if (!keyword.equals("deadline")) {
            System.out.println("The keyword 'deadline' should be the first word");
            return false;
        }


        char slash = words[words.length - 1].charAt(0);
        int compareChars = Character.compare(slash, '/');
        if (compareChars != 0) {

            System.out.println("ERROR!  INVALID FORMAT! \nThe deadline function is used in the following format:");
            System.out.println("deadline your_item_name /(due date or time)\n");
            return false;
        }

        if (words.length < 3) {
            System.out.println("ERROR! Deadline name is empty");
            return false;
        }

        return true;


    }

    public static boolean checkTodoInput(String command) {
        command = command.toLowerCase();
        String[] words = command.split(" ");
        String keyword = command.substring(0, 4);


        if (!keyword.equals("todo")) {
            System.out.println("The keyword 'todo' should be the first word");
            return false;
        }


        if (words.length < 2) {
            System.out.println("ERROR! Todo name is empty");
            return false;
        }

        return true;


    }

    public static boolean checkEventInput(String command) {
        command = command.toLowerCase();
        String keyword = command.substring(0, 5);

        int numberOfErrors = 0;
        if (!keyword.equals("event")) {
            System.out.println("The keyword 'event' should be the first word");
            return false;
        }

        String[] words = command.split(" ");
        char fromSlash = words[words.length - 2].charAt(0);
        char toSlash = words[words.length - 1].charAt(0);
        int compareFromSlash = Character.compare(fromSlash, '/');
        int compareToSlash = Character.compare(toSlash, '/');
        if (compareFromSlash != 0 || compareToSlash != 0) {

            System.out.println("ERROR! INVALID FORMAT! \n" + "The event function is used in the following format:");
            System.out.println("event your_item_name /(start time) /(end time) \n");
            return false;
        }

        if (words.length<4){
            System.out.println("ERROR! Event name is empty");
            return false;
        }


        return true;


    }

    public static boolean checkDeleteInput(String command, int numberOfTasks){
        command = command.toLowerCase();
        String[] words = command.split(" ");
        int numberOfErrors = 0;
        if (words.length > 2) {
            System.out.println("The delete function is used in the following format:");
            System.out.println("Delete 'task number'");
            numberOfErrors++;
        }

        if (!Objects.equals(words[0], "delete")) {
            System.out.println("The keyword 'delete' should be the first word");
            numberOfErrors++;
        }
        int taskNumber = Integer.parseInt(words[1]);

        if (taskNumber > numberOfTasks || taskNumber < 1) {
            System.out.println("The task number is invalid!");
            numberOfErrors++;
        }

        if (numberOfErrors > 0) {
            System.out.println("Please input again in the correct format!");
            return false;
        }
        return true;

    }

    public static boolean checkFindInput(String input){
        try {
            input = input.toLowerCase();
            String keyword = input.substring(5);
            keyword = keyword.replace(" ","");

            if (keyword.length() == 0 || keyword.equals(" ")) {
                System.out.println("The keyword is missing!");
                return false;
            }
        } catch (StringIndexOutOfBoundsException e){
            System.out.println("The keyword is missing!");
            return false;
        }
        return true;
    }


    public static boolean checkUnmarkInput(String command, int numberOfTasks) {
        command = command.toLowerCase();
        String[] words = command.split(" ");

        int numberOfErrors = 0;
        if (words.length > 2) {
            System.out.println("The unmark function is used in the following format:");
            System.out.println("Unmark 'task number'");
            numberOfErrors++;
        }
        if (!Objects.equals(words[0], "unmark")) {
            System.out.println("The keyword 'unmark' should be the first word");
            numberOfErrors++;
        }

        int taskNumber = Integer.parseInt(words[1]);

        if (taskNumber > numberOfTasks || taskNumber < 1) {
            System.out.println("The task number is invalid!");
            numberOfErrors++;
        }

        if (numberOfErrors > 0) {
            System.out.println("Please input again in the correct format!");
            return false;
        }
        return true;


    }

    public static boolean checkMarkInput(String command, int numberOfTasks) {
        command = command.toLowerCase();
        String[] words = command.split(" ");

        int numberOfErrors = 0;
        if (words.length > 2) {
            System.out.println("The mark function is used in the following format:");
            System.out.println("mark 'task number'");
            numberOfErrors++;
        }
        if (!Objects.equals(words[0], "mark")) {
            System.out.println("The keyword 'mark' should be the first word");
            numberOfErrors++;
        }

        int taskNumber = Integer.parseInt(words[1]);

        if (taskNumber > numberOfTasks || taskNumber < 1) {
            System.out.println("The task number is invalid!");
            numberOfErrors++;
        }

        if (numberOfErrors > 0) {
            System.out.println("Please input again in the correct format!");
            return false;
        }
        return true;


    }


    private static void printDuplicateKeywordErrorMessage() {
        System.out.println("ERROR! Multiple keywords have been detected!");
        System.out.println("The keywords are:");
        printAllKeywords();
        System.out.println("Make sure your input only contains 1 keyword!");

    }


    public static boolean checkForValidInput(String input) {
        input = input.toLowerCase();
        String[] contents = input.split(" ");
        int i = 0;
        int len = contents.length;
        boolean isValidFirstWord = checkFirstKeyword(contents[0]);

        if(!isValidFirstWord){
            System.out.println("ERROR! The first word is not a valid keyword!");
        }

        int numberOfKeywords = 0;
        while (i < len) {
            if (contents[i].equals("list") || contents[i].equals("mark") || contents[i].equals("unmark") || contents[i].equals("deadline") || contents[i].equals("event") || contents[i].equals("todo")) {
                numberOfKeywords++;
            }
            i++;
        }

        if (numberOfKeywords > 1) {
            printDuplicateKeywordErrorMessage();
        }

        if (!isValidFirstWord|| numberOfKeywords>1){
            return false;
        }
        return true;
    }

    private static boolean checkFirstKeyword(String content) {
        String[] keywords = Constants.listOfKeywords;
        int len = keywords.length;
        int i = 0;
        while (i<len){
            if (Objects.equals(content, keywords[i])){
                return true;
            }
            i++;
        }
        return false;

    }

    private static void printAllKeywords() {
        String[] keywords = Constants.listOfKeywords;
        int len = keywords.length;
        int i = 0;
        while (i < len) {
            System.out.println((i + 1) + ": " + keywords[i]);
            i++;
        }

    }


}