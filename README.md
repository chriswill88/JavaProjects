# JavaProjects

## CRUD Project
### Synopsis
This app allows users to do CRUD operations (Create, Read, Update, Delete) to a Local File Storage system.

### Current Operations
```
create - push a key value to DB
readAll - get all keys and values from DB
get - get one value from a key
delete - delete one value from a key
clear - clears the whole DB
update - update a key value in the DB
quit - quit
```

### Technologies
<img src="https://github.com/chriswill88/JavaProjects/assets/46333279/352ec8f7-ed1d-4bfa-a8ac-7b9a599d8a04"  width="150" >\
<img src="https://github.com/chriswill88/JavaProjects/assets/46333279/3d93dc00-5c96-4575-993f-4d2580044728"  width="150" >


### Future
- [ ] DB Storage option
- [ ] Front-End web page
- [ ] Caching with redis

### How to run
*Do this all from your terminal*
1. Install Maven (3.9.3) and java ("17.0.7" 2023-04-18 LTS)
2. to prep: `mvn clean compile assembly:single`
3. to run: `java -jar target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar`
