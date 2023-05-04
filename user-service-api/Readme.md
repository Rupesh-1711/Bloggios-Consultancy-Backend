# User Service Module API

    Server Port -> 7000
    Database -> MySql
    Databse Port -> 3306
    Database Name -> bloggios_learn

### Register a User [POST]

    Curl -> Bash and CMD
        curl -X POST http://localhost:7000/auth/register -H "Content-Type:application/json" -d "{\"email\":\"example@gmail.com\",\"password\":\"example\"}"

    Curl -> Powershell
        curl.exe -X POST http://localhost:7000/auth/register -H 'Content-Type:application/json' -d '{\"email\":\"hello@gmail.com\",\"password\":\"helloworld\"}'
####

    ● URI
        http://localhost:<server-port>/register

    ● URI
        POST Request
    
    ● Headers
        Content-Type : application/json

    ● Body (JSON)
        {
            "email": "example@gmail.com",
            "password": "<password>"
        }
####

    Response

        ● 200 OK
            {
                "message": "User Registered Successfully to Bloggios. Please verify your email"
            }

        ● 409 Conflict
            {
                "message": "Email already exists with that Email. Please try to login",
                "errorCode": 1007
            }
