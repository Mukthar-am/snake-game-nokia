# Nokia - snake game app

This is the same old game which we are aware of during the times of Nokia phone.
  We have a snake which traverses/moves of the game board, starting from the board left-top most corner cell and on its way, keeps find a path to consume the food which is randomly placed at the board.

## Getting Started
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites
    * [JDK-1.8] (oracle JDK) - dev environment
    * [Maven](https://maven.apache.org/) - Dependency Management
    and rest of the dependencies are handled in pom.xml

### Building the application jar
    git clone this repository, locally.
    mvn clean install (Building the target artifacts along with running Unit tests)

## Running the tests
- All the tests are run by TestNG, using the test sute file found at the path <project-root>/src/test/resources/test-suite.xml
- TestCoverage is enabled using JaCoCo in conjunction with maven-surefire-plugin and is set to very minimal code coverage.


## Authors

* **Mukthar Ahamed, Maniyar** - *Initial work* - [mukthar-am](https://github.com/mukthar-am)

