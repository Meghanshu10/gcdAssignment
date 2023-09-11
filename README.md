# gcdAssignment

Curls to run the program are:

Task 1 :
curl --location 'http://localhost:8082/addLoan' \
--header 'Content-Type: application/json' \
--data '{
    "loanId":"L4",
    "customerId":"C3",
    "lenderId":"LEN2",
    "amount":"50000",
    "remainingAmount":"30000",
    "paymentDate":"2023-04-04",
    "interestPerDay":"2",
    "dueDate":"2023-05-04",
    "penaltyPerDay":"0.02",
    "cancel":""
}'

Task 2 :
curl --location 'http://localhost:8082/get-aggregation'

Task 3:
Auto runs when application is started using scheduler.

Note: Mongo uri is available in application.properties and is accessible for 7 days.
