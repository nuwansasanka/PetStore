# JavaEE Assignment with a mini project


### 18000371
### D.N.S.P. Deraniyagala
### SCS3203 / IS3108 Middleware Architecture


# Pet Store

To build the project use following command:

#### Command
    ./gradlew build

To Deploy and Run the project use following command:

#### Command
    java -jar build/PetStorenew-runner.jar

To Run test suite use following command:

#### Command

    ./gradlew test
##CURL/WGET commands

###For Pets

* View All Pets

        curl -XGET -H "Content-type: application/json" 'http://localhost:8080/data/pets'

* Add new Pet

        curl -XPOST -H "Content-type: application/json" -d '"age": 2,
         "name": "Mojo",
        "type": "Bird"' 'http://localhost:8080/data/pets/add'

* Update Pet details

        curl -XPUT -H "Content-type: application/json" -d '"age": 5,' 'http://localhost:8080/data/pets/edit/2'

* Delete a Pet

        curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/data/pets/delete/1'

* Search Pet by id 

        curl -XGET -H "Content-type: application/json" 'http://localhost:8080/data/pets/search?id=1'

* Search Pet by age 

        curl -XGET -H "Content-type: application/json" 'http://localhost:8080/data/pets/search?age=3'

* Search Pet by name

        curl -XGET -H "Content-type: application/json" 'http://localhost:8080/data/pets/search?name=Boola'

### For Pet Types

* View all pet types

        curl -XGET -H "Content-type: application/json" 'http://localhost:8080/data/petsType'

* Add new pet type

        curl -XPOST -H "Content-type: application/json" -d '"type": "Rabbit",' 'http://localhost:8080/data/petsType/add'

* Update Pet type 
  
        curl -XPUT -H "Content-type: application/json" -d '"type": "Fish",' 'http://localhost:8080/data/petsType/edit/2'

* Delete Pet type

        curl -XDELETE -H "Content-type: application/json" 'http://localhost:8080/data/petsType/delete/2'

