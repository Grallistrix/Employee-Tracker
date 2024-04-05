# Employee Tracker Application

This is a Java-based Employee Tracker application that utilizes Hibernate for ORM (Object-Relational Mapping) and MySQL for database management.

## Getting Started

### Prerequisites
- Java Development Kit (JDK)
- MySQL database server
- Maven

### Installation

1. Clone the repository:

    ```
    git clone https://github.com/Grallistrix/employee-tracker.git
    ```

2. Navigate to the project directory:

    ```
    cd employee-tracker
    ```

3. Build the project using Maven:

    ```
    mvn clean install
    ```

4. Configure `hibernate.cfg.xml`:

    Update the `hibernate.cfg.xml` file located in `src/main/resources` directory with your MySQL database connection details:

    ```xml
    <property name="hibernate.connection.url">YOUR_DATABASE_URL</property>
    <property name="hibernate.connection.username">YOUR_USERNAME</property>
    <property name="hibernate.connection.password">YOUR_PASSWD</property>
    ```

5. Run the application:

    ```
    mvn exec:java -Dexec.mainClass="com.example.EmployeeTrackerApplication"
    ```

## Usage

After running the application, you can interact with the Employee Tracker through the console. Use the provided command-line interface to perform various operations such as adding, updating, or deleting employee records. This is only temporarary as there is a JFX version of interface in the works

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your proposed changes.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
