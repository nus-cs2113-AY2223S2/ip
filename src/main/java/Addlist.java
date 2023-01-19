import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Addlist {
    String unableToFindPikachuFaceLogo =
              "⣿⣿⡶⢄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀   ⣀⢴⣾⣿⣿\n"
            + "⣿⣿⠀⠀⠈⠓⢤⡀⠀⠀⢀⡤⣶⠞⠟⠲⣲⢤⣀⠀⠀⠀⢀⠴⠊⠀⠀⢹⣿⠟\n"
            + "⠈⠻⢄⠀⠀⠀⠀⠙⢤⠖⢁⠞⠁⠀⢸⠀⠈⠑⣮⣑⣄⠔⠁⠀⠀⠀⢀⠜⠁⠀\n"
            + "⠀⠀⠀⠑⠠⣀⠀⠀⢸⣿⣾⣶⠶⠶⠾⠶⢶⣾⣿⣿⠻⠀⠀⢀⡠⠒⠁⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⠀⠉⡳⣲⠯⠭⢤⠖⠒⠛⠒⠲⠯⠭⠭⠶⢗⠂⠁⠀⠀⡠⠔⠂⠀\n"
            + "⠀⠀⠀⢀⡀⠀⢰⠋⠀⠀⠀⠀⠈⠁⠒⠠⠤⠀⠤⠒⠀⠉⠆⠀⡴⠊⠀⠀⠀⠀\n"
            + "⠀⠂⡇⡼⠟⠀⡄⢠⣴⠶⠦⢖⣄⠀⠀⠀⠀⠀⢀⡀⠀⠀⢸⠊⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠹⠤⡄⠀⣧⡟⢡⣾⣈⡆⢱⡆⠀⠀⠀⢠⣇⣹⣦⠀⠈⠀⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠸⠇⠸⠺⡱⡘⠠⠗⠁⣸⡇⡀⠀⠀⠀⠻⠿⠋⢠⠒⡆⠀⠀⠀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⠀⢠⡀⢱⠹⣶⣶⠾⠋⠀⠀⠀⠀⠀⠀⠀⠀⠃⢀⠇⡀⢀⠤⠂⠀⠀\n"
            + "⠀⠀⠀⠀⠀⢀⠕⠊⠁⠈⠱⠀⠀⠀⠿⠀⠀⠀⠀⠀⠀⡣⠊⠠⢞⣁⡀⠀⠀⠀\n"
            + "⠀⠀⠀⠀⢠⡇⠀⠀⠀⣰⡃⠲⣀⠀⠀⠀⢀⠔⠀⠀⠈⠘⡤⡀⠀⡴⠁⠀⠀\n";
    String surprisedPikachuFaceLogo =
              "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n"
            + "⣿⣿⣿⣿⣿⣿⠀⠀⠀⠈⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠛⠉⠁⠀⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣧⡀⠀⠀⠀⠀⠙⠿⠿⠿⠻⠿⠿⠟⠿⠛⠉⠀⠀⠀⠀⠀⣸⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣿⣷⣄⠀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⣿⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⣴⣿⣿⣿⣿\n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⢰⣹⡆⠀⠀⠀⠀⠀⠀⣭⣷⠀⠀⠀⠸⣿⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠈⠉⠀⠀⠤⠄⠀⠀⠀⠉⠁⠀⠀⠀⠀⢿⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⢾⣿⣷⠀⠀⠀⠀⡠⠤⢄⠀⠀⠀⠠⣿⣿⣷⠀⢸⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⡀⠉⠀⠀⠀⠀⠀⢄⠀⢀⠀⠀⠀⠀⠉⠉⠁⠀⠀⣿⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿ \n"
            + "⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⣿\n";
    String addingToListPikachuLogo =
              "░░░░░░░░▀████▀▄▄░░░░░░░░░░░░░░▄█\n"
            + "░░░░░░░░░░█▀░░░░▀▀▄▄▄▄▄░░░░▄▄▀▀█  (\\ \n"
            + "░░▄░░░░░░░░█░░░░░░░░░░▀▀▀▀▄░░▄▀   \\'\\ \n"
            + "░▄▀░▀▄░░░░░░▀▄░░░░░░░░░░░░░░▀▄▀    \\'\\     __________\n"
            + "▄▀░░░░█░░░░░█▀░░░▄█▀▄░░░░░░▄█      / '|   ()_________) \n"
            + "▀▄░░░░░▀▄░░█░░░░░▀██▀░░░░░██▄█     \\ '/    \\ ~~~~~~~~ \\ \n"
            + "░▀▄░░░░▄▀░█░░░▄██▄░░░▄░░▄░░▀▀░█      \\       \\ ~~~~~~   \\ \n"
            + "░░█░░▄▀░░█░░░░▀██▀░░░░▀▀░▀▀░░▄▀      ==).      \\__________\\ \n"
            + "░█░░░█░░█░░░░░░▄▄░░░░░░░░░░░▄▀      (__)       ()__________) \n";

    String showingListPikachuLogo =
                      "░░░░█░▀▄░░░░░░░░░░▄▄███▀░░\n"
                    + "░░░░█░░░▀▄░▄▄▄▄▄░▄▀░░░█▀░░\n"
                    + "░░░░░▀▄░░░▀░░░░░▀░░░▄▀░░░░\n"
                    + "░░░░░░░▌░▄▄░░░▄▄░▐▀▀░░░░░░\n"
                    + "░░░░░░▐░░█▄░░░▄█░░▌▄▄▀▀▀▀█\n"
                    + "░░░░░░▌▄▄▀▀░▄░▀▀▄▄▐░░░░░░█\n"
                    + "░░░▄▀▀▐▀▀░░░░░░░▀▀▌▄▄▄░░░█\n"
                    + "░░░█░░░▀▄░░░░░░░▄▀░░░░█▀▀▀\n"
                    + "Pikapi give you your list \n";

    public void AddList(){
        Task[] ListOfThings = new Task[100];
        Scanner newScanner = new Scanner(System.in);
        int index = 0;
        while(true)
        {
            try {
                System.out.println("______________________________________________");
                String input = newScanner.next();
                System.out.println("______________________________________________");
                switch (input) {
                    case "list":
                        System.out.println(showingListPikachuLogo + "\n");
                        for (int i = 0; i < index; i++) {
                            System.out.println((i + 1) + ". " + ListOfThings[i].toString());
                        }
                        break;
                    case "bye":
                        System.out.println("    *surprised Pikachu face* \n");
                        System.out.println(surprisedPikachuFaceLogo + "\n");
                        System.out.println("Pikapi is surprised to see you go, see you soon fwen\n");
                        System.exit((0));
                    case "todo":
                        try {
                            String restOfString = newScanner.nextLine();
                            if (restOfString.equals(""))
                            {
                                throw new DukeException(unableToFindPikachuFaceLogo + "Description cannot be empty :<");
                            }
                            else
                            {
                                System.out.println(addingToListPikachuLogo);
                                System.out.println("Pikapi add this task:");
                                ListOfThings[index] = new Todo(restOfString);
                                System.out.println("  " + ListOfThings[index].toString());
                                index += 1;
                                System.out.println("Pikapi sees that now you have " + index + " tasks in the list");
                                break;
                            }
                        }
                        catch (DukeException e)
                        {
                            System.out.println(e.getMessage());
                            break;
                        }
                    case "deadline":
                        String description = "";
                        while (true)
                        {
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
                            System.out.println(unableToFindPikachuFaceLogo + "Description cannot be empty :<, please write a description after the word deadline");
                            break;
                        } else if (dueDate.equals("") ) {
                            System.out.println(unableToFindPikachuFaceLogo + "DueDate cannot be empty :<, please write a the description of a deadline");
                            break;
                        }
                        System.out.println(addingToListPikachuLogo + "\n");
                        System.out.println("Pikapi add this task");
                        ListOfThings[index] = new Deadline(description, dueDate);
                        System.out.println("  " + ListOfThings[index].toString());
                        index += 1;
                        System.out.println("Pikapi sees that now you have " + index + " tasks in the list");
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
                                throw new DukeException((unableToFindPikachuFaceLogo + "End Date cannot be empty :<"));
                            } else {
                                System.out.println(addingToListPikachuLogo);
                                System.out.println("Pikapi add this task:");
                                ListOfThings[index] = new Event(descriptionEvent, startDate, endDate);
                                System.out.println("  " + ListOfThings[index].toString());
                                index += 1;
                                System.out.println("Pikapi sees that now you have " + index + " tasks in the list");
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
                                    System.out.println(addingToListPikachuLogo + "\n");
                                    System.out.println("Pikapi has marked the task as done\n");
                                    ListOfThings[num - 1].done = true;
                                    System.out.println("[" + ListOfThings[num - 1].getStatusIcon() + "] " + ListOfThings[num - 1].description);
                                    break;
                                }
                            } catch(InputMismatchException e)
                            {
                                System.out.println("PIKAPII thats not a number! Please input a number after you type mark!");
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
                                    if (index == 0)
                                    {
                                        lowerBound = 0;
                                    }
                                    else
                                    {
                                        lowerBound = 1;
                                    }
                                    System.out.println("PIKAPII you dont have negative number of tasks silly, please input a number between " + lowerBound  + " and " + index + " after the word unmark");
                                    break;
                                } else {
                                    System.out.println(addingToListPikachuLogo + "\n");
                                    System.out.println("Pikapi has unmarked the task\n");
                                    ListOfThings[numUnmarkIndex - 1].done = false;
                                    System.out.println("[" + ListOfThings[numUnmarkIndex - 1].getStatusIcon() + "] " + ListOfThings[numUnmarkIndex - 1].description);
                                    break;
                                }
                            }
                        } catch (InputMismatchException e)
                        {
                            System.out.println("PIKAPII thats not a number! Please input a number after you type unmark!");
                            newScanner.skip("[a-zA-Z1-9]*");
                            System.out.println(input);
                        }
                        break;
                    default:
                            System.out.println(input);
                            throw new DukeException("Pikapi is unable to find that command, please type in a correct command");

                }
            }catch(DukeException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }

}
