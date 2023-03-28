# 🎧 Onandon project template

This is the Onandon chatbot project repo for the AY2223S2-CS2113, NUS. If you want to explore how to use
chatbot, check this [[project page]](https://0nandon.github.io/ip/)!

## 🤖 Getting start with Onandon chatbot!

To download the latest release, click the [[Download]](https://github.com/0nandon/ip/releases/download/A-Release/ip.jar.zip).

To execute the program, follow the below steps.
* Unzip the file.
* Copy the jar file into an empty folder.
* Open a command window in that folder.
* Run the command `java -jar {filename}.jar e.g., java -jar ip.jar` (i.e., run the command in the same folder as the jar file)


## 🧑🏻‍💻 Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/Onandon.java` file, right-click it, and choose `Run Onandon.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
      ____________________________________________________________
       Hello! I'm onandon.
       What can I do for you?
      ____________________________________________________________
   ```
