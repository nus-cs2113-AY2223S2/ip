import java.util.Scanner;

public class Duke {
    static private final String HI = "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB           SSSSSSS" +
            "SSSSSSSS             AAA          YYYYYYY       YYYYYYY  SSSSSSSSSSSSSSS      HHHHHHHHH     HHHHHHHHIIII" +
            "IIIIII\n" +
            "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B        SS:::::::::::::::S           A:::A       " +
            "  Y:::::Y       Y:::::YSS:::::::::::::::S     H:::::::H     H:::::::I::::::::I\n" +
            "B::::::BBBBBB:::::B OO:::::::::::::OOB::::::BBBBBB:::::B      S:::::SSSSSS::::::S          A:::::A      " +
            "  Y:::::Y       Y:::::S:::::SSSSSS::::::S     H:::::::H     H:::::::I::::::::I\n" +
            "BB:::::B     B:::::O:::::::OOO:::::::BB:::::B     B:::::B     S:::::S     SSSSSSS         A:::::::A     " +
            "  Y::::::Y     Y::::::S:::::S     SSSSSSS     HH::::::H     H::::::HII::::::II\n" +
            "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B     S:::::S                    A:::::::::A    " +
            "  YYY:::::Y   Y:::::YYS:::::S                   H:::::H     H:::::H   I::::I  \n" +
            "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B     S:::::S                   A:::::A:::::A   " +
            "     Y:::::Y Y:::::Y  S:::::S                   H:::::H     H:::::H   I::::I  \n" +
            "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B       S::::SSSS               A:::::A A:::::A  " +
            "      Y:::::Y:::::Y    S::::SSSS                H::::::HHHHH::::::H   I::::I  \n" +
            "  B:::::::::::::BB O:::::O     O:::::O B:::::::::::::BB         SS::::::SSSSS         A:::::A   A:::::A " +
            "       Y:::::::::Y      SS::::::SSSSS           H:::::::::::::::::H   I::::I  \n" +
            "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B          SSS::::::::SS      A:::::A     A:::::A" +
            "        Y:::::::Y         SSS::::::::SS         H:::::::::::::::::H   I::::I  \n" +
            "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B            SSSSSS::::S    A:::::AAAAAAAAA:::::" +
            "A        Y:::::Y             SSSSSS::::S        H::::::HHHHH::::::H   I::::I  \n" +
            "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B                 S:::::S  A::::::::::::::::::::" +
            ":A       Y:::::Y                  S:::::S       H:::::H     H:::::H   I::::I  \n" +
            "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B                 S:::::S A:::::AAAAAAAAAAAAA:::" +
            "::A      Y:::::Y                  S:::::S       H:::::H     H:::::H   I::::I  \n" +
            "BB:::::BBBBBB::::::O:::::::OOO:::::::BB:::::BBBBBB::::::B     SSSSSSS     S:::::SA:::::A             A::" +
            ":::A     Y:::::Y      SSSSSSS     S:::::S     HH::::::H     H::::::HII::::::II\n" +
            "B:::::::::::::::::B OO:::::::::::::OOB:::::::::::::::::B      S::::::SSSSSS:::::A:::::A               A:" +
            "::::A YYYY:::::YYYY   S::::::SSSSSS:::::S     H:::::::H     H:::::::I::::::::I\n" +
            "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B       S:::::::::::::::SA:::::A                 A" +
            ":::::AY:::::::::::Y   S:::::::::::::::SS      H:::::::H     H:::::::I::::::::I\n" +
            "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB         SSSSSSSSSSSSSSSAAAAAAA                   " +
            "AAAAAAYYYYYYYYYYYYY    SSSSSSSSSSSSSSS        HHHHHHHHH     HHHHHHHHIIIIIIIIII";
    static private final String BYE = "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB           SSSSSSSSS" +
            "SSSSSS             AAA          YYYYYYY       YYYYYYY  SSSSSSSSSSSSSSS      BBBBBBBBBBBBBBBBB  YYYYYYY  " +
            "     YYYYYYEEEEEEEEEEEEEEEEEEEEEE\n" +
            "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B        SS:::::::::::::::S           A:::A       " +
            "  Y:::::Y       Y:::::YSS:::::::::::::::S     B::::::::::::::::B Y:::::Y       Y:::::E::::::::::::::::::" +
            "::E\n" +
            "B::::::BBBBBB:::::B OO:::::::::::::OOB::::::BBBBBB:::::B      S:::::SSSSSS::::::S          A:::::A      " +
            "  Y:::::Y       Y:::::S:::::SSSSSS::::::S     B::::::BBBBBB:::::BY:::::Y       Y:::::E::::::::::::::::::" +
            "::E\n" +
            "BB:::::B     B:::::O:::::::OOO:::::::BB:::::B     B:::::B     S:::::S     SSSSSSS         A:::::::A     " +
            "  Y::::::Y     Y::::::S:::::S     SSSSSSS     BB:::::B     B:::::Y::::::Y     Y::::::EE::::::EEEEEEEEE::" +
            "::E\n" +
            "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B     S:::::S                    A:::::::::A    " +
            "  YYY:::::Y   Y:::::YYS:::::S                   B::::B     B:::::YYY:::::Y   Y:::::YYY E:::::E       EEE" +
            "EEE\n" +
            "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B     S:::::S                   A:::::A:::::A   " +
            "     Y:::::Y Y:::::Y  S:::::S                   B::::B     B:::::B  Y:::::Y Y:::::Y    E:::::E          " +
            "   \n" +
            "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B       S::::SSSS               A:::::A A:::::A  " +
            "      Y:::::Y:::::Y    S::::SSSS                B::::BBBBBB:::::B    Y:::::Y:::::Y     E::::::EEEEEEEEEE" +
            "   \n" +
            "  B:::::::::::::BB O:::::O     O:::::O B:::::::::::::BB         SS::::::SSSSS         A:::::A   A:::::A " +
            "       Y:::::::::Y      SS::::::SSSSS           B:::::::::::::BB      Y:::::::::Y      E:::::::::::::::E" +
            "   \n" +
            "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B          SSS::::::::SS      A:::::A     A:::::A" +
            "        Y:::::::Y         SSS::::::::SS         B::::BBBBBB:::::B      Y:::::::Y       E:::::::::::::::E" +
            "   \n" +
            "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B            SSSSSS::::S    A:::::AAAAAAAAA:::::" +
            "A        Y:::::Y             SSSSSS::::S        B::::B     B:::::B      Y:::::Y        E::::::EEEEEEEEEE" +
            "   \n" +
            "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B                 S:::::S  A::::::::::::::::::::" +
            ":A       Y:::::Y                  S:::::S       B::::B     B:::::B      Y:::::Y        E:::::E          " +
            "   \n" +
            "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B                 S:::::S A:::::AAAAAAAAAAAAA:::" +
            "::A      Y:::::Y                  S:::::S       B::::B     B:::::B      Y:::::Y        E:::::E       EEE" +
            "EEE\n" +
            "BB:::::BBBBBB::::::O:::::::OOO:::::::BB:::::BBBBBB::::::B     SSSSSSS     S:::::SA:::::A             A::" +
            ":::A     Y:::::Y      SSSSSSS     S:::::S     BB:::::BBBBBB::::::B      Y:::::Y      EE::::::EEEEEEEE:::" +
            "::E\n" +
            "B:::::::::::::::::B OO:::::::::::::OOB:::::::::::::::::B      S::::::SSSSSS:::::A:::::A               A:" +
            "::::A YYYY:::::YYYY   S::::::SSSSSS:::::S     B:::::::::::::::::B    YYYY:::::YYYY   E::::::::::::::::::" +
            "::E\n" +
            "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B       S:::::::::::::::SA:::::A                 A" +
            ":::::AY:::::::::::Y   S:::::::::::::::SS      B::::::::::::::::B     Y:::::::::::Y   E::::::::::::::::::" +
            "::E\n" +
            "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB         SSSSSSSSSSSSSSSAAAAAAA                   " +
            "AAAAAAYYYYYYYYYYYYY    SSSSSSSSSSSSSSS        BBBBBBBBBBBBBBBBB      YYYYYYYYYYYYY   EEEEEEEEEEEEEEEEEEE" +
            "EEE";
    static private final String DIV = "\n===========================================================================\n";
    static private final Task[] tasks = new Task[100];
    static private int taskCount = 0;

