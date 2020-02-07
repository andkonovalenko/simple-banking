Simple banking system
===========================================
Simple Bank is a REST spring boot application. Application allows check balance, increase and decrease balance

Database development
-------------------------------------------
Create database TestAPI and enter DB name, password and username to application.property file.

IntelliJ Idea development
-------------------------------------------
Install Lombok plugin under File -> Settings -> Plugins -> Browse repositories... search for the Lombok and install it.

API
-------------------------------------------
  * `GET /user/list`
  * `GET /user/<userId>`
  * `GET /balance/user/<userId>`
  * `GET /history/user/<userId>`
  * `POST /balance/deposit/user/<userId>`
  * `POST /balance/withdraw/user/<userId>`
