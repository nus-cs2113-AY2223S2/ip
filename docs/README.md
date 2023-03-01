<div id="top"></div>

# User Guide

Duke is a command-line (CLI) chatbot application that helps you keep track of your tasks.

- [Quick Start](#quick-start)
- [Features](#features)
- [Usage](#usage)
  - [`add` - Adding tasks](#add)
  - [`list` - Printing list of tasks](#list)
  - [`mark` - Marking task as done](#mark)
  - [`unmark` - Marking task as undone](#unmark)
  - [`find` - Finding task by keyword](#find)
  - [`delete` - Deleting task](#delete)
  - [`bye` - Exiting the application](#exit)
- [Command Summary](#command-summary)

<div id="quick-start"></div>

## Quick Start

1. Ensure that you have Java 11 installed in your computer. To check your java version, open your terminal and type the following command `java --version` and the following should show up:

```
C:\Users\example>java --version
java 11.0.17 2022-10-18 LTS
```

2. Download the latest `.jar` file from [here](https://github.com/ong-ck/ip/releases). You may move it to a directory of your choice in your computer.
3. Open the terminal in the directory that the `.jar` file is stored and type the following command `java -jar ip.jar`. A welcome message should pop up as follows:

```
C:\Users\example>java -jar ip.jar
____________________________________________________________
 ____        _
|  _ \ _   _| | _____
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|


Hello! I'm Duke
What can I do for you?
____________________________________________________________
```

4.You may start using Duke following the instructions in this user guide!

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="features"></div>

## Features

### Adding tasks into your list

You can add tasks, of three different types - todo, deadline and event - into your list to keep track of them.

### Printing your list of tasks

You can print out your list of items for viewing.

### Mark/Unmark your task as done/undone

You can mark/unmark your task as done/undone to track completion.

### Finding task(s) by keyword

You can find the tasks within your list that matches a keyword.

### Deleting tasks in your list

You can delete tasks in your list that you do not need to keep track of anymore.

### Saving tasks in your list locally

Your list of tasks are saved regularly at every update and after exiting the program. The saved tasks are also loaded at the start of the program.

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="usage"></div>

## Usage

<div id="add"></div>

### `add` - Adding tasks

You can add tasks, of three different types - todo, deadline and event - into your list to keep track of them.

Format:

- `todo NAME_OF_TASK`
- `deadline NAME_OF_TASK /by DEADLINE_OF_TASK`
- `event NAME_OF_EVENT /from EVENT_START /to EVENT_END`

Example of usage:

- `todo TaskA`
- `deadline TaskB /by 8 May 2023 6pm`
- `deadline TaskC /by 2023-01-01`
- `event EventD /from 1 May /to 2 May`

Note: For `deadline` tasks, if you have input your `DEADLINE_OF_TASK` in the following format `yyyy-mm-dd` (e.g., `2019-10-15`), it will be reformatted to a different format `mmm dd yyyy` e.g., (`Oct 15 2019`). Formats other than the above will not reformat the `DEADLINE_OF_TASK`.

Expected outcome:

Feedback will be given by Duke to inform you that your task has been added.

```
____________________________________________________________
todo TaskA
____________________________________________________________
Great job adding a new task!
Added: [T][ ] TaskA
You currently have 1 tasks.
____________________________________________________________
deadline this /by 2022-03-03
____________________________________________________________
Great job adding a new task!
Added: [D][ ] this (by: Mar 03 2022)
You currently have 2 tasks.
____________________________________________________________
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="list"></div>

### `list` - Printing list of tasks

You can print out your list of items for viewing.

Format: `list`

Note: The command `list` should not have any other keywords after the command. An error message will return if other keywords are added.

Example of usage and expected outcome:

The items added will be printed out as a list. If there are no items in the list, a message will be printed instead.

```
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] TaskA
____________________________________________________________
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="mark"></div>

### `mark` - Mark task as done

You can mark your task as done to track completion.

Format: `mark INDEX`
Example of usage: `mark 1`

Note: The `INDEX` must be a integer within the indexes present when `list` command is executed.

Expected outcome:

The task at `INDEX` will be marked. It will be shown with an 'X' in the checkbox when the list is printed.

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] TaskA
____________________________________________________________
mark 1
____________________________________________________________
Nice! I've marked this task as done:
[T][X] TaskA
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][X] TaskA
____________________________________________________________
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="unmark"></div>

### `unmark` - Marking task as undone

You can unmark your task as undone to track completion.

Format: `unmark INDEX`
Example of usage: `unmark 1`

Note: The `INDEX` must be a integer within the indexes present when `list` command is executed.

Expected outcome:

The task at `INDEX` will be unmarked. It will be shown with an space in the checkbox when the list is printed.

```
____________________________________________________________
Here are the tasks in your list:
1.[T][X] TaskA
____________________________________________________________
unmark 1
____________________________________________________________
OK, I've marked this task as not done yet:
[T][ ] TaskA
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] TaskA
____________________________________________________________
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="find"></div>

### `find` - Finding task by keyword

You can find the tasks within your list that matches a keyword.

Format: `find KEYWORD`
Example of usage: `find book`

Note: `KEYWORD` is case-sensitive.

Expected outcome:

All tasks saved that matches the `KEYWORD` will be printed as a list.

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] TaskA
2.[T][ ] TaskB
3.[T][ ] ItemC
____________________________________________________________
find Task
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] TaskA
2.[T][ ] TaskB
____________________________________________________________
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="delete"></div>

### `delete` - Deleting task

You can delete tasks in your list that you do not need to keep track of anymore.

Format: `delete INDEX`
Example of usage: `delete 1`

Note: The `INDEX` must be a integer within the indexes present when `list` command is executed.

Expected outcome:

The task at `INDEX` will be removed from the list of saved tasks.

```
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] TaskA
2.[T][ ] TaskB
3.[T][ ] ItemC
____________________________________________________________
delete 1
____________________________________________________________
OK, the following task has been removed:
[T][ ] TaskA
You currently have 2 tasks.
____________________________________________________________
list
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] TaskB
2.[T][ ] ItemC
____________________________________________________________
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="exit"></div>

### `bye` - Exiting the application

You can exit the application.

Format: `bye`

Example of usage and expected outcome:

```
____________________________________________________________
bye
____________________________________________________________
Bye. Hope to see you again soon!
____________________________________________________________
```

<p align="right">(<a href="#top">back to top</a>)</p>
<div id="command-summary"></div>

## Command Summary

<table>
<thead>
  <tr>
    <th>Action</th>
    <th>Format</th>
    <th>Example(s)</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td>Add</td>
    <td>`todo NAME_OF_TASK`<br>`deadline NAME_OF_TASK /by DEADLINE_OF_TASK`<br>`event NAME_OF_EVENT /from EVENT_START /to EVENT_END`</td>
    <td>`todo TaskA`<br>`deadline TaskB /by 8 May 2023 6pm`<br>`deadline TaskC /by 2023-01-01`<br>`event EventD /from 1 May /to 2 May`</td>
  </tr>
  <tr>
    <td>List</td>
    <td>`list`</td>
    <td>-</td>
  </tr>
  <tr>
    <td>Mark</td>
    <td>`mark INDEX`</td>
    <td>`mark 1`</td>
  </tr>
  <tr>
    <td>Unmark</td>
    <td>`unmark INDEX`</td>
    <td>`unmark 1`</td>
  </tr>
  <tr>
    <td>Find</td>
    <td>`find KEYWORD`</td>
    <td>`find book`<br>`find Task`</td>
  </tr>
  <tr>
    <td>Delete</td>
    <td>`delete INDEX`</td>
    <td>`delete 1`</td>
  </tr>
  <tr>
    <td>Exit</td>
    <td>`bye`</td>
    <td>-</td>
  </tr>
</tbody>
</table>                                                                                                              | -                                                                                                                     |

<p align="right">(<a href="#top">back to top</a>)</p>
