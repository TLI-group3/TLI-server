# TLI-server

The TLI server consists of the backend and database of our program. 

# Database
MySQL will be used to store the database. JDBC will be used to access the database. There will be four tables in our database:
1. A table storing the general informations about cars
2. A table storing all the account holders of a bank
3. A table storing monthly bank statements and financial data of a specific account holder
4. A table storing each account holder's budget and recommended cars. 

In real life, tables 2 and 3 would be from the bank's database with access provided to us. However, for the simplicity of our program, all those data will be stored in the same dataset. 

The mock dataset containing banking info and the dataset of car information will be in CSV format. 

# Backend

For the backend, Java will be the sole language used. 

There are two main components the backend will handle: calculate an account holder's budget and generate a list of recommended cars. 

To calculate a budget, the program will identify salary and average money spent monthly. By taken into other financial aspects, such as savings and credit score, a estimated budget is calculated. Then, our program will use the Senso API to get back the available loan product. The budget will be used to identify budget-suitable cars to generate a list of recommended cars.

# Environment Variables

For both the database and backend functionality, you will require the following environment variables:

* `SENSO_API_URL`: the base URL for the Senso API, excluding endpoints and no final forwardslash
  * e.g. `https://senso-api.example.com`
* `SENSO_API_KEY`: the access credential for the Senso API, to be supplied in the
`x-api-key` header.
* `AVANTAGE_SQLDB_URL`: the url to access the RDS instance of our MySQL database
  * e.g. `db.rds.aws.com`
* `AVANTAGE_SQLDB_USER`: the username to access the MySQL database on RDS
* `AVANTAGE_SQLDB_PWD`: the password to access the MySQL database on RDS

# How to Run the Files Locally

### Windows
- In the "service" directory, run the command "./gradlew bootRun" (without the quotation marks) to activate the server locally

### Linux/Mac
- In the "service" directory, run the command "sudo sh gradlew bootRun" (without the quotation marks) to activate the server locally
