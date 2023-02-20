package ui;

public class UI {
    //Goodbye Strings
    public static final String ERROR_TOO_MUCH_TASKS = "Your free trial has concluded. " +
            "To unlock more tasks, please purchase the DLC for $9.99/mth";
    public static final String GOODBYE_MESSAGE = "Otsu-Nakiri!";
    public static final String NAKIRI_AYAME = "" +
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
            "⠀⣼⡿⣻⣾⣿⡿⢛⣥⣾⣿⣿⣿⣿⣿⠿⣋⣴⡙⣿⣿⣿⣿⣷⣍⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠘⠁⠀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠰⠁⠀⠹⣿⣿⣿⣿⣿⣧⣽⣿⢸\n";
    public static final String GOODBYE_WITH_NOTHING_DONE = "You must think you're funny, Ningen";

    public static void printGoodbyeMessage(boolean didSomethingUseful, int sizeOfTaskList) {
        if (!didSomethingUseful) {
            System.out.println(NAKIRI_AYAME + GOODBYE_WITH_NOTHING_DONE);
        } else if (sizeOfTaskList == 100) {
            System.out.println(ERROR_TOO_MUCH_TASKS);
        } else {
            System.out.println(GOODBYE_MESSAGE);
        }
    }
}
