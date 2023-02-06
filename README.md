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
3. After that, locate the `src/main/java/Chronos.java` file, right-click it, and choose `Run Chronos.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

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

'list': Take a look at your To-Do list to get your day started!
'mark <task number>': Marks task as done. Try entering 'mark 1' to mark your first task as done!
'unmark <task_number>': Unmarks task as not done. Try entering 'unmark 1' to mark your first task as not done.
'help': If you forgot how to use me, don't be afraid to ask!
There are 3 types of tasks you can add to your list. Please follow the format given: 

* Type 'todo' and I will add it into the list.

** Type 'deadline' and the due date '/due' for me to register the deadline. 

*** Type 'event' and the start time '/start' AND end time '/end' for me to register the event. 

Type 'timer' to start a pomodoro timer and get to work!

=============================================================================================================== 


What is your name? (Please enter name)

>> 

```

