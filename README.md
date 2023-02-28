# Duke Task Tracker

This is a desktop app to track your tasks. Optimized for used via Command Line Interface (CLI)

- [Quick Start](#quick-start)
- [Commands Format](#commands-format)
- [Additional Notes](#additional-notes)
- [Guide for setting up in IntelliJ](#setting-up-in-intellij)

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer  
   a. If you are new to java you may follow the [provided guide](#setting-up-in-intellij) to confirm your installation
2. Download the latest duke.jar from here
3. Copy the file to the folder you want to use as the home folder for your AddressBook
4. Start with `java -jar duke.jar`

## Commands Format
- Words in UPPER_CASE are the parameters to be supplied by the user e.g. in `todo DESCRIPTION`, DESCRIPTION is a parameter that can be added with variable length with even white space in between

- Words in lower_case are fixed key words. e.g. in `deadline DESCRIPTION /by END_DATETIME`, all fixed key words are separated by white space. Consult the [Feature List](#feature-list) for the list of commands/ key words required by each command

## Feature List
1. Add a Todo task `todo DESCRIPTION`
2. Add a Deadline task `deadline DESCRIPTION /by END_DATETIME`
3. Add a Event task `event DESCRIPTION /from START_DATETIME /to END_DATETIME`
4. List all tasks that exists already `list`
5. Mark the task that as completed `mark TASK_IDX`
6. Unmark the task that as uncompleted `unmark TASK_IDX`
7. Lists all tasks that are like the input TASK_NAMELIKE `find TASK_NAMELIKE`
8. Save all tasks that are currently stored `save`
8. Load all tasks that are in the file data/duke.txt `load`
9. End the conversation `bye`

## Additional Notes
- Leaving the app does NOT save your current task list. Make sure to save using the `save` command if you would like to keep your tasks. You can find the output file '{ROOT_FOLDER}/data/duke.txt'
- You may also edit the files directly from the output save file '{ROOT_FOLDER}/data/duke.txt' for quick loads. The `load` function works appends all tasks into the current tasklist. Make sure to check for duplicates
- The non-autosave function allows more advanced users to edit their tasklists quickly

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```