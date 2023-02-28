# Duke User Guide

* [What is Duke?](https://mustafaah10.github.io/ip/#what-is-duke) 
* [Setting up Duke for use](https://mustafaah10.github.io/ip/#setting-up-duke-for-use)
* [Features](https://mustafaah10.github.io/ip/#features) 
  * Adding a new task
    * [Adding a Todo](https://mustafaah10.github.io/ip/#add-a-new-todo-todo)
    * [Adding a Deadline](https://mustafaah10.github.io/ip/#add-a-new-deadline-deadline)
    * [Adding an Event](https://mustafaah10.github.io/ip/#add-a-new-event-event)
  * [Deleting a task](https://mustafaah10.github.io/ip/#deleting-a-task-delete)
  * [Viewing all tasks](https://mustafaah10.github.io/ip/#viewing-all-tasks-list)
  * [Finding all tasks containing a keyword](https://mustafaah10.github.io/ip/#finding-all-tasks-containing-a-keyword-find)
  * [Marking a task as done](https://mustafaah10.github.io/ip/#marking-a-task-as-done-mark)
  * [Unmarking a task as not done](https://mustafaah10.github.io/ip/#unmarking-a-task-as-not-done-unmark)
* [FAQ](https://mustafaah10.github.io/ip/#faq)
  
## What is Duke?

#### Duke is a chatbot that users can use to keep track of their tasks and can be used with a Command Line Interface (CLI).
<br/><br/>
## Setting up Duke for use

1. Ensure you have Java 11 or above installed on your computer.
2. Download the latest JAR file.
3. Copy the file to the folder you want to use as the home folder for Duke.
4. Open a command terminal, cd into the folder you put the jar file in, and use the java -jar ip.jar command to run the application.
   <br/>
   <br/>
## Features 
<br/><br/>
### Add a new Todo: `todo`

Adds a task of 'Todo' type to the current task list.

Format: `todo TASK_DESCRIPTION`

Examples: 
* `todo run`
* `todo complete CS assignment`

Example output:
```
Got it. I've added this task:
 [T][ ] run
You now have 1 tasks in your list.
```

```
Got it. I've added this task:
 [T][ ] complete CS assignment
You now have 2 tasks in your list.
```
<br/><br/>
### Add a new Deadline: `deadline`

Adds a task of 'Deadline' type to the current task list.

Format: `deadline TASK_DESCRIPTION /by DEADLINE`

Examples: 
* `deadline submit job application /by 26 February 2023`
* `deadline complete CS assignment /by 11pm`

Example output:
```
Got it. I've added this task:
 [D][ ] submit job application (by: 26 February 2023)
You now have 1 tasks in your list.
```

```
Got it. I've added this task:
 [D][ ] complete CS assignment (by: 11pm)
You now have 2 tasks in your list.
```
<br/><br/>
### Add a new Event: `event`

Adds a task of 'Event' type to the current task list.

Format: `event TASK_DESCRIPTION /from START /to END`

Examples: 
* `event CS2113 tutorial /from 3pm /to 4pm`
* `event complete CS assignment /from 26/02/2023 9pm /to 27/02/2023 10pm`

Example output:
```
Got it. I've added this task:
 [E][ ] CS2113 tutorial (from: 3pm to: 4pm)
You now have 1 tasks in your list.
```

```
Got it. I've added this task:
 [E][ ] complete CS2113 assignment (from: 26/02/2023 9pm to: 27/02/2023 10pm)
You now have 2 tasks in your list.
```
<br/><br/>
### Deleting a task: `delete`

Deletes a specified task from the current task list.

Format: `delete INDEX`

 > :bulb: **Tip:** If you are unsure of the index of the task to be deleted, use the `list` command first to obtain the index.

Example: 
* `delete 1`

Example output:
```
Noted, I've removed this task.
 [E][ ] CS2113 tutorial (from: 3pm to: 4pm)
You now have 1 tasks left in your list.
```
<br/><br/>
### Viewing all tasks: `list`

Displays all the tasks in the current task list.

Format: `list`

Example output:
```
Here are the tasks in your list:
1.  [E][ ] complete CS2113 assignment (from: 26/02/2023 9pm to: 27/02/2023 10pm)
2.  [T][ ] groceries
3.  [D][ ] submit job application (by: 26 February 2023)
```
<br/><br/>
### Finding all Tasks containing a keyword: `find`

Displays all the tasks in the current task list whose task description contains a specified keyword.

Format: `find KEYWORD [MORE_KEYWORDS]`

Example: 
* `find cs`

Example output:
```
Here are the matching tasks in your list:
1.  [E][ ] complete CS2113 assignment (from: 26/02/2023 9pm to: 27/02/2023 10pm)
2.  [E][ ] CS2113 tutorial (from: 3pm to: 4pm)
```
 > :bulb: **Tip:** The search is NOT case-sensitive. `cs` will recognise `CS` as shown above.
 
 > :memo: **Note:** The keyword to filter on may contain more than 1 word. For example, `CS 2113` is allowed.
 
 <br/><br/>
### Marking a task as done: `mark`

Marks a task as done/completed.

Format: `mark INDEX`

Example: 
* `mark 3`

Example output:
```
Nice! I've marked this task as done:
 [D][X] submit job application (by: 26 February 2023)
```
<br/><br/>
### Unmarking a task as not done: `unmark`

Unmarks a task as not done/completed yet.

Format: `unmark INDEX`

Example: 
* `unmark 4`

Example output:
```
OK! I've marked this task as not done yet:
 [E][ ] CS2113 tutorial (from: 3pm to: 4pm)
```
<br/><br/>
## FAQ
❓ **Q:** How do I save my current task list?

🗣️ **A:** Your tasks are automatically saved to memory after every command. They are stored in a text file in a folder called 'data',
           which should be in the same directory that you ran your JAR file from.


