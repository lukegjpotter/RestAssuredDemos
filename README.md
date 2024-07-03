# REST Assured Demos

Learning REST Assured via a combination of open APIs and custom Spring Boot Web ones.

### How to Use

Optional: Install JSON to format/pretty print the Response.

    sudo npm i -g json

Curl instructions

    curl -X GET http://localhost:8080/lotto/check/1,2,3,4,5,6 | json

Then it will return

    {
        "result": "Winner",
        "errorMessage": ""
    }

History is another API that Returns a list.

    curl -X GET http://localhost:8080/lotto/history | json

    {
        "lottodraws": [ {
                "winningNumbers": [ 4, 8, 15, 16, 23, 42 ],
                "drawDateTime": "2004-08-16T23:42:00"
            }, {
                "winningNumbers": [ 5, 10, 12, 34, 35, 42 ],
                "drawDateTime": "2024-03-15T19:00:00"
            }, {
                "winningNumbers": [ 1, 2, 3, 4, 5, 6 ],
                "drawDateTime": "2024-07-03T22:52:55.429948"
            }],
        "errorMessage": ""
    }

### How to Build and Run

CLI Gradle

    ./gradlew build bootRun

Docker CLI Instructions

    docker build --pull -t rest-assured-demos:latest .
    
    docker run --name rest_assured_demos \
      -p 8080:8080 \
      -d --rm rest-assured-demos:latest

Then you can run the `curl` command above.