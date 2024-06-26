## Data Model Diagram

![image](https://github.com/expdi/java-final-capstone-big-bend/assets/113630640/e3cf4c24-7267-41cf-8162-cbdd3a7fa8f4)

## Database Creation

Replace `{password}` with your chosen database password in the following instructions.

First create the podman container:
```
podman run -dt --name java-capstone -e POSTGRES_USER=capstone -e POSTGRES_PASSWORD={password} -v podman-capstone-volume:/var/lib/postgresql/data:Z -p 5432:5432 postgres
```
Next, open a psql terminal within the container:
```
podman exec -it java-capstone bash
psql -U postgres
```
Finally, create the database and user:
```
CREATE USER capstone PASSWORD '{password}' CREATEDB CREATEROLE;
ALTER ROLE capstone WITH LOGIN;
SET ROLE capstone;
CREATE DATABASE java_capstone;
```
You can see your database connection in Intellij by opening the `database` panel and adding a Postgres data source with the following properties:
```
Host: localhost
Port: 5432
User: capstone
Password: {password}
Database: java_capstone
Database Url: jdbc:postgresql://localhost:5432/java_capstone
```