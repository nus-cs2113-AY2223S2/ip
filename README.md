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
   
## Run the jar file
Put the jar file in some directory in your computer, and use java -jar "your jar file path" to run it in your terminal


## User's input accepted:
1. If you want to add task, you can type:
   - todo
   - deadline
   - event<br>
And your task status will be shown as T, D, E respectively.<br>
e.g., event do homework /by no idea :wuwu: [E][ ] do homework (by: no idea :wuwu)<br>
If you want to add time to your task, you can do so:<br>
e.g., deadline return book /by 2/12/2019 1800: [D][ ] return book (by: Dec 2 2019 18:00)<br>
or deadline return book /by 2/12/2019: [D][ ] return book (by: Dec 2 2019)<br>

2. If you want to list all the task you have added before, you can type:
   - list
Your tasks will be displayed in the chronological order in which you add them

3. If you want to delete one task based on its position, you can type:
   - delete "The task position"
Then this task will be deleted, and the system will tell you which one is deleted

4. If you want to mark one of your tasks, or you want to remove the mark of one of your tasks, you can type:
   - mark "The task position"
   - unmark "The task position"

5. If you want to find the task position that contain some specific information, you can type:
   - find "The information you want to find"
e.g., <br>
Your task list have tasks: <br>
1. [D][ ] return book (by: Dec 2 2019)<br>
2. [E][X] dinner<br>
If you type "- find book"<br>
It will print "1. [D][ ] return book (by: Dec 2 2019)"<br>

6. If you want to terminate your program, you can type:
   - bye
And you tasks will be stored in a txt file in your computer.

7. If you type some illegal command that the system cannot understand, it will respond to you, and require you to type in another legal command.

## Enjoy Duke!
