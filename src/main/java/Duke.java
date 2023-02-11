import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;

import java.io.FileWriter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;

public class Duke {

    public static final String MESSAGE_DATA_LOADED_ON_STARTUP = "Hai, Ningensama-tachi! Loading your previous data...";
    public static final String WELCOME_BACK_MESSAGE = "Welcome back, Ningensama! Kon-Nakiri!";
    public static final String GREETING_MESSAGE_NEW_USER = "Hai, Ningensama-tachi! I see you're a new user, Kon-Nakiri!";
    public static final String GOODBYE_MESSAGE = "Otsu-Nakiri!";
    public static final String DATA_PATH = "data.txt";
    //Instructions Strings
    public static final String ACTION_LIST = "list";
    public static final String ACTION_MARK_COMPLETE = "mark";
    public static final String ACTION_MARK_INCOMPLETE = "unmark";
    public static final String ACTION_NEW_TODO = "todo";
    public static final String ACTION_NEW_DEADLINE = "deadline";
    public static final String ACTION_NEW_EVENT = "event";
    public static final String ACTION_GOODBYE = "bye";
    //All the messages Strings
    public static final String COMPLETED_TASK_MESSAGE = "Nice! I've marked this task as done!";
    public static final String INCOMPLETE_TASK_MESSAGE = "Why are you being lazy? >:(";
    public static final String ADDED_TASK_MESSAGE = "Done! Added: ";
    //Error Strings
    public static final String ERROR_UNKNOWN_INSTRUCTION = "What are you talking about?";
    public static final String ERROR_NON_INTEGER_INDEX = "Invalid Input. Give Just an Integer!";
    public static final String ERROR_NON_EXISTENT_TASK = "That task doesn't exist...";
    public static final String ERROR_NO_INDEX_PROVIDED = "Do you want me to mark the whole list?";
    public static final String ERROR_MISSING_OR_EMPTY_FIELDS = "1 or more Missing/Empty Fields found!";
    public static final String ERROR_MISSING_TASK = "Why is your task empty?";
    public static final String ERROR_BAD_FORMATTING = "That's some terrible formatting.";
    public static final String ERROR_TOO_MUCH_TASKS = "Your free trial has concluded. " +
            "To unlock more tasks, please purchase the DLC for $9.99/mth";
    public static final String ERROR_WITH_DATA_FILE = "An error occurred with the data file...\n";
    public static final String ERROR_LOADING_FILE = "Why did you edit the data file?";
    public static final String GOODBYE_WITH_NOTHING_DONE = "" +
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
            "⠀⣼⡿⣻⣾⣿⡿⢛⣥⣾⣿⣿⣿⣿⣿⠿⣋⣴⡙⣿⣿⣿⣿⣷⣍⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠀⠀⠀⠀⠀⠘⠁⠀⣤⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡀⠀⠀⠀⠀⠰⠁⠀⠹⣿⣿⣿⣿⣿⣧⣽⣿⢸⠀\n" +
            "You must think you're funny, Ningen";

    public static String[] processInputMessage(Scanner in) {
        String input = in.nextLine();
        return input.split(" ", 2);
    }

    public static File initialiseData(ArrayList<Task> todoList) {
        File data = new File(DATA_PATH);
        try {
            if (data.createNewFile()) {
                System.out.println(GREETING_MESSAGE_NEW_USER);
            } else {
                System.out.println(MESSAGE_DATA_LOADED_ON_STARTUP);
                Scanner in = new Scanner(data);
                boolean brokenFile = false;
                while (in.hasNextLine()) {
                    String[] inputMessage = processInputMessage(in);
                    switch (inputMessage[0]) {
                    case ACTION_MARK_COMPLETE:
                        int taskIndex = checkActionInputValidity(inputMessage, todoList.size());
                        Task currentTodo = todoList.get(taskIndex);
                        currentTodo.setComplete();
                        break;
                    case ACTION_MARK_INCOMPLETE:
                        taskIndex = checkActionInputValidity(inputMessage, todoList.size());
                        currentTodo = todoList.get(taskIndex);
                        currentTodo.setComplete();
                        break;
                    case ACTION_NEW_TODO:
                        String task = processToDoMessage(inputMessage);
                        Todo newTodo = new Todo(task);
                        todoList.add(newTodo);
                        break;
                    case ACTION_NEW_DEADLINE:
                        inputMessage = processDeadlineMessage(inputMessage);
                        Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
                        todoList.add(newDeadline);
                        break;
                    case ACTION_NEW_EVENT:
                        inputMessage = processEventMessage(inputMessage);
                        Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
                        todoList.add(newEvent);
                        break;
                    default:
                        brokenFile = true;
                    }
                    if (brokenFile) {
                        System.out.println(ERROR_LOADING_FILE);
                        //TODO: introduce clear feature to wipe everything, to force-use here + use as feature.
                    }
                }
                System.out.println(WELCOME_BACK_MESSAGE);
            }
        } catch (Exception e) {
            System.out.println(ERROR_WITH_DATA_FILE);
            e.printStackTrace();
        }
        return data;
    }

