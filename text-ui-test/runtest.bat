@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
if exist ACTUAL.TXT del ACTUAL.TXT

REM compile the code into the bin folder
@REM javac  -cp ..\src\main\java -Xlint:none -d ..\bin ..\src\main\java\duke\*.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0
cd ../src/main/java
REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java duke/Duke < ../../../text-ui-test/input.txt > ../../../text-ui-test/ACTUAL.TXT

REM compare the output to the expected output
cd ../../../text-ui-test
FC ACTUAL.TXT EXPECTED.TXT
