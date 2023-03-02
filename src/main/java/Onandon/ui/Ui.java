package Onandon.ui;

import Onandon.exception.OnandonEmptyException;
import Onandon.exception.OnandonException;
import Onandon.exception.OnandonNotaskException;
import Onandon.exception.OnandonUnknownException;

import java.util.Scanner;

public class Ui {
    public static final String underline = "\t____________________________________________________________";
    public static final String todo = "\t Got it. I've added this task:";

    public static final String mark = "\t Nice! I've marked this task as done:";
    public static final String unmark = "\t OK, I've marked this task as not done yet:";
    public static final String list = "\t Here are the tasks in your list:";
    public static final String delete = "\t Noted. I've removed this task:";


    public static void printUnderline(){
        System.out.println(underline);
    }
    public static void printFormAbove(){
        printUnderline();
        System.out.println(todo);
    }
    public static void printCnt(int cnt){
        System.out.println("\t Now you have " + cnt + " tasks in the list.");
        printUnderline();
    }
    public static void printList(){
        printUnderline();
        System.out.println(list);
    }
    public static void printMark(){
        printUnderline();
        System.out.println(mark);
    }
    public static void printUnmark(){
        printUnderline();
        System.out.println(unmark);
    }
    public static void printDelete(){
        printUnderline();
        System.out.println(delete);
    }
    public static void printGreet(){
        printUnderline();
        System.out.println("\t Hello! I'm onandon");
        System.out.println("\t What can I do for you?");
        printUnderline();
    }
    public static void printBye(){
        printUnderline();
        System.out.println("\t Bye. Hope to see you again soon!");
        printUnderline();
    }
    public static void printException(OnandonException e){
        if(e instanceof OnandonUnknownException){
            printUnderline();
            System.out.println("\t ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            printUnderline();
        }else if(e instanceof OnandonEmptyException){
            printUnderline();
            System.out.println("\t ☹ OOPS!!! The input cannot be empty.");
            printUnderline();
        }else if(e instanceof OnandonNotaskException){
            printUnderline();
            System.out.println("\t ☹ OOPS!!! There is no task on input!");
            printUnderline();
        }
    }
    public static String readCommand(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }
}
