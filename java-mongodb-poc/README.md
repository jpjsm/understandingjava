# java-mongodb-poc

PoC to manage permission and visibility files for the Source of Truth SoT,
geared towards providing information to the security service

## Executing the code while developing

`mvn exec:java`

Will execute what's defined in `mainClass`.

```POM
    <configuration>
        <mainClass>com.service.mongodb.Insert1KDocuments</mainClass>
    </configuration>
```
