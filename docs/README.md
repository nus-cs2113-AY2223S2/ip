# User Guide for Duke

Duke is a Personal Assistant Chat bot that helps a person keep track of his/her tasking.

## How to start Duke

1. Download **jdk 11** by following the steps in [JDK Installation](https://docs.oracle.com/en/java/javase/11/install/overview-jdk-installation.html#GUID-8677A77F-231A-40F7-98B9-1FD0B48C346A).
2. Download the **JAR** file from the latest release. 
3. Run the **JAR** file in a terminal using the command `java -jar duke.jar`
   ![welcome.png](images%2Fwelcome.png)
4. Enter the command of your choice. The list of supported commands are listed in the **Features** section.

## Duke's Features 

---

### List of Features
1. Add a ToDo task: `todo`
2. Add a Deadline task: `deadline`
3. Add an Event task: `event`
4. Delete a task: `delete`
5. Mark a task as done: `mark`
6. Mark a task as not done: `unmark`
7. List all the tasks: `list`
8. Filter tasks using keyword: `find`
9. Save all tasks to hard disk
9. Exit Duke: `bye`

---

### Feature - Add a ToDo task: `todo`

Add a new ToDo task to Duke.
> A ToDo is a task that is to be done and does not come with a deadline or a venue.

Command : `todo [description]`

Parameter:
- **description**: The description of the Todo task.

Example :
![todo_eg.png](images%2Ftodo_eg.png)

---

### Feature - Add a Deadline task: `deadline`

Add a new Deadline task to Duke.
> A deadline is a task that is to be done by a specific time.

Command : `deadline [description] /by [due]`

Parameter:
- **description**: The description of the deadline task.
- **due**: The deadline of the task.

Example :
![delete_eg.png](images%2Fdelete_eg.png)

---

### Feature - Add an event task: `event`

Add a new event task to Duke.
> An event is a task that will take place between a specific start and end time.

Command : `event [description] /from [start] /to [end]`

Parameter:
- **description**: The description of the event task.
- **start**: The start time of the event.
- **end**: The end time of the event.

Example :
![event_eg.png](images%2Fevent_eg.png)

---

### Feature - Delete a task: `delete`

Delete a task from Duke. 
It is recommended to call `list` to get the correct index number of the task to be deleted before `delete`.
> A task can be either a Todo, Deadline or Event.

Command : `delete [task_number]`

Parameter:
- **task_number**: The index number of the task to be deleted.

Example :
![delete_eg.png](images%2Fdelete_eg.png)

---

### Feature - Mark a task as done: `mark`

Mark a task as done. 
It is recommended to call `list` to get the correct index number of the task to be marked done before `mark`.
> A task can be either a Todo, Deadline or Event.

Command : `mark [task_number]`

Parameter:
- **task_number**: The index number of the task to be deleted.

Example :
![mark_eg.png](images%2Fmark_eg.png)

---

### Feature - Mark a task as not done: `unmark`

Mark a task as not done. 
It is recommended to call `list` to get the correct index number of the task to be marked not done before `unmark`.
> A task can be either a Todo, Deadline or Event.

Command : `unmark [task_number]`

Parameter:
- **task_number**: The index number of the task to be deleted.

Example :
![unmark_eg.png](images%2Funmark_eg.png)

---

### Feature - List all the tasks: `list`

List all the tasks currently in Duke. 
All properties of a task will be display, including its index number, description, task type and done status.

Command : `list`

Example :
![list_eg.png](images%2Flist_eg.png)

---

### Feature - Filter tasks using keyword: `find`

List all the tasks currently in Duke that contains a specific keyword in its description.

Command : `find [keyword]`

Parameter:
- **keyword**: The keyword to search for in a task's description.

Example :
![find_eg.png](images%2Ffind_eg.png)

---

### Feature - Save all tasks to hard disk

All tasks in Duke are automatically saved into ./data/duke.txt. 
Every commands that make changes to the tasks will update ./data/duke.txt as well. 
These commands includes add new tasks, `delete`, `mark` and `unmark`.

---

### Feature - Exit Duke: `bye`

Exit from Duke safely. 

Command : `bye`

Example :
![bye_eg.png](images%2Fbye_eg.png)

---