    public static void hello() {
        System.out.println(HI);
    }

    public static void shutdown() {
        System.out.println("\n" + BYE);
    }

    public static void query() {
        System.out.println(DIV + "\nWhat do you want from me boss?\n" + DIV);
    }

    public static void listOut() {
        if (taskCount == 0) {
            System.out.println("*Tumbleweed passes by*\nUh oh! Looks like your list is empty!");
            return;
        } else {
            System.out.println("Here's your list boss! *Crosses arms and nods* : ");
        }
        for (int i = 0; i < taskCount; ++i) {
            System.out.println(tasks[i].getNumber() + "." + tasks[i]);
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }

    public static  void printError() {
        System.out.println("Wrong words boss! Try again!");
    }

    public static int convertString(String str) {
        int num = -1;
        str = str.trim();
        if (isNumeric(str)) {
            num = Integer.parseInt(str);
        }
        if (num < 1 || num > taskCount) {
            System.out.println("This does not exist boss! Try again! *Shakes head* ");
            num = -1;
        }
        return num;
    }

    public static boolean checkSameDone(int index, boolean changeTo, String type) {
        if (tasks[index - 1].isDone == changeTo) {
            System.out.println("It is already " + type + "ed! *Shakes head* ");
            return true;
        }
        return false;
    }

    public static void command() {
        Scanner in = new Scanner(System.in);
        do {
            System.out.print("What do you want: ");
            String cmd = in.next();
            String checkCmd = cmd.toLowerCase();
            if (cmd.equals("bye")) {
                return;
            }
            System.out.println(DIV);
            switch (checkCmd) {
            case "list": {
                listOut();
                break;
            }
            case "mark": {
                String next = in.nextLine();
                //For error handling
                int num = convertString(next);
                if (num == -1 || checkSameDone(num, true, checkCmd)) {
                    break;
                }
                System.out.println("Bob commends you! *Nods head* ");
                tasks[num - 1].setDone(true);
                System.out.println(tasks[num - 1]);
                break;
            }
            case "unmark": {
                String next = in.nextLine();
                //For error handling
                int num = convertString(next);
                if (num == -1 || checkSameDone(num, false, checkCmd)) {
                    break;
                }
                System.out.println("A mistake! *Shakes head* ");
                tasks[num - 1].setDone(false);
                System.out.println(tasks[num - 1]);
                break;
            }
            case "todo": {
                String next = in.nextLine();
                System.out.println("Understood! *Salutes* Task added!");
                tasks[taskCount] = new ToDo(next.stripLeading(), taskCount + 1, false);
                System.out.println(tasks[taskCount]);
                ++taskCount;
                break;
            }
            case "deadline": {
                String next = in.nextLine();
                if (!next.contains("/by")) {
                    printError();
                    break;
                }
                String[] deadline = next.split("/by", 2);
                System.out.println("Understood *Salutes* Task with deadline added!\n"
                        + "Remember to complete it by the deadline!");
                tasks[taskCount] = new Deadline(deadline[0].trim(), taskCount + 1, false,
                        deadline[1].trim());
                System.out.println(tasks[taskCount]);
                ++taskCount;
                break;
            }
            case "event": {
                String next = in.nextLine();
                if (!next.contains("/from") || !next.contains("/to")) {
                    printError();
                    break;
                }
                String[] eventName = next.split("/from", 2);
                String[] eventTime = eventName[1].split("/to", 2);
                System.out.println("Understood *Salutes* Event added!\n"
                        + "Remember the starting time! Don't be late!");
                tasks[taskCount] = new Event(eventName[0].trim(), taskCount + 1, false,
                        eventTime[0].trim(), eventTime[1].trim());
                System.out.println(tasks[taskCount]);
                ++taskCount;
                break;
            }
            default:
                printError();
                in.nextLine();
            }
            System.out.println(DIV);
        } while (true);
    }

    public static void main(String[] args) {
        hello();
        query();
        command();
        shutdown();
    }
}