# myRetail
Case Study

## Development Environment

- Docker (for running local mongoDB)
- MongoDb (for running mongodbimport)
- gradle
- SprintBoot
- IntelliJ

### starting a MongoDb in Docker an populating it with test data.

```
docker run -itd --rm --name mongo_myretail -p 27017:27017 mongo

mongoimport --db Products --collection product --drop --jsonArray --file ./import.json

```

### starting the application and removing the DB when you are done

```
./gradlew bootrun

docker stop mongo_myretail
```

or you can build the


### Postman Collection

Import myRetail.postman_collection.json into Postman.


### Calling the api from Commandline

- cUrl

- PowerShell


## Additional Thoughts

Add Exception handling

Add security.  Before this could be deployed outside of a local
development environment AuthN/AuthZ is required becuase the PUT and POST Methods give control of the data to anyone.

Add responses to PUT/POST methods

Implementing Hypermedia support (HATEOS) would be very useful as datasets get larger to enable paging.






