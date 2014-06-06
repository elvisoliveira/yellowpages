## YellowPages

![alt tag](http://i.imgur.com/AAzkzn3.jpg)

![alt tag](http://i.imgur.com/7IBcjXb.png)

How to manually run:

* Build the Maven package.

```bash
mvn package
```

* Download the dependency folder

```bash
mvn dependency:copy-dependencies
```

* Execute with all the dependencies

```bash
java -cp "target/yellowpages-1.0-SNAPSHOT.jar;target/dependency/jsoup-1.7.3.jar" com.elvisoliveira.yellowpages.yellowpages
```

