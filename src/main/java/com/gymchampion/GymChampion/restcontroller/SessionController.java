package com.gymchampion.GymChampion.restcontroller;

import com.gymchampion.GymChampion.exceptions.ResourceAlreadyExistsException;
import com.gymchampion.GymChampion.exceptions.ResourceDoesNotExistException;
import com.gymchampion.GymChampion.model.Session;
import com.gymchampion.GymChampion.service.LoginDataService;
import com.gymchampion.GymChampion.service.SessionService;
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

    @Autowired
    public SessionController(LoginDataService loginDataService, SessionService sessionService) {
        this.loginDataService = loginDataService;
        this.sessionService = sessionService;
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
     *   LoginData object handling - TODO
     */
    /*
    @PostMapping("/login_data")
    public LoginData addLoginData(@RequestBody LoginData loginData) {
        return this.loginDataService.addLoginData(loginData);
    }

    @GetMapping("/login_data")
    public List<LoginData> getAllLoginData() {
        return this.loginDataService.getAllLoginData();
    }


    @GetMapping("/login_data")
    public LoginData getLoginData(@RequestBody String loginData) {
        return this.loginDataService.getLoginData(loginData);
    }

    @PatchMapping("/login_data")
    public LoginData updateLoginDataPassword(@RequestBody String newPassword) {
        return this.loginDataService.updateLoginDataPassword(newPassword);
    }

    @DeleteMapping("login_data/{id}")
    public void removeLoginData(@PathVariable("id") int id) {
        this.loginDataService.removeLoginData(id);
    }   */
}
