import java.util.Scanner;

public class Bob {

    static public String[] list = new String[100];
    static public int listCount = 0;

    public static void hello() {
        String hello_art = "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB           SSSSSSSSSSSSSSS             AAA          YYYYYYY       YYYYYYY  SSSSSSSSSSSSSSS      HHHHHHHHH     HHHHHHHHIIIIIIIIII\n" +
                "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B        SS:::::::::::::::S           A:::A         Y:::::Y       Y:::::YSS:::::::::::::::S     H:::::::H     H:::::::I::::::::I\n" +
                "B::::::BBBBBB:::::B OO:::::::::::::OOB::::::BBBBBB:::::B      S:::::SSSSSS::::::S          A:::::A        Y:::::Y       Y:::::S:::::SSSSSS::::::S     H:::::::H     H:::::::I::::::::I\n" +
                "BB:::::B     B:::::O:::::::OOO:::::::BB:::::B     B:::::B     S:::::S     SSSSSSS         A:::::::A       Y::::::Y     Y::::::S:::::S     SSSSSSS     HH::::::H     H::::::HII::::::II\n" +
                "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B     S:::::S                    A:::::::::A      YYY:::::Y   Y:::::YYS:::::S                   H:::::H     H:::::H   I::::I  \n" +
                "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B     S:::::S                   A:::::A:::::A        Y:::::Y Y:::::Y  S:::::S                   H:::::H     H:::::H   I::::I  \n" +
                "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B       S::::SSSS               A:::::A A:::::A        Y:::::Y:::::Y    S::::SSSS                H::::::HHHHH::::::H   I::::I  \n" +
                "  B:::::::::::::BB O:::::O     O:::::O B:::::::::::::BB         SS::::::SSSSS         A:::::A   A:::::A        Y:::::::::Y      SS::::::SSSSS           H:::::::::::::::::H   I::::I  \n" +
                "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B          SSS::::::::SS      A:::::A     A:::::A        Y:::::::Y         SSS::::::::SS         H:::::::::::::::::H   I::::I  \n" +
                "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B            SSSSSS::::S    A:::::AAAAAAAAA:::::A        Y:::::Y             SSSSSS::::S        H::::::HHHHH::::::H   I::::I  \n" +
                "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B                 S:::::S  A:::::::::::::::::::::A       Y:::::Y                  S:::::S       H:::::H     H:::::H   I::::I  \n" +
                "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B                 S:::::S A:::::AAAAAAAAAAAAA:::::A      Y:::::Y                  S:::::S       H:::::H     H:::::H   I::::I  \n" +
                "BB:::::BBBBBB::::::O:::::::OOO:::::::BB:::::BBBBBB::::::B     SSSSSSS     S:::::SA:::::A             A:::::A     Y:::::Y      SSSSSSS     S:::::S     HH::::::H     H::::::HII::::::II\n" +
                "B:::::::::::::::::B OO:::::::::::::OOB:::::::::::::::::B      S::::::SSSSSS:::::A:::::A               A:::::A YYYY:::::YYYY   S::::::SSSSSS:::::S     H:::::::H     H:::::::I::::::::I\n" +
                "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B       S:::::::::::::::SA:::::A                 A:::::AY:::::::::::Y   S:::::::::::::::SS      H:::::::H     H:::::::I::::::::I\n" +
                "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB         SSSSSSSSSSSSSSSAAAAAAA                   AAAAAAYYYYYYYYYYYYY    SSSSSSSSSSSSSSS        HHHHHHHHH     HHHHHHHHIIIIIIIIII";
        System.out.println(hello_art);
    }

