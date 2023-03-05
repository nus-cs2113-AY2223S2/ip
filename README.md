# Mike project

This is a project based on a template for a greenfield Java project. It was named after the Java mascot _Duke_ but has since been renamed to _Mike_. Given below are instructions on how to use it.

## How to use Mike
1. Open the project into Intellij as follows:
   1. Click `Open`.
   2. Select the project directory, and click `OK`.
   3. If there are any further prompts, accept the defaults.
2. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   1. In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
    ___       __   _______   ___       ________  ________  _____ ______   _______ 
   |\  \     |\  \|\  ___ \ |\  \     |\   ____\|\   __  \|\   _ \  _   \|\  ___ \
    \ \  \    \ \  \ \   __/|\ \  \    \ \  \___|\ \  \|\  \ \  \\\__\ \  \ \   __/|
     \ \  \  __\ \  \ \  \_|/_\ \  \    \ \  \    \ \  \\\  \ \  \\|__| \  \ \  \_|/__
      \ \  \|\__\_\  \ \  \_|\ \ \  \____\ \  \____\ \  \\\  \ \  \    \ \  \ \  \_|\ \
       \ \____________\ \_______\ \_______\ \_______\ \_______\ \__\    \ \__\ \_______\
        \|____________|\|_______|\|_______|\|_______|\|_______|\|__|     \|__|\|_______|
   
   Hi! I'm Mike, your personal assistant! How can I help you today?
   
   We have loaded your file and this is the current state of your list.
   Here are the tasks in your list:
   You have a total of 0 task(s).
   ```
4. If you're starting from a new file it should show that you have 0 task in the list, otherwise it will display the last save state of your task list.
5. Mike can only take in 1 of 9 specific commands below. Type them in accordance to the format given ,and you see a similar sample output:
   1. `todo`: creates a todo task and adds it to the task list.
      1. Input format: `todo description of task`
      2. Sample output: 
         ```
         Got it. I've added this task:
         [T][ ] description of task
         Now you have 1 task(s) in the list.
         ```
   2. `deadline`: creates a deadline and adds it to the task list.
      1. Input format: `deadline description of task /by date of deadline`
      2. Sample output:
         ```
         Got it. I've added this task:
         [D][ ] description of task (by: date of deadline)
         Now you have 2 task(s) in the list.
         ```
   3. `event`: creates an event and adds it to the list.
      1. Input format: `event description of task /from start date of event /to end date of event`
      2. Sample output:
         ```
         Got it. I've added this task:
         [E][ ] description of task (from: start date of event to: end date of event)
         Now you have 3 task(s) in the list.
         ```
   4. `list`: shows all the current tasks in the task list.
      1. Input format: `list`
      2. Sample output:
         ```
         Here are the task(s) in your list:
         1.[T][ ] description of task
         2.[D][ ] description of task (by: date of deadline)
         3.[E][ ] description of task (from: start date of event to: end date of event)
         You have a total of 3 task(s).
         ```
   5. `mark`: marks a certain task as completed.
      1. Input format: `mark (task number)`
      2. Sample output:
         ```
         OK, I've marked this task as completed:
           [X] description of task
         ```
   6. `unmark`: unmarks a certain completed task.
      1. Input format: `unmark (task number)`
      2. Sample output:
         ```
         OK, I've marked this task as not done yet:
           [ ] description of task
         ```
   7. `find`: searches for tasks that contains a given keyword.
      1. Input format: `find (keyword)`
      2. Sample output:
         ```
         Here's the list of task(s) in your list that matches your keyword: 
         1.[T][ ] description of task
         2.[D][ ] description of task (by: date of deadline)
         3.[T][X] description of task
         ```
   8. `delete`: delete a certain task from the task list.
      1. Input format: `delete (task number)`
      2. Sample output:
         ```
         Noted. I've removed this task
         3.[E][ ] description of task (from: start date of event to: end date of event)
         ```
   9. `bye`: exits the program.
      1. Input format: `bye`
      2. Sample output:
         ```
         Good bye! Hope to see you again soon.
         ```