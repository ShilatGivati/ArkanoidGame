compile: bin
	javac -d bin -cp biuoop-1.4.jar src/*.java
jar:
	jar cfm0 Arknoid.jar MANIFEST.MF -C bin .
run:
	java -jar Arknoid.jar
bin:
	mkdir bin
