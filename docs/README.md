# User Guide for Duke Program

## Execution Setup of Duke Program

1. Ensure you have the latest version of Java (11 or above)

2. Download the latest copy of the duke.jar file [here](https://github.com/ghzr0/ip/releases)

3. Move the .jar file to the folder where you want to run the program

4. Open the command terminal and navigate to the directory of that same folder which you save your .jar file at

5. Use the following command to run the programme: java -jar duke.jar

6. If successful, you will see the following welcome message on the command terminal:

![image](https://user-images.githubusercontent.com/88625158/221926439-622d791d-6307-4c67-8d92-b87488ceb8e5.png)


## Features of Duke Program

### Task Addition

Add a task to the tasklist

### Task Deletion

Delete a task, specified by the user, from the tasklist

### Listing of Tasks

Displays all the tasks currently stored in the tasklist to the user

### Marking of Task

Mark a task,specified by the user, as done in the tasklist

### Unmarking of Task

Unmark a task,specified by the user, in the tasklist

### Finding Task

Finds all relevant tasks in the tasklist based on a keyword specified by the user

## Usage of Commands and Examples

### `todo` - Adds a todo task to the tasklist

Adds a todo task to the tasklist and also displays the number of tasks currently in the tasklist.

Example of usage: 

`todo return book`

Expected outcome:

A task of type todo has been added to the task list, alongside a statement displaying the number of tasks in the list.

![image](https://user-images.githubusercontent.com/88625158/221926742-015ea6f5-c889-401d-89be-d3411e2a919c.png)

### `deadline` - Adds a deadline task to the tasklist

Adds a deadline task to the tasklist and also displays the number of tasks currently in the tasklist.

Example of usage: 

`deadline submit assignment /by Friday 2359`

Expected outcome:

A task of type deadline has been added to the task list, alongside a statement displaying the number of tasks in the list.

![image](https://user-images.githubusercontent.com/88625158/221926925-0370c740-8d4a-4120-ba3c-2e7c9117d159.png)

### `event` - Adds an event task to the tasklist

Adds an event task to the tasklist and also displays the number of tasks currently in the tasklist.

Example of usage: 

`event attend project meeting /from noon /to evening`

Expected outcome:

A task of type event has been added to the task list, alongside a statement displaying the number of tasks in the list.

![image](https://user-images.githubusercontent.com/88625158/221927036-c5f873d1-2d55-4394-81f1-e9cde99c9af7.png)

### `list` - Shows all current stored tasks

Shows all tasks currently stored in the tasklist.

Example of usage: 

`list`

Expected outcome:

Shows all stored tasks in the tasklist.

![image](https://user-images.githubusercontent.com/88625158/221927133-fd6ea9ae-a76d-4087-9f94-93cd6e267f8c.png)

### `delete` - Deletes a task specified by user from the tasklist

Deletes a specified task from the tasklist and also displays the number of tasks remaining in the tasklist.

Example of usage: 

`delete 1`

Expected outcome:

Deletes the first task from the tasklist, and also displays the remaining number of tasks left in the tasklist.

![image](https://user-images.githubusercontent.com/88625158/221927184-f130d3a6-d7f6-4315-a323-7bc10a6120a0.png)

### `mark` - Marks a task specified by user as done in the tasklist 

Mark the status of a specified task as done, and updates a status icon indicating it being marked.

Example of usage: 

`mark 1`

Expected outcome:

The first task of the tasklist is marked, indicated by the 'X' character in the second square brackets.

![image](https://user-images.githubusercontent.com/88625158/221927287-dfab6c3a-44cd-4ebc-9415-29409255fe40.png)

### `unmark` - Unmarks a task specified by user in the tasklist 

Unmark the status of a specified task, and updates a status icon indicating it being marked.

Example of usage: 

`unmark 1`

Expected outcome:

The first task of the tasklist is unmarked, indicated by the empty whitespace in between the second square brackets.

![image](https://user-images.githubusercontent.com/88625158/221927366-0fe9f963-1449-4816-bb80-4c81540ba61f.png)

### `find` - Finds all relevant tasks in the tasklist which contain the keyword(s) specified by the user

Finds the tasks containing the keyword specified by the user in the tasklist, and outputs them.

Example of usage: 

`find project`

Expected outcome:

Displays all tasks with the matching keyword in the tasklist.

![image](https://user-images.githubusercontent.com/88625158/221927446-f422a4bf-8715-4616-8976-f94fddb5d835.png)

### `bye` - Terminates the program and saves all current tasks stored in the tasklist

Terminates the program and outputs an exit message to the user.

All tasks that have been added to the tasklist will be saved and updated to a text file, which can be accessed when re-opening the program.

Example of usage: 

`bye`

Expected outcome:

Terminates the program and outputs an exit message to the user.

![image](https://user-images.githubusercontent.com/88625158/221928376-04d95da2-2ebf-4823-b76b-95932fe7e598.png)

