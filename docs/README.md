# **User Guide**

Project Duke is a Personal Assistant Chatbot in the form of a Command Line Interface (CLI) Application. It helps you keep track of all your tasks with ease. Hope you enjoy using it!

## Getting Started
To run the application, you need to have Java 11 installed on your device. 

1. Download the `"ip.jar"` file.
2. Locate the file in your computer and copy its `absolute path`.
3. Open a terminal and key in the following command, replace the `<absolute_path>` with the `absolute path` which you have copied in the previous step:

```
java -jar <absolute_path>
```


When you have successfully setup, you should see something like this:
```
File created: duke.txt
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|
____________________________________________________________
Hello! I'm Duke
What can I do for you?
____________________________________________________________
```

## Features 

### Add Task
There are 3 different types of tasks that you can add: `todo`, `deadline`, and `event`. Tasks are shown in the form:

`<task_character><task_status> <task_description + details>`

Example:
```
[X][X] this is my task (these are the task details)
```

`NOTE:` task description and details should not contain the character `"/"`

#### Todo
A todo task is marked with the `<task_character>: "[T]"` and contains a task description.

Command Format:
`todo <task_description>`

Command:
```
todo read book
```
Expected Outcome:
```
Added todo: [T][ ] read book
```

#### Deadline
A deadline task is marked with the `<task_character>: "[D]"` and contains a task description and the time/date that it should be completed by.

Command Format: `deadline <task_description> /by <date/time>`

Command:
```
deadline book review /by tues 2359
```
Expected Outcome:
```
Added deadline: [D][ ] book review (by: tues 2359)
```

#### Event
An event task is marked with the `<task character>: "[E]"` and contains a task description, the time/date that it starts and the time/date it ends.

Command Format:
`event <task_description> /from <date/time> /to <date/time>`

Command:
```
event book fair /from fri 3pm /to fri 5pm
```
Expected Outcome:
```
Added event: [E][ ] book fair (from: fri 3pm to: fri 5pm)
```

### List Tasks
This command allows you to list all tasks in your task-list.

Command:
```
list
```
Expected Outcome:
```
1. [T][ ] read book
2. [D][ ] book review (by: tues 2359)
3. [E][ ] book fair (from: fri 3pm to: fri 5pm)
all tasks listed!
```

### Delete Task
This command allows you to delete a task that is specified by a task number.

Command Format:
`delete <task_number>`

Command:
```
delete 1
```
Expected Outcome:
```
Deleted Task: [T][ ] read book
```

### Change Task Status
There are two commands that can help you change the status of your task.

#### Mark Task
This command allows you to mark a task as done.

Command Format:
`mark <task_number>`

Command:
```
mark 1
```
Expected Outcome:
```
Marked Task: [E][X] book fair (from: fri 3pm to: fri 5pm)
```

#### Unmark Task
This command allows you to mark a task as not done.

Command Format:
`unmark <task_number>`

Command:
```
unmark 1
```
Expected Outcome:
```
Unmarked Task: [E][ ] book fair (from: fri 3pm to: fri 5pm)
```

### Find Task By Keyword
This command allows you to search your task-list for tasks containing a keyword.

Command Format:
`find <keyword>`

Command:
```
find review
```

Expected Outcome:
```
[D][ ] book review (by: tues 2359)
All matching tasks have been listed!
```

### Exit
This commands allows you to exit the application. Your task-list will be automatically saved in a file named `"duke.txt"` and auto-loaded the next time you run the duke application.

Command:
```
bye
```
Expected outcome:
```
Bye. Hope to see you again soon!
```

## Summary of Commands

| Command | Format | Example | 
| ------- | ------ | ------- | 
| `todo` | todo <task_description> | todo read book |
| `deadline` | deadline <task_description> /by <date/time> | deadline book review /by tues 2359 |
| `event` | event <task_description> /from <date/time> /to <date/time> | event book fair /from fri 3pm /to fri 5pm
| `list` | list | list |
| `delete` | delete <task_number> | delete 1|
| `mark` | mark <task_number> | mark 1 |
| `unmark` | unmark <task_number> | unmark 1|
| `find` | find <keyword> | find review |
| `bye` | bye | bye |

### Credits
This project was completed with reference to [nus-cs2113-AY2223S2/personbook](https://github.com/nus-cs2113-AY2223S2/personbook)
