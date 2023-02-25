package luke.icon;

import java.util.Random;

/**
 * A <code>LukeLogo</code> object is use to indicate generate and display a logo whenever Luke runs.
 */
public class LukeLogo {
    /** List of logos corresponding to the ASCII art of "LUKE" */
    private final String[] logos = {
            //Logo 1
            " __         __  __     __  __     ______\n"
                    + "/\\ \\       /\\ \\/\\ \\   /\\ \\/ /    /\\  ___\\\n"
                    + "\\ \\ \\____  \\ \\ \\_\\ \\  \\ \\  _\"-.  \\ \\  __\\\n"
                    + " \\ \\_____\\  \\ \\_____\\  \\ \\_\\ \\_\\  \\ \\_____\\\n"
                    + "  \\/_____/   \\/_____/   \\/_/\\/_/   \\/_____/\n",
            //Logo 2
            " _     _   _ _   __ _____ \n"
                    + "| |   | | | | | / /|  ___|\n"
                    + "| |   | | | | |/ / | |__  \n"
                    + "| |   | | | |    \\ |  __|\n"
                    + "| |___| |_| | |\\  \\| |___\n"
                    + "\\_____/\\___/\\_| \\_/\\____/\n",
            //Logo 3
            "    __    __  ____ __ ______\n"
                    + "   / /   / / / / //_// ____/\n"
                    + "  / /   / / / / ,<  / __/   \n"
                    + " / /___/ /_/ / /| |/ /___   \n"
                    + "/_____/\\____/_/ |_/_____/   \n",
            //Logo 4
            " __       __  __  __  __   ____      \n"
                    + "/\\ \\     /\\ \\/\\ \\/\\ \\/\\ \\ /\\  _`\\    \n"
                    + "\\ \\ \\    \\ \\ \\ \\ \\ \\ \\/'/'\\ \\ \\L\\_\\  \n"
                    + " \\ \\ \\  __\\ \\ \\ \\ \\ \\ , <  \\ \\  _\\L  \n"
                    + "  \\ \\ \\L\\ \\\\ \\ \\_\\ \\ \\ \\\\`\\ \\ \\ \\L\\ \\\n"
                    + "   \\ \\____/ \\ \\_____\\ \\_\\ \\_\\\\ \\____/\n"
                    + "    \\/___/   \\/_____/\\/_/\\/_/ \\/___/ \n"
    };

    /**
     * Returns a randomized string corresponding to the name of the chatbot.
     *
     * @return String corresponding to an ASCII art of the string "LUKE".
     */
    public String getRandLogo() {
        Random generateLogo = new Random();
        return logos[generateLogo.nextInt(logos.length)];
    }

    /**
     * Returns a string representing an ASCII art of the string "LUKE".
     * The string returned corresponds to index.
     *
     * @param index The 0-based index of the logo to be returned.
     * @return String corresponding to an ASCII art of the string "LUKE".
     */
    public String getLogo(int index) {
        if (index < 0 || index >= logos.length) {
            return logos[0];
        }
        return logos[index];
    }
}
