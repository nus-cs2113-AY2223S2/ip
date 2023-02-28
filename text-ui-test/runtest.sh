#!/usr/bin/env bash

# Must run this script from the root directory

# Build the jar file using gradle
./gradlew jar -q

# run the jar file, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -jar app/build/libs/sagyo.jar < text-ui-test/input.txt > text-ui-test/ACTUAL.TXT

# compare the output to the expected output
diff text-ui-test/ACTUAL.TXT text-ui-test/EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi
