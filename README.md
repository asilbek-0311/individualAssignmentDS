# Distributed Price Checker

This is a simple distributed client-server application for checking prices based on ISBN values. The system uses Scala with TCP for communication and supports multi-threading for handling multiple client requests concurrently.

## Project Structure

- `server`: Contains the Scala code for the server application.
- `client`: Contains the Scala code for the client application.

## Requirements

- [Scala](https://www.scala-lang.org/)
- [sbt](https://www.scala-sbt.org/)
- [JDK](https://www.oracle.com/java/technologies/javase-downloads.html)
- [OpenCSV](http://opencsv.sourceforge.net/) (for interacting with the database)

## Usage

### Server

1. Navigate to the `server` directory.
2. Run the server using the following command:

    ```bash
    sbt run
    ```

3. The server will start and wait for client connections on port 9999.

### Client

1. Navigate to the `client` directory.
2. Run the client using the following command:

    ```bash
    sbt run
    ```

3. Follow the instructions to enter a 13-digit ISBN and retrieve the price from the server.

## Instructions

- Ensure you have [sbt](https://www.scala-sbt.org/) and [JDK](https://www.oracle.com/java/technologies/javase-downloads.html) installed to build and run the project.
- The server reads ISBN-price pairs from a CSV file (`data.csv`), and you can customize this file as needed.
- The client sends ISBN values to the server, and the server responds with the corresponding prices.
- If the ISBN is not found, the server responds with an error message.
- [OpenCSV](http://opencsv.sourceforge.net/) is used to interact with the database.

