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
7. Mark/Unmark task as completed

### Feature 1: Greeting

Upon starting up the app, the Chatbot will greet you with the following message.

```bash
WELCOME
```

### Feature 2: todo Task

If you have a task that does not have any start or end date, you may save it in the following format `todo <Task Name>`.

```bash
# todo <Task Name>
todo complete my homework

```

The expected output will be as such.

```bash
Got it. I've added this task:
[T][ ] complete my homework
Now you have 1 tasks in the list.
```

### Feature-XYZ

Description of the feature.

## Usage

### `Keyword` - Describe action

Describe the action and its outcome.

Example of usage:

`keyword (optional arguments)`

Expected outcome:

Description of the outcome.

```
expected output
```
