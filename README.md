# springboot-kotlin-webflux-r2dbc-mssql
Template Project Springboot kotlin Coroutines API

### Base Project
- gradle (kotlin)
- kotlin
- webflux
- mssql connect multiple databases with R2dbcEntityTemplate

## CURL

### Member

#### Get All
```
curl --location --request GET 'http://localhost:8080/member/get'
```

#### Get By Id
```
curl --location --request GET 'http://localhost:8080/member/get/1'
```

#### Add
```
curl --location --request POST 'http://localhost:8080/member/add' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "1",
    "firstName": "Sumeta",
    "lastName": "Pon"
}'
```


#### Get By Id
```
curl --location --request PATCH 'http://localhost:8080/member/edit' \
--header 'Content-Type: application/json' \
--data-raw '{
    "id": "1",
    "firstName": "Sumeta",
    "lastName": "Pon"
}'
```


#### Delete
```
curl --location --request DELETE 'http://localhost:8080/member/delete/1'
```






