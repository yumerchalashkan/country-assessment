FROM openjdk:17
ADD target/country-assessment-0.0.1-SNAPSHOT.jar springboot-country-assessment
ENTRYPOINT ["java","-jar","springboot-country-assessment"]