package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.exceptions.ResourceAlreadyExistsException;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.LoginData;
import com.gymchampion.GymChampion.model.Role;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.RoleService;
import com.gymchampion.GymChampion.service.SessionService;
import com.gymchampion.GymChampion.util.LoginDataPasswordOnly;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/v1/sessions")
public class SessionController {

    private LoginDataService loginDataService;
    private SessionService sessionService;
    private RoleService roleService;

    @Autowired
    public SessionController(LoginDataService loginDataService,
                             SessionService sessionService,
                             RoleService roleService) {
        this.loginDataService = loginDataService;
        this.sessionService = sessionService;
        this.roleService = roleService;
    }

    /*
     *   Session object handling
     */

    @PostMapping
    public ResponseEntity<?> addSession(@RequestBody Session session, UriComponentsBuilder ucBuilder) {
//        logger.info("Creating Session : {}", session);
        if (this.sessionService.doesSessionExist(session)) {
//             logger.error("Unable to create. A Session with session key {} already exist", session.getSessionKey());
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. A Session with session key " +
                    session.getSessionKey() + " already exist.").getMessage(), HttpStatus.CONFLICT);
        }
        this.sessionService.addSession(session);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/sessions/id/{id}").buildAndExpand(session.getSessionId()).toUri());
//        logger.info("Session created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Session>> getAllSessions() {
//        logger.info("Fetching all Sessions");
        List<Session> sessions = this.sessionService.getAllSessions();
        if (sessions.isEmpty()) {
//            logger.error("Sessions not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/session_key/{key}")
    public ResponseEntity<?> getSessionBySessionKey(@PathVariable("key") String sessionKey) {
//        logger.info("Fetching Session with session key {}", sessionKey);
        Session session = this.sessionService.getSessionBySessionKey(sessionKey);
        if (session == null) {
//            logger.error("Session with session key {} not found.", sessionKey);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Session with session key " + sessionKey
                    + " not found").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getSessionById(@PathVariable("id") int id) {
//        logger.info("Fetching Session with id {}", id);
        Session session = this.sessionService.getSessionById(id);
        if (session == null) {
//            logger.error("Session with id {} not found.", id);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Session with id" + id
                    + " not found").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<List<Session>> getActiveSessions() {
//        logger.info("Fetching active Sessions");
        List<Session> sessions = this.sessionService.getActiveSessions();
        if (sessions.isEmpty()) {
//            logger.error("Active sessions not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }

    @GetMapping("/archived")
    public ResponseEntity<List<Session>> getArchivedSessions() {
//        logger.info("Fetching archived Sessions");
        List<Session> sessions = this.sessionService.getArchivedSessions();
        if (sessions.isEmpty()) {
//            logger.error("Archived sessions not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(sessions, HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<?> archiveSession(@PathVariable("id") int id) {
//        logger.info("Archiving Session with id {}", id);
        Session session = this.sessionService.getSessionById(id);
        if (session == null) {
//            logger.error("Unable to archive. Session with id {} not found.", id);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. Session with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        session.setActive(false);
        this.sessionService.updateSession(session);
        return new ResponseEntity<>(session, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSession(@PathVariable("id") int id) {
//        logger.info("Fetching & Deleting Session with id {}", id);
        Session session = this.sessionService.getSessionById(id);
        if (session == null) {
//            logger.error("Unable to delete. Session with id {} not found.", id);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete. Session with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.sessionService.removeSessionById(id);
        return new ResponseEntity<Session>(HttpStatus.NO_CONTENT);
    }

    /*
     *   LoginData object handling
     */

    @PostMapping("/login_data")
    public ResponseEntity<?> addUserLoginData(@RequestBody LoginData loginData, UriComponentsBuilder ucBuilder) {
//        logger.info("Creating LoginData : {}", loginData);
        if (this.loginDataService.doesLoginDataExist(loginData)) {
//            logger.error("Unable to create. Login data with id {} already exist", loginData.getLoginId());
            return new ResponseEntity<>(new ResourceAlreadyExistsException("Unable to create. Login data with id " +
                    loginData.getLoginId() + " already exist."), HttpStatus.CONFLICT);
        }
        Role userRole = this.roleService.getRoleByRoleName("USER");
        loginData.setUserRole(userRole);
        this.loginDataService.addLoginData(loginData);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/v1/session/login_data/{id}").buildAndExpand(loginData.getLoginId()).toUri());
//        logger.info("Login data created.");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @GetMapping("/login_data")
    public ResponseEntity<List<LoginData>> getAllLoginData() {
//        logger.info("Fetching all Login data");
        List<LoginData> loginData = this.loginDataService.getAllLoginData();
        if (loginData.isEmpty()) {
//            logger.error("Login data not found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @GetMapping("login_data/password/{password}")
    public ResponseEntity<?> getLoginDataByPassword(@PathVariable("password") String password) {
//        logger.info("Fetching Login data with password {}", password);
        LoginData loginData = this.loginDataService.getLoginDataByPassword(password);
        if (loginData == null) {
//            logger.error("Login data with password {} not found.", password);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Login data with password " + password
                    + " not found").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @GetMapping("login_data/login/{login}")
    public ResponseEntity<?> getLoginDataByLogin(@PathVariable("login") String login) {
//        logger.info("Fetching Login data with user login {}", login);
        LoginData loginData = this.loginDataService.getLoginDataByLogin(login);
        if (loginData == null) {
//            logger.error("Login data with user login {} not found.", login);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Login data with user login " + login
                    + " not found").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @GetMapping("login_data/id/{id}")
    public ResponseEntity<?> getLoginDataById(@PathVariable("id") int id) {
//        logger.info("Fetching Login data with id {}", id);
        LoginData loginData = this.loginDataService.getLoginDataById(id);
        if (loginData == null) {
//            logger.error("Login data with id {} not found.", id);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Login data with id" + id
                    + " not found").getMessage(), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @GetMapping("login_data/active")
    public ResponseEntity<List<LoginData>> getLoginDataFromActiveUsers() {
//        logger.info("Fetching Login data from active users");
        List<LoginData> loginData = this.loginDataService.getLoginDataFromActiveUsers();
        if (loginData.isEmpty()) {
//            logger.error("Login data from active users not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @GetMapping("login_data/archived")
    public ResponseEntity<List<LoginData>> getArchivedLoginData() {
//        logger.info("Fetching Login data from archived users");
        List<LoginData> loginData = this.loginDataService.getArchivedLoginData();
        if (loginData.isEmpty()) {
//            logger.error("Archived login data not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @PatchMapping("login_data/password/{id}")
    public ResponseEntity<?> updatePassword(@RequestBody LoginDataPasswordOnly loginDataPasswordOnly,
                                            @PathVariable("id") int id) {
//        logger.info("Updating password for Login data with id {}", id);
        LoginData loginData = this.loginDataService.getLoginDataById(id);
        if (loginData == null) {
//            logger.error("Unable to update password. Login data with id {} not found.", id);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update password. Login data with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        loginData.setPassword(loginDataPasswordOnly.getPassword());
        this.loginDataService.updateLoginData(loginData);
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @PatchMapping("login_data/{id}")
    public ResponseEntity<?> archiveLoginData(@PathVariable("id") int id) {
//        logger.info("Archiving Login data with id {}", id);
        LoginData loginData = this.loginDataService.getLoginDataById(id);
        if (loginData == null) {
//            logger.error("Unable to archive. Login data with id {} not found.", id);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to update. Login data with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        loginData.setArchived(true);
        this.loginDataService.updateLoginData(loginData);
        return new ResponseEntity<>(loginData, HttpStatus.OK);
    }

    @DeleteMapping("/login_data/{id}")
    public ResponseEntity<?> removeLoginData(@PathVariable("id") int id) {
//        logger.info("Fetching & Deleting Login data with id {}", id);
        LoginData loginData = this.loginDataService.getLoginDataById(id);
        if (loginData == null) {
//            logger.error("Unable to delete. Login data with id {} not found.", id);
            return new ResponseEntity<>(new ResourceDoesNotExistException("Unable to delete. Login data with id " +
                    id + " not found.").getMessage(),
                    HttpStatus.NOT_FOUND);
        }
        this.loginDataService.removeLoginData(id);
        return new ResponseEntity<LoginData>(HttpStatus.NO_CONTENT);
    }
}
