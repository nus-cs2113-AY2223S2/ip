<h1> User Guide </h1>



<h2> Introduction </h2>

Duke Bot is a <strong>Command Line Interface (CLI) application that helps you manage your tasks</strong>.



<h2> Table of Contents </h2>

* **[Quick Start](#quick-start)**

* **[Features](#features)**
    
    * **[Adding a task: `todo`](#adding-a-task-todo)**
    * **[Adding a deadline: `deadline`](#adding-a-task-deadline)**
    * **[Adding an event: `event`](#adding-a-task-event)**
    * **[Listing all tasks: `list`](#listing-all-tasks-list)**
    * **[Marking a task as done: `mark`](#marking-a-task-as-done-mark)**
    * **[Unmarking a task as done: `unmark`](#unmarking-a-task-as-done-unmark)**
    * **[Finding a task: `find`](#finding-a-task-find)**
    * **[Deleting a task: `delete`](#deleting-a-task-delete)**
    * **[Exiting the program: `bye`](#exiting-the-program-bye)**
    
* **[Command Summary](#command-summary)**

    

<h2 id="quick-start"> Quick Start</h2>

1. Ensure you have `Java 11` installed on your Computer.

2. Download the latest `ip.jar` from here.

3. Copy the file to the folder you want to use as home folder for the Duke app.

4. Use `Win+R` to open the command prompt and type `cmd` and press Enter.

5. Then `cd` into the folder where you copied the jar file. e.g. `cd C:\Users\Lau\Desktop\MyTask`

6. Type `java -jar ip.jar` and press Enter to start the program.

7. Type the command in the command prompt and press Enter to execute it.

   

<h2 id="features"> Features</h2>

<h2 id="adding-a-task-todo"> Adding a task: <code class="language-plaintext highlighter-rouge">todo</code></h2>

Add a task to Duke Bot.<br>
**Format**: `todo DESCRIPTION`<br>
**Constraints**: `DESCRIPTION` must be a non-empty string.<br>
**Example**: 

```
______________________________
todo play basketball
______________________________
Got it. I've added this task:
   [T][ ]play basketball
Now you have 1 task in the list.
______________________________

```

<h2 id="adding-a-task-deadline"> Adding a deadline: <code class="language-plaintext highlighter-rouge">deadline</code></h2>

Add a deadline to Duke Bot.<br>
**Format**: `deadline DESCRIPTION /by DATE`<br>
**Constraints**: `DESCRIPTION` must be a non-empty string.  <br>
**Example**: 

```
______________________________
deadline Midterm assignment /by not announce yet
______________________________
Got it. I've added this task:
   [D][ ]Midterm assignment (by: not announce yet)
Now you have 2 tasks in the list.
______________________________
deadline Midterm assignment /by 3 March 2023
______________________________
Got it. I've added this task:
   [D][ ]Midterm assignment (by: 3 March 2023)
Now you have 3 tasks in the list.
______________________________
```

<h2 id="adding-a-task-event"> Adding an event: <code class="language-plaintext highlighter-rouge">event</code></h2>

Add an event to the Duke Bot.<br>
**Format**: `event DESCRIPTION /from START_DATE /to END_DATE`<br>
**Constraints**: `DESCRIPTION` must be a non-empty string.<br>
**Example**: 

```
______________________________
event recess week /from 2023-2-20 /to 2023-2-27
______________________________
Got it. I've added this task:
   [E][ ]recess week (from: 2023-2-20 to: 2023-2-27)
Now you have 4 tasks in the list.
______________________________
```

<h2 id="listing-all-tasks-list"> Listing all tasks: <code class="language-plaintext highlighter-rouge">list</code></h2>

List all the tasks in Duke Bot.<br>
**Format**: `list`<br>
**Example**: 

```
______________________________
list
______________________________
Here are the tasks in your list:
1. [T][ ]play basketball
2. [D][ ]Midterm assignment (by: not announce yet)
3. [D][ ]Midterm assignment (by: 3 March 2023)
4. [E][ ]recess week (from: 2023-2-20 to: 2023-2-27)
______________________________
```


<h2 id="marking-a-task-as-done-mark"> Marking a task as done: <code class="language-plaintext highlighter-rouge">mark</code></h2>

Mark a task as done.<br>
**Format**: `mark INDEX`<br>
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. <br>
**Example**: 

```
______________________________
mark 1
______________________________
Nice! I've marked this task as done:
   [T][X]play basketball
______________________________
mark 4
______________________________
Nice! I've marked this task as done:
   [E][X]recess week (from: 2023-2-20 to: 2023-2-27)
______________________________
```
<h2 id="unmarking-a-task-as-done-unmark"> Unmarking a task as done: <code class="language-plaintext highlighter-rouge">unmark</code></h2>

When a task is marked as done, you can unmark it as done.<br>
**Format**: `unmark INDEX`<br>
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. <br>
**Example**: 

```
______________________________
unmark 1
______________________________
OK, I've marked this task as not done yet:
   [T][ ]play basketball
______________________________
list
______________________________
Here are the tasks in your list:
1. [T][ ]play basketball
2. [D][ ]Midterm assignment (by: not announce yet)
3. [D][ ]Midterm assignment (by: 3 March 2023)
4. [E][X]recess week (from: 2023-2-20 to: 2023-2-27)
______________________________
```

<h2 id="finding-a-task-find"> Finding a task: <code class="language-plaintext highlighter-rouge">find</code></h2>

Find all the tasks that contain the keyword.<br>
**Format**: `find KEYWORD`<br>
**Constraints**: `KEYWORD` must be a non-empty string.<br>
**Example**: 
```
______________________________
find ball
______________________________
Here are the matching tasks in your list:
1. [T][ ]play basketball
______________________________
```

<h2 id="deleting-a-task-delete"> Deleting a task: <code class="language-plaintext highlighter-rouge">delete</code></h2>
Delete a task from the task manager by its index.

**Format**: `delete INDEX`<br>
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. <br>
**Example**: 

```
______________________________
delete 2
______________________________
Noted. I've removed this task:
	[D][ ]Midterm assignment (by: not announce yet)
Now you have 3 tasks in the list.
______________________________
```

<h2 id="exiting-the-program-bye"> Exiting the program: <code class="language-plaintext highlighter-rouge">bye</code></h2>

Exit the program.<br>
**Format**: `bye`<br>
**Example**: 

```
______________________________
bye
______________________________
Bye. Hope to see you again soon!
______________________________

Process finished with exit code 0
```

<h2 id='command-summary'> Command Summary </h2>

|  Action   | 	Format  | Example |
|  ----  | ----  | ----  |
|  [todo](#adding-a-task-todo)  | 	**todo** DESCRIPTION  | todo readbook |
|  [deadline](#adding-a-task-deadline)  | 	**deadline** DESCRIPTION /by DATE  | deadline return book /by 2023-02-28 |
|  [event](#adding-a-task-event)  | 	**event** DESCRIPTION /from DATE /to DATE  | event project meeting /from 2023-02-28 3pm /to 4pm |
|  [list](#listing-all-tasks-list)  | 	**list**  | list |
|  [mark](#marking-a-task-as-done-mark)  | 	**mark** INDEX  | mark 1 |
|  [unmark](#unmarking-a-task-as-done-unmark)  | 	**unmark** INDEX  | unmark 1 |
|  [find](#finding-a-task-find)  | 	**find** KEYWORD  | find book |
|  [delete](#deleting-a-task-delete)  | 	**delete** INDEX  | delete 1 |
|  [bye](#exiting-the-program-bye)  | 	**bye**  | bye |