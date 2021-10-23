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

To calculate a budget, the program will identify salary and average money spent monthly. By taken into other financial aspects, such as savings and credit score, a estimated budget is calculated. The budget will be used to identify budget-suitable cars to generate a list of recommended cars. Then, our program will use the Senso API to get back the available loan product. 

# How to Run the Files Locally
- Run the command "./gradlew bootRun" (without the quotation marks) to activate the server locally

# Testing commit and merge stuff - An