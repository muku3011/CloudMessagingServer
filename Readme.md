[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-orange.svg)](https://sonarcloud.io/dashboard?id=muku3011_cloud-messaging-server)

# Backed for Google cloud message application

## Source code:
* [Git link for BE](https://github.com/muku3011/cloud-messaging-server)
* [Git link for UI](https://github.com/muku3011/cloud-messaging-ui)
* [Git link for Android App](https://github.com/muku3011/cloud-messaging-app)

## Other info:
* H2 console can be accessed at: `<HOST>:<PORT>/h2-console/`
* Database url: `jdbc:h2:mem:mydb`
* Api documentation: `<HOST>:<PORT>/swagger-ui/`

## Steps to use this application:

1. ~~Create Key~~ (removed for now) :
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