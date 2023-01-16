import java.util.Scanner;

public class Addlist {
    String surprisedPikachuFaceLogo = "⣿⣿⣿⣿⣿⡏⠉⠛⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⣿\n"
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
    String addingToListPikachuLogo = "░░░░░░░░▀████▀▄▄░░░░░░░░░░░░░░▄█\n"
            + "░░░░░░░░░░█▀░░░░▀▀▄▄▄▄▄░░░░▄▄▀▀█  (\\ \n"
            + "░░▄░░░░░░░░█░░░░░░░░░░▀▀▀▀▄░░▄▀   \\'\\ \n"
            + "░▄▀░▀▄░░░░░░▀▄░░░░░░░░░░░░░░▀▄▀    \\'\\     __________\n"
            + "▄▀░░░░█░░░░░█▀░░░▄█▀▄░░░░░░▄█      / '|   ()_________) \n"
            + "▀▄░░░░░▀▄░░█░░░░░▀██▀░░░░░██▄█     \\ '/    \\ ~~~~~~~~ \\ \n"
            + "░▀▄░░░░▄▀░█░░░▄██▄░░░▄░░▄░░▀▀░█      \\       \\ ~~~~~~   \\ \n"
            + "░░█░░▄▀░░█░░░░▀██▀░░░░▀▀░▀▀░░▄▀      ==).      \\__________\\ \n"
            + "░█░░░█░░█░░░░░░▄▄░░░░░░░░░░░▄▀      (__)       ()__________) \n";

    String showingListPikachuLogo = "Pikapi give you your list \n"
            + "░░░░█░▀▄░░░░░░░░░░▄▄███▀░░\n"
            + "░░░░█░░░▀▄░▄▄▄▄▄░▄▀░░░█▀░░\n"
            + "░░░░░▀▄░░░▀░░░░░▀░░░▄▀░░░░\n"
            + "░░░░░░░▌░▄▄░░░▄▄░▐▀▀░░░░░░\n"
            + "░░░░░░▐░░█▄░░░▄█░░▌▄▄▀▀▀▀█\n"
            + "░░░░░░▌▄▄▀▀░▄░▀▀▄▄▐░░░░░░█\n"
            + "░░░▄▀▀▐▀▀░░░░░░░▀▀▌▄▄▄░░░█\n"
            + "░░░█░░░▀▄░░░░░░░▄▀░░░░█▀▀▀\n";

    public void AddList(){
        Task[] ListOfThings = new Task[100];
        System.out.println("HEHE" + ListOfThings.length);
        Scanner newScanner = new Scanner(System.in);
        int index = 0;
        while(true)
        {
            System.out.println("______________________________________________");
            String input = newScanner.next();
            System.out.println("______________________________________________");
            if (input.equals("list"))
            {
                System.out.println(showingListPikachuLogo + "\n");
                for (int i = 0; i < index; i++)
                {
                    System.out.println((i + 1) + ". [" + ListOfThings[i].getStatusIcon() + "] " + ListOfThings[i].description);
                }
            }
            else if (input.equals("bye"))
            {
                System.out.println("    *surprised Pikachu face* \n");
                System.out.println(surprisedPikachuFaceLogo + "\n");
                System.out.println("Pikapi is surprised to see you go, see you soon fwen\n");
                System.exit((0));
            }
            else if (input.equals("mark") )
            {
                while (true)
                {
                    int num = newScanner.nextInt();
                    if (num > index)
                    {
                        System.out.println("You do not have that many items yet, Pikapi requests a correct number less than " + index  + "\n");
                    }
                    else if (num < 0)
                    {
                        System.out.println("PIKAPII you dont have negative number of tasks silly, please input a number between 1 and " + index);
                    }
                    else
                    {
                        System.out.println("Pikapi has marked the task as done\n");
                        System.out.println(addingToListPikachuLogo + "\n");
                        ListOfThings[num - 1].done = true;
                        System.out.println(". [" + ListOfThings[num - 1].getStatusIcon() + "] " + ListOfThings[num - 1].description);
                        break;
                    }
                }
            }
            else if (input.equals("unmark") )
            {
                while (true)
                {
                    int num = newScanner.nextInt();
                    if (num > index)
                    {
                        System.out.println("You do not have that many items yet, Pikapi requests a correct number less than " + index  + "\n");
                    }
                    else if (num < 0)
                    {
                        System.out.println("PIKAPII you dont have negative number of tasks silly, please input a number between 1 and " + index);
                    }
                    else
                    {
                        System.out.println("Pikapi has unmarked the task\n");
                        System.out.println(addingToListPikachuLogo + "\n");
                        ListOfThings[num - 1].done = false;
                        System.out.println(". [" + ListOfThings[num - 1].getStatusIcon() + "] " + ListOfThings[num - 1].description);
                        break;
                    }
                }
            }
            else
            {
                String restOfString = newScanner.nextLine();
                String entireString = input.concat(" ");
                entireString = entireString.concat(restOfString);
                System.out.println("Pikapi add to list\n");
                System.out.println(addingToListPikachuLogo + "\n");
                ListOfThings[index] = new Task(entireString);
                index += 1;
            }
        }
    }

}
