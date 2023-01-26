import java.util.Scanner;

public class Bob {

    static private final String hello_art = "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB           SSSSSSS" +
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
    static private final String bye_art = "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB           SSSSSSSSS" +
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
    static private final Task[] list = new Task[100];
    static private int listCount = 0;

    public static void hello() {
        System.out.println(hello_art);
    }

    public static void shutdown() {
        System.out.println(bye_art);
    }

    public static void query() {
        System.out.println("What do you want from BOB?");
    }

    public static void listOut() {
        for (int i = 0; i < listCount; ++i) {
            System.out.println(list[i].getNumber() + "." + list[i].checkDone() + " " + list[i].getTask());
        }
    }

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[0-9]+");
    }

    public static int convertString(String str) {
        int num = -1;
        if (isNumeric(str)) {
            num = Integer.parseInt(str);
        }
        if (num < 1 || num > listCount + 1) {
            System.out.println("Invalid entry");
        }
        return num;
    }

    public static void command() {
        Scanner in = new Scanner(System.in);
        do {
            String cmd = in.next();
            if (cmd.equals("bye")) {
                return;
            }
            switch (cmd) {
            case "list":
                listOut();
                break;
            case "mark": {
                String str = in.next();
                //For error handling
                int num = convertString(str);
                if (num == -1) {
                    continue;
                }
                System.out.println("Bob commends you!");
                list[num - 1].setDone(true);
                System.out.println("  " + list[num - 1].checkDone() + " " + list[num - 1].getTask());
                break;
            }
            case "unmark": {
                String str = in.next();
                //For error handling
                int num = convertString(str);
                if (num == -1) {
                    continue;
                }
                System.out.println("Boo a mistake!");
                list[num - 1].setDone(false);
                System.out.println("  " + list[num - 1].checkDone() + " " + list[num - 1].getTask());
                break;
            }
            default:
                String next = in.nextLine();
                System.out.println("added: " + cmd + next);
                list[listCount] = new Task(cmd + next, listCount + 1, false);
                ++listCount;
                break;
            }
        } while (true);
    }

    public static void main(String[] args) {
        hello();
        query();
        command();
        shutdown();
    }
}