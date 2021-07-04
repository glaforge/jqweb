# JQ web service container

The goal of this small Micronaut-based service is to use the 
[jq-web](https://github.com/arakelian/java-jq) library to filter the output of
JSON-based REST APIs to just return the subset we're interested in, using JQ filters.

Once the application is running, with `./gradlew run`, you can make some test calls with curl:

```
curl -X POST 'localhost:8080/?filter=.text' -H "content-type: application/json" -d '{"text"="Il était un petit navire","number"=1234,"truth":true}' 
```

Or using [httpie](https://httpie.io/), with the command:

```
http "localhost:8080/?filter=.text" text='Il était un petit navire' number=1234 truth=true
```

---
This is not an official Google product.