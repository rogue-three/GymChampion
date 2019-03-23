# Release Notes for Gym Champion REST API project

### Thoughts after the first week and evaluation

1. Generally about refactor:
	* spelling: change "archivize"/"archivezed" words to "archive"
	* read about requirements and good practices in REST APIs, eg. use PATCH with simple 
	  Json instead of PUT with whole object in Json when updating
	* rethink all two-way relations, eg. Gender doesn't have to have list of Users at all
	* when initializing db don't invoke methods in constructor, use Spring's "command line runner"
	* use IntelliJ's suggestions, eg. use "optionalBodypart.orElseGet(BodyPart::new)" instead 
	  of if statements (probably we'll not use this particular solution after implementation
	  of exception handling)
2. API architecture:
	* rethink usage of separate controllers/services to each model object, eg. aggregate
	  manipulation of body parts/ex schemes/equipment in exercise controller
	* it works in both ways, maybe some functionality should be separated extra
3. Provide proper information to API user:
	* handle exceptions, eg. when record is not founded raise custom exception and send
	  info to a user (read about Spring's "controller advise" to handle this from service layer)
	* think about appropriate response codes
4. Implement Logger with all user's actions logged into terminal/file in all controllers (and services?)
5. Implement Spring Security with user and admin roles along with session mechanism
