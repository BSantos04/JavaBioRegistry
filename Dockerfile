# Use Eclipse Temurin as base image
FROM eclipse-temurin:21

# Create working directory
WORKDIR /javabioreg
COPY Java/ /javabioreg/Java/
VOLUME /javabioreg/data/

# Run the script
RUN javac Java/*.java
CMD ["java", "-cp", "Java", "Main"]