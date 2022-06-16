# Application assignment for my first job as Junior Developer
As a Developer <br>
I want to see all the ip ranges for selectable regions from AWS ip ranges <br>
in order to create ip filter on security groups <br>
 
- Accepance Criteria:
  - A Spring Boot Application is created 
  - The application has a REST Controller and lists the expected data 
  - The datasource is public and readable from here: https://ip-ranges.amazonaws.com/ip-ranges.json
  - The REST controller takes URL query parameter like "?region=EU" or "?region=US" or "?region=ALL" to filter the data (valid regions are EU, US, AP, CN, SA, AF, CA)
  - The data is presented as MIME type text/plain
  - Each value is shown as single line in the output
  - The data (from AWS ip-ranges.json) is not stored 
  - The code should be stored in github 
- Hints / Remarks:
  - Remember to write unit tests for the application 
  - There is no expectation of creating a security group in AWS and use the data 
  a pipeline in github actions is not requested but would be plus 
  - Putting the app into a docker container is not requested but would be plus
