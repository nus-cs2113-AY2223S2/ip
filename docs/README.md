# User Guide

Have you ever find it difficult to keep track of the many tasks at hand? Fret not, Duke is here to save the day. Find out how you can use Duke to save the data.

## Features

1. Greeting
2. Save a todo task
3. Save an event task
4. Save a deadline task
5. Delete tasks
6. Find tasks
7. Mark/Unmark task as completed

## Setup

The jar file already contains the necessary dependencies. Therefore, the only command that you would need to run is

```bash
java -jar duke.jar
```

It will automatically create a `data.json` file in the root directory of the jar file if none exist, and you may start using the app as per your desire. A word of caution: **_Do not try to manually edit the data.json file as it will lead to unexpected consequences. _**

### Feature 1: Greeting

The Chatbot will greet you with the following message. Do note that this command will only run once.

```bash
>>> java -jar duke.jar # Command to run
WELCOME # Chat bot response
```

### Feature 2: todo Task

If you have a task that does not have any start or end date, you may save it in the following format. `todo <Task Name>`. Here is the expected input and output where `<>` represents the variable data to be keyed in by the user..

```bash
# Instructions
>>> todo <Task Name>

# Response by the chat bot
>>> Got it. I\'ve added this task:
>>> [T][ ] <Task Name>
>>> Now you have <X> tasks in the list.
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
