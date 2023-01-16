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
                int num = newScanner.nextInt();
                System.out.println("Pikapi has marked the task as done\n");
                System.out.println(addingToListPikachuLogo + "\n");
                ListOfThings[num - 1].done = true;
                System.out.println(". [" + ListOfThings[num - 1].getStatusIcon() + "] " + ListOfThings[num - 1].description);
            }
            else if (input.equals("unmark") )
            {
                int num = newScanner.nextInt();
                System.out.println("Pikapi has unmarked the task\n");
                System.out.println(addingToListPikachuLogo + "\n");
                ListOfThings[num - 1].done = false;
                System.out.println(". [" + ListOfThings[num - 1].getStatusIcon() + "] " + ListOfThings[num - 1].description);
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
