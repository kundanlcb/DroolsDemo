This is a demo project of Drools using Spring Boot.

Bsically there is one end point to recharge a number using payment type ("paypal","tokenizedCard","applePay"). Make a curl request with follwing data:


POST http://localhost:8080/recharge
Content-Type: application/json

{
  "mobileNumber": "3292839382938",
  "paymentType": "paypal",
  "topUpAmount": 100,
  "discount": 0
}

Response:

{
  "mobileNumber": "3292839382938",
  "paymentType": "paypal",
  "topUpAmount": 100,
  "discount": 10
}

Code is using drools to set discount for the topup for different different payment type.
