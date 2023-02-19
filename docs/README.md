# Duke User Guide
By: `sansders` Last Updated: `February 2023`

# Contents
- [1. Introduction](#1.-Introduction)
- [2. Quick Start](#2.-Quick-Start)
- [3. Features](#3.-Features)
    - [3.1 Add To-do](#3.1-Add-To-do)
    - [3.2 Add Deadline](#3.2-Add-Deadline)
    - [3.3 Add Event](#3.3-Add-Event)
    - [3.4 List all Tasks](#3.4-List-all-Tasks)
    - [3.5 Mark Task as Done/Undone](#3.5-Mark-Task-as-Done/Undone)
    - [3.6 Delete a Task](#3.6-Delete-a-Task)
    - [3.7 Find a Task](#3.7-Find-a-Task)
    - [3.8 Exit Duke](#3.8-Exit-Duke)
    - [3.9 Task Storage](#3.9-Task-Storage)
- [4. Command Summary](#4.-Command-Summary)

# 1. Introduction
Duke, your personal bot,
Second only to ChatGPT,
Tracks your to-dos and deadlines a lot,
Just like a journal, but electronically.

With Duke by your side,
Events are always on time,
Tasks you no longer need to hide,
Completed or not, Duke keeps them in line.

Minimal effort is all it takes,
To keep your life organized and neat.
No need to worry about missed dates,
With Duke, everything is a treat.

So why wait any longer?
Install Duke today!
Your life will be much stronger,
And organized in every way!


# 2. Quick Start

1. Ensure that you have Java 11 installed on your computer. If you do not have Java 11 installed, you can do so [here](https://www.oracle.com/sg/java/technologies/downloads/#java11).
1. Download the latest ip.jar from [here](https://github.com/sansders/ip/releases).
1. Copy the file to the folder you wish to use as the home folder for the application.
1. Open the command terminal and navigate to the folder where the jar file is.
    1. If you do not know how, open file explorer and navigate to the application.
    1. Then, right-click on the folder and select Properties
    1. Under the General tab, copy the path listed as Location
1. Then, open a command terminal by typing cmd in your start menu and launch the command terminal. 
1. Access the folder via the command terminal, by doing cd FILEPATH. If you followed steps 4.1 to 4.3, you can paste the filepath which was previously copied.
1. Use the command `java -jar ip.jar` to run the command. 
1. To execute commands, enter the command with the necessary parameters, then press the Enter key. 
1. To see more details, refer to the Features section.
1. When you are done using the app, enter bye to shut down the application, instead of just closing it. Doing so allows the application to save the data.

# 3. Features

## 3.1 Add To-do

This feature adds a todo task to the task list.

Format: `todo <task description>`

Example: `todo read a book`

Expected Output:
```
____________________________________________________________
Got it. I've added this task:
[T][ ] do homework
Now you have 1 task in the list.
____________________________________________________________

```

## 3.2 Add Deadline

This feature adds a deadline task to the task list.

Format: `deadline <task description> /by <deadline>`

Example: `deadline finish CS2113T report /by 2023-02-28 2359`

Expected Output:
```
____________________________________________________________
Got it. I've added this task:
[D][ ] finish CS2113T report (by: Feb 28 2023 2359)
Now you have 2 tasks in the list.
____________________________________________________________

```

## 3.3 Add Event

This feature adds an event task to the task list.

Format: `event <task description> /from <from> /to <to>`

Example: `event CS2113T Finals /from 03 May 9am /to 11am`

Expected Output:
```
____________________________________________________________
Got it. I've added this task:
[E][ ] CS2113T Finals (from: 03 May 9am to: 11am)
Now you have 3 tasks in the list.
____________________________________________________________
```

## 3.4 List all Tasks

This feature allows you to look at all the tasks.

Format: `list`

Expected Output:
```
____________________________________________________________
1.[T][ ] read a book
2.[D][ ] finish CS2113T report (by: Feb 28 2023 2359)
3.[E][ ] CS2113T Finals (from: 03 May 9am to: 11am)
____________________________________________________________
```

## 3.5 Mark Task as Done/Undone

This feature allows you to keep track of what tasks in your list you have already completed.

You must first obtain the index of the specific task from using the `list` command to use this command.

If you mark a task as done by accident, you may unmark it at any point of time by entering `unmark` instead.

Format: `mark <task index>` or `unmark <task index>`

Example: `mark 2`

Expected Output:
```
____________________________________________________________
Nice! I've marked this task as done:
[D][X] finish CS2113T report (by: Feb 28 2023 2359)
____________________________________________________________
```

## 3.6 Delete a Task

You may choose to delete a task from the list of tasks at any point of time, regardless of whether they are marked as done or not.

Similar to the `mark` and `unmark` commands, you must first get the index of the task you wish to delete by using the `list` command.

Format: `delete <task index>`

Example: `delete 2`

Expected Output:
```
____________________________________________________________
Noted. I've removed this task:
2.[D][X] finish CS2113T report (by: Feb 28 2023 2359)
Now you have 2 tasks in the list.
____________________________________________________________
```

## 3.7 Find a Task

You may find out whether or not a Task exists in your list of tasks by entering a keyword.

Format: `find <keyword>`

Example: `find Finals`

Expected Output: 
```
____________________________________________________________
Here are matching tasks in your list:
- [E][ ] CS2113T Finals (from: 03 May 9am to: 11am)
____________________________________________________________
```

## 3.8 Exit Duke

When you are done with using Duke, you may safely exit the application by bidding Duke farewell.

Format: `bye`

Expected Output:
```
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

## 3.9 Task Storage

When you first run Duke, a save file will not exist. 

Upon adding a new Task, Duke then creates and writes to a save file `tasks.txt`. 
Please **do not** delete the file unless you wish to intentionally delete your save data.

Expected output (on first run):
```
Save file does not exist.
Creating new save file...
____________________________________________________________
```

Expected output (on subsequent runs):
```
Save file found.
Loading save file...
____________________________________________________________
```

## 4. Command Summary

Command     | Format                                           | Example
------------|--------------------------------------------------|----------------
todo        | `todo <task description>`                        | `todo homework`
deadline    |`deadline <task description> /by <deadline>`      | `deadline finish CS2113T report /by 2023-02-28 2359 `
event       | `event <task description> /from <from> /to <to>` | `event CS2113T Finals /from 03 May 9am /to 11am`
list        | `list`                                           | `list`
mark        | `mark <task index>`                              | `mark 2`
unmark      | `unmark <task index>`                            | `unmark 2`
delete      | `delete <task index>`                            | `delete 2`
find        | `find <task substring>`                          | `find homework`









