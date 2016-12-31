# Spring WebSocket with STOMP app for App Engine Flexible Environment

## Requirements
* [Apache Maven](http://maven.apache.org) 3.3.9 or greater
* [Google Cloud SDK](https://cloud.google.com/sdk/)
* `gcloud components install app-engine-java`
* `gcloud components update`

## Setup

Use either:

* `gcloud init`
* `gcloud beta auth application-default login`

We support building with [Maven](http://maven.apache.org/), [Gradle](https://gradle.org), and [Intelij Idea](https://cloud.google.com/tools/intellij/docs/).
The samples have files to support both Maven and Gradle.  To use the IDE plugins, see the documentation pages above.

## Maven
[Using Maven and the App Engine Plugin](https://cloud.google.com/appengine/docs/flexible/java/using-maven)
& [Maven Plugin Goals and Parameters](https://cloud.google.com/appengine/docs/flexible/java/maven-reference)

### Running locally
```
    $ mvn jetty:run
-OR-
    $ mvn jetty:run-exploded
-OR-
	$ mvn clean install jetty:run    
```  
### Deploying to App Engine Flexible Environment - currently in Beta
```
    $ mvn appengine:deploy
-OR-
	$ mvn clean install appengine:stage appengine:deploy    
```

@see https://github.com/GoogleCloudPlatform/nodejs-docs-samples/tree/master/appengine/websockets

#### Firewall rule
```
$ gcloud compute firewall-rules create default-allow-websockets \
  --allow tcp:65080 \
  --target-tags websocket \
  --description "Allow websocket traffic on port 65080"
```

// In order to use websockets on App Engine, you need to connect directly to
// application instance using the instance's public external IP. This IP can
// be obtained from the metadata server.
```
METADATA_NETWORK_INTERFACE_URL = 'http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/access-configs/0/external-ip';
```

http://metadata.google.internal/computeMetadata/v1/instance/network-interfaces/0/access-configs/0/external-ip
-OR-
http://metadata/computeMetadata/v1/instance/network-interfaces/0/access-configs/0/external-ip

#### open issue
[https://stackoverflow.com/questions/25708617/spring-4-websocket-configure-port](https://stackoverflow.com/questions/25708617/spring-4-websocket-configure-port)

### Ref
[http://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html](http://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html)


![Architecture](https://cloud.google.com/images/articles/real-time-gaming-with-node-js-websocket-on-gcp/client-server.png)
