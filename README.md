Follow these steps to run this proof-of-concept:

* install a Postgres 9.x database server
* execute the SQL scripts in `src/sql/postgresql/init/prod` and `src/sql/postgresql/init/junit` to create the production and test databases 
* run `mvn clean install`
* run `mvn jetty:run` to start the webapp
* open your browser and navigate to `http://localhost:8080/storefront`
* run `mvn jetty:stop` to stop the webapp

That's it!