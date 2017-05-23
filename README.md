# host-tracker
This is a tand alone java application (maven based) that tracks after the server and let the developer know weather its alive or not

`host-tracker` sends the `URL` as a GET request and check for the response

If the HTML response is **200** - it is OK, otherwise, it sends a mail to the preconfigured recipients

## Configuration
Before you start using the application you need to fill the following variables that are in `HostTracker` class:
- `MAIL` the mail you use to send mails
- `PASSWORD` the mail password
- `URL` the URL you want Host-Tracker to check

Also, in `HostTracker` in the `main` method there are the following variables 
`from` who sends the mail
`subject` the mail subject
`message` the actual message

## Packaging

In order to package and run the application you need to do the following

```
cd ${HOST_TRACKER_HOME} // go to the host-tracker dir

mvn clean install

mvn package

//  to test the application
java -jar target/host-tracker-1.0-SNAPSHOT-shaded.jar

```

Now create the 