    public static void shutdown() {
        String bye_art = "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB           SSSSSSSSSSSSSSS             AAA          YYYYYYY       YYYYYYY  SSSSSSSSSSSSSSS      BBBBBBBBBBBBBBBBB  YYYYYYY       YYYYYYEEEEEEEEEEEEEEEEEEEEEE\n" +
                "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B        SS:::::::::::::::S           A:::A         Y:::::Y       Y:::::YSS:::::::::::::::S     B::::::::::::::::B Y:::::Y       Y:::::E::::::::::::::::::::E\n" +
                "B::::::BBBBBB:::::B OO:::::::::::::OOB::::::BBBBBB:::::B      S:::::SSSSSS::::::S          A:::::A        Y:::::Y       Y:::::S:::::SSSSSS::::::S     B::::::BBBBBB:::::BY:::::Y       Y:::::E::::::::::::::::::::E\n" +
                "BB:::::B     B:::::O:::::::OOO:::::::BB:::::B     B:::::B     S:::::S     SSSSSSS         A:::::::A       Y::::::Y     Y::::::S:::::S     SSSSSSS     BB:::::B     B:::::Y::::::Y     Y::::::EE::::::EEEEEEEEE::::E\n" +
                "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B     S:::::S                    A:::::::::A      YYY:::::Y   Y:::::YYS:::::S                   B::::B     B:::::YYY:::::Y   Y:::::YYY E:::::E       EEEEEE\n" +
                "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B     S:::::S                   A:::::A:::::A        Y:::::Y Y:::::Y  S:::::S                   B::::B     B:::::B  Y:::::Y Y:::::Y    E:::::E             \n" +
                "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B       S::::SSSS               A:::::A A:::::A        Y:::::Y:::::Y    S::::SSSS                B::::BBBBBB:::::B    Y:::::Y:::::Y     E::::::EEEEEEEEEE   \n" +
                "  B:::::::::::::BB O:::::O     O:::::O B:::::::::::::BB         SS::::::SSSSS         A:::::A   A:::::A        Y:::::::::Y      SS::::::SSSSS           B:::::::::::::BB      Y:::::::::Y      E:::::::::::::::E   \n" +
                "  B::::BBBBBB:::::BO:::::O     O:::::O B::::BBBBBB:::::B          SSS::::::::SS      A:::::A     A:::::A        Y:::::::Y         SSS::::::::SS         B::::BBBBBB:::::B      Y:::::::Y       E:::::::::::::::E   \n" +
                "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B            SSSSSS::::S    A:::::AAAAAAAAA:::::A        Y:::::Y             SSSSSS::::S        B::::B     B:::::B      Y:::::Y        E::::::EEEEEEEEEE   \n" +
                "  B::::B     B:::::O:::::O     O:::::O B::::B     B:::::B                 S:::::S  A:::::::::::::::::::::A       Y:::::Y                  S:::::S       B::::B     B:::::B      Y:::::Y        E:::::E             \n" +
                "  B::::B     B:::::O::::::O   O::::::O B::::B     B:::::B                 S:::::S A:::::AAAAAAAAAAAAA:::::A      Y:::::Y                  S:::::S       B::::B     B:::::B      Y:::::Y        E:::::E       EEEEEE\n" +
                "BB:::::BBBBBB::::::O:::::::OOO:::::::BB:::::BBBBBB::::::B     SSSSSSS     S:::::SA:::::A             A:::::A     Y:::::Y      SSSSSSS     S:::::S     BB:::::BBBBBB::::::B      Y:::::Y      EE::::::EEEEEEEE:::::E\n" +
                "B:::::::::::::::::B OO:::::::::::::OOB:::::::::::::::::B      S::::::SSSSSS:::::A:::::A               A:::::A YYYY:::::YYYY   S::::::SSSSSS:::::S     B:::::::::::::::::B    YYYY:::::YYYY   E::::::::::::::::::::E\n" +
                "B::::::::::::::::B    OO:::::::::OO  B::::::::::::::::B       S:::::::::::::::SA:::::A                 A:::::AY:::::::::::Y   S:::::::::::::::SS      B::::::::::::::::B     Y:::::::::::Y   E::::::::::::::::::::E\n" +
                "BBBBBBBBBBBBBBBBB       OOOOOOOOO    BBBBBBBBBBBBBBBBB         SSSSSSSSSSSSSSSAAAAAAA                   AAAAAAYYYYYYYYYYYYY    SSSSSSSSSSSSSSS        BBBBBBBBBBBBBBBBB      YYYYYYYYYYYYY   EEEEEEEEEEEEEEEEEEEEEE";
        System.out.println(bye_art);
    }

    public static void query() {
        System.out.println("What do you want from BOB?");
    }

    public static void listOut() {
        for (int i = 0; i < listCount; ++i) {
            int j = i + 1;
            System.out.println(j + ". " + list[i]);
        }
    }

    public static void command() {
        Scanner in = new Scanner(System.in);
        String cmd = in.nextLine();
        while (true) {
            if (cmd.equals("bye")) {
                return;
            }
            if (cmd.equals("list")) {
                listOut();
            } else {
                System.out.println("added: " + cmd);
                list[listCount] = cmd;
                ++listCount;
            }
            cmd = in.nextLine();
        }
    }

    public static void main(String[] args) {
        hello();
        query();
        command();
        shutdown();
    }
}
