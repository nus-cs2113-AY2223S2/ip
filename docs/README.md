
# User Guide

This is a user guide for CS2113 IP by Magmanat(Prince Pereira)

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/grandduke/GrandDuke.java` file, right-click it, and choose `Run GrandDuke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
```
Hello from
  _____                     _______       _
|  __ \                   | |  _  \     | |
| |  \/_ __ __ _ _ __   __| | | | |_   _| | _____
| | __| '__/ _` | '_ \ / _` | | | | | | | |/ / _ \
| |_\ \ | | (_| | | | | (_| | |/ /| |_| |   <  __/
 \____/_|  \__,_|_| |_|\__,_|___/  \__,_|_|\_\___|

        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Hello! I'm GrandDuke
        What can I do for you?
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ./store does not exist. Creating directory...
        ./store/data.txt does not exist. Creating file...
        ./store/data.txt found. Loading data...
        Data loaded successfully.
```
5. If you have already run the program before, if your data files already exist, you should get this instead
```
Hello from
  _____                     _______       _
|  __ \                   | |  _  \     | |
| |  \/_ __ __ _ _ __   __| | | | |_   _| | _____
| | __| '__/ _` | '_ \ / _` | | | | | | | |/ / _ \
| |_\ \ | | (_| | | | | (_| | |/ /| |_| |   <  __/
 \____/_|  \__,_|_| |_|\__,_|___/  \__,_|_|\_\___|

        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Hello! I'm GrandDuke
        What can I do for you?
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

        ./store/data.txt found. Loading data...
        Data loaded successfully.
```

## Features

### Feature - Greet

Upon startup, GrandDuke greets the user with a friendly welcome message

### Feature - Goodbye

On exit, GrandDuke sends the user off with a kind goodbye message

### Feature - Tasks

GrandDuke will keep track of your various tasks and display them so that you will not forget them

### Feature - Mark

You can tell GrandDuke to mark tasks when you are done or unmark tasks when you need to complete them in the future

### Feature - Save and Load

GrandDuke is capable of saving and loading data form previous interactions!

### Feature - Find Tasks
GrandDuke is capable of finding tasks with certain keywords and display it to you

## Usage

### `list` - Lists the tasks stored

Displays the tasks stored thus far.

Example of usage:
`list`

Expected outcome:

```
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        1. [D][ ] write essay (by: this Friday 2359)
        2. [E][ ] ESP32 workshop (from: 23/2/2023 4pm to: 23/2/2023 6pm)
        3. [T][ ] read CS2113 textbook
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `todo` - Adds a new Todo task

Creates a new Todo task and stores it in the task list. Also displays the number of tasks currently stored in your list.

Example of usage:
`todo {description}`

Expected outcome:

```
		~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Got it. I've added this task:
          [T][ ] read CS2113 textbook
        Now you have 3 tasks in the list.
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `deadline` - Adds a new Deadline task

Creates a new Deadline task and stores it in the task list. Requires an input of a valid date.

Example of usage:
`deadline {description} /by {datetime}`

Expected outcome:

```
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Got it. I've added this task:
          [D][ ] write essay (by: this Friday 2359)
        Now you have 1 tasks in the list.
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `event` - Adds a new Event task

Creates a new Event task and stores it in the task list. Requires an input of a valid time.

Example of usage:
`event {description} /from {datetime} /to {datetime}` 

Expected outcome:

```
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Got it. I've added this task:
          [E][ ] ESP32 workshop (from: 23/2/2023 4pm to: 23/2/2023 6pm)
        Now you have 2 tasks in the list.
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `mark` - Marks a task

Mark the task number. Requires an input of a valid task number.

Example of usage:
`mark {task number}`

Expected outcome:

```
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Alright!, I helped you mark this task as done:
          [D][X] write essay (by: this Friday 2359)
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `unmark` - Unmarks a task

Unmarks the task number. Requires an input of a valid task number.

Example of usage:
`unmark {task number}`

Expected outcome:

```
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        OK, I helped you mark this task as not done yet:
          [D][ ] write essay (by: this Friday 2359)
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `delete` - Deletes a task

Deletes the task at given task number. Also displays the number of tasks remaining after deletion. Requires an input of a valid task number.

Example of usage:
`delete {task number}`

Expected outcome:

```
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Alright. I've removed this task:
          [T][ ] read CS2113 textbook
        Now you have 2 tasks in the list.
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```

### `find` - Find keyword

Search keyword within description of all tasks in task list. Displays a list of tasks with the given keyword in the description.

Example of usage:
`find {keyword}`

Expected outcome:

```
find workshop
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Here are the matching tasks in your list:
        2. [E][ ] ESP32 workshop (from: 23/2/2023 4pm to: 23/2/2023 6pm)
        ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
```
