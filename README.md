# 🎧 Onandon project template

This is the Onandon chatbot project repo for the AY2223S2-CS2113, NUS.

## 🤖 Getting start with Onandon chatbot!

## 🧐 How to use chatbot?

If you run the chatbot, you will see the greeting message which means that
chatbot program is properly executed.
   ```
      ____________________________________________________________
       Hello! I'm onandon.
       What can I do for you?
      ____________________________________________________________
   ```

There are 9 types of command you can give to this chatbot.
1. `todo` : You can register todo task by typing `todo {description of the todo}`.
   You will see the resulting message like below after pres the `Enter` key.
   ```
   todo read book
      ____________________________________________________________
       Got it. I've added this task:
         [T][ ] read book
       Now you have 1 tasks in the list.
      ____________________________________________________________
   ```
   In the line of `[T][ ] read book`, `[T]` means this task is created from `todo` command,
   and `[ ]` means that this task is not done yet. If the task was already done, it is indicated like
   `[X]`.
   
2. `deadline` : You can register deadline task by typing `deadline {description of the deadline} \by {due date}`.
   You will see the resulting message like below after pres the `Enter` key.
   ```
   deadline paper submission /by 2023-03-08
      ____________________________________________________________
       Got it. I've added this task:
         [D][ ] paper submission (by: Mar 08 2023)
       Now you have 2 tasks in the list.
      ____________________________________________________________
   ```
   In the line of `[D][ ] paper submission (by: Mar 08 2023)`, `[D]` means this task is created from `deadline` command.
   Also, if you type the due date of the task, you should follow the specified format of `YYYY-MM-dd`
   just like the example above. If you don't, the program would be terminated with the error message.
   
3. `event` : You can register event task by typing `event {description of the event} \from {start date}
   \to {end date}`. You will see the resulting message like below after pres the `Enter` key.
   ```
   event take midterm /from 2023-03-01 /to 2023-03-03
      ____________________________________________________________
       Got it. I've added this task:
         [E][ ] take midterm (from: Mar 03 2023 to: Mar 01 2023)
       Now you have 3 tasks in the list.
      ____________________________________________________________
   ```
   In the line of `[E][ ] take midterm (from: Mar 03 2023 to: Mar 01 2023)`, `[E]` means this task is created from `event` command.
   Note that you should follow the specified date format `yyyy-MM-dd` when you type the date.
   
4. `list` : You can print all of the tasks with `list` command.
   ```
   list
      ____________________________________________________________
       Here are the tasks in your list:
       1. [T][ ] read book
       2. [D][ ] paper submission (by: Mar 08 2023)
       3. [E][ ] take midterm (from: Mar 03 2023 to: Mar 01 2023)
      ____________________________________________________________
   ```

5. `delete` : You can delete the task with `delete {index of the task you want to remove}` command.
   ```
   delete 1
      ____________________________________________________________
       Noted. I've removed this task:
         [T][ ] read book
       Now you have 2 tasks in the list.
      ____________________________________________________________
   ```
   
6. `mark` : You can mark the task as done with `mark {index of the task you want to remove}` command.
   ```
   mark 1
      ____________________________________________________________
       Nice! I've marked this task as done:
         [X] paper submission
      ____________________________________________________________
   ```
7. `unmark` : You can unmark the task as not done with `unmark {index of the task you want to remove}` command.
   ```
   unmark 1
      ____________________________________________________________
       OK, I've marked this task as not done yet:
         [ ] paper submission
      ____________________________________________________________
   ```
8. `find` : You can find the task with the search key with `find {search key}` command.
   ```
   find term
      ____________________________________________________________
       Here are the matching tasks in your list:
         1. [E][ ] take midterm (from: Mar 03 2023 to: Mar 01 2023)
      ____________________________________________________________
   ```
9. `exit` : If you want to exit the program, just type the `exit` command. Then, you will see the
   farewell messag after pressing the `Enter` key.
   ```
      ____________________________________________________________
       Bye. Hope to see you again soon!
      ____________________________________________________________
   ```  

## 🧑🏻‍💻 Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Onandon.java` file, right-click it, and choose `Run Onandon.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
      ____________________________________________________________
       Hello! I'm onandon.
       What can I do for you?
      ____________________________________________________________
   ```