    public static String[] processEventMessage(String[] input) {
        String[] eventArray = new String[3];
        try {
            String[] inputArray = input[1].split(" /from ");
            if (inputArray.length != 2) {
                System.out.println(ERROR_BAD_FORMATTING);
                return new String[0];
            }
            eventArray[0] = inputArray[0];
            inputArray = inputArray[1].split(" /to ");
            if (inputArray.length != 2) {
                System.out.println(ERROR_BAD_FORMATTING);
                return new String[0];
            }
            eventArray[1] = inputArray[0];
            eventArray[2] = inputArray[1];
            for (int i = 0; i < 2; i += 1) {
                if (eventArray[i].trim().equals("")) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return eventArray;
        } catch (IndexOutOfBoundsException outOfBoundsIndex) {
            System.out.println(ERROR_MISSING_OR_EMPTY_FIELDS);
            return new String[0];
        }
    }

    public static String[] processDeadlineMessage(String[] input) {
        String[] deadlineArray;
        try {
            deadlineArray = input[1].split(" /by ");
            if (deadlineArray.length != 2) {
                System.out.println(ERROR_BAD_FORMATTING);
                return new String[0];
            }
            for (int i = 0; i < 2; i++) {
                if (deadlineArray[i].trim().equals("")) {
                    throw new IndexOutOfBoundsException();
                }
            }
            return deadlineArray;
        } catch (IndexOutOfBoundsException outOfBoundsIndex) {
            System.out.println(ERROR_MISSING_OR_EMPTY_FIELDS);
            return new String[0];
        }
    }

    public static String processToDoMessage(String[] input) {
        try {
            String task = input[1].trim();
            if (task.equals("")) {
                System.out.println(ERROR_MISSING_TASK);
            }
            return task;
        } catch (IndexOutOfBoundsException BadFormatting) {
            System.out.println(ERROR_MISSING_TASK);
            return "";
        }
    }

    public static int checkActionInputValidity(String[] input, int indexLimit) {
        try {
            if (input.length == 1) {
                throw new NullPointerException();
            }
            int taskIndex = Integer.parseInt(input[1]);
            if (taskIndex > indexLimit || taskIndex < 1) {
                throw new IndexOutOfBoundsException();
            }
            return taskIndex - 1;
        } catch (NumberFormatException nonIntegerIndex) {
            System.out.println(ERROR_NON_INTEGER_INDEX);
            return -1;
        } catch (IndexOutOfBoundsException outOfBoundsIndex) {
            System.out.println(ERROR_NON_EXISTENT_TASK);
            return -1;
        } catch (NullPointerException noIndex) {
            System.out.println(ERROR_NO_INDEX_PROVIDED);
            return -1;
        }
    }

    private static void addNewEventTask(ArrayList<Task> todoList, String[] inputMessage) {
        Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
        todoList.add(newEvent);
        System.out.println(ADDED_TASK_MESSAGE + newEvent);
    }

    private static void addNewDeadlineTask(ArrayList<Task> todoList, String[] inputMessage) {
        Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
        todoList.add(newDeadline);
        System.out.println(ADDED_TASK_MESSAGE + newDeadline);
    }

    private static void addNewTodoTask(ArrayList<Task> todoList, String task) {
        Todo newTodo = new Todo(task);
        todoList.add(newTodo);
        System.out.println(ADDED_TASK_MESSAGE + newTodo);
    }

    private static void markTaskIncomplete(ArrayList<Task> todoList, int taskIndex) {
        Task currentTodo;
        currentTodo = todoList.get(taskIndex);
        currentTodo.setIncomplete();
        System.out.println(INCOMPLETE_TASK_MESSAGE);
        System.out.println(currentTodo);
    }

    private static void markTaskComplete(ArrayList<Task> todoList, int taskIndex) {
        Task currentTodo;
        currentTodo = todoList.get(taskIndex);
        currentTodo.setComplete();
        System.out.println(COMPLETED_TASK_MESSAGE);
        System.out.println(currentTodo);
    }

    private static void listAllTasks(ArrayList<Task> todoList) {
        Task currentTodo;
        int sizeOfTodoList = todoList.size();
        for (int i = 0; i < sizeOfTodoList; i += 1) {
            currentTodo = todoList.get(i);
            String printedMessage = String.format("%d.%s", i + 1, currentTodo);
            System.out.println(printedMessage);
        }
    }


    private static void updateData(ArrayList<Task> todoList) {
        Task currentTodo;
        try {
            FileWriter writer = new FileWriter(DATA_PATH);
            int sizeOfTodoList = todoList.size();
            for (int i = 0; i < sizeOfTodoList; i += 1) {
                currentTodo = todoList.get(i);
                String type = currentTodo.getType();
                String task = currentTodo.getTask();
                String instruction;
                switch (type) {
                case ACTION_NEW_TODO:
                    instruction = String.format("%s %s\n", type, task);
                    break;
                case ACTION_NEW_DEADLINE:
                    Deadline deadline = (Deadline) currentTodo;
                    String by = deadline.getDetails();
                    instruction = String.format("%s %s /by %s\n", type, task, by);
                    break;
                case ACTION_NEW_EVENT:
                    Event event = (Event) currentTodo;
                    String[] details = event.getDetails();
                    instruction = String.format("%s %s /from %s /to %s\n", type, task, details[0], details[1]);
                    break;
                default:
                    instruction = "Something is wrong I can feel it\n";

                }
                writer.write(instruction);
                if (currentTodo.getComplete() == 'X') {
                    instruction = String.format("mark %d\n", i + 1);
                    writer.write(instruction);
                }
            }
            writer.close();
        } catch (Exception e) {
            System.out.println(ERROR_WITH_DATA_FILE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<Task> todoList = new ArrayList<Task>();
        File data = initialiseData(todoList);
        Task currentTodo;
        boolean madeAValidInstruction = false;

        Scanner in = new Scanner(System.in);
        String[] inputMessage = processInputMessage(in);
        while (!inputMessage[0].equals(ACTION_GOODBYE) && todoList.size() < 100) {
            switch (inputMessage[0]) {
            case ACTION_LIST:
                listAllTasks(todoList);
                inputMessage = processInputMessage(in);
                break;
            case ACTION_MARK_COMPLETE:
                int taskIndex = checkActionInputValidity(inputMessage, todoList.size());
                if (taskIndex >= 0) {
                    markTaskComplete(todoList, taskIndex);
                    updateData(todoList);
                    madeAValidInstruction = true;
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_MARK_INCOMPLETE:
                taskIndex = checkActionInputValidity(inputMessage, todoList.size());
                if (taskIndex >= 0) {
                    markTaskIncomplete(todoList, taskIndex);
                    updateData(todoList);
                    madeAValidInstruction = true;
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_NEW_TODO:
                String todoTask = processToDoMessage(inputMessage);
                if (!todoTask.equals("")) {
                    addNewTodoTask(todoList, todoTask);
                    updateData(todoList);
                    madeAValidInstruction = true;
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_NEW_DEADLINE:
                String[] deadlineTask = processDeadlineMessage(inputMessage);
                if (deadlineTask.length == 2) {
                    addNewDeadlineTask(todoList, deadlineTask);
                    updateData(todoList);
                    madeAValidInstruction = true;
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_NEW_EVENT:
                String[] eventTask = processEventMessage(inputMessage);
                if (eventTask.length == 3) {
                    addNewEventTask(todoList, eventTask);
                    updateData(todoList);
                    madeAValidInstruction = true;
                }
                inputMessage = processInputMessage(in);
                break;
            default:
                System.out.println(ERROR_UNKNOWN_INSTRUCTION);
                inputMessage = processInputMessage(in);
            }
        }
        if (!madeAValidInstruction) {
            System.out.println(GOODBYE_WITH_NOTHING_DONE);
        } else if (todoList.size() == 100) {
            System.out.println(ERROR_TOO_MUCH_TASKS);
        } else {
            System.out.println(GOODBYE_MESSAGE);
        }
        //CS2113T will not let students customise chatbots next sem onwards because of me
    }
}

