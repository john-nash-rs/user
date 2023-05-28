Example

POST

curl -X POST -H "Content-Type: application/json" -d '{
"name": "John Doe",
"email": "johndoe@example.com"
}' http://localhost:8080/users

GET

curl -X GET http://localhost:8080/users/1

PUT

curl -X PUT -H "Content-Type: application/json" -d 'abc@gmail.com' "http://localhost:8080/users/1/email"  

DELETE

curl -X DELETE http://localhost:8080/users/1

Validation Error

curl -X POST -H "Content-Type: application/json" -d '{
"email": "johndoe@example.com"
}' http://localhost:8080/users

