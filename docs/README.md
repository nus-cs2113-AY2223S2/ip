# Duke User Guide
Duke is a desktop app that allows users to keep track of tasks
easily. This is optimised for use via a Command Line Interface (CLI).

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest iP.jar.
3. Copy the file to the folder you want to use as the home folder.
4. Enter the `java -jar iP.jar` command to run the application.

## Features 
Duke contains features that allow for users to enter whatever tasks they please
such as to-do, event and deadline type tasks.
The following features are to be typed into the command line interface
with the necessary task descriptions/number.  
1. Display tasks: `/list`
2. Mark task: `/mark`
3. Un-mark task `/unmark`
4. Create to-do task: `/todo`
5. Create deadline task: `/deadline`
6. Create event task: `/event`
7. Display tasks with specified words: `/find`
8. Delete task: `/delete`
9. Display help: `/help`


### Display task: /list
Look at your current tasks with convenience! Save your brain space for more important things in life :)
Enter this command to display the list of current tasks available in your saved 
task list. There is a task number displayed for every task which is used for other
commands such as /delete and /mark. 
More in the Usage section.

### Create to-do task: /todo
Want to create a to-do task but don't have a deadline nor an event horizon?
Enter this command with your task description to create a to-do task into your task list.

### Create deadline task: /deadline
Want to create a deadline task with just a due date?
Enter this command with your task description to create a deadline task into your task list.
More in the Usage section.

### Create event task: /event
Want to create an event task with a start date and an end date?
Enter this command with your task description to create an event task into your task list.
More in the Usage section.

### Mark task: /mark
Want to mark a task?
Enter this command with the task number to mark the task in your task list.
More in the Usage section.

### Unmark task: /unmark
Want to unmark a task?
Enter this command with the task number to unmark the task in your task list.
More in the Usage section.

### Display task with specified keywords: /find
Want to look for a task but only looking for a specific day?  
Enter this command with the keyword to find relevant tasks in your task list.
More in the Usage section.

### Delete task: /delete
Want to delete a task because it is done?  
Enter this command with the keyword to delete such task in your task list.
More in the Usage section.

### Display Help: /help
Want more help?
Enter this command for more help. :)
More in the Usage section.

## Usage

### `/list` 

List current tasks in saved task list

Usage: `/list` Example of usage: `/list`

Expected outcome:
```
Please enter your data below: (send '/bye' to exit) 
/list
	____________________________________________________________
	 Here are the tasks in your list:
	  1. [T][ ] make coffee 
	____________________________________________________________

Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.

```
There is 1 task in your task list, which is a to-do, and is currently unmarked.
```

### `/todo`

Usage: `/todo [TASK DESCRIPTION]`
Example of usage: `/todo mark coffee`

Expected Outcome:
```
Please enter your data below: (send '/bye' to exit)
/todo make coffee
____________________________________________________________
Got it. I've added this task:
[T][ ] make coffee
Now you have 1 item(s) in your list.
____________________________________________________________

Please enter your data below: (send '/bye' to exit)

```
Description of the outcome.
```
You have added an unmarked to-do task: "make coffee" into your task list.
```

### `/deadline`

Usage: `/deadline [TASK DESCRIPTION] /by [TASK DEADLINE]`
Example of usage: `/deadline finish CG2023 project /by Week 11`

Expected Outcome:
```
Please enter your data below: (send '/bye' to exit) 
/deadline finish CG2023 project /by Week 11
	____________________________________________________________
	 Got it. I've added this task:
	  [D][ ] finish CG2023 project (By: Week 11)
	 Now you have 2 item(s) in your list.
	____________________________________________________________

Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.
```
You have added an unmarked deadline task: "/deadline finish CG2023 project" 
and its deadline: "/by Week 11" into your task list.
```

### `/event`

Usage: `/event [TASK DESCRIPTION] /from [START DATE] /to [END DATE]`
Example of usage: `/event watch Avatar 2 /from March 3 2pm /to March 3 5.30pm`

Expected Outcome:
```
Please enter your data below: (send '/bye' to exit) 
/event watch Avatar 2 /from March 3 2pm /to March 3 5.30pm 
	____________________________________________________________
	 Got it. I've added this task:
	  [E][ ] watch Avatar 2 (From: March 3 2pm To: March 3 5.30pm )
	 Now you have 3 item(s) in your list.
	____________________________________________________________

Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.
```
You have added an unmarked event task: "/event finish CG2023 project",
its start date: "/from March 3 2pm", and its end date: "/to March 3 5.30pm"
into your task list.
```

### `/mark`

Usage: `/mark [TASK NUMBER]`
Example of usage: `/mark 2`

Expected Outcome:
```
Please enter your data below: (send '/bye' to exit) 
/mark 2
	____________________________________________________________
	 Nice! I've marked this task as done:
	  [D][X] finish CG2023 project (By: Week 11)
	____________________________________________________________

Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.
```
You marked task number 2: "finish CG2023 project" from your task list.
```

### `/unmark`

Usage: `/unmark [TASK NUMBER]`
Example of usage: `/unmark 2`

Expected Outcome:
```
Please enter your data below: (send '/bye' to exit) 
/unmark 2
	____________________________________________________________
	 Nice! I've marked this task as not done yet:
	  [D][ ] finish CG2023 project (By: Week 11)
	____________________________________________________________

Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.
```
You unmarked task number 2: "finish CG2023 project" from your task list, based on its number from task list.
```

### `/delete`

Usage: `/delete [TASK NUMBER]`
Example of usage: `/delete 1`

Expected Outcome:
```
Please enter your data below: (send '/bye' to exit) 
/delete 1
	____________________________________________________________
	 Got it. I've deleted this task:
	  [T][ ] make coffee 
	 Now you have 2 item(s) in your list.
	____________________________________________________________

Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.
```
You deleted task: "make coffee" from your task list, based on its number from task list.
```

### `/find`

Usage: `/find [KEYWORD]`
Example of usage: `/find finish`

Expected Outcome:
```
Please enter your data below: (send '/bye' to exit) 
/find finish
	____________________________________________________________
	 Here are the matching tasks in your list:
	  1. [D][ ] finish CG2023 project (By: Week 11)
	  2. [D][ ] finish EE2211 assignment (By: today)
	____________________________________________________________
Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.
```
You found 2 tasks with keyword: "finish" in your task list.
```

### `/help`

Usage: `/help`
Example of usage: `/help`

Expected Outcome:
```
/help
	____________________________________________________________
	 Please read the User Guide in docs/README.md for more help.
	____________________________________________________________

Please enter your data below: (send '/bye' to exit) 

```
Description of the outcome.
```
Refers the user to the User Guide in docs/README.md for more help.
```





