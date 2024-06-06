# !!!IMPORTANT!!!

The main endpoint of the restcountries api on gitlab was giving a timeout error. so i downloaded the project to my computer and dockerized it. instead of the endpoint “restcountries.com/v3.1/all” i used the endpoint “localhost:8080/v3.1/all”

![alt text](https://github.com/yumerchalashkan/country-assessment/blob/main/images/error.png?raw=true)

Before starting, the country api that I dockerized needs to be pulled from dockerhub

```bash
docker pull yumerchalashkan/countries-api
```
```bash
docker run -d -p 8080:8080 yumerchalashkan/countries-api 
```
![alt text](https://github.com/yumerchalashkan/country-assessment/blob/main/images/api.png?raw=true)

The api is working properly

# Installation

```bash
clone the project
```
```bash
mvn package
```
```bash
java -jar target/country-assessment-0.0.1-SNAPSHOT.jar
```

# Project Architecture

![alt text](https://github.com/yumerchalashkan/country-assessment/blob/main/images/project.png?raw=true)

The project that runs on Spring boot consists of 5 layers. The controller layer receives the request from the user and sends it to the service layer with the help of dto classes. Service layer makes a distinction according to the incoming request and runs the business logics of the relevant class. In the next flow, the request is sent to the API with the help of Rest Template. The request from the API is mapped to the Country class in the model layer. Then, after the methods in the service layer finish the necessary business logics, the data obtained is mapped to dto classes with the help of converter classes. Then the data is transferred directly to the controller class and Get Responses are returned to the user.

```bash
docker pull yumerchalashkan/countries-api
```



# Sorting algorithm
![alt text](https://www.wikihow.com/images/thumb/e/e2/Calculate-Population-Density-Step-4-Version-3.jpg/v4-460px-Calculate-Population-Density-Step-4-Version-3.jpg)
The formula above shows how to calculate the population density of a country. 

![alt text](https://github.com/yumerchalashkan/country-assessment/blob/main/images/formula.png?raw=true)

Accordingly, I made the implementation of this formula in the Country entity in the model layer. This will allow me to do a simple sorting with lambda expressions directly from the service layer.

![alt text](https://github.com/yumerchalashkan/country-assessment/blob/main/images/sorted.png?raw=true)

The data received with the rest template is mapped from the streams to the dto class as List in a decreasing manner with the help of compare classes.

![alt text](https://github.com/yumerchalashkan/country-assessment/blob/main/images/sortedresult.png?raw=true)

# Border algorithm

![alt text](https://github.com/yumerchalashkan/country-assessment/blob/main/images/border.png?raw=true)

First, a map is created according to the country code. Then a foreach loop is created for the countries in the assian region. each country is checked one by one and the number of borders of each country is checked again with the max value. if the number of borders is greater than the max value, the current max value becomes the number of borders of that country. in this way we find the country with the highest number of borders. then some manipulations are made in the names with dto classes and passed to the controller.




## License

[MIT](https://choosealicense.com/licenses/mit/)
