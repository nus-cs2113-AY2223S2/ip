# User Guide

## Table of Contents

1. Execution Setup
2. Features

## Execution Setup

1. Ensure you have the latest version of Java (11 or above)

2. Download the latest copy of the duke.jar file

3. Move the .jar file to the folder where you want to run the program

4. Open the command terminal and navigate to the directory of that same folder which you save your .jar file at

5. Use the following command to run the programme: java -jar duke.jar

`>>> java -jar ip.jar`
    
6. If successful, you will see the following welcome message on the command terminal:

![img.png](img.png)

## Features 

1. Greetings 
2. Add a todo Task
3. Add a deadline Task
4. Add an event Task
5. List tasks
6. Mark tasks as done/undone
7. Delete tasks
8. Find tasks
9. Terminate the programme

## Usage of Commands and Examples

### `todo` - Adds a todo task to the tasklist

Adds a todo task to the tasklist and displays the number of tasks currently in the tasklist.

Example of usage: 

`todo return book`

Expected outcome:

```
Got it. I've added this task:
 [T][] return book
Now you have 1 tasks in the list.
```
### `deadline` - Adds a deadline task to the tasklist

Adds a deadline task to the tasklist and displays the number of tasks currently in the tasklist.


Example of usage:

`deadline return book /by Friday`

Expected outcome:

```
Got it. I've added this task:
 [D][] return book (by: Friday)
Now you have 2 tasks in the list.
```
### `event` - Adds a event task to the tasklist

Adds an event task to the tasklist and displays the number of tasks currently in the tasklist.


Example of usage:

`event attend birthday party /from 6pm /to 10pm`

Expected outcome:

```
Got it. I've added this task:
 [E][] attend birthday part (from: 6pm to: 10pm)
Now you have 3 tasks in the list.
```
### `list` - Shows all current stored tasks

Shows all tasks currently stored in the tasklist.

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[T][] return book
2.[D][] return book (by: Friday)
3.[E][] attend birthday part (from: 6pm to: 10pm)
```
### `mark` - Marks a specific task as done in the tasklist

Mark the status of a specific task as done, and updates a status icon to [X] that represents it being marked.

Example of usage:

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done:
  [X] return book
```
### `unmark` - Unmarks a specific task as undone in the tasklist

Unmark the status of a specific task as undone, and updates a status icon to [] that represents it being marked.

Example of usage:

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
  [] return book
```
### `delete` - Deletes a specific task from the tasklist

Deletes a specified task from the tasklist and displays the number of tasks remaining in the tasklist.

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
 [E][] attend birthday part (from: 6pm to: 10pm)
Now you have 2 tasks in the list.
```
### `find` - Finds all relevant tasks in the tasklist which contain the keyword

Finds all the relevant tasks in the tasklist that contains the keyword given by the user and displays it to the user alongside its index in the tasklist.

Example of usage:

`find birthday`

Expected outcome:

```
____________________________________________________________
Here are the matching tasks in your list:
2.[E][] attend birthday part (from: 6pm to: 10pm)
____________________________________________________________
```
### `bye` - Terminates the programme

Terminates the program and outputs an exit message to the user.
The tasklist would have been updated alongside the commands for the user to use once they rerun the programme.

Example of usage:

`bye`

Expected outcome:

```
Bye. Hope to see you again soon!
```
