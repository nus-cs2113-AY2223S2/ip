./gradlew jar
java -jar app/build/libs/sagyo.jar < text-ui-test/input.txt > text-ui-test/EXPECTED.TXT
