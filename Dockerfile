# Use Eclipse Temurin as base image
FROM eclipse-temurin:21

# Create working directory
WORKDIR /javabiodb
COPY Java/ /javabiodb/Java/

# Run the script
RUN javac Java/*.java
CMD ["java", "-cp", "Java", "Main"]