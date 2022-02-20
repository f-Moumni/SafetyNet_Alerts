# SafetyNet Alerts

Rest API developed with Spring Boot, that allows to send information to the emergency service  

---------
## Getting Started

These instructions will get you a copy of the project up and running on your local machine

### Prerequisites  

What things you need to install the software  

[Java 1.8](https://adoptopenjdk.net/?variant=openjdk8&jvmVariant=hotspot)  
[Maven 3.6.2](https://maven.apache.org/download.cgi)  

### Running App  

To run the app you will have to go to the folder `SafetyNet_Alerts`   

1. Compile and generate the final jar by running: `mvn package`  

2. Run the APP with: `java -jar target/alerts-0.0.1-SNAPSHOT.jar`  

### Testing  

To run the tests execute the command: `mvn verify`  
To generate a site that includes project's reports,please run :`mvn site`

## content 

__CURD operations endpoints__

 *Person endpoints*
* GET http://localhost:8080/person/persons : get All Persons
* GET http://localhost:8080/person/?firstName=`firstname`&lastName=`Lastname` : get a person by his name
* POST http://localhost:8080/person/ : to save a person
* PUT http://localhost:8080/person/ : to update a person
* DELETE http://localhost:8080/person/?firstName=`firstname`&lastName=`Lastname` : to delete a person

*Fire station endpoints*
* GET http://localhost:8080/firestations : get All fire station
* POST http://localhost:8080/firestation :to save fire station
* PUT http://localhost:8080/firestation :to update fire station
* DELETE http://localhost:8080/firestation?address=`address` :to delete a fire station

*Medical record endpoints*
* GET http://localhost:8080/medicalRecord : get All fire medical records 
* POST http://localhost:8080/medicalRecord/?firstName=`firstname`&lastName=`Lastname` :to save medical record
* PUT http://localhost:8080/medicalRecord :to update medical record
* DELETE http://localhost:8080/medicalRecord/?firstName=`firstname`&lastName=`Lastname` :to delete a medical record


__Alerts URLS__
      
* http://localhost:8080/firestation?stationNumber=`station`:  
This url returns a list of persons covered by the corresponding fire station. 

* http://localhost:8080/childAlert?address=`address`:  
This url returns a list of children living at that address and a list of other household members. 

* http://localhost:8080/phoneAlert?firestation=`firestation_number`:  
This url returns a list of telephone numbers of residents served by the given fire station

* http://localhost:8080/fire?address=`address`:  
This url returns the list of inhabitants living at the given address and the number of the fire station serving it.

* http://localhost:8080/flood/stations?stations=`station`:  
This url returns a list of all homes served by the fire station.

* http://localhost:8080/personInfo?firstName=`firstName`&lastName=`lastName`:  
This url returns the information of all people with the given name.

* http://localhost:8080/communityEmail?city=`city`:  
This url returns the email addresses of all the inhabitants of the city.
