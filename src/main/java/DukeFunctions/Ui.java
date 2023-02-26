package DukeFunctions;

import Exceptions.DukeError;

public class Ui {

    public void welcome() {
        System.out.println("おかえり～　ご飯にする？お風呂にする？それとも。。。　わ・た・し？ (start)");

    }

    public void bye() {
        System.out.println("じゃねえ～ (bye)");
        ;

    }

    public void listEmpty() {
        System.out.println("何もいない。。。頭のように。。。(list is empty)");

    }

    public void doesNotExist() {
        System.out.println("バカにさせないで。その目標は実在しません。");

    }

    void badCommand() {
        System.out.println("i dont recognize that command");

    }

    public void taskDoesNotexist() {
        System.out.println("バカにさせないで。その目標は実在しません。(that task does not exist)");

    }

    public void clearAck() {
        System.out.println("List cleared");

    }

    public void acknowledge(Todo newTask) {
        switch (newTask.getType()) {
            case "T":
                System.out.println("覚えましたよ～ (todo recorded) " + newTask.toString());
                break;
            case "D":
                System.out.println("覚えましたよ～ (deadline recorded) " + newTask.toString());
                break;
            case "E":
                System.out.println("覚えましたよ～ (event recorded) " + newTask.toString());
                break;


        }

    }

    public void showError(DukeError e) {

        System.out.println("error has occured: " + e.problem);
    }

    public void ackDelete(Todo task) {

        System.out.println("deleted this task: " + task.toString());
    }

    public void markAck(Todo task) {
        System.out.println("目標達成！- [" + task.getIsDone() + "] " + task.toString());

    }

    public void unmarkAck(Todo task) {
        System.out.println("ええ。。。噓つき。- [" + task.getIsDone() + "] " + task.toString());

    }

    public void listTasks(TaskList tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". [" + tasks.get(i).getType() + "]" + "[" + tasks.get(i).getIsDone() + "] " + tasks.get(i).toString());
        }
    }

    public void printSearchResults(TaskList searchResults) {

        System.out.println("Here are the matching tasks: ");
        listTasks(searchResults);
    }


}
