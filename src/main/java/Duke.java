import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.Todo;

import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static final String GREETING_MESSAGE = "Hai, Ningensama-tachi! Kon-Nakiri!";
    public static final String GOODBYE_MESSAGE = "Otsu-Nakiri!";
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
    public static final String GOODBYE_WITH_NO_TASK_MESSAGE = "" +
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
    public static final String ACTION_DELETE = "delete";


    public static String[] processInputMessage(Scanner in) {
        String input = in.nextLine();
        return input.split(" ", 2);
    }

    public static String[] processEventMessage(String[] input) {
        String[] eventArray = new String[3];
        try {
            String[] inputArray = input[1].split(" /from ");
            if (inputArray.length > 2) {
                System.out.println(ERROR_BAD_FORMATTING);
                return new String[0];
            }
            eventArray[0] = inputArray[0];
            inputArray = inputArray[1].split(" /to ");
            if (inputArray.length > 2) {
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
            if (deadlineArray.length > 2) {
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

    private static void printRemovedMessage(int sizeOfTodoList, Task currentTodo) {
        System.out.println("Task removed:");
        System.out.println(currentTodo);
        System.out.println("You have " + sizeOfTodoList + " tasks left");
    }

    public static void main(String[] args) {
        System.out.println(GREETING_MESSAGE);

        ArrayList<Task> todoList = new ArrayList<Task>();
        int sizeOfTodoList = 0;
        Task currentTodo;

        Scanner in = new Scanner(System.in);
        String[] inputMessage = processInputMessage(in);
        while (!inputMessage[0].equals(ACTION_GOODBYE) && sizeOfTodoList < 100) {
            switch (inputMessage[0]) {
            case ACTION_LIST:
                for (int i = 0; i < sizeOfTodoList; i += 1) {
                    currentTodo = todoList.get(i);
                    String printedMessage = String.format("%d.%s", i + 1, currentTodo);
                    System.out.println(printedMessage);
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_DELETE:
                int taskIndex = checkActionInputValidity(inputMessage, sizeOfTodoList);
                if(taskIndex >= 0) {
                    currentTodo = todoList.get(taskIndex);
                    todoList.remove(taskIndex);
                    sizeOfTodoList -= 1;
                    printRemovedMessage(sizeOfTodoList, currentTodo);
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_MARK_COMPLETE:
                taskIndex = checkActionInputValidity(inputMessage, sizeOfTodoList);
                if (taskIndex >= 0) {
                    currentTodo = todoList.get(taskIndex);
                    currentTodo.setComplete();
                    System.out.println(COMPLETED_TASK_MESSAGE);
                    System.out.println(currentTodo);
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_MARK_INCOMPLETE:
                taskIndex = checkActionInputValidity(inputMessage, sizeOfTodoList);
                if (taskIndex >= 0) {
                    currentTodo = todoList.get(taskIndex);
                    currentTodo.setIncomplete();
                    System.out.println(INCOMPLETE_TASK_MESSAGE);
                    System.out.println(currentTodo);
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_NEW_TODO:
                String task = processToDoMessage(inputMessage);
                if (!task.equals("")) {
                    Todo newTodo = new Todo(task);
                    todoList.add(newTodo);
                    sizeOfTodoList += 1;
                    System.out.println(ADDED_TASK_MESSAGE + newTodo);
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_NEW_DEADLINE:
                inputMessage = processDeadlineMessage(inputMessage);
                if (inputMessage.length == 2) {
                    sizeOfTodoList += 1;
                    Deadline newDeadline = new Deadline(inputMessage[0], inputMessage[1]);
                    todoList.add(newDeadline);
                    System.out.println(ADDED_TASK_MESSAGE + newDeadline);
                }
                inputMessage = processInputMessage(in);
                break;
            case ACTION_NEW_EVENT:
                inputMessage = processEventMessage(inputMessage);
                if (inputMessage.length == 3) {
                    sizeOfTodoList += 1;
                    Event newEvent = new Event(inputMessage[0], inputMessage[1], inputMessage[2]);
                    todoList.add(newEvent);
                    System.out.println(ADDED_TASK_MESSAGE + newEvent);
                }
                inputMessage = processInputMessage(in);
                break;
            default:
                System.out.println(ERROR_UNKNOWN_INSTRUCTION);
                inputMessage = processInputMessage(in);
            }
        }
        if (sizeOfTodoList == 0) {
            System.out.println(GOODBYE_WITH_NO_TASK_MESSAGE);
        } else if (sizeOfTodoList == 100) {
            System.out.println(ERROR_TOO_MUCH_TASKS);
        } else {
            System.out.println(GOODBYE_MESSAGE);
        }
        //CS2113T will not let students customise chatbots next sem onwards because of me
    }
}

