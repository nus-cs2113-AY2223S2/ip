# User Guide

## Features 

### Stores and saves your tasks.

If you need a quick checklist, you can use this CLI-based digital checklist that saves your previous tasks.

### Ability to mark tasks as complete and delete tasks.

After completion, users can mark their tasks and delete them as they wish.

## Usage

### `Keyword` - todo

Creates a task with no startdate or deadline. Ability to mark tasks as completed and delete them.

Example of usage: 

todo task0

After listing (see list), your list should have:

0. [T] [ ] task0

### `Keyword` - deadline

Creates a task with a deadline. Ability to mark tasks as completed and delete them. The deadline is defined by any character after the first delimiter "/".

Example of usage: 

deadline task1 /tomorrow

After listing (see list), your list should have:

0. [T] [ ] task0
1. [D] [ ] task1 (by: tomorrow)

Expected outcome:

Description of the outcome.

```
expected output
```
