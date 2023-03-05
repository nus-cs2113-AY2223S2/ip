# User Guide
This is a user guide for CS2113 Individual Project by Reynold Samel Lam

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the
   existing project first)
2. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
3. Configure the project to use **JDK 11** (not other versions) as explained in
   [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
4. After that, locate the `src/main/java/duke/Duke` file, right-click it, and choose `Run Duke.main()`
   (if the code editor is showing compile errors, try restarting the IDE).
   If the setup is correct, you should see something like the below as the output.
````
███████████████████████████████████████████████████████████████████████████████████████████████
█████████░░░░░░█░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██████████░░░░░░█
█████████░░▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██████████░░▄▀░░█
█████████░░▄▀░░█░░░░▄▀░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██████████░░▄▀░░█
█████████░░▄▀░░███░░▄▀░░███░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██████████░░▄▀░░█
█████████░░▄▀░░███░░▄▀░░███░░▄▀░░█████████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░░░░░██░░▄▀░░█
█████████░░▄▀░░███░░▄▀░░███░░▄▀░░██░░░░░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░██░░▄▀░░█
█░░░░░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░█░░░░░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░██░░▄▀░░█
█░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░█████████░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░░░▄▀░░█
█░░▄▀░░░░░░▄▀░░█░░░░▄▀░░░░█░░▄▀░░░░░░▄▀░░█░░░░░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀░░█
█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░░░▄▀░░█
█░░░░░░░░░░░░░░█░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░██░░░░░░██░░░░░░█
███████████████████████████████████████████████████████████████████████████████████████████████

Hello! I'm Jigsaw

What can I do for you?
````
If you have already run the program before and to check if the previously saved data is loaded,
type "list" into the command line and a list of your previously saved tasks will be printed.
You should see something like the output below (task 1 to 4 in the list are only used as examples):
````
list
1.[T][ ] read
2.[E][ ] write (from: 5pm to: 6pm)
3.[D][ ] try (by: 7pm)
4.[E][ ] read (from: 5pm to: 6pm)
````
However, if there is no folder or file loaded, a message - "Folder not found" will be printed at the beginning of
the startup of the program.
You should see something like the output below:
````
Folder not found
███████████████████████████████████████████████████████████████████████████████████████████████
█████████░░░░░░█░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██████████░░░░░░█
█████████░░▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██████████░░▄▀░░█
█████████░░▄▀░░█░░░░▄▀░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██████████░░▄▀░░█
█████████░░▄▀░░███░░▄▀░░███░░▄▀░░█████████░░▄▀░░█████████░░▄▀░░██░░▄▀░░█░░▄▀░░██████████░░▄▀░░█
█████████░░▄▀░░███░░▄▀░░███░░▄▀░░█████████░░▄▀░░░░░░░░░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░░░░░██░░▄▀░░█
█████████░░▄▀░░███░░▄▀░░███░░▄▀░░██░░░░░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░██░░▄▀░░█
█░░░░░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░█░░░░░░░░░░▄▀░░█░░▄▀░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░██░░▄▀░░█
█░░▄▀░░██░░▄▀░░███░░▄▀░░███░░▄▀░░██░░▄▀░░█████████░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░░░▄▀░░█
█░░▄▀░░░░░░▄▀░░█░░░░▄▀░░░░█░░▄▀░░░░░░▄▀░░█░░░░░░░░░░▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀▄▀░░█
█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀▄▀▄▀▄▀▄▀░░█░░▄▀░░██░░▄▀░░█░░▄▀░░░░░░▄▀░░░░░░▄▀░░█
█░░░░░░░░░░░░░░█░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░░░░░░░░░█░░░░░░██░░░░░░█░░░░░░██░░░░░░██░░░░░░█
███████████████████████████████████████████████████████████████████████████████████████████████

Hello! I'm Jigsaw

What can I do for you?
````

## Features

### Feature - Greet

When booting the program, Jigsaw greets the user with a welcome message

### Feature - Goodbye

On exiting the program, Jigsaw sends the user a goodbye message and a picture of Jigsaw

### Feature - Tasks

Jigsaw will keep track of your various tasks and display them so that you will not forget them

### Feature - Mark

You can tell Jigsaw to mark tasks when you are done or unmark tasks when you need to complete them in the future

### Feature - Save and Load

Jigsaw is able to save and load data from previous interactions!

### Feature - Find Tasks
Jigsaw is able to find tasks with certain keywords and display the tasks containing the identified keyword

## Usage

### `list` - Lists the tasks stored

Displays the tasks stored thus far.

Enter the following command into the command line:
`list`

Expected outcome:

````
1.[T][ ] read
2.[E][ ] write (from: 5pm to: 6pm)
3.[D][ ] try (by: 7pm)
4.[E][ ] read (from: 5pm to: 6pm)
````

### `todo` - Adds a new Todo task

Creates a new Todo task and stores it in the task list.
It also displays the number of tasks currently stored in your list.

Enter the following command in the format into the command line:
`todo {description}`

Example of usage:
`todo read books`

Expected outcome:

````
Got it I have added this task:
  [T][ ] read books
Now you have 1 tasks in the list.
````

### `deadline` - Adds a new Deadline task

Creates a new Deadline task and stores it in the task list. It also displays the number of tasks currently stored in
your list. This input requires a date or time.

Enter the following command in the format into the command line:
`deadline {description} /by {datetime}`

Example of usage:
`deadline write books /by 5pm`

Expected outcome:

````
Got it I have added this task:
  [D][ ] write books (by: 5pm)
Now you have 3 tasks in the list.
````

### `event` - Adds a new Event task

Creates a new Event task and stores it in the task list. It also displays the number of tasks currently stored in
your list. Requires an input of a valid date or time.

Enter the following command in the format into the command line:
`event {description} /from {datetime} /to {datetime}`

Example of usage:
`event listen to music /from 5pm /to 6pm`

Expected outcome:

````
Got it I have added this task:
  [E][ ] listen to music (from: 5pm to: 6pm)
Now you have 4 tasks in the list.
````

### `mark` - Marks a task

Mark the task number. Requires an input of a valid task number.

Enter the following command in the format into the command line:
`mark {task number}`

Example of usage:
`mark 2`

Expected outcome:

````
Nice! I've marked this task as done:
  [X] write books
````

### `unmark` - Unmarks a task

Unmarks the task number. Requires an input of a valid task number.

Enter the following command in the format into the command line:
`unmark {task number}`

Example of usage:
`unmark 2`

Expected outcome:

````
OK, I've marked this task as not done yet:
  [ ] write books
````

### `delete` - Deletes a task

Deletes the task at given task number. Also displays the number of tasks remaining after deletion. Requires an input of a valid task number.

Enter the following command in the format into the command line:
`delete {task number}`

Example of usage:
`delete 1`

Expected outcome:

````
Noted. I've removed this task:
  [T][X] read books
Now you have 2 tasks in the list.
````

### `find` - Find keyword

Search keyword within description of all tasks in task list. Displays a list of tasks with the given keyword in the description.

Enter the following command in the format into the command line:
`find {keyword}`

Example of usage:
`find books`

Expected outcome:

````
Here are the matching tasks in your list:
1.[E][ ] read books (from: 5pm to: 6pm)
2.[D][ ] design books (by: monday)
3.[T][ ] read books
````

### `bye` - Bye keyword

Exits the program and saves all items in the tasklist to a data file

Enter the following command into the command line:
`bye`

Expected outcome:

````
Bye. Hope to see you again soon!

─────▄██▀▀▀▀▀▀▀▀▀▀▀▀▀██▄─────
────███───────────────███────
───███─────────────────███───
──███───▄▀▀▄─────▄▀▀▄───███──
─████─▄▀────▀▄─▄▀────▀▄─████─
─████──▄████─────████▄──█████
█████─██▓▓▓██───██▓▓▓██─█████
█████─██▓█▓██───██▓█▓██─█████
█████─██▓▓▓█▀─▄─▀█▓▓▓██─█████
████▀──▀▀▀▀▀─▄█▄─▀▀▀▀▀──▀████
███─▄▀▀▀▄────███────▄▀▀▀▄─███
███──▄▀▄─█──█████──█─▄▀▄──███
███─█──█─█──█████──█─█──█─███
███─█─▀──█─▄█████▄─█──▀─█─███
███▄─▀▀▀▀──█─▀█▀─█──▀▀▀▀─▄███
████─────────────────────████
─███───▀█████████████▀───████
─███───────█─────█───────████
─████─────█───────█─────█████
───███▄──█────█────█──▄█████─
─────▀█████▄▄███▄▄█████▀─────
──────────█▄─────▄█──────────
──────────▄█─────█▄──────────
───────▄████─────████▄───────
─────▄███████───███████▄─────
───▄█████████████████████▄───
─▄███▀───███████████───▀███▄─
███▀─────███████████─────▀███
IIII||───███████████───||IIII
─────||──███████████──||─────
──────||─███████████─||──────
───────|||||||||||||||───────
─────────████||█████─────────
────────█████||██████────────
──────███████||███████───────
─────█████──█||█──█████──────
─────█████──████──█████──────
──────████──████──████───────
──────████──████──████───────
──────████───██───████───────
──────████───██───████───────
──────████──████──████───────
─██────██───████───██─────██─
─██───████──████──████────██─
─███████████████████████████─
─██─────────████──────────██─
─██─────────████──────────██─
────────────████─────────────
─────────────██──────────────
````
