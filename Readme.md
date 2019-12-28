### Backed for Google cloud message application

Application is hosted at:
* [https://cloud-messaging-server.herokuapp.com][Backend]
* [https://cloud-messaging-ui.herokuapp.com][Frontend] 
* [https://github.com/muku3011/CloudMessagingUi][Git link for UI]
* [https://github.com/muku3011/CloudMessagingApp][Git link for Android App]

* H2 console can be accessed at: HOST:PORT/h2-console/
* Database url: jdbc:h2:mem:mydb

* Api documentation available at HOST:PORT/swagger-ui.html 

Steps to user this application:

1. Create Key:
    * URL: http://localhost:8080/key
    * Request: POST
    * Body: 
        ```json
        {
            "key": "OurMostSecureKey",
            "initialVector": "OurInitialVector",
            "keyName": "EncryptKey"
        }
      
2. Create User:
    * URL: http://localhost:8080/user
    * Request: POST
    * Body: 
        ```json
        {
            "userName": "CloudMessage",
            "userToken": "OurMostSecureKey"
        }
      
3. Create Server:
    * URL: http://localhost:8080/server
    * Request: POST
    * Body: 
        ```json
        {
            "serverUrl": "https://fcm.googleapis.com/fcm/send",
            "serverKey": "*********************"
        }
      
3. Send Message:
    * URL: http://localhost:8080/message
    * Request: POST
    * Body: 
        ```json
        {
            "userName": "CloudMessage",
            "serverUrl": "https://fcm.googleapis.com/fcm/send",
            "title": "First message title",
            "body": "First message body"
        }
     

[Backend]: https://cloud-messaging-server.herokuapp.com

[Angular]: https://cloud-messaging-ui.herokuapp.com

[Backend]: https://cloud-messaging-server.herokuapp.com

[Frontend]: https://cloud-messaging-ui.herokuapp.com

[Git link for UI]: https://github.com/muku3011/CloudMessagingUi

[Git link for Android App]: https://github.com/muku3011/CloudMessagingApp