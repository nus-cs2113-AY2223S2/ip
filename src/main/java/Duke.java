import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void printErrorMessage() {
        System.out.println(
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⣶⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠺⣟⣶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣆⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⡈⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⢮⡻⣦⡀⠀⠀⠀⢀⣀⣀⣀⣘⡇⢸⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⣴⣿⠃⠀⠀⠀⠀⠈⠳⣄⠙⠲⣴⡟⢋⣉⣭⣭⣭⣑⠲⢧⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⡤⠶⠖⠛⠋⠉⠉⠉⣠⢃⠋⠉⠙⠒⠒⠲⠤⢤⣸⣦⣄⠈⠙⠲⢭⣻⢿⣿⣿⣷⢤⣝⢷⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡴⠞⠋⠁⠀⠀⠀⠀⡀⠀⠀⣴⠃⢸⠀⠀⠀⠀⠀⠀⢀⣀⠀⠉⠛⠷⣵⣀⠀⣸⡆⠙⣿⣿⣬⣿⣿⢻⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠟⡩⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⡑⠀⠘⠈⠁⠀⠀⠀⠀⠀⠀⠀⡉⠀⠀⢿⣿⣿⣿⣧⠀⡘⣟⠀⣼⠛⢿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡴⠋⡠⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣤⠳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠳⡄⠈⢿⣿⣿⣿⠀⠁⠘⣾⡏⠀⠀⢡⣍⣳⡄⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⠏⠀⢈⠎⠀⠀⠀⠀⠀⠀⠀⢀⠔⣡⠊⠇⠀⠀⠀⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⠢⡀⢻⣿⣿⡇⠀⠀⠸⣿⣦⣄⠈⣿⣿⣿⡆⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⠃⢀⠔⠁⠀⠀⠀⠀⠀⠀⠀⠀⢁⡼⠁⡸⠀⠀⠀⠀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢆⠀⠘⡬⣿⣿⡇⠀⠀⠀⢿⠥⠤⢾⣿⡿⣿⣷⠄⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡟⠁⡰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠏⠀⠀⡇⠀⠀⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣧⠀⢁⠸⣿⡇⠀⠀⠀⠘⠛⡿⡿⢿⣧⡽⢻⡆⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⢶⠶⢒⢛⡦⢬⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡰⢱⠀⠀⣦⡀⣠⣶⠗⠀⢇⢀⡆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡆⠸⠀⣻⠇⠀⠀⡄⢸⣷⡓⠃⠶⢶⣿⣿⡆⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡴⠁⢸⡼⠀⣼⡿⡞⠀⠀⡀⠀⠀⠀⠀⠀⠀⡼⠁⠀⢣⠀⢸⠟⠉⠀⠀⠀⢸⡞⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡀⠀⠟⠀⠀⠠⠁⢸⣿⣝⡒⣖⣯⣿⣿⡇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣾⢠⣇⠸⡙⣠⡟⢠⠇⠀⠔⠉⠀⠀⠀⠀⠀⡜⠁⠀⠀⠈⣆⣘⣟⠁⠀⠀⠀⡼⠀⠀⣸⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⢱⣇⡼⠀⠀⠀⠀⣀⡜⠿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⡇⡌⡟⣆⢳⡝⠂⢸⠤⠒⠀⢀⠒⠦⣀⠀⡜⠀⠀⠀⠀⢰⠋⡇⡌⣦⡀⢀⢰⠃⠀⢀⡏⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠈⢸⣷⣾⣿⣿⣿⣿⠀⠀⡎⢿⣿⡿⠛⠿⠃⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⡇⠃⡇⠀⣗⠻⡶⠁⡀⢐⠩⠀⠀⠂⠉⡶⠁⡰⠀⠀⢀⠄⠀⡇⠘⡏⠍⠊⢸⠀⠀⣸⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣷⢸⣾⣿⣿⣿⡟⠁⠀⢂⢱⠸⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⡝⠄⠱⣸⡎⢀⡧⢪⠞⠁⠀⠀⠀⠱⢰⠁⣰⠇⠀⠰⠁⡠⠁⡇⢸⠀⠀⠀⢸⠀⢀⠇⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣿⢸⠙⠛⠟⡇⠀⠀⠀⠈⡌⡼⢌⢷⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢻⡄⢀⡿⡄⠎⣷⠙⢄⢆⠀⠀⠀⣠⠇⣰⠟⠀⠀⠀⠰⠁⣸⠻⢸⠒⠢⢤⣼⠀⡘⠀⢣⠀⠘⡀⠀⠀⠀⠀⠀⢰⢸⢹⡇⠀⢠⠀⠃⠀⠀⢠⠀⢡⠇⢸⣿⡆⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⣿⢠⢤⣼⠗⠁⠑⣤⡍⠊⠤⠤⡏⡔⣽⠀⠀⠀⠀⠇⡰⠃⠀⢻⡄⠀⠀⠈⡄⡿⠂⢌⣆⠀⣧⠀⢀⠀⠀⠀⢸⣼⢸⠇⡀⢸⠀⢸⠀⠀⠀⠀⠸⠸⢞⣹⣿⡀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⠃⡟⡞⡈⠅⠒⣾⣿⡗⠚⠁⢀⡞⡼⠇⢘⡀⠀⢰⡰⠁⠀⠨⠤⠕⣀⡀⢸⠐⣇⠑⢄⠙⠓⠏⠢⡈⡆⠀⠀⣾⡟⡹⠉⡟⢙⡇⢸⠀⠀⠀⠀⡄⡇⠈⡏⢸⠇⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠏⠀⢰⠱⢰⠀⣼⣿⣽⣧⢀⣇⠸⢰⠃⡇⣸⠘⠄⢸⣁⠀⠀⠀⠀⠀⠀⠈⠑⡆⠈⠢⠈⠀⠁⠒⠠⡈⠚⡶⠄⡸⠁⠃⡰⣡⣟⢱⡾⠀⠀⠀⠀⡇⡇⣈⠇⣼⠃⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⢰⡏⠀⡸⡺⡟⣿⣿⣿⡼⢹⠀⡏⠀⢰⢷⣆⠀⢸⡟⠛⠿⢷⣶⣶⣦⣤⣤⡄⠀⠀⠀⠀⠀⠀⠀⠙⠈⠀⡖⠂⠐⠂⢱⠇⠤⢠⡇⢀⢠⢰⠀⠁⣇⣝⣴⠇⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣼⠁⡀⢸⠁⠀⢱⢹⣿⣧⣿⢿⢳⠀⢠⠘⡄⠀⢸⡏⠦⡀⢯⠀⢸⣿⢿⣿⢿⣿⡏⠉⠂⠀⠀⠀⠀⠀⠀⠀⠀⢈⣠⣤⣀⣀⣁⣀⡀⢀⠃⡌⠘⠀⠀⢠⣿⡟⠁⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠇⠀⠀⠇⠀⢀⠇⢸⣿⣿⢻⠘⣷⣷⡄⠀⢰⢠⡈⡃⢀⠈⠙⠓⠚⠿⠦⠾⠽⠊⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢛⣻⣿⣿⣿⠟⣿⠾⡸⠀⠀⢰⠀⢸⣿⣷⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡿⢀⠀⠀⡀⠀⣼⡴⠈⠛⠛⠦⠤⡼⣼⠃⠀⠈⡇⠀⢸⠀⠑⠄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠸⣟⣿⢽⣻⠀⢡⡷⠁⠃⠀⠀⠀⢸⣻⢻⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠇⡎⠀⠀⡇⣴⡏⠀⠀⠀⠀⠀⢰⢠⡿⠀⠀⠀⢷⠀⢸⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠋⠁⠀⡞⢡⢰⠀⠀⠀⠀⢸⡙⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢰⡅⠀⠀⣧⠋⠀⠀⠀⠀⠀⠀⠘⣜⡇⠀⠀⠰⢸⢁⢀⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⣴⢣⠃⠀⠀⠀⠀⡎⣿⡍⣧⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⢸⡇⠀⠀⡇⠀⠀⠀⠀⠀⠀⠀⢴⢹⡇⣲⠀⠀⠃⢸⡀⢇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠴⠛⡿⣷⠃⠀⢰⠀⡇⠀⡇⢿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡌⢱⠀⠀⠇⠀⠀⠀⠀⠀⡀⠀⡇⢸⢇⣿⣧⠀⠀⠇⢣⠘⡄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠒⠒⠒⠒⠂⠄⠀⠀⠀⠀⠀⠀⠀⢀⡟⡽⠙⠀⠀⣾⠀⡇⢀⠓⢻⠉⡿⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠿⢳⢟⡆⠄⠀⠀⠀⠀⠀⣠⠃⢰⠁⡎⢸⡏⠻⣣⡀⠘⡈⠑⢜⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡠⡎⠈⠀⠇⢀⠴⠿⠤⠧⠼⠠⠼⠀⠳⠤⠤⠤⢤⣄⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣴⢏⣴⣿⡏⠘⡄⢠⠀⠀⠀⡴⣿⠀⡼⡆⢰⠈⣇⠀⠙⢕⢄⠘⡆⠘⡝⠢⢑⠢⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡠⠒⠉⢠⠁⠂⠄⢀⢃⡎⠀⠀⠈⠉⠉⠉⠉⠉⠉⠉⠉⠁⡿⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡾⣣⡾⣿⠟⣧⠀⠈⡄⡆⠀⡞⢰⣿⠀⠃⢱⠸⡄⡁⠑⣼⢠⠹⣷⣜⢆⢳⠀⠀⠀⠀⠈⠒⠤⣀⠀⠀⠀⠀⠀⢀⡠⠔⠈⠀⠀⠀⠀⡎⠀⣸⢀⠇⡜⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠁⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⢋⣾⠋⣰⠏⠀⠘⣆⢰⠉⢿⡞⠀⣾⣿⠀⠀⣤⠀⣿⡇⢠⠈⡗⢵⠀⠛⠣⣙⢄⡀⠀⠀⠀⠀⠀⠀⠉⠒⠤⡖⠊⠁⠀⠀⠀⠀⠀⠀⡰⢠⢀⡇⡞⡰⠀⠀⣰⣶⣶⣶⣶⣶⣶⣶⣾⣿⣷⠇⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⢞⡵⠟⠁⣰⠏⠀⠀⠀⠈⡟⠀⡼⠛⢠⡏⢹⠀⠀⢿⡄⡇⠱⠀⢆⢣⠀⢠⠀⠀⠀⡏⠉⠂⠀⠀⠀⠀⠀⠀⠀⢸⡀⠀⠀⠀⠀⡆⠀⠀⢀⢣⠃⡼⣰⢁⠃⠀⠀⠿⠿⠟⠛⠛⠛⠛⠛⠛⢻⡏⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡼⢫⡞⠁⠀⣰⠏⠀⠀⠀⠀⣸⡇⢠⡇⠀⢸⡇⢸⡀⠀⢸⡇⡇⠀⠀⠈⠘⡇⢸⣆⣀⣤⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⣇⠀⠀⠀⢠⡇⠀⠀⠘⠀⡰⢡⠃⡈⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡟⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⢋⡴⠋⠀⠀⣰⠏⠀⠀⠀⠀⢠⡏⢧⢀⣄⠠⠾⠓⠚⠣⠤⠼⣿⡇⠀⠀⠀⠀⢻⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⣸⣿⡀⠀⠀⣸⡇⠀⠀⠂⢠⢗⡝⡰⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡾⠁⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⠀⠀⠀⠀⠀⣠⠞⣡⠞⠁⠀⠀⢠⠟⠀⠀⠀⠀⢀⡞⢀⡼⠊⠁⠀⠀⠀⠀⠀⠀⠀⠀⢧⠀⠀⠀⠀⢜⣿⣿⣿⡇⠏⠀⠀⠀⠀⠀⠀⠀⠀⠀⢹⣿⣿⣷⣦⣿⡇⠀⠀⡄⣆⡸⠤⠧⠀⠀⠀⠀⢀⣀⠀⠀⠀⠀⠀⣰⠃⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠀⠀⣀⡴⠚⠋⡻⠋⡴⢯⣀⠀⠀⢰⠏⠀⠀⠀⠀⠄⡞⡠⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠀⠀⢻⣿⣿⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⡗⠤⣀⣙⣿⢓⢤⣀⠀⠀⠀⠀⠀⠀⠑⢄⠀⠀⢠⡏⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⢀⠾⠃⠀⣠⠎⢀⠞⠀⠀⠙⣿⣿⠏⠀⠀⠀⢀⠎⡜⡰⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠆⠀⠀⠀⠀⠘⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣿⣿⣿⣿⣿⣧⡀⠄⢊⠃⡜⠀⢀⢉⣑⣒⡒⠒⣄⠀⠈⠳⣀⡾⠀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⠃⠀⢀⠜⠁⡰⠁⠀⠀⠀⢠⣿⠏⠀⠀⠀⢠⠊⣰⠙⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠠⡈⡠⡀⠀⠀⢻⣿⣿⣿⣆⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣿⠿⠛⠉⢀⠸⡁⠀⠁⠀⠀⠀⠀⠀⠈⠑⠾⣗⢄⡀⠙⢷⡀⠀⠀⠀⠀⠀⠀⠀⠀\n" +
                "⣄⣠⠂⣠⠊⠀⠀⢀⣀⣤⣿⢯⠀⠀⠀⢠⠃⢠⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣷⡀⢈⠀⠑⣼⣿⣿⣿⣿⡄⠀⠀⠀⠀⠀⠀⢀⠼⠛⠉⣀⠠⢐⣨⣴⢺⡹⣉⣐⣒⣒⠒⠒⢦⣄⡀⠀⠈⠓⢌⡒⢄⠹⣦⡀⠀⠀⠀⠀⠀⠀\n" +
                "⡜⠁⣴⡥⠶⠛⠋⠉⠙⣿⠃⠃⠀⠀⢀⠃⢠⣿⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠀⠀⠀⠀⠀⢴⣿⣷⡀⢢⠐⢌⠛⢿⣿⣿⣿⡄⠀⠀⠀⢀⠔⠁⠀⣀⣀⠀⠉⠻⢿⣿⠏⣦⡀⠀⠀⠀⠀⠉⠒⢽⡪⣢⢀⠀⠀⠉⠚⠀⡆⠙⣦⡀⠀⠀⠀⠀\n" +
                "⢀⣼⣿⠁⠀⠀⠀⠀⣼⠃⣶⣶⣶⣤⣦⣄⣞⣆⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⠎⠀⠀⠀⢀⢊⣿⣿⣿⣷⡀⡆⠀⢈⣷⣾⣿⣿⣿⡄⠀⡰⠁⠀⠀⠀⠉⠒⠤⡉⠀⠐⢹⡸⣇⠙⠓⠢⢤⣤⢀⡀⠀⠈⠙⠿⣷⠀⠀⠀⠀⡇⠀⠸⣿⢷⣤⣦⣤\n" +
                "⣾⠟⠁⠀⠀⠀⠀⣼⢃⣀⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣄⣀⠀⠀⠀⢀⠎⠀⠀⠀⠀⢠⣾⣿⣿⣿⣿⣧⣿⠀⣸⣿⣿⣿⣿⣿⣿⠞⠀⠀⠀⠈⠪⡁⠐⠒⢘⡄⠀⣧⢃⣈⣢⣄⠀⠀⠈⠑⠪⣶⣤⣄⡀⠀⠀⠀⠀⢠⣅⠀⠀⣿⣆⠙⢿⣿\n" +
                "⠁⠀⠀⠀⠀⠀⣼⠃⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣶⣤⣄⡀⠀⠀⢠⣾⣿⣿⣿⣿⣿⣿⣿⣧⣿⣿⣿⣿⣿⣿⠏⠀⠀⢀⡤⠄⣀⣈⡆⠀⢸⢀⡼⠀⠀⢀⣴⡙⡏⠢⣄⡀⠀⠀⠉⠓⠞⠀⠀⠀⢀⡟⠉⠻⣶⣿⣿⡆⠈⢿\n" +
                "⠀⠀⠀⠀⠀⣼⢃⢠⣿⣿⣿⣿⣿⣿⣿⣿⠿⣟⣿⣿⣿⣿⠿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠏⠀⠀⢠⠧⣟⠳⠂⣠⠇⠒⠉⠉⠀⢀⣴⣿⣿⣷⡆⣴⠀⢉⣗⡠⠀⠀⠀⠀⠀⣰⡏⠀⠀⢀⣙⣿⣿⣿⡀⠈\n" +
                "⠀⠀⠀⠀⣼⠃⢌⣼⣿⣿⣿⣿⠿⣛⣭⣶⣿⡿⠟⢋⣭⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡟⠀⠀⠀⠈⡄⠈⠉⠙⠁⠀⠀⠀⠀⣠⣾⣿⣿⣿⣿⣎⢸⢔⠝⠉⠀⠀⠀⠀⠀⠰⣙⣷⣶⣿⣿⣿⣿⣿⣿⣷⣀\n" +
                "⠀⠀⠀⣼⣃⣴⣿⡿⠟⣋⣥⣶⣿⡿⠟⣋⣥⣶⣿⣷⣝⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠁⠀⠀⠀⠀⠃⡜⠀⠀⠀⠀⠀⣠⣶⣿⣿⣿⣿⣿⣿⣿⡦⠂⠀⠀⠀⠀⠀⠀⣠⣿⣿⣿⣿⣿⣿⣿⣿⣼⣿⣿⡟\n" +
                "⠀⠀⣼⣿⡿⣛⣭⣶⣿⡿⢟⣫⣵⣶⣿⣿⣿⣿⣿⣿⣿⣷⣌⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⡰⠁⠀⠀⢀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⡰⠙⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀\n" +
                "⠀⣼⡿⣻⣾⣿⡿⢛⣥⣾⣿⣿⣿⣿⣿⠿⣋⣴⡙⣿⣿⣿⣿⣷⣍⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠘⠁⠀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠰⠁⠀⠹⣿⣿⣿⣿⣿⣧⣽⣿⢸⠀");
    }

    public static String[] processInputMessage(Scanner in) {
        String input = in.nextLine();
        String[] newInput = input.split(" ", 2);
        return newInput;
    }

    public static String[] processEventMessage(String input) {
        String[] eventArray = new String[3];
        String[] inputArray = input.split(" /from ", 2);
        if(inputArray.length == 1) {
            eventArray[0] = "";
            return eventArray;
        }
        eventArray[0] = inputArray[0];
        inputArray = inputArray[1].split(" /to ", 2);
        if(inputArray.length == 1) {
            eventArray[0] = "";
            return eventArray;
        }
        eventArray[1] = inputArray[0];
        eventArray[2] = inputArray[1];
        return eventArray;
    }

    public static String[] processDeadlineMessage(String input) {
        String[] deadlineArray = input.split(" /by ", 2);
        if(deadlineArray .length == 1) {
            deadlineArray [0] = "";
        }
        return deadlineArray ;
    }

    public static void main(String[] args) {
        System.out.println("Hai, Ningensama-tachi! Kon-Nakiri!\n");

        ArrayList<Task> todoList = new ArrayList<Task>();
        int sizeOfTodoList = 0;
        Task currentTask;

        Scanner in = new Scanner(System.in);
        String[] inputMessage = processInputMessage(in);
        while(!inputMessage[0].equals("bye")) {
            switch (inputMessage[0]) {
            case "list":
                for(int i = 0; i < sizeOfTodoList; i += 1) {
                    currentTask = todoList.get(i);
                    String printedMessage = String.format("%d.%s", i+1, currentTask);
                    System.out.println(printedMessage);
                }
                inputMessage = processInputMessage(in);
                break;
            case "mark":
                int taskIndex = Integer.parseInt(inputMessage[1]);
                if(taskIndex > sizeOfTodoList) {
                    printErrorMessage();
                } else {
                    currentTask = todoList.get(taskIndex - 1);
                    currentTask.setComplete();
                    System.out.println("Nice! I've marked this task as done!");
                    System.out.println(currentTask);
                }
                inputMessage = processInputMessage(in);
                break;
            case "unmark":
                taskIndex = Integer.parseInt(inputMessage[1]);
                if (taskIndex > sizeOfTodoList) {
                    printErrorMessage();
                } else {
                    currentTask = todoList.get(taskIndex - 1);
                    currentTask.setIncomplete();
                    System.out.println("Why are you being lazy? >:(");
                    System.out.println(currentTask);
                }
                inputMessage = processInputMessage(in);
                break;
            case "todo":
                Task newTask = new Task(inputMessage[1]);
                todoList.add(newTask);
                sizeOfTodoList += 1;
                System.out.println("Done! Added: " + newTask);
                inputMessage = processInputMessage(in);
                break;
            case "deadline":
                inputMessage = processDeadlineMessage(inputMessage[1]);
                if (inputMessage[0].equals("")){
                    printErrorMessage();
                } else {
                    sizeOfTodoList += 1;
                    Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
                    todoList.add(newDeadline);
                    System.out.println("Done! Added: " + newDeadline);
                }
                inputMessage = processInputMessage(in);
                break;
            case "event":
                inputMessage = processEventMessage(inputMessage[1]);
                if (inputMessage[0].equals("")){
                    printErrorMessage();
                } else {
                    sizeOfTodoList += 1;
                    Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
                    todoList.add(newEvent);
                    System.out.println("Done! Added: " + newEvent);
                }
                inputMessage = processInputMessage(in);
                break;
            default:
                printErrorMessage();
                inputMessage = processInputMessage(in);
            }
        }
        System.out.println("Otsu-Nakiri!");
        //CS2113T will not let students customise chatbots next sem onwards because of me
    }
}
