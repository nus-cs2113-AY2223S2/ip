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
    String addingToListPikachuLogo = "Pikapi add to list \n"
            + "░░░░░░░░▀████▀▄▄░░░░░░░░░░░░░░▄█\n"
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
        String[] ListOfThings = new String[100];
        int index = 0;
        while(true)
        {
            System.out.println("______________________________________________");
            Scanner newScanner = new Scanner(System.in);
            String input = newScanner.nextLine();
            System.out.println("______________________________________________");
            if (input.equals("list"))
            {
                System.out.println(showingListPikachuLogo + "\n");
                for (int i = 0; i < index; i++)
                {
                    System.out.println((i + 1) + ": " + ListOfThings[i]);
                }
            }
            else if (input.equals("bye"))
            {
                System.out.println("    *surprised Pikachu face* \n");
                System.out.println(surprisedPikachuFaceLogo + "\n");
                System.out.println("Pikapi is surprised to see you go, see you soon fwen\n");
                System.exit((0));
            }
            else
            {
                System.out.println(addingToListPikachuLogo + "\n");
                ListOfThings[index] = input;
                index += 1;
            }
        }
    }

}
