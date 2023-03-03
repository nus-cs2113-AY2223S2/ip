---
title: Quick Start
weight: 11
---

# Quick Start

1. Ensure that you have Java `11` or above installed on your computer.
2. Download the latest version of `Duke` from [our repository](https://github.com/jinxuan-owyong/ip/releases).
3. In the folder where you have downloaded `Duke.jar`, run the application in the command line with the following command:
   ```
   java -jar Duke.jar
   ```
4. You should see the following screen:
   ```
       Hello from
        ____        _        
        |  _ \ _   _| | _____ 
        | | | | | | | |/ / _ \
        | |_| | |_| |   <  __/
        |____/ \__,_|_|\_\___|
        ____________________________________________________________
        Hello! I'm Duke
        What can I do for you?
        ____________________________________________________________
   ```
5. To perform an operation, type the required command and press <kbd>Enter</kbd>. 
   {{< hint info >}} 
   Example:
   ```
    todo buy mangoes
    ____________________________________________________________
    Got it. I've added this task:
        [T][ ] buy mangoes
    Now you have 5 tasks in the list
    ____________________________________________________________
   ```
   {{< /hint >}}
6. Refer to the [Features](../features) page for the list of commands available.

{{< hint danger >}}
__Startup__

If the application is unable to process the saved data, you will receive a warning message as shown:
```
    Warning: Save data is corrupted.
    All data will be overwritten after the next command is entered.
    Corrupt data: {"name":"buy mangoes","isCompleted"false,"type":"TODO"}
```

If you wish to preserve the saved data, close the application __immediately__.
{{< /hint >}}
