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
