# Duke User Guide

Duke is a command line interface based task manager app. 

## Setting up

Prerequisites: JDK 11, 

1. Download Duke.jar into an empty folder
2. Open a terminal or linux shell and enter the directory that contains the Duke.jar file
3. Run the command `java -jar Duke.jar`.

If successful, you should be greeted with something like this: 
```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   Hello! I'm Duke
   All saved data has been loaded. What can I do for you today?
   ```
Do not be confused if you do not have any saved data. Duke will initialise properly regardless and save your inputted data at the end.

## Features

#### Add tasks to task list (todo, deadline, event)
#### Delete tasks from task list
#### Mark tasks as completed/not completed
#### Find tasks in task list
#### Load tasks from text file
#### Saved inputted tasks into text file

## Usage

### List All Tasks

Displays all tasks that are currently in the list or returns an empty list if there are no tasks.

Format: `list`

Sample command:
```
list
------------------------------------------------
1. [T][X] return book
2. [D][X] loan book (by: sunday)
3. [E][ ] lunch with friend (from: 2pm to: 3pm)
------------------------------------------------
```

### Add Tasks To List

#### Add todo Task

Adds a todo task to your list.

Format: `todo <task name>`

Sample command:
```
todo return book
____________________________________________________________
Got it. I've added this task:
  [T][ ] return book
Now you have 1 task in the list.
____________________________________________________________
```

#### Add Deadline Task

Adds a deadline task to your list.

Format: `deadline <task name> /by <deadline>`

Sample command:
```
deadline loan book /by sunday
____________________________________________________________
Got it. I've added this task:
  [D][ ] loan book (by: sunday)
Now you have 2 task in the list.
____________________________________________________________
```

#### Add Event Task

Adds a event task to your list.

Format: `event <task name> /from <start date or time> /to <end date or time>`

Sample command:
```
event lunch with friend /from 2pm /to 3pm
____________________________________________________________
Got it. I've added this task:
  [E][ ] lunch with friend (from: 2pm to: 3pm)
Now you have 2 task in the list.
____________________________________________________________
```

### Mark Tasks

#### Mark Tasks As Completed

Marks a task as completed.

Format: `mark <task number>`

Sample command:
```
mark 1
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] return book
____________________________________________________________
list
____________________________________________________________
1.[T][X] return book
2.[D][ ] loan book (by: sunday)
3.[E][ ] lunch with friend (from: 2pm to: 3pm)
____________________________________________________________
```

#### Mark Tasks As Not Completed

Marks a task as not completed.

Format: `unmark <task number>`

Sample command:
```
unmark 1
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] return book
____________________________________________________________
list
____________________________________________________________
1.[T][ ] return book
2.[D][ ] loan book (by: sunday)
3.[E][ ] lunch with friend (from: 2pm to: 3pm)
____________________________________________________________
```

### Find Task In List

Displays all task that has the keyword inputted, else returns an empty list.

Format: `find <keyword>`

Sample command:
```
find book
____________________________________________________________
Here are the matching tasks in your list:
1. [T][ ] return book
Here are the matching tasks in your list:
2. [D][ ] loan book (by: sunday)
____________________________________________________________
find dinner
____________________________________________________________
____________________________________________________________
```

### Loads Save Data From Text File

No commands needed. Data will be loaded from text file if text file is found once Duke is started. Else, a new text file will be created to save the inputted tasks later.

### Save Task List Into Text File

All tasks will be saved into the text file ~/Desktop/data/duke.txt

### Exit Task Manager Aoo

Exits task manager and saves all tasks into local text file

Format: `bye`

Sample command:
```
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```