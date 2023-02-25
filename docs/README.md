# User Guide

Have you ever find it difficult to keep track of the many tasks at hand? Do you ever wish for a personal assistant to help you record all of your tasks? Well, Duke is here to save the day. This user guide will provide a step-by-step guide on how you can use Duke to make your life easier.

## Table of Contents

1. Setup
2. Features

## Setup

1. Install the jar file.
2. Execute the following command to run the program. The jar file already contains the necessary dependencies. A `data.json` file will automatically be created in the same directory as the jar file if none exist, and you may start using the app as per your desire. A word of caution: **Please do not manually edit the data.json unless you know what you are doing, as it will lead to unexpected consequences.**

```bash
>>> java -jar duke.jar
```

## Features

1. Greeting
2. Save a todo task
3. Save an event task
4. Save a deadline task
5. Delete tasks
6. Find tasks
7. List tasks
8. Mark/Unmark task as completed
9. Terminate the program

### Feature 1: Greeting

Upon starting up the app, the Chatbot will greet you with the following message.

```
WELCOME
```

### Feature 2: Save a todo task

If you have a task that does not have any start or end date, you may save it in the following format `todo <Task Name>`.

```
# todo <Task Name>
todo Kiss my girlfriend
```

The expected outcome will be as such.

```
Got it. I've added this task:
[T][ ] Kiss my girlfriend
Now you have 1 tasks in the list.
```

### Feature 3: Save an event task

If you are attending an event with a start and end date, you may save it in the following format `event <description> /from <start> /to <end>`

```
event attend my wedding /from 12 PM /to 6 PM
```

The expected outcome will be as such.

```
Got it. I've added this task:
[E][ ] attend my wedding (from: 12 PM to: 6 PM)
Now you have 1 tasks in the list.
```

### Feature 4: Save a deadline task

If the task is a deadline task, you may save it in the following format `deadline <description> /by <deadline>`

```
deadline RSVP /by tomorrow
```

The expected outcome will be as such.

```
Got it. I've added this task:
[D][ ] RSVP (by: tomorrow)
Now you have 2 tasks in the list.
```

### Feature 5: Delete tasks

In the event that you want to delete a task, you can do so by indicating the id of the task in the following format `delete <id>`

```
delete 1
```

The expected outcome is

```
Task successfully deleted
```

In the event that the id is invalid, the following message will be shown:

```
Invalid index provided.
```

### Feature 6: Find task

You may search for a task with a keyword using the following format `find <keyword>`.

```
find RSVP
```

The expected outcome is as such:

```
Here are the matching tasks in your list:
1. [D][ ] RSVP (by: tomorrow)
```

Whether there is a task that matches the outuput, the `Here are the matching tasks in your list:` will always be printed so do not worry if no task is found.

### Feature 7: List tasks

You may list out all the tasks using the following format `list`.

The expected outcome is as such:

```
Here are the tasks in your list:
1. [E][ ] attend my wedding (from: 12 PM to: 6 PM)
2. [D][ ] RSVP (by: tomorrow)
```

Whether there is a task in the list, the `Here are the tasks in your list:` will always be printed so do not worry if no tasks are displayed.

### Feature 8: Mark/Unmark task as completed

Supposed you are done with the task, you may mark it as complete using the following command `mark <id>` or `unmark <id>`.

```
mark 1
```

The expected outcome is as such:

```
Nice! I've marked this task as done:
[D][X] RSVP (by: tomorrow)
```

If an invalid index has been provided, the following message will be shown:

```
Invalid index provided.
```

### Feature 9: Terminate the programme

In the event that you are done for the day, you may terminate the programme with the following command `bye`.

The expected outcome is as such:

```
Bye. Hope to see you again soon!
```

The tasks that you have previously saved will not be deleted as it is already saved in the data.json.
