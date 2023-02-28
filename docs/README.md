# User Guide

## Quick Start

1. Ensure that you have Java 11 or above installed on your computer.

2. Download Duke.1.1.jar from [here](https://github.com/tyuyang/ip/releases/tag/Duke_1.1.0). Note that you do not need to download the zip files for the application.

3. You should find the jar file in your default downloads folder. Please place the jar file into a separate folder that will be used as your `home folder`.

4. Open a command terminal, and change the current working directory to the `home folder`. 

5. Type ```java -jar Duke.1.1.jar``` in the terminal to open the application. You should see the welcome message "Hello, I am Duke." on the next line.

6. The application is now ready for you to use! Type `help` to see a list of commands that you will be able to use in the application.



## Features 

* [Viewing help : `help`](#viewing-help--help)

* [Viewing list of tasks : `list`](#viewing-list-of-tasks--list)

* [Adding tasks](#adding-tasks)

  * [Adding ToDo task : `todo`](#adding-todo-task--todo)

  * [Adding Deadline task : `deadline`](#adding-deadline-task--deadline)

  * [Adding Event task : `event`](#adding-event-task--event)

* [Marking task as done : `mark`](#marking-task-as-done--mark)

* [Marking task as not done : `unmark`](#marking-task-as-not-done--unmark)

* [Deleting a task : `delete`](#deleting-a-task--delete)

* [Finding a task : `find`](#finding-a-task--find)

* [Saving data : `save`](#saving-data--save)

* [Exiting the application : `bye`](#exiting-the-application--bye)



### Viewing help : `help`

Shows the list of commands with the command format and short explanation.

Format: `help`

Expected output: 

```
help
List of commands:
list: Lists all tasks.
todo <name>: Adds a ToDo task.
deadline <name> /by <by when>: Adds a deadline task with specified deadline.
event <name> /from <from when> /to <to when>: Adds an event with specified time period.
mark <task number>: Marks the task specified as done.
unmark <task number>: Marks the task specified as not done.
delete <task number>: Deletes the task from the list.
find <keyword>: Finds and lists the tasks that contains the keyword in the name of the task.
save: Saves all the tasks to the savefile
bye: Exits the program.
____________________________________________________________
```



### Viewing list of tasks : `list`

Shows the list of tasks entered. Example of what a list can look like:

```
1. [T][X] hi
2. [D][ ] hihihi (by: now)
3. [E][X] hihihihi (from: here) (to: there)
```

Format: `list`

The first `[]` is the task type. `[T]` denotes a ToDo task, `[D]` denotes a Deadline task, `[E]` denotes an Event task.

The second `[]` is the completion status of the task. `[X]` denotes that task is completed, `[ ]` denotes that task is not completed.

The string not enclosed by brackets is the name, following strings enclosed by brackets are the arguments of the keyword, indicated by the keyword before `:`.

**TLDR** 
List format: `[Task Type][Completion status] name (keyword: argument of keyword) (keyword: argument of keyword)`



### Adding tasks

Command format:

* All keywords must be present. E.g. `/by`, `/to`.

* All arguments cannot be blank. E.g. `<name>`, `<by when>`

* All arguments cannot contain the character `|`.

* If any of the above rules are violated, an error message will be shown and no task will be added to the list.



### Adding ToDo task : `todo`

Adds a ToDo task into the list of tasks. ToDo tasks are tasks that need to be done but do not have a specified deadline.

Format: `todo <name>`

Example: `todo hi` adds a ToDo task with the name/description **"hi"**.

Expected output: 

```
todo hi
Got it. I have added this task:
[T][ ] hi
Now you have 1 tasks in the list
____________________________________________________________
```



### Adding Deadline task : `deadline`

Adds a Deadline task into the list of tasks. Deadline tasks are tasks that need to be done before a specific deadline.

Format: `deadline <name> /by <by when>`

Example: `deadline hihi /by today` adds a Deadline task with the name/description **"hihi"** and deadline **"today"**.


Expected output:
```
deadline hihi /by today
Got it. I have added this task:
[D][ ] hihi (by: today)
Now you have 2 tasks in the list
____________________________________________________________
```



### Adding Event task : `event`

Adds an Event task into the list of tasks. Event tasks are tasks that have a specific start and end time.

Format: `event <name> /from <from when> /to <to when>`

Example: `event hihihi /from 28 Feb 10 am /to 28 Feb 10 pm` adds an Event task with the name/description **"hihihi"**, start time **"28 Feb 10 am"** and end time **"28 Feb 10 pm"**.

Expected output:
```
event hihihi /from 28 Feb 10 am /to 28 Feb 10 pm
Got it. I have added this task:
[E][ ] hihihi (from: 28 Feb 10 am) (to: 28 Feb 10 pm)
Now you have 3 tasks in the list
____________________________________________________________
```



### Marking task as done : `mark`

Marks a task as done. 

Format: `mark <task number>`

Example: `mark 2` marks task number 2 as done.

Expected output:
```
mark 2
Nice! I have marked this task as done.
[D][X] hihi (by: today)
____________________________________________________________
```

Note: Error messages will be shown if `<task number>` is invalid. E.g. Negative task number, task number bigger than number of existing tasks.



### Marking task as not done : `unmark`

Marks a task as not done.

Format: `unmark <task number>`

Example: `unmark 1` marks task number 1 as not done.

Expected output:
```
unmark 1
Ok I have marked this as not done yet.
[T][ ] hi
____________________________________________________________
```

Note: Error messages will be shown if `<task number>` is invalid. E.g. Negative task number, task number bigger than number of existing tasks.



### Deleting a task : `delete`

Deletes a task.

Format: `delete <task number>`

Example: `delete 3` deletes task number 3 from the list.

Expected output:
```
delete 3
Noted. I've removed this task:
[E][ ] hihihi (from: 28 Feb 10 am) (to: 28 Feb 10 pm)
Now you have 2 tasks in the list
____________________________________________________________
```

Note: Error messages will be shown if `<task number>` is invalid. E.g. Negative task number, task number bigger than number of existing tasks.



### Finding a task : `find`

Finds a task which contains the keyword in its name.

Format: `find <keyword>`

Example: `find hihi` will find and print out the tasks which have `hihi` in its name.

Expected output:
```
find hihi
These tasks contain the keyword hihi
2. [D][X] hihi (by: today)
3. [E][ ] hihihi (from: 28 Feb 10 am) (to: 28 Feb 10 pm)
____________________________________________________________
```



### Saving data : `save`

Data is automatically saved when the application is properly exited, but there is the option to manually save with the command `save`.

Format: `save`

Expected output:
```
save
Saving tasks to savefile, please do not close the application.
____________________________________________________________
```



### Exiting the application : `bye`

Exits the application, and also saves the data.

Format: `bye`

Expected output: 
```
bye
____________________________________________________________
Saving tasks to savefile, please do not close the application.
Bye. Hope to see you again soon!
____________________________________________________________
```