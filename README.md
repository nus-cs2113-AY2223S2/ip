# DUDE project template

Dude is a task tracking tool to keep track of a variety of tasks, giving you a productivity boost by helping you plan your schedule.

It uses optimised CLI performance to give you the best task manager features in a single consolidated package!
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
   HELLLOO there! I am
    ____   _   _  ____   _____   _        
   |  _ \ | | | ||  _ \ |  ___| | |    
   | | | || | | || | | || |__   | |      
   | | | || | | || | | ||  __|  | |     
   | |_| || |_| || |_| || |___   -  
   |____/ \____/ |____/ |_____|  O    
   
   Your personal robot assistant!
   What can I do for you today?
   
   You can start by adding items to a task list that I can generate, simply follow the format below: 
   
   Todos      : "todo <insert todo task description>"
   Deadlines  : "deadline <insert deadline task description> /by <insert date in "YYYY-MM-DD" format> 
   Events     : "event <insert event description> /from <insert date in "YYYY-MM-DD" format> /to <insert date in "YYYY-MM-DD" format>
   
   If you wish to view the full list of commands, simply type "help"!
   ```
