# Chronos project template

This is a project template for a greenfield Java project. It's named Chronos, the god of time. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/chronos/java/Chronos.java` file, right-click it, and choose `Run Chronos.chronos()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

```java

Hello from

        ░█████╗░██╗░░██╗██████╗░░█████╗░███╗░░██╗░█████╗░░██████╗
        ██╔══██╗██║░░██║██╔══██╗██╔══██╗████╗░██║██╔══██╗██╔════╝
        ██║░░╚═╝███████║██████╔╝██║░░██║██╔██╗██║██║░░██║╚█████╗░
        ██║░░██╗██╔══██║██╔══██╗██║░░██║██║╚████║██║░░██║░╚═══██╗
        ╚█████╔╝██║░░██║██║░░██║╚█████╔╝██║░╚███║╚█████╔╝██████╔╝
        ░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝░╚════╝░╚═╝░░╚══╝░╚════╝░╚═════╝░

        Tick, tick, boom! I'm Chronos, your personal time manager.

        ===============================================================================================================
        Here are some useful commands to get you started!
        ===============================================================================================================
        1. todo <task name> - Adds a todo task to the list.
        2. deadline <task name> /due <date> - Adds a deadline task to the list.
        3. event <task name> /start <date/time> /end <date/time> - Adds an event task to the list.
        4. list - Lists all tasks in the list.
        5. mark <task number> - Marks a task as done.
        6. unmark <task_number>: Unmarks task as not done
        7. help - If you forgot how to use me, don't be afraid to ask!.
        8. timer - starts a 25 minute Pomodoro timer
        9. done - Exits Chronos.
        10. find <keyword> - Finds a task and displays all tasks with corresponding keywords
        ===============================================================================================================

        What is your name? (Please enter name)

        >>

```

