<h1 align="center"><a href="https://www.github.com/beingrohit25/Bloggios-Learning-Platform-Backend"><img alt="downloads" src="https://img.shields.io/badge/-BLOGGIOS_LEARN-0088CC" height=70 target="_blank" /></a></br>
<a href="https://www.github.com/beingrohit25/Bloggios-Learning-Platform-Backend">
    <img alt="downloads" src="https://img.shields.io/badge/-v1.0-lightblue" height=40 target="_blank" />
  </a>
</h1>
<p align="center">
<a href="https://www.npmjs.com/package/readme-md-generator">
    <img alt="downloads" src="https://img.shields.io/static/v1?label=JAVA&message=v17&color=brightgreen&style=plastic&logo=openjdk" height=20 target="_blank" />
  </a>
  <span>&nbsp</span>
  <a href="https://www.npmjs.com/package/readme-md-generator">
    <img alt="downloads" src="https://img.shields.io/static/v1?label=SpringBoot&message=v3.1.0&color=brightgreen&style=plastic&logo=spring" height=20 target="_blank" />
  </a>
  <span>&nbsp</span>
  <a href="https://www.npmjs.com/package/readme-md-generator">
    <img alt="downloads" src="https://img.shields.io/static/v1?label=MySql&message=v8.0 CE&color=brightgreen&style=plastic&logo=mysql&logoColor=white" height=20 target="_blank" />
  </a>
  <span>&nbsp</span>
  <a href="https://www.npmjs.com/package/readme-md-generator">
    <img alt="downloads" src="https://img.shields.io/static/v1?label=MongoDB&message=v6.0.5&color=brightgreen&style=plastic&logo=mongodb" height=20 target="_blank" />
  </a>
   <span>&nbsp</span>
  <a href="https://www.npmjs.com/package/readme-md-generator">
    <img alt="downloads" src="https://img.shields.io/static/v1?label=Apache Maven&message=v3.8.7&color=brightgreen&style=plastic&logo=apachemaven" height=20 target="_blank" />
  </a>
</p>

# User Service Module API

    Server Port -> 7000
    Database -> MySql
    Databse Port -> 3306
    Database User -> Your mysql database Username
    Database Password -> Your mysql database Password
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
