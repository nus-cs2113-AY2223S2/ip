# User Guide
Duke is a desktop program to store a list of tasks for the user via a Command Line Interface (CLI).
## Quick Start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest `ip.jar` from [here](https://github.com/thomasjlalba/ip/releases).
3. Copy downloaded `ip.jar` file to the desired home folder of choice.
4. Open a command terminal and `cd` into the folder mentioned in step 3.
5. Type in the following to run the application:
```
java -jar ip.jar
```
6. If successful, the following greeting should appear:
```
____________________________________________________________
Hello! I'm Bob
What can I do for you?
____________________________________________________________
```
7. Type in desired command to start using the program! List of commands are listed below.

## Features
### Adding a todo: `todo`
Adds a task of type todo to the list.
<br>Todo contains a description.

Format: `todo DESCRIPTION`
<br>Example: `todo read book`

### Adding an event: `event`
Adds a task of type event to the list.
<br>Event contains a description, from and to.

Format: `event DESCRIPTION /from FROM /to TO`
<br>Example: `event CS2113 tutorial /from 4 April 2023 4pm /to 6pm`

### Adding a deadline: `deadline`
Adds a task deadline to the list.
<br>Deadline contains description and by.

Format: `deadline DESCRIPTION /by BY`
<br>Example: `deadline CS2113 iP /by 3 March 11.59pm`

### Viewing full list of tasks: `list`
Shows a list of all tasks recorded in the list.

Format: `list`

### Mark task as done: `mark`
Marks task as done based on the task number indicated.

Format: `mark TASK_NUMBER`
- TASK_NUMBER must be an integer that is within the list size

Example: `mark 2`

### Mark task as not done: `unmark`
Marks task as not done based on the task number indicated.

Format: `unmark TASK_NUMBER`
- TASK_NUMBER must be an integer that is within the list size

Example: `unmark 3`

### Delete task: `delete`
Deletes the task based on the task number indicated.

Format: `delete TASK_NUMBER`
- TASK_NUMBER must be an integer that is within the list size

Example: `delete 1`

### Clear entire list: `clear`
Deletes all the tasks in the list, making the list empty

Format: `clear`

### Find task(s): `find`
Searches the tasks in the list, returning the tasks containing the keyword in their description.

Format: `find KEYWORD`
<br>Example: `find CS2113`

### Viewing help: `help`
Shows the list of available commands for the program.

Format: `help`

### Exiting Duke: `bye`
Exits and closes the program.

Format: `bye`

### Saving the data
Duke's data is saved in a save.txt file created upon using the program. It will automatically save the list of tasks after every time a command is executed.

## FAQ
**Q**: Can I edit the file manually from the save.txt file?
<br>**A**: It is highly advised not to do so, as formatting of the save.txt file incorrectly will cause the program to crash.

## Command Summary
| **Command** | **Format, Examples**                                                                                 |
|-------------|------------------------------------------------------------------------------------------------------|
| todo        | `todo DESCRIPTION`<br>e.g. `todo read book`                                                          |
| event       | `event DESCRIPTION /from FROM /to TO`<br>e.g. `event CS2113 tutorial /from 4 April 2023 4pm /to 6pm` |
| deadline    | `deadline DESCRIPTION /by BY`<br>e.g. `deadline CS2113 iP /by 3 March 11.59pm`                       |
| list        | `list`                                                                                               |
| mark        | `mark TASK_NUMBER`<br>e.g. `mark 2`                                                                  |
| unmark      | `unmark TASK_NUMBER`<br>e.g. `unmark 3`                                                              |
| delete      | `delete TASK_NUMBER`<br>e.g. `delete 1`                                                              |
| clear       | `clear`                                                                                              |
| find        | `find KEYWORD`<br>e.g. `find CS2113`                                                                 |
| help        | `help`                                                                                               |
| bye         | `bye`                                                                                                |
