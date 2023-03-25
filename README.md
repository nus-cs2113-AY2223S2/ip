# Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

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

## How to use

The commands this program uses are; `todo`, `deadline`, `event`, `list`, `mark`, `find`, `delete` and `bye`**
`todo`, `deadline` and `event` are the different types of tasks you can add into your list to keep track of.

1. `todo`s are the simplest task to add as they do not require you to add in a start/end time, only a description of the task
e.g. `todo lunch`

2. `deadline`s, as the name suggests, are tasks which have a deadline. The due date or time is indicated after a `/`
e.g. `lunch/12pm`

3. `event`s are tasks which have a duration, from a start time to an end time
e.g. `lunch/12pm/1pm

4. `list` shows you the tasks which you have added. You can view them by simply typing `list`

5. `mark` checks off the task which you have completed.
e.g. `mark 3`

6. `find` searches your list of tasks to find those which have a certain ketword present.
e.g. `find lunch`

7. `delete` removes one of the tasks in your list
e.g. `delete 2`

Finally, `bye` allows you the exit program
