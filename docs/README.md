# 📜 <span style="color:DarkMagenta">User Guide</span> 

<pre>

██╗░░██╗██╗███╗░░██╗░██████╗░
██║░██╔╝██║████╗░██║██╔════╝░
█████═╝░██║██╔██╗██║██║░░██╗░
██╔═██╗░██║██║╚████║██║░░╚██╗
██║░╚██╗██║██║░╚███║╚██████╔╝
╚═╝░░╚═╝╚═╝╚═╝░░╚══╝░╚═════╝░
</pre>

King is a Command Line Interface (CLI) based application for keeping track of your tasks.

___

<!-- Table of Contents -->
##  <span style="color:DarkMagenta">Table of Contents</span> 📔
- [Quick Start](#quick-start)
- [Features](#features)
    + [Adding a Task](#adding-a-task)
        1. [Todo: `todo`](#todo)
        2. [Event: `event`](#event)
        3. [Deadline: `deadline`](#deadline)
    + [List all tasks: `list`](#list)
    + [Mark a task: `mark`](#mark)
    + [Unmark a task: `unmark`](#unmark)
    + [Delete a task: `delete`](#delete)
    + [Find a task: `find`](#find)
    + [Exit the program: `bye`](#bye)
    + [Saving the data](#saving)
    + [Editing the data file](#editing)
- [FAQ](#faq)
- [Command Summary](#command-summary)

___

<!-- Quick Start guide -->
## <span id="quick-start" style="color:DarkMagenta">Quick Start</span> ⚙️
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest `king.jar` [here](https://github.com/bentohset/ip/releases/download/A-Release/king-0.2.jar).
3. Copy the file to the folder you want to use as the home folder for your King.
4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar king.jar` command to run the application.
5. Type the command in the command box and press Enter to execute it.
    <br/>Some example commands you can try:
    - `list`: Lists all tasks.
    - `todo read book`: Adds a Todo task named `read book` to the Task List.
    - `delete 1`: Deletes the 1st task in the current list.
    - `bye`: Exits the app.
6. Refer to the [Features](#features) below for details of each command

___

<!-- Feature List -->
## <span id="features" style="color:DarkMagenta">Features</span> 👾

### Adding a Task<a id='adding-a-task'></a>:
_3 types of tasks supported - Todo, Event and Deadline:_

### 1. Todo: `todo`<a id='todo'></a>
Adds a todo task to the task list.<br/>
Format: `todo TASK_NAME`
- `TASK_NAME` accepts a String of multiple words
- `TASK_NAME` is compulsory

Examples:
```js
todo complete CS2113 ip level
todo read
```

### 2. Event: `event`<a id='event'></a>
Adds an event task to the task list.<br/>
Format: `event TASK_NAME /by BY_DATE`
- `TASK_NAME` accepts a String of multiple words and is **compulsory**.
- `/by` is **compulsory**.
- `BY_DATE` accepts a string in the format of YYYY-MM-DD HHmm.
    - `YYYY` represents the year (eg. `2023`)
    - `MM` represents the month (eg. `04` for April)
    - `DD` represents the day (eg. `08`)
    - `HHmm` represents the time in 24-hour format (eg. `0530` or `1830`)
- `BY_DATE` **must** be entered in the correct format.

Examples:
```js
deadline CS2113 homework /by 2023-06-26 2359
deadline apply BTO /by 2025-08-01 1200
```


### 3. Deadline: `deadline`<a id='deadline'></a>
Adds a deadline task to the task list. <br/>
Format: `deadline TASK_NAME /from FROM_DATE /to TO_DATE`
- `TASK_NAME` accepts a String of multiple words and is **compulsory**.
- `FROM_DATE` and `TO_DATE` accepts a string in the format of YYYY-MM-DD HHmm.
    - `YYYY` represents the year (eg. `2023`)
    - `MM` represents the month (eg. `04` for April)
    - `DD` represents the day (eg. `08`)
    - `HHmm` represents the time in 24-hour format (eg. `0530` or `1830`)
- `FROM_DATE` and `TO_DATE` **must** be entered in the correct format.

Examples:
```js
event NUS open day /from 2023-07-01 1000 /to 2023-07-01 1600
event valorant comp /from 2025-04-01 0800 /to 2025-04-10 1600
```

<br/>

### List all tasks: `list`<a id='list'></a>
Shows a list of all tasks in the task list.<br/>
Format: `list`

<br/>

### Mark a task: `mark`<a id='mark'></a>
Marks a specific task as done.<br/>
Format: `mark TASK_NUMBER`
- `TASK_NUMBER` accepts positive integer from 1 to N (the number of tasks in the list)
- The task number refers to the index number shown in the displayed `list`

Examples:
- `list` followed by `mark 1` marks the 1st task in the list as done.

<br/>

### Unmark a task: `unmark`<a id='unmark'></a>
Unmarks a specific task as not done.<br/>
Format: `unmark TASK_NUMBER`
- `TASK_NUMBER` accepts positive integer from 1 to N (the number of tasks in the list)
- The task number refers to the index number shown in the displayed `list`

Examples:
- `list` followed by `mark 3` unmarks the 3rd task in the list as undone.


<br/>

### Delete a task: `delete`<a id='delete'></a>
Deletes a specific task in the task list.<br/>
Format: `delete TASK_NUMBER`
- `TASK_NUMBER` accepts positive integer from 1 to N (the number of tasks in the list)
- The task number refers to the index number shown in the displayed `list`

Examples:
- `list` followed by `delete 2` deletes the 2nd task in the list


<br/>

### Find a task: `find`<a id='find'></a>
Searches the task list for all tasks containing the entered keyword.<br/>
Format: `find KEYWORD`
- `KEYWORD` accepts a snlge `String` or multiple `Strings`
- `KEYWORD` is not case-sensitive
- Only exact matches will be found
- If no matches are found `My utmost apologies sir, nay tasks fit thy keyword KEYWORD` is returned

Examples:
```js
find Book
find event
```


<br/>

### Exit the program: `bye`<a id='bye'></a>
Exits the program.<br/>
Format: `bye`

<br/>

### Saving the data<a id='saving'></a>
King's data are saved in the hard disk automatically after exiting the program. <br/>
There is no need to save manually.

<br/>

### Editing the data file<a id='editing'></a>
King's data are saved as a .txt file in `[JAR file location]/data/king.txt`. <br/>
Edits must be made according to the formatting of the data.

<br/>

___
<!-- FAQs -->
## <span id="faq" style="color:DarkMagenta">FAQ</span> 💻
> Q: How do I transfer my data to another Computer?
>
> A: In your new application folder in the new Computer, paste the copied `/data/king.txt` file from your old Computer into the same folder as the new `king.jar`.

___
<!-- Summary of Commands -->
## <span id="command-summary" style="color:DarkMagenta">Command Summary</span> 🔑

| Command      | Format                                        |
|--------------|-----------------------------------------------|
| **todo**     | `todo TASK_NAME`                              |
| **deadline** | `deadline TASK_NAME /by BY_DATE`              |
| **event**    | `event TASK_NAME /from FROM_DATE /to TO_DATE` |
| **list**     | `list`                                        |
| **mark**     | `mark TASK_NUMBER`                            |
| **unmark**   | `unmark TASK_NUMBER`                          |
| **delete**   | `delete TASK_NUMBER`                          |
| **find**     | `find KEYWORD`                                |
| **exit**     | `bye`                                         |