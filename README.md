# Compile this library
git clone https://github.com/Adomas-beep-boop-maggots/uzduotys3.git &&
cd uzduotys3 &&
javac ./src/lt/auskim/*.java ./src/lt/auskim/utils/*.java -d ./build/ &&
jar cf lib2.jar -C ./build lt
