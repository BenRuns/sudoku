# Sudoku

Live version running at https://ben-sudoku.appspot.com  

### Quick start
Note: this instructions were written for developing on Ubuntu

- make sure you have java 8 on your machine `java -version` you shoul see Build '1.8.XXX'
- install maven if needed  `sudo apt-get install mvn`
- clone the repo `git clone git@github.com:BenRuns/sudoku.git`
- go into the directory `cd sudoku`
- run it   `mvn package && java -jar target/sudoku-0.1.0.jar`
- app will be  be serving on http://localhost:8080

### Where to look
 - Relevant code is in src/main/java/
 - Tests are in src/test
 - client is in src/main/resources

### Backend
- Written in java using spring-boot
- hosted on google app engine

### Client
- written using [vuejs.org](vuejs.org)

### Testing
- `mvn test`

### Deploying
This is set up to deploy on google app engine. Instructions for installing the
required tools can be found  [here](https://codelabs.developers.google.com/codelabs/cloud-app-engine-springboot/index.html?index=..%2F..%2Findex#0)
- If you're all set up, run `mvn -DskipTests appengine:deploy`

### Sudoku rules
from [www.sudoku.name](http://www.sudoku.name/rules/en)
- Sudoku is a 9x9 grid divided into 3x3 sub grids called "regions"
- Each square must contain a integer between 1 - 9;
- Each integer can only appear once in a column
- Each integer can only appear once in a row
- Each integer can only appear once in a region





