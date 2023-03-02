# User Guide

This is a user guide for CS2113 IP by Ryuji Kow Jie Si A0240519J (Github username: Ryujikjs)

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
    1. Click `Open`.
    1. Select the project directory, and click `OK`.
    1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/dude/Dude.java` file, right-click it, and choose `Run Dude.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:

### Dude bot startup
```
Hello from
██████╗░██╗░░░██╗██████╗░███████╗       ██████╗░░█████╗░████████╗
██╔══██╗██║░░░██║██╔══██╗██╔════╝      ██╔══██╗██╔══██╗╚══██╔══╝
██║░░██║██║░░░██║██║░░██║█████╗░░      ██████╦╝██║░░██║░░░██║░░░
██║░░██║██║░░░██║██║░░██║██╔══╝░░      ██╔══██╗██║░░██║░░░██║░░░
██████╔╝╚██████╔╝██████╔╝███████╗      ██████╦╝╚█████╔╝░░░██║░░░
╚═════╝░░╚═════╝░╚═════╝░╚══════╝      ╚═════╝░░╚════╝░░░░╚═╝░░░

__________________________________
Greetings! I am DUDE_BOT, how can i be of service to you?
__________________________________
```
## Features

### Feature - Tasks

Dude bot will keep track of your various tasks (todo, deadline, event) that you have input 

### Feature - Mark

User can instruct Dude bot to mark/unmark tasks as done 

### Feature - Save and Load

Dude bot automatically loads data from previous use and also automatically saves current list on exit

### Feature - Find Tasks
Dude bot is capable of searching for tasks with certain keywords from the task list

## Usage

### `todo` - Adds a new Todo task

Creates a new Todo task and stores it in the task list. Also displays the number of tasks currently stored in your list.

Command:
`todo [description]`

Example:

```
todo task1
__________________________________
added: [T][ ] task1
Now you have 1 tasks in the list
__________________________________

```

### `deadline` - Adds a new Deadline task

Creates a new Deadline task and stores it in the task list.

Command:
`deadline [description] /by [datetime]`

Example:

```
deadline task2 /by today
__________________________________
added: [D][ ] task2 (by: today)
Now you have 2 tasks in the list
__________________________________
```

### `event` - Adds a new Event task

Creates a new Event task and stores it in the task list. Requires an input of a valid time.

Command:
`event [description] /from [datetime] /to [datetime]`

Example:

```
event task3 /from 1pm /to 3pm
__________________________________
added: [E][ ] task3 (from: 1pm to: 3pm)
Now you have 3 tasks in the list
__________________________________
```

### `list` - Lists the tasks stored

Displays current tasks in list.

Command:
`list`

Example:

```
list
__________________________________
1.[T][ ] task1
2.[D][ ] task2 (by: today)
3.[E][ ] task3 (from: 1pm to: 3pm)
__________________________________

```

### `mark` - Marks a task as done

Mark the task number. Takes in a valid task number.

Command:
`mark [task number]`

Example:

```
mark 1
__________________________________
Nice! I've marked this task as done:
__________________________________
[T][X] task1
__________________________________

```

### `unmark` - Unmarks a task

Unmarks the task number. Takes in a valid task number.

Command:
`unmark [task number]`

Example:

```
unmark 1
__________________________________
I have Unmarked this task:
__________________________________
[T][ ] task1
__________________________________
```

### `delete` - Deletes a task

Deletes the task at given task number. Takes in a valid task number.

Command:
`delete [task number]`

Example:

```
delete 1
Okay, I have removed this task from your list
[T][ ] task1
__________________________________
You now have 2 tasks in your list
```

### `find` - find by description

Search keyword within description of all tasks in list. Displays the tasks with the given keyword.

Command:
`find [keyword]`

Example:

```
find task2
__________________________________
Here are the matching tasks in your list:

1.[D][ ] task2 (by: today)
__________________________________
```

### `bye` - exit program

Exits the program and saves all items in the tasklist to a data file

Command:
`bye`

Example:

```
bye
Saving current list
__________________________________
Goodbye, it was a pleasure to be of service to you. Here have a chocolate bar as my parting gift
  ___  ___  ___  ___  ___.---------------.
.'\__\'\__\'\__\'\__\'\__,`   .  ____ ___ \
|\/ __\/ __\/ __\/ __\/ _:\   |`.  \  \___ \
 \\'\__\'\__\'\__\'\__\'\_`.__|""`. \  \___ \
  \\/ __\/ __\/ __\/ __\/ __:                \
   \\'\__\'\__\'\__\ \__\'\_;-----------------`
    \\/   \/   \/   \/   \/ :                 |
     \|______________________;________________|
```

