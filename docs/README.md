<!-- ---
layout: page
title: User Guide
--- -->
<h1> User Guide </h1>
<h2> Introduction </h2>
Task Manager is a <strong>Command Line Interface(CLI) task manager application that helps you to manage your tasks</strong>. If you are a fast typist, you can manage your tasks faster than traditional GUI apps.
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
    * **[Searching a task by date: `date`](#searching-a-task-by-date-date)**
    * **[Exiting the program: `bye`](#exiting-the-program-bye)**
* **[FAQ](#faq)**
* **[Command Summary](#command-summary)**

<h2 id="quick-start"> Quick Start</h2>

1. Enusure you have `Java 11` installed on your Computer.
2. Download the latest `ip.jar` from here.
3. Copy the file to the folder you want to use as home folder for the task manager.
4. Use `Win+R` to open the command prompt and type `cmd` and press Enter.
5. Then `cd` into the folder where you copied the jar file. e.g. `cd C:\Users\Lau\Desktop\MyTask`
6. Type `java -jar ip.jar` and press Enter to start the program.
7. Type the command in the command prompt and press Enter to execute it.

<h2 id="features"> Features</h2>
<ol>
<li>
Words in <strong>UPPER_CASE</strong> are the parameters to be supplied by the user.
    
    e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo readbook`.
</li>    
<li>
<strong>Extraneous parameters</strong> for commands that do not take in parameters (such as <code class="language-plaintext highlighter-rouge">bye</code> and <code class="language-plaintext highlighter-rouge">list</code>) <strong>will be ignored</strong>.
    
    e.g. if the command specifies `bye 123`, it will be interpreted as `bye`.
</li>
</ol>
<h2 id="adding-a-task-todo"> Adding a task: <code class="language-plaintext highlighter-rouge">todo</code></h2>

Add a task to the task manager.
**Format**: `todo DESCRIPTION`
**Constraints**: `DESCRIPTION` must be a non-empty string.
**Example**: 
```
____________________________________________________________
todo play football
Got it. I've added this task:
[T] [ ] play football
Now you have 9 tasks in the list.
____________________________________________________________
```

<h2 id="adding-a-task-deadline"> Adding a deadline: <code class="language-plaintext highlighter-rouge">deadline</code></h2>

Add a deadline to the task manager.
**Format**: `deadline DESCRIPTION /by DATE`
**Constraints**: `DESCRIPTION` must be a non-empty string. `DATE` can be in the format of `YYYY-MM-DD`. 
If the `DATE` is in the format of `YYYY-MM-DD`, the manager will automatically convert it to `MMM DD YYYY`. 
If the `DATE` is not in the format of `YYYY-MM-DD`, the manager still accepts it but will not convert it.
**Example**: 
```
____________________________________________________________
deadline Midterm assignment /by not announce yet
Got it. I've added this task:
[D] [ ] Midterm assignment (by: not announce yet)
Now you have 10 tasks in the list.
____________________________________________________________
deadline Final exam /by 2019-10-15
Got it. I've added this task:
[D] [ ] Final exam (by: Oct 15 2019)
Now you have 11 tasks in the list.
____________________________________________________________
```

<h2 id="adding-a-task-event"> Adding an event: <code class="language-plaintext highlighter-rouge">event</code></h2>

Add an event to the task manager.
**Format**: `event DESCRIPTION /from START_DATE /to END_DATE`
**Constraints**: `DESCRIPTION` must be a non-empty string. `START_DATE` and `END_DATE` can be in the format of `YYYY-MM-DD`.
If the `START_DATE` and `END_DATE` are in the format of `YYYY-MM-DD`, the manager will automatically convert it to `MMM DD YYYY`.
If the `START_DATE` and `END_DATE` are not in the format of `YYYY-MM-DD`, the manager still accepts it but will not convert it.
**Example**: 
```
____________________________________________________________
event recess week /from 2023-2-20 /to 2023-2-27
Got it. I've added this task:
[E] [ ] recess week (from: 2023-2-20 to: 2023-2-27)
Now you have 12 tasks in the list.
____________________________________________________________
event tour /from 2023-02-20 /to 2023-02-22
Got it. I've added this task:
[E] [ ] tour (from: Feb 20 2023 to: Feb 22 2023)
Now you have 13 tasks in the list.
____________________________________________________________
```

<h2 id="listing-all-tasks-list"> Listing all tasks: <code class="language-plaintext highlighter-rouge">list</code></h2>

List all the tasks in the task manager.
**Format**: `list`
**Example**: 
```
____________________________________________________________
list
Here are the tasks in your list:
1.[T] [X] readbook
2.[E] [ ] project meeting (from: Aug 6th 2pm to: 4pm)
3.[T] [ ] join sports club
____________________________________________________________
```


<h2 id="marking-a-task-as-done-mark"> Marking a task as done: <code class="language-plaintext highlighter-rouge">mark</code></h2>

Mark a task as done.
**Format**: `mark INDEX`
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. 
If the `INDEX` is out of range, an error message will be shown. 
If the task is already marked as done, an warning message will be shown.
**Example**: 
```
____________________________________________________________
mark 2
Nice! I've marked this task as done:
[E] [X] project meeting (from: Aug 6th 2pm to: 4pm)
____________________________________________________________
mark 2
[Warning] This task is already done!
Nice! I've marked this task as done:
[E] [X] project meeting (from: Aug 6th 2pm to: 4pm)
____________________________________________________________
mark -1
[>Error] [mark] Sorry, the id is invalid!
____________________________________________________________
```
<h2 id="unmarking-a-task-as-done-unmark"> Unmarking a task as done: <code class="language-plaintext highlighter-rouge">unmark</code></h2>

When a task is marked as done, you can unmark it as done.
**Format**: `unmark INDEX`
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. 
If the `INDEX` is out of range, an error message will be shown. 
If the task is not marked as done, an warning message will be shown.
**Example**: 
```
____________________________________________________________
unmark 2
Nice! I've unmarked this task as done:
[E] [ ] project meeting (from: Aug 6th 2pm to: 4pm)
____________________________________________________________
unmark 2
[Warning] This task is not done yet!
Nice! I've unmarked this task as done:
[E] [ ] project meeting (from: Aug 6th 2pm to: 4pm)
____________________________________________________________
unmark 10
[>Error] [unmark] Sorry, the id is invalid!
____________________________________________________________
```

<h2 id="finding-a-task-find"> Finding a task: <code class="language-plaintext highlighter-rouge">find</code></h2>

Find all the tasks that contain the keyword.
**Format**: `find KEYWORD`
**Constraints**: `KEYWORD` must be a non-empty string.
**Example**: 
```
____________________________________________________________
find book
Here are the matching tasks in your list:
1.[T] [X] readbook
3.[T] [ ] borrow book
____________________________________________________________
```

<h2 id="deleting-a-task-delete"> Deleting a task: <code class="language-plaintext highlighter-rouge">delete</code></h2>
Delete a task from the task manager by its index.

**Format**: `delete INDEX`
**Constraints**: `INDEX` must be a positive integer and must be within the range of the task list. If the `INDEX` is out of range, an error message will be shown.
**Example**: 
```
____________________________________________________________
delete 2
Noted. I've removed this task:
[E] [ ] project meeting (from: Aug 6th 2pm to: 4pm)
Now you have 8 tasks in the list.
____________________________________________________________
delete 0
[>Error] [delete] Sorry, the id is invalid!
____________________________________________________________
```

<h2 id="searching-a-task-by-date-date"> Searching a task by date: <code class="language-plaintext highlighter-rouge">date</code></h2>

Search all the tasks that are on the date.
**Format**: `date DATE`
**Constraints**: `DATE` must be in the format of `YYYY-MM-DD`.
**Example**: 
```
____________________________________________________________
date 2019-10-15
Here are the matching tasks in your list:
5.[D] [ ] running (by: Oct 15 2019)
8.[E] [X] greeting (from: Oct 14 2019 to: Oct 16 2019)
11.[D] [ ] Final exam (by: Oct 15 2019)
____________________________________________________________
```

<h2 id="exiting-the-program-bye"> Exiting the program: <code class="language-plaintext highlighter-rouge">bye</code></h2>

Exit the program.
**Format**: `bye`
**Example**: 
```
bye
Bye. Hope to see you again soon!
  ____  ___   ___  ____  ______   _______ _
 / ___|/ _ \ / _ \|  _ \| __ ) \ / / ____| |
| |  _| | | | | | | | | |  _ \\ V /|  _| | |
| |_| | |_| | |_| | |_| | |_) || | | |___|_|
 \____|\___/ \___/|____/|____/ |_| |_____(_)

____________________________________________________________

Process finished with exit code 0
```

<h2 id='faq'> FAQ </h2>
<strong>Q</strong>: If I do not use the correct format, will the program still work?<br>
<strong>A</strong>: No, the program will show an error message and provide you the correct format.<br>
<strong>Example</strong>: 
```
____________________________________________________________
event hahahah
[>Error] Please use the format: event <task name> /from <event time from> /to <event time to>
____________________________________________________________
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
|  [date](#searching-a-task-by-date-date)  | 	**date** DATE  | date 2023-02-28 |
|  [bye](#exiting-the-program-bye)  | 	**bye**  | bye |