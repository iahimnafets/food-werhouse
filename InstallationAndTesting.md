

1) Unzip the .ZIP file or Import by link from https://github.com/

2) Import into IntelliJ as a new project

3) Use your Database Progress or Install Postgres in your PC,
    if you need to install: Open a new terminal and write all this commands ( I use Mac )

   a) brew update
   b) brew install postgresql
   c) brew services start postgresql
   d) CREATE ROLE mihai WITH LOGIN PASSWORD 'password';
      ALTER ROLE chris CREATEDB;
      Or create another user/pass like you want and modify: application.properties

   c) If you have problems, Followed this guide for install Postgres  ( I followed this )
       https://daily-dev-tips.com/posts/installing-postgresql-on-a-mac-with-homebrew/

    Now you are ready to move for next steps


3) Open the "terminal" tab in IntelliJ and run the command:
     mvn clean install

5) Open the class: WarehouseApplication and launch the application
   here I made some methods that load some tables, take a look if you can

6) Now you can test via POSTMAN vei file
   which I have always enclosed here in this package


