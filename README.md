# Onandon project template

This is the Onandon chatbot project repo for the AY2223S2-CS2113, NUS.

## 🧐 How to use chatbot?

If you run the chatbot, you will see the greeting message which means that
chatbot program is properly executed.
   ```
      ____________________________________________________________
       Hello! I'm onandon.
       What can I do for you?
      ____________________________________________________________
   ```

There are 5 types of command you can give to this chatbot.
1. `todo` : You can register todo list by typing `todo {description of the todo}` and
   press the enter key. Then, you will see the resulting message like below.
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
