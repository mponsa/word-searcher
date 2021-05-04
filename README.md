# Word Searcher

This is a Java 11 application built in maven. It receives a directory as input, and searches for .txt files inside. It enables queries to those text files.

```
searcher> How are you doing?
```

Searchs for these words `['How','are','you','doing','?']` inside the text files contained in directory passed as argument.

## How to run.

Just clone these repo, and make sure you have Maven installed.

````
mvn build
mvn package
java -jar target/word-searcher-1.0-SNAPSHOT.jar <directory-to-look-into>
````

## Run tests

```
mvn test
```

### Architecture

This application was made following Clean Architecture principles, in which business logic is separated from business layer. You can find core logic inside core package, which is divided into Entities & Usecases.

Entities represent models inside our domain, and usecases create and uses those entities to serve a single purpose.